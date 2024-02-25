package services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import Dtos.ParsingObject;
import common.CommonCharacters;
import constants.GrammarLibrary;
import entities.*;
import entities.TokenTypes.EndOfText;
import enums.GrammarStatus;
import interfaces.IParser;

public class Parser implements IParser {
	private List<ParsingObject> currentExpressions;
	private List<ParsingObject> previousExpressions;
	private boolean waitSynchToken;

	public Parser() {
		currentExpressions = null;
		previousExpressions = null;
		waitSynchToken = false;
	}

	public void Parse(Token token) {

		if (waitSynchToken && CommonCharacters.isSynchronizedToken(token.value)) {
			waitSynchToken = false;
		}

		if (waitSynchToken)
			return;

		if (currentExpressions == null)
			currentExpressions = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfAll(), false)
					.cloneParsingObject();

		previousExpressions = new ArrayList<ParsingObject>();
		for (ParsingObject po : currentExpressions) {
			previousExpressions.add(po.clone());
		}

		var maxProgress = previousExpressions.stream().mapToInt(ParsingObject::getProgressCounter).max().orElse(0);

		var deleteList = new ArrayList<ParsingObject>();
		List<String> finalError = new ArrayList<String>();
		for (int i = 0; i < currentExpressions.size(); i++) {

			var gObj = currentExpressions.get(i);

			var currentNode = gObj.grammar.getGrammarNodeById(gObj.currentNodeId);

			if (currentNode == null) {
				deleteList.add(gObj);
				continue;
			}

			if (currentNode.getChildIds().size() == 0) {
				deleteList.add(gObj);
				continue;
			}
			List<String> childErrors = new ArrayList<String>();
			boolean passed = false;
			for (UUID childId : currentNode.getChildIds()) {

				if (deleteList.contains(gObj))
					break;

				var child = gObj.grammar.getGrammarNodeById(childId);

				if (child == null) {
					childErrors.add("No child found.");
					continue;
				}

				if (child.getClass() == TerminalNode.class) {

					if (token.tokenType instanceof EndOfText) {
						if (gObj.progressCounter == 0)
							continue;
						childErrors.add(((TerminalNode) child).tokenType.getError());
						continue;
					}
					if (((TerminalNode) child).tokenType.getClass().isInstance(token.tokenType)) {

						if (child.canBeEnd) {
							gObj.grammarStatus = GrammarStatus.isEnded;
							gObj.currentNodeId = child.Id;
							gObj.progressCounter++;
							passed = true;
							break;
						} else {
							gObj.grammarStatus = GrammarStatus.processing;
							gObj.currentNodeId = child.Id;
							gObj.progressCounter++;
							passed = true;
							break;
						}
					} else {
						childErrors.add(((TerminalNode) child).tokenType.getError());
						continue;
					}

				} else if (child.getClass() == NonTerminalNode.class) {
					if (token.tokenType instanceof EndOfText) {
						continue;
					}
					if (breakDownChilds(gObj, gObj.currentNodeId, child.Id, token))
						if (!deleteList.contains(gObj))
							deleteList.add(gObj);

				}
				if (deleteList.contains(gObj)) {
					continue;
				}

			}
			if (!passed) {
				if (gObj.getProgressCounter() >= maxProgress && childErrors != null && !childErrors.isEmpty()) {
					for (String childError : childErrors)
						if (childError != null && childError.length() > 0 && !finalError.contains(childError))
							finalError.add(childError);
				}

				gObj.grammarStatus = GrammarStatus.failed;
				deleteList.add(gObj);
			}

		}

		currentExpressions.removeAll(deleteList);

		if (currentExpressions.isEmpty()) {
			if (previousExpressions.stream().anyMatch(a -> a.grammarStatus == GrammarStatus.isEnded)) {
				// refresh retry
				refresh();
				Parse(token);
			} else {
				// failed
				waitSynchToken = true;
				token.errors.addAll(finalError);
				refresh();
			}
		}

		return;
	}

	private boolean breakDownChilds(ParsingObject gObj, UUID currentNodeId, UUID childId, Token token) {

		boolean result = false;
		var childNode = gObj.grammar.getGrammarNodeById(childId);

		if (childNode.getClass() == TerminalNode.class) {

			return result;
		}

		var nextNodes = childNode.getChildIds();

		for (ParsingObject parsingObject : ((NonTerminalNode) childNode).cloneParsingObject()) {
			if (!parsingObject.grammar.childExistsInRoot(token)) {
				continue;
			}

			var newParsingObj = gObj.clone();

			List<GrammarNode> nodesToAdd = parsingObject.grammar.getNodesExceptRoot();

			nodesToAdd.stream().filter(a -> a.canBeEnd).forEach(o -> {
				o.addChild(nextNodes);
				o.canBeEnd = childNode.canBeEnd;
			});

			newParsingObj.grammar.grammarNodes.addAll(nodesToAdd);

			var currentNode = newParsingObj.grammar.grammarNodes.stream().filter(a -> a.Id == currentNodeId)
					.findFirst();
			if (currentNode.isEmpty())
				return result;

			currentNode.get().removeChild(childId);
			currentNode.get()
					.addChild(parsingObject.grammar.getGrammarNodeById(parsingObject.grammar.rootNodeId).getChildIds());
			currentExpressions.add(newParsingObj);
			result = true;
		}
		return result;

	}

	private void refresh() {
		currentExpressions = null;
		previousExpressions = null;
		waitSynchToken = false;
	}
}
