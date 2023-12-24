package services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import Dtos.ParsingObject;
import Dtos.StatementParsingResult;
import entities.*;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import enums.GrammarStatus;
import grammars.Grammar;

public class ErrorDetecter {
	public List<ParsingObject> currentGrammars = null;
	public List<ParsingObject> previousGrammars = null;

	public StatementParsingResult Parse(StatementNode statementNode, Token token) {

		if (currentGrammars == null)
			currentGrammars = statementNode.cloneParsingObject();

		var previousGrammars = new ArrayList<ParsingObject>();
		for (ParsingObject po : currentGrammars) {
			previousGrammars.add(po.clone());
		}

		var maxProgress = previousGrammars.stream().mapToInt(ParsingObject::getProgressCounter).max().orElse(0);

		var deleteList = new ArrayList<ParsingObject>();
		String finalError = null;
		for (int i = 0; i < currentGrammars.size(); i++) {

			var gObj = currentGrammars.get(i);

			var currentNode = gObj.grammar.getGrammarNodeById(gObj.currentNodeId);

			if (currentNode.isEmpty()) {
				deleteList.add(gObj);
				continue;
			}

			if (currentNode.get().getChildIds().size() == 0) {
				deleteList.add(gObj);
				continue;
			}

			for (UUID childId : currentNode.get().getChildIds()) {
				var child = gObj.grammar.getGrammarNodeById(childId);

				if (child.isEmpty()) {
					continue;
				}

				if (child.get().getClass() == StatementNode.class) {
					breakDownChilds(gObj, gObj.currentNodeId, child.get().Id);
					if (!deleteList.contains(gObj))
						deleteList.add(gObj);

				}

			}
			if (deleteList.contains(gObj)) {
				continue;
			}
			String childError = null;
			boolean passed = false;
			for (UUID childId : currentNode.get().getChildIds()) {
				var child = gObj.grammar.getGrammarNodeById(childId);

				if (child.isEmpty()) {
					continue;
				}

				if (child.get().getClass() == SingleNode.class) {

					if (((SingleNode) child.get()).tokenType.getClass().isInstance(token.tokenType)) {
						if(((SingleNode) child.get()).tokenType instanceof OpenParenthesisType)
						{
							gObj.openParenthesis++;
						}
						if(((SingleNode) child.get()).tokenType instanceof CloseParenthesisType)
						{
							if(gObj.openParenthesis==0) {
								childError="Too many )";
								continue;
							}
							gObj.openParenthesis--;
						}
						
						if(child.get().countParenthesis&&gObj.openParenthesis>0) {
							childError="Expected )";
							continue;
						}

						
						if (child.get().canBeEnd) {
							gObj.grammarStatus = GrammarStatus.isEnded;
							gObj.currentNodeId = child.get().Id;
							gObj.progressCounter++;
							passed = true;
							break;
						} else {
							gObj.grammarStatus = GrammarStatus.processing;
							gObj.currentNodeId = child.get().Id;
							gObj.progressCounter++;
							passed = true;
							break;
						}
					} else {
						childError = ((SingleNode) child.get()).tokenType.getError();
						continue;
					}

				}

			}
			if (!passed) {
				if (gObj.getProgressCounter() >= maxProgress)
					finalError = childError;
				gObj.grammarStatus = GrammarStatus.failed;
				deleteList.add(gObj);
			}

		}

		currentGrammars.removeAll(deleteList);
		var result = new StatementParsingResult();

		if (currentGrammars.isEmpty()) {
			if (previousGrammars.stream().anyMatch(a -> a.grammarStatus == GrammarStatus.isEnded)) {
				result.grammarStatus = GrammarStatus.refresh_Retry;
			} else {
				result.grammarStatus = GrammarStatus.failed;

				result.error = finalError;
			}
		} else {
			result.grammarStatus = GrammarStatus.processing;
		}

		return result;
	}

	private void breakDownChilds(ParsingObject gObj, UUID currentNodeId, UUID childId) {

		var grammarsToAdd = gObj.grammar.breakDown(currentNodeId, childId);
		for (Grammar grammar : grammarsToAdd) {

			var newParsingObj = gObj.clone();
			newParsingObj.grammar = grammar;
			currentGrammars.add(newParsingObj);
		}

	}

	public void refresh() {
		currentGrammars = null;
		previousGrammars = null;
	}
}
