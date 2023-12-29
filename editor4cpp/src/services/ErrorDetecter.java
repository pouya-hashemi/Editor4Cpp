package services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import Dtos.ParsingObject;
import Dtos.StatementParsingResult;
import entities.*;
import entities.TokenTypes.DataType;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Literal;
import entities.TokenTypes.Punctuations.EqualType;
import enums.GrammarStatus;
import interfaces.HasType;

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

			if (currentNode == null) {
				deleteList.add(gObj);
				continue;
			}

			if (currentNode.getChildIds().size() == 0) {
				deleteList.add(gObj);
				continue;
			}
			String childError = null;
			boolean passed = false;
			for (UUID childId : currentNode.getChildIds()) {
				var child = gObj.grammar.getGrammarNodeById(childId);

				if (child == null) {
					childError = "No child found.";
					continue;
				}

				if (child.getClass() == SingleNode.class) {

					if (((SingleNode) child).tokenType.getClass().isInstance(token.tokenType)) {

//						if(token.tokenType instanceof HasType) {
//							if(gObj.dataType ==null && (token.tokenType instanceof DataType|| token.tokenType instanceof Identifier )) {
//								gObj.dataType=((HasType)token.tokenType).getDataType();
//							}
//							else if(gObj.dataType !=null) {
//								if(!gObj.dataType.canBe(((HasType)token.tokenType).getDataType())) {
//									childError = ((SingleNode) child).tokenType.getError()+" " +gObj.dataType.getError();
//									continue;
//								}
//							}
//						}

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
											childError = ((SingleNode) child).tokenType.getError() + " "
													+ gObj.dataType.getError();
											continue;
										}
									}
								} else {
									if (!gObj.dataType.canBe(((HasType) token.tokenType).getDataType())) {
										childError = ((SingleNode) child).tokenType.getError() + " "
												+ gObj.dataType.getError();
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
						childError = ((SingleNode) child).tokenType.getError();
						continue;
					}

				} else if (child.getClass() == StatementNode.class) {
					breakDownChilds(gObj, gObj.currentNodeId, child.Id);
					if (!deleteList.contains(gObj))
						deleteList.add(gObj);

				}
				if (deleteList.contains(gObj)) {
					continue;
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

		var childNode = gObj.grammar.getGrammarNodeById(childId);

		if (childNode.getClass() == SingleNode.class) {

			return;
		}

		var nextNodes = childNode.getChildIds();

		for (ParsingObject parsingObject : ((StatementNode) childNode).cloneParsingObject()) {

			var newParsingObj = gObj.clone();

			List<GrammarNode> nodesToAdd = parsingObject.grammar.getPureNodes();

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
			currentGrammars.add(newParsingObj);
		}

	}

	public void refresh() {
		currentGrammars = null;
		previousGrammars = null;
	}
}
