package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
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

		SingleNode openParenthesis_Node1 = new SingleNode(new OpenParenthesisType(), false);

		SingleNode logicalNot_Node2 = new SingleNode(new LogicalNotOperator(), false);

		SingleNode identifier_Node3 = new SingleNode(new Identifier(""), false);

		SingleNode literal_Node4 = new SingleNode(new Literal(), false);

		SingleNode singleComparisonOperator_Node5 = new SingleNode(new SingleComparisonOperator(), false);

		SingleNode firstDoubleComparisonOperator_Node6 = new SingleNode(new DoubleComparisonOperator(), false);

		SingleNode secondDoubleComparisonOperator_Node7 = new SingleNode(new DoubleComparisonOperator(), false);

		SingleNode identifier_Node8 = new SingleNode(new Identifier(""), true);

		SingleNode literal_Node9 = new SingleNode(new Literal(), true);

		SingleNode logicalOperator_Node10 = new SingleNode(new LogicalOperator(), false);

		SingleNode closeParenthesis_Node11 = new SingleNode(new CloseParenthesisType(), true,true);

		SingleNode secondLogicalOperator_Node12 = new SingleNode(new LogicalOperator(), false);
		
		StatementNode comparison_Node13 = new StatementNode(()->GrammarLibrary.getParsingObjectsOfComparison(), false);

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
