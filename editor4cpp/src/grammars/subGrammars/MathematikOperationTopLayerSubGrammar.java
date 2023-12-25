package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
import entities.TokenTypes.Operations.DoubleOperandOperator;
import entities.TokenTypes.Operations.SingleOperandOperator;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import grammars.Grammar;

public class MathematikOperationTopLayerSubGrammar extends Grammar {
	public MathematikOperationTopLayerSubGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);
	}
	public MathematikOperationTopLayerSubGrammar() {
		super();
		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		SingleNode openParenthesis_Node1 = new SingleNode(new OpenParenthesisType(), false);
		StatementNode mathOp_Node2 = new StatementNode(()->GrammarLibrary.getParsingObjectsOfMathematikOperationSubGrammar(),true);
		StatementNode mathOp_Node3 = new StatementNode(()->GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(),false);
		SingleNode closeParenthesis_Node4 = new SingleNode(new CloseParenthesisType(), true);
		SingleNode firstSingleOperator_Node5 = new SingleNode(new SingleOperandOperator(), false);
		SingleNode secondSingleOperator_Node6 = new SingleNode(new SingleOperandOperator(), true);
		SingleNode doubleOperator_Node7 = new SingleNode(new DoubleOperandOperator(), false);
		StatementNode mathTopLayer_Node8 = new StatementNode(()->GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(),true);
		
		
		

		// -----------------------------------------------------------------
		root.addChild(openParenthesis_Node1.Id);
		root.addChild(mathOp_Node2.Id);
		
		
		openParenthesis_Node1.addChild(mathOp_Node3.Id);
		
		mathOp_Node3.addChild(closeParenthesis_Node4.Id);
		closeParenthesis_Node4.addChild(doubleOperator_Node7.Id);
		closeParenthesis_Node4.addChild(firstSingleOperator_Node5.Id);
		firstSingleOperator_Node5.addChild(secondSingleOperator_Node6.Id);
		secondSingleOperator_Node6.addChild(doubleOperator_Node7.Id);
		doubleOperator_Node7.addChild(mathTopLayer_Node8.Id);

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(openParenthesis_Node1);
		grammarNodes.add(mathOp_Node2);
		grammarNodes.add(mathOp_Node3);
		grammarNodes.add(closeParenthesis_Node4);
		grammarNodes.add(firstSingleOperator_Node5);
		grammarNodes.add(secondSingleOperator_Node6);
		grammarNodes.add(doubleOperator_Node7);
		grammarNodes.add(mathTopLayer_Node8);
		
	}
	
	

	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new MathematikOperationTopLayerSubGrammar(this.Id,nodes,this.rootNodeId);
	}
}
