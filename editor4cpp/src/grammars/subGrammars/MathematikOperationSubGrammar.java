package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
import entities.TokenTypes.FloatingPointLiteral;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.NumericLiteral;
import entities.TokenTypes.TextLiteral;
import entities.TokenTypes.Literals.BoolLiteral;
import entities.TokenTypes.Operations.DoubleOperandOperator;
import entities.TokenTypes.Operations.SingleOperandOperator;
import grammars.Grammar;

public class MathematikOperationSubGrammar extends Grammar {

	public MathematikOperationSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public MathematikOperationSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;

		SingleNode firstSingleOperator_Node1 = new SingleNode(new SingleOperandOperator(), false);
		SingleNode secondSingleOperator_Node2 = new SingleNode(new SingleOperandOperator(), false);

		SingleNode textLiteral_Node3 = new SingleNode(new TextLiteral(), true);
		SingleNode boolLiteral_Node11 = new SingleNode(new BoolLiteral(), true);
		SingleNode floatLiteral_Node12 = new SingleNode(new FloatingPointLiteral(), false);
		SingleNode numericLiteral_Node13 = new SingleNode(new NumericLiteral(), true);
		SingleNode identifier_Node4 = new SingleNode(new Identifier(""), true);
		SingleNode firstSingleOperator_Node5 = new SingleNode(new SingleOperandOperator(), false);
		SingleNode secondSingleOperator_Node6 = new SingleNode(new SingleOperandOperator(), true);
		SingleNode doubleOperator_Node7 = new SingleNode(new DoubleOperandOperator(), false);
		SingleNode secondFloatingPoint_Node8 = new SingleNode(new FloatingPointLiteral(), false);
		SingleNode thirdFloatingPoint_Node9 = new SingleNode(new FloatingPointLiteral(), true);
		StatementNode rightAssignment_Node10 = new StatementNode(
				() -> GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(), true);
		
		StatementNode functionCall_Node14 = new StatementNode(
				() -> GrammarLibrary.getParsingObjectsOfFunctionCallSubGrammar(), true);

		// -----------------------------------------------------------------
		root.addChild(firstSingleOperator_Node1.Id);
		root.addChild(identifier_Node4.Id);
		root.addChild(functionCall_Node14.Id);
		root.addChild(textLiteral_Node3.Id);
		root.addChild(boolLiteral_Node11.Id);
		root.addChild(floatLiteral_Node12.Id);
		root.addChild(numericLiteral_Node13.Id);

		firstSingleOperator_Node1.addChild(secondSingleOperator_Node2.Id);


		secondSingleOperator_Node2.addChild(identifier_Node4.Id);

		textLiteral_Node3.addChild(textLiteral_Node3.Id);

		floatLiteral_Node12.addChild(secondFloatingPoint_Node8.Id);
		secondFloatingPoint_Node8.addChild(thirdFloatingPoint_Node9.Id);
		thirdFloatingPoint_Node9.addChild(doubleOperator_Node7.Id);

		numericLiteral_Node13.addChild(doubleOperator_Node7.Id);

		identifier_Node4.addChild(doubleOperator_Node7.Id);
		identifier_Node4.addChild(firstSingleOperator_Node5.Id);

		firstSingleOperator_Node5.addChild(secondSingleOperator_Node6.Id);

		secondSingleOperator_Node6.addChild(doubleOperator_Node7.Id);

		doubleOperator_Node7.addChild(rightAssignment_Node10.Id);
		
		functionCall_Node14.addChild(doubleOperator_Node7.Id);

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(firstSingleOperator_Node1);
		grammarNodes.add(secondSingleOperator_Node2);
		grammarNodes.add(textLiteral_Node3);
		grammarNodes.add(identifier_Node4);
		grammarNodes.add(firstSingleOperator_Node5);
		grammarNodes.add(secondSingleOperator_Node6);
		grammarNodes.add(doubleOperator_Node7);
		grammarNodes.add(secondFloatingPoint_Node8);
		grammarNodes.add(thirdFloatingPoint_Node9);
		grammarNodes.add(rightAssignment_Node10);
		grammarNodes.add(boolLiteral_Node11);
		grammarNodes.add(floatLiteral_Node12);
		grammarNodes.add(numericLiteral_Node13);
		grammarNodes.add(functionCall_Node14);
		
	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new MathematikOperationSubGrammar(this.Id, nodes, this.rootNodeId);
	}
}
