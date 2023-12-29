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
import entities.TokenTypes.Punctuations.CloseBracket;
import entities.TokenTypes.Punctuations.OpenBracket;
import grammars.Grammar;

public class MathematikOperationSubGrammar extends Grammar {

	public MathematikOperationSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public MathematikOperationSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;

//		SingleNode firstSingleOperator_Node1 = new SingleNode(new SingleOperandOperator(), false);
//		SingleNode secondSingleOperator_Node2 = new SingleNode(new SingleOperandOperator(), false);

		SingleNode textLiteral_Node1 = new SingleNode(new TextLiteral(), true);
		SingleNode boolLiteral_Node2 = new SingleNode(new BoolLiteral(), true);
		SingleNode floatLiteral_Node3 = new SingleNode(new FloatingPointLiteral(), false);
		SingleNode numericLiteral_Node4 = new SingleNode(new NumericLiteral(), true);
		SingleNode identifier_Node5 = new SingleNode(new Identifier(""), true);
		SingleNode firstSingleOperator_Node6 = new SingleNode(new SingleOperandOperator(), false);
		SingleNode secondSingleOperator_Node7 = new SingleNode(new SingleOperandOperator(), true);
		SingleNode doubleOperator_Node8 = new SingleNode(new DoubleOperandOperator(), false);
		SingleNode secondFloatingPoint_Node9 = new SingleNode(new FloatingPointLiteral(), false);
		SingleNode thirdFloatingPoint_Node10 = new SingleNode(new FloatingPointLiteral(), true);
		StatementNode rightAssignment_Node11 = new StatementNode(
				() -> GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(), true);
		
		StatementNode functionCall_Node12 = new StatementNode(
				() -> GrammarLibrary.getParsingObjectsOfFunctionCallSubGrammar(), true);
		
		SingleNode openBracket_Node13 = new SingleNode(new OpenBracket(), false);

		SingleNode numericLiteral_Node14 = new SingleNode(new NumericLiteral(), false);

		SingleNode closeBracket_Node15 = new SingleNode(new CloseBracket(), true);

		// -----------------------------------------------------------------
//		root.addChild(firstSingleOperator_Node1.Id);
		root.addChild(identifier_Node5.Id);
		root.addChild(textLiteral_Node1.Id);
		root.addChild(boolLiteral_Node2.Id);
		root.addChild(floatLiteral_Node3.Id);
		root.addChild(numericLiteral_Node4.Id);
		root.addChild(functionCall_Node12.Id);

//		firstSingleOperator_Node1.addChild(secondSingleOperator_Node2.Id);


//		secondSingleOperator_Node2.addChild(identifier_Node7.Id);

		textLiteral_Node1.addChild(textLiteral_Node1.Id);

		floatLiteral_Node3.addChild(secondFloatingPoint_Node9.Id);
		secondFloatingPoint_Node9.addChild(thirdFloatingPoint_Node10.Id);
		thirdFloatingPoint_Node10.addChild(doubleOperator_Node8.Id);

		numericLiteral_Node4.addChild(doubleOperator_Node8.Id);

		identifier_Node5.addChild(doubleOperator_Node8.Id);
		identifier_Node5.addChild(firstSingleOperator_Node6.Id);

		firstSingleOperator_Node6.addChild(secondSingleOperator_Node7.Id);

		secondSingleOperator_Node7.addChild(doubleOperator_Node8.Id);

		doubleOperator_Node8.addChild(rightAssignment_Node11.Id);
		
		functionCall_Node12.addChild(doubleOperator_Node8.Id);
		
		identifier_Node5.addChild(openBracket_Node13.Id);
		
		openBracket_Node13.addChild(numericLiteral_Node14.Id);
		
		numericLiteral_Node14.addChild(closeBracket_Node15.Id);
		
		closeBracket_Node15.addChild(openBracket_Node13.Id);
		
		closeBracket_Node15.addChild(doubleOperator_Node8.Id);
		closeBracket_Node15.addChild(firstSingleOperator_Node6.Id);

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
//		grammarNodes.add(firstSingleOperator_Node1);
//		grammarNodes.add(secondSingleOperator_Node2);
		grammarNodes.add(textLiteral_Node1);
		grammarNodes.add(boolLiteral_Node2);
		grammarNodes.add(floatLiteral_Node3);
		grammarNodes.add(numericLiteral_Node4);
		grammarNodes.add(identifier_Node5);
		grammarNodes.add(firstSingleOperator_Node6);
		grammarNodes.add(secondSingleOperator_Node7);
		grammarNodes.add(doubleOperator_Node8);
		grammarNodes.add(secondFloatingPoint_Node9);
		grammarNodes.add(thirdFloatingPoint_Node10);
		grammarNodes.add(rightAssignment_Node11);
		grammarNodes.add(functionCall_Node12);
		grammarNodes.add(openBracket_Node13);
		grammarNodes.add(numericLiteral_Node14);
		grammarNodes.add(closeBracket_Node15);
		
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
