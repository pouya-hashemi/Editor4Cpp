package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
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

		TerminalNode openParenthesis_Node1 = new TerminalNode(new OpenParenthesisType(), false);
		NonTerminalNode mathOp_Node2 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfMathematikOperationSubGrammar(),true);
		NonTerminalNode mathToplayer_Node3 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(),false);
		TerminalNode closeParenthesis_Node4 = new TerminalNode(new CloseParenthesisType(), true);
		TerminalNode secondSingleOperator_Node6 = new TerminalNode(new SingleOperandOperator(), true);
		TerminalNode doubleOperator_Node7 = new TerminalNode(new DoubleOperandOperator(), false);
		NonTerminalNode mathTopLayer_Node8 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(),true);
		TerminalNode secondPreSingleOperator_Node10 = new TerminalNode(new SingleOperandOperator(), false);
		NonTerminalNode dynamicAllocation_Node11 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfDynamicAllocationSubGrammar(),true);

		// -----------------------------------------------------------------
		root.addChild(openParenthesis_Node1.Id);
		root.addChild(secondPreSingleOperator_Node10.Id);
		root.addChild(mathOp_Node2.Id);
		root.addChild(dynamicAllocation_Node11.Id);
		
		openParenthesis_Node1.addChild(mathToplayer_Node3.Id);
		
		mathToplayer_Node3.addChild(closeParenthesis_Node4.Id);
		closeParenthesis_Node4.addChild(doubleOperator_Node7.Id);
		closeParenthesis_Node4.addChild(secondSingleOperator_Node6.Id);
		secondSingleOperator_Node6.addChild(doubleOperator_Node7.Id);
		doubleOperator_Node7.addChild(mathTopLayer_Node8.Id);

		secondPreSingleOperator_Node10.addChild(openParenthesis_Node1.Id);
		secondPreSingleOperator_Node10.addChild(mathOp_Node2.Id);
		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(openParenthesis_Node1);
		grammarNodes.add(mathOp_Node2);
		grammarNodes.add(mathToplayer_Node3);
		grammarNodes.add(closeParenthesis_Node4);
		grammarNodes.add(secondSingleOperator_Node6);
		grammarNodes.add(doubleOperator_Node7);
		grammarNodes.add(mathTopLayer_Node8);
		grammarNodes.add(secondPreSingleOperator_Node10);
		grammarNodes.add(dynamicAllocation_Node11);
		
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
