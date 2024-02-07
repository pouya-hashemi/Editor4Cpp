package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
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

		TerminalNode textLiteral_Node1 = new TerminalNode(new TextLiteral(), true);
		TerminalNode boolLiteral_Node2 = new TerminalNode(new BoolLiteral(), true);
		TerminalNode floatLiteral_Node3 = new TerminalNode(new FloatingPointLiteral(), true);
		TerminalNode numericLiteral_Node4 = new TerminalNode(new NumericLiteral(), true);
		TerminalNode identifier_Node5 = new TerminalNode(new Identifier(""), true);
//		TerminalNode firstSingleOperator_Node6 = new TerminalNode(new SingleOperandOperator(), false);
		TerminalNode secondSingleOperator_Node7 = new TerminalNode(new SingleOperandOperator(), true);
		TerminalNode doubleOperator_Node8 = new TerminalNode(new DoubleOperandOperator(), false);
//		TerminalNode secondFloatingPoint_Node9 = new TerminalNode(new FloatingPointLiteral(), false);
//		TerminalNode thirdFloatingPoint_Node10 = new TerminalNode(new FloatingPointLiteral(), true);
		NonTerminalNode rightAssignment_Node11 = new NonTerminalNode(
				() -> GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(), true);
		
		NonTerminalNode functionCall_Node12 = new NonTerminalNode(
				() -> GrammarLibrary.getParsingObjectsOfFunctionCallSubGrammar(), true);
		
		TerminalNode openBracket_Node13 = new TerminalNode(new OpenBracket(), false);

		TerminalNode numericLiteral_Node14 = new TerminalNode(new NumericLiteral(), false);

		TerminalNode closeBracket_Node15 = new TerminalNode(new CloseBracket(), true);
		

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

//		textLiteral_Node1.addChild(textLiteral_Node1.Id);

//		floatLiteral_Node3.addChild(secondFloatingPoint_Node9.Id);
//		secondFloatingPoint_Node9.addChild(thirdFloatingPoint_Node10.Id);
		floatLiteral_Node3.addChild(doubleOperator_Node8.Id);

		numericLiteral_Node4.addChild(doubleOperator_Node8.Id);

		identifier_Node5.addChild(doubleOperator_Node8.Id);
		identifier_Node5.addChild(secondSingleOperator_Node7.Id);

//		firstSingleOperator_Node6.addChild(secondSingleOperator_Node7.Id);

		secondSingleOperator_Node7.addChild(doubleOperator_Node8.Id);

		doubleOperator_Node8.addChild(rightAssignment_Node11.Id);
		
		functionCall_Node12.addChild(doubleOperator_Node8.Id);
		
		identifier_Node5.addChild(openBracket_Node13.Id);
		
		openBracket_Node13.addChild(numericLiteral_Node14.Id);
		
		numericLiteral_Node14.addChild(closeBracket_Node15.Id);
		
		closeBracket_Node15.addChild(openBracket_Node13.Id);
		
		closeBracket_Node15.addChild(doubleOperator_Node8.Id);
		closeBracket_Node15.addChild(secondSingleOperator_Node7.Id);

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
//		grammarNodes.add(firstSingleOperator_Node6);
		grammarNodes.add(secondSingleOperator_Node7);
		grammarNodes.add(doubleOperator_Node8);
//		grammarNodes.add(secondFloatingPoint_Node9);
//		grammarNodes.add(thirdFloatingPoint_Node10);
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
