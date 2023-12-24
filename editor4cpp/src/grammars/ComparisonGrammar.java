package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import entities.GrammarNode;
import entities.SingleNode;

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

		SingleNode openParenthesis_Node2 = new SingleNode(new OpenParenthesisType(), false);

		SingleNode logicalNot_Node3 = new SingleNode(new LogicalNotOperator(), false);

		SingleNode identifier_Node4 = new SingleNode(new Identifier(""), false);

		SingleNode literal_Node5 = new SingleNode(new Literal(), false);

		SingleNode singleComparisonOperator_Node6 = new SingleNode(new SingleComparisonOperator(), false);

		SingleNode firstDoubleComparisonOperator_Node7 = new SingleNode(new DoubleComparisonOperator(), false);

		SingleNode secondDoubleComparisonOperator_Node8 = new SingleNode(new DoubleComparisonOperator(), false);

		SingleNode identifier_Node9 = new SingleNode(new Identifier(""), true);

		SingleNode literal_Node10 = new SingleNode(new Literal(), true);

		SingleNode logicalOperator_Node11 = new SingleNode(new LogicalOperator(), false);

		SingleNode closeParenthesis_Node12 = new SingleNode(new CloseParenthesisType(), true,true);

		SingleNode secondLogicalOperator_Node13 = new SingleNode(new LogicalOperator(), false);

		// -----------------------------------------------------------------
		root.addChild(openParenthesis_Node2.Id);
		root.addChild(logicalNot_Node3.Id);
		root.addChild(identifier_Node4.Id);
		root.addChild(literal_Node5.Id);// E1
		openParenthesis_Node2.addChild(logicalNot_Node3.Id);// E2
		logicalNot_Node3.addChild(openParenthesis_Node2.Id);// E3
		openParenthesis_Node2.addChild(openParenthesis_Node2.Id);// E4
		openParenthesis_Node2.addChild(identifier_Node4.Id);// E5
		openParenthesis_Node2.addChild(literal_Node5.Id);// E6
		identifier_Node4.addChild(singleComparisonOperator_Node6.Id);// E7
		identifier_Node4.addChild(firstDoubleComparisonOperator_Node7.Id);// E8
		literal_Node5.addChild(singleComparisonOperator_Node6.Id);// E9
		literal_Node5.addChild(firstDoubleComparisonOperator_Node7.Id);// E10
		firstDoubleComparisonOperator_Node7.addChild(secondDoubleComparisonOperator_Node8.Id);// E11
		singleComparisonOperator_Node6.addChild(identifier_Node9.Id);// E12
		singleComparisonOperator_Node6.addChild(literal_Node10.Id);// E13
		secondDoubleComparisonOperator_Node8.addChild(literal_Node10.Id);// E14
		secondDoubleComparisonOperator_Node8.addChild(identifier_Node9.Id);// E15
		literal_Node10.addChild(closeParenthesis_Node12.Id);// E16
		literal_Node10.addChild(logicalOperator_Node11.Id);// E17
		identifier_Node9.addChild(logicalOperator_Node11.Id);// E18
		identifier_Node9.addChild(closeParenthesis_Node12.Id);// E19
		closeParenthesis_Node12.addChild(closeParenthesis_Node12.Id);// E20
		closeParenthesis_Node12.addChild(logicalOperator_Node11.Id);// E21
		secondLogicalOperator_Node13.addChild(literal_Node5.Id);// E22
		secondLogicalOperator_Node13.addChild(identifier_Node4.Id);// E23
		secondLogicalOperator_Node13.addChild(openParenthesis_Node2.Id);// E24

		literal_Node5.addChild(literal_Node5.Id);// E25
		literal_Node10.addChild(literal_Node10.Id);// E26
		logicalNot_Node3.addChild(identifier_Node4.Id);// E27
		logicalNot_Node3.addChild(literal_Node5.Id);// E28
		logicalOperator_Node11.addChild(secondLogicalOperator_Node13.Id);// E29
		secondLogicalOperator_Node13.addChild(logicalNot_Node3.Id);// E30

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(openParenthesis_Node2);
		grammarNodes.add(logicalNot_Node3);
		grammarNodes.add(identifier_Node4);
		grammarNodes.add(literal_Node5);
		grammarNodes.add(singleComparisonOperator_Node6);
		grammarNodes.add(firstDoubleComparisonOperator_Node7);
		grammarNodes.add(secondDoubleComparisonOperator_Node8);
		grammarNodes.add(identifier_Node9);
		grammarNodes.add(literal_Node10);
		grammarNodes.add(logicalOperator_Node11);
		grammarNodes.add(closeParenthesis_Node12);
		grammarNodes.add(secondLogicalOperator_Node13);


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
