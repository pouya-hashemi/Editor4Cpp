package services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import Dtos.ParsingObject;
import common.CommonCharacters;
import constants.GrammarLibrary;
import entities.*;
import entities.TokenTypes.DataType;
import entities.TokenTypes.EndOfText;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Literal;
import entities.TokenTypes.Punctuations.EqualType;
import enums.GrammarStatus;
import interfaces.HasType;
import interfaces.IParser;

public class Parser implements IParser {
	private List<ParsingObject> currentExpressions;
	private List<ParsingObject> previousExpressions ;
	private boolean waitSynchToken;

	public Parser() {
		currentExpressions=null;
		previousExpressions=null;
		waitSynchToken=false;
	}
	
	public void Parse( Token token) {

		if(waitSynchToken&& CommonCharacters.isSynchronizedToken(token.value)) {
			waitSynchToken=false;
		}
		
		if(waitSynchToken)
			return;
		
		if (currentExpressions == null)
			currentExpressions = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfAll(), false).cloneParsingObject();

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
				var child = gObj.grammar.getGrammarNodeById(childId);

				if (child == null) {
					childErrors.add("No child found.");
					continue;
				}

				if (child.getClass() == TerminalNode.class) {

					if(token.tokenType instanceof EndOfText) {
						if(gObj.progressCounter==0)
							continue;
						childErrors.add(((TerminalNode) child).tokenType.getError());
						continue;
					}
					if (((TerminalNode) child).tokenType.getClass().isInstance(token.tokenType)) {

						if (token.tokenType instanceof HasType) {
							if ((token.tokenType instanceof DataType)) {
								gObj.dataType = ((HasType) token.tokenType).getDataType();
							}
							if (token.tokenType instanceof Identifier && (token.absolutePrevToken == null
									|| !(token.absolutePrevToken.tokenType instanceof EqualType))) {
								gObj.dataType = ((HasType) token.tokenType).getDataType();
							}
							if (gObj.dataType != null) {
								if (token.tokenType instanceof Literal) {
									if (token.absolutePrevToken != null
											&& token.absolutePrevToken.tokenType instanceof EqualType) {
										if (!gObj.dataType.canBe(((HasType) token.tokenType).getDataType())) {
											childErrors.add(((TerminalNode) child).tokenType.getError() + " "
													+ gObj.dataType.getError());
											continue;
										}
									}
								} else {
									if (!gObj.dataType.canBe(((HasType) token.tokenType).getDataType())) {
										childErrors.add(((TerminalNode) child).tokenType.getError() + " "
												+ gObj.dataType.getError());
										continue;
									}
								}

							}
						}

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
					if(token.tokenType instanceof EndOfText) {
						continue;
					}
					breakDownChilds(gObj, gObj.currentNodeId, child.Id);
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
						if (childError != null && childError.length()>0&&!finalError.contains(childError))
							finalError.add(childError);
				}

				gObj.grammarStatus = GrammarStatus.failed;
				deleteList.add(gObj);
			}

		}

		currentExpressions.removeAll(deleteList);

		if (currentExpressions.isEmpty()) {
			if (previousExpressions.stream().anyMatch(a -> a.grammarStatus == GrammarStatus.isEnded)) {
				//refresh retry
				refresh();
				Parse(token);
			} else {
				//failed
				waitSynchToken=true;
				token.errors.addAll(finalError);
				refresh();
			}
		} 

		return;
	}

	private void breakDownChilds(ParsingObject gObj, UUID currentNodeId, UUID childId) {

		var childNode = gObj.grammar.getGrammarNodeById(childId);

		if (childNode.getClass() == TerminalNode.class) {

			return;
		}

		var nextNodes = childNode.getChildIds();

		for (ParsingObject parsingObject : ((NonTerminalNode) childNode).cloneParsingObject()) {

			var newParsingObj = gObj.clone();

			List<GrammarNode> nodesToAdd = parsingObject.grammar.getNodesExceptRoot();

			nodesToAdd.stream().filter(a -> a.canBeEnd).forEach(o -> {
				o.addChild(nextNodes);
				o.canBeEnd = childNode.canBeEnd;
			});

			newParsingObj.grammar.grammarNodes.addAll(nodesToAdd);

			// parsingObject.grammar.grammarNodes.stream().filter(a->a.canBeEnd).forEach(o->o.addChild(nextNodes));
			var currentNode = newParsingObj.grammar.grammarNodes.stream().filter(a -> a.Id == currentNodeId)
					.findFirst();
			if (currentNode.isEmpty())
				return;

			currentNode.get().removeChild(childId);
			currentNode.get()
					.addChild(parsingObject.grammar.getGrammarNodeById(parsingObject.grammar.rootNodeId).getChildIds());
			currentExpressions.add(newParsingObj);
		}

	}

	private void refresh() {
		currentExpressions = null;
		previousExpressions = null;
		waitSynchToken=false;
	}
}
