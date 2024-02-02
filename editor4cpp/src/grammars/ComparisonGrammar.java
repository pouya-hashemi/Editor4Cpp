package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Literal;
import entities.TokenTypes.Operations.DoubleComparisonOperator;
import entities.TokenTypes.Operations.LogicalNotOperator;
import entities.TokenTypes.Operations.LogicalOperator;
import entities.TokenTypes.Operations.SingleComparisonOperator;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.OpenParenthesisType;

public class ComparisonGrammar extends Grammar {
	
	public ComparisonGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);
	}
	
	public ComparisonGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;

		TerminalNode openParenthesis_Node1 = new TerminalNode(new OpenParenthesisType(), false);

		TerminalNode logicalNot_Node2 = new TerminalNode(new LogicalNotOperator(), false);

		TerminalNode identifier_Node3 = new TerminalNode(new Identifier(""), false);

		TerminalNode literal_Node4 = new TerminalNode(new Literal(), false);

		TerminalNode singleComparisonOperator_Node5 = new TerminalNode(new SingleComparisonOperator(), false);

		TerminalNode firstDoubleComparisonOperator_Node6 = new TerminalNode(new DoubleComparisonOperator(), false);

		TerminalNode secondDoubleComparisonOperator_Node7 = new TerminalNode(new DoubleComparisonOperator(), false);

		TerminalNode identifier_Node8 = new TerminalNode(new Identifier(""), true);

		TerminalNode literal_Node9 = new TerminalNode(new Literal(), true);

		TerminalNode logicalOperator_Node10 = new TerminalNode(new LogicalOperator(), false);

		TerminalNode closeParenthesis_Node11 = new TerminalNode(new CloseParenthesisType(), true);

		TerminalNode secondLogicalOperator_Node12 = new TerminalNode(new LogicalOperator(), false);
		
		NonTerminalNode comparison_Node13 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfComparison(), false);

		// -----------------------------------------------------------------
		root.addChild(openParenthesis_Node1.Id);
		root.addChild(logicalNot_Node2.Id);
		root.addChild(identifier_Node3.Id);
		root.addChild(literal_Node4.Id);// E1
		
		openParenthesis_Node1.addChild(comparison_Node13.Id);// E2
		
		logicalNot_Node2.addChild(openParenthesis_Node1.Id);// E3
		logicalNot_Node2.addChild(identifier_Node3.Id);// E27
		logicalNot_Node2.addChild(literal_Node4.Id);// E28
		
		identifier_Node3.addChild(singleComparisonOperator_Node5.Id);// E7
		identifier_Node3.addChild(firstDoubleComparisonOperator_Node6.Id);// E8
		
		literal_Node4.addChild(singleComparisonOperator_Node5.Id);// E9
		literal_Node4.addChild(firstDoubleComparisonOperator_Node6.Id);// E10
		literal_Node4.addChild(literal_Node4.Id);// E25
		
		firstDoubleComparisonOperator_Node6.addChild(secondDoubleComparisonOperator_Node7.Id);// E11
		
		singleComparisonOperator_Node5.addChild(identifier_Node8.Id);// E12
		singleComparisonOperator_Node5.addChild(literal_Node9.Id);// E13
		
		secondDoubleComparisonOperator_Node7.addChild(literal_Node9.Id);// E14
		secondDoubleComparisonOperator_Node7.addChild(identifier_Node8.Id);// E15
		
		literal_Node9.addChild(logicalOperator_Node10.Id);// E17
		literal_Node9.addChild(literal_Node9.Id);// E26
		
		identifier_Node8.addChild(logicalOperator_Node10.Id);// E18
		
		logicalOperator_Node10.addChild(secondLogicalOperator_Node12.Id);// E29
		
		closeParenthesis_Node11.addChild(logicalOperator_Node10.Id);// E21
		
		secondLogicalOperator_Node12.addChild(literal_Node4.Id);// E22
		secondLogicalOperator_Node12.addChild(identifier_Node3.Id);// E23
		secondLogicalOperator_Node12.addChild(openParenthesis_Node1.Id);// E24
		secondLogicalOperator_Node12.addChild(logicalNot_Node2.Id);// E30

		comparison_Node13.addChild(closeParenthesis_Node11.Id);

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(openParenthesis_Node1);
		grammarNodes.add(logicalNot_Node2);
		grammarNodes.add(identifier_Node3);
		grammarNodes.add(literal_Node4);
		grammarNodes.add(singleComparisonOperator_Node5);
		grammarNodes.add(firstDoubleComparisonOperator_Node6);
		grammarNodes.add(secondDoubleComparisonOperator_Node7);
		grammarNodes.add(identifier_Node8);
		grammarNodes.add(literal_Node9);
		grammarNodes.add(logicalOperator_Node10);
		grammarNodes.add(closeParenthesis_Node11);
		grammarNodes.add(secondLogicalOperator_Node12);
		grammarNodes.add(comparison_Node13);


	}

	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new ComparisonGrammar(this.Id, nodes,this.rootNodeId);
	}
}
