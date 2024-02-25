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
import entities.TokenTypes.NamespaceMemberType;
import entities.TokenTypes.NamespaceType;
import entities.TokenTypes.Keywords.NullptrKeyword;
import entities.TokenTypes.Keywords.ThisKeyword;
import entities.TokenTypes.Operations.DoubleComparisonOperator;
import entities.TokenTypes.Operations.LogicalNotOperator;
import entities.TokenTypes.Operations.LogicalOperator;
import entities.TokenTypes.Operations.SingleComparisonOperator;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.ColonType;
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

		TerminalNode identifier_Node3 = new TerminalNode(new Identifier(""), true);

		TerminalNode literal_Node4 = new TerminalNode(new Literal(), false);

		TerminalNode singleComparisonOperator_Node5 = new TerminalNode(new SingleComparisonOperator(), false);

		TerminalNode doubleComparisonOperator_Node7 = new TerminalNode(new DoubleComparisonOperator(), false);

		TerminalNode identifier_Node8 = new TerminalNode(new Identifier(""), true);

		TerminalNode literal_Node9 = new TerminalNode(new Literal(), true);

		TerminalNode logicalOperator_Node10 = new TerminalNode(new LogicalOperator(), false);

		TerminalNode closeParenthesis_Node11 = new TerminalNode(new CloseParenthesisType(), true);
		
		NonTerminalNode comparison_Node13 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfComparison(), false);

		TerminalNode namespace_Node14 = new TerminalNode(new NamespaceType(""), true);

		TerminalNode firstColon_Node15 = new TerminalNode(new ColonType(), false);

		TerminalNode secondColon_Node16 = new TerminalNode(new ColonType(), false);
		
		TerminalNode namespaceMember_Node17 = new TerminalNode(new NamespaceMemberType(""), true);
		
		TerminalNode namespace_Node18 = new TerminalNode(new NamespaceType(""), true);

		TerminalNode firstColon_Node19= new TerminalNode(new ColonType(), false);

		TerminalNode secondColon_Node20 = new TerminalNode(new ColonType(), false);
		
		TerminalNode namespaceMember_Node21 = new TerminalNode(new NamespaceMemberType(""), true);
		
		TerminalNode nullprt_Node22 = new TerminalNode(new NullptrKeyword(), false);
		TerminalNode nullprt_Node23 = new TerminalNode(new NullptrKeyword(), true);
		
		NonTerminalNode pointerAccess_Node24 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfPointerAccessWithoutIdentifierSubGrammar(), true);
		NonTerminalNode pointerAccess_Node25 =new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfPointerAccessWithoutIdentifierSubGrammar(), true);
		
		TerminalNode ThisKeyword_Node26 = new TerminalNode(new ThisKeyword(), false);
		TerminalNode ThisKeyword_Node27 = new TerminalNode(new ThisKeyword(), false);
		
		// -----------------------------------------------------------------
		root.addChild(openParenthesis_Node1.Id);
		root.addChild(logicalNot_Node2.Id);
		root.addChild(identifier_Node3.Id);
		root.addChild(literal_Node4.Id);// E1
		root.addChild(namespace_Node14.Id);
		root.addChild(nullprt_Node22.Id);
		root.addChild(pointerAccess_Node24.Id);
		root.addChild(ThisKeyword_Node26.Id);
		
		namespace_Node14.addChild(firstColon_Node15.Id);
		firstColon_Node15.addChild(secondColon_Node16.Id);
		secondColon_Node16.addChild(namespaceMember_Node17.Id);
		secondColon_Node16.addChild(namespace_Node14.Id);
		
		namespaceMember_Node17.addChild(singleComparisonOperator_Node5.Id);// E7
		namespaceMember_Node17.addChild(doubleComparisonOperator_Node7.Id);// E8
		
		logicalOperator_Node10.addChild(namespaceMember_Node17.Id);// E23
		logicalNot_Node2.addChild(namespaceMember_Node17.Id);// E27
		
		logicalOperator_Node10.addChild(namespace_Node14.Id);// E23
		logicalNot_Node2.addChild(namespace_Node14.Id);// E27
		
		openParenthesis_Node1.addChild(comparison_Node13.Id);// E2
		
		logicalNot_Node2.addChild(openParenthesis_Node1.Id);// E3
		logicalNot_Node2.addChild(identifier_Node3.Id);// E27
		logicalNot_Node2.addChild(literal_Node4.Id);// E28
		logicalNot_Node2.addChild(nullprt_Node22.Id);// E28
		
		identifier_Node3.addChild(singleComparisonOperator_Node5.Id);// E7
		identifier_Node3.addChild(doubleComparisonOperator_Node7.Id);// E8
		identifier_Node3.addChild(logicalOperator_Node10.Id);// E7
		identifier_Node3.addChild(pointerAccess_Node24.Id);
		
		ThisKeyword_Node26.addChild(pointerAccess_Node24.Id);
		
		literal_Node4.addChild(singleComparisonOperator_Node5.Id);// E9
		literal_Node4.addChild(doubleComparisonOperator_Node7.Id);// E10

		nullprt_Node22.addChild(singleComparisonOperator_Node5.Id);// E9
		nullprt_Node22.addChild(doubleComparisonOperator_Node7.Id);// E10
		
		pointerAccess_Node24.addChild(singleComparisonOperator_Node5.Id);// E9
		pointerAccess_Node24.addChild(doubleComparisonOperator_Node7.Id);// E10
		pointerAccess_Node24.addChild(logicalOperator_Node10.Id);// E10
		
		singleComparisonOperator_Node5.addChild(identifier_Node8.Id);// E12
		singleComparisonOperator_Node5.addChild(literal_Node9.Id);// E13
		singleComparisonOperator_Node5.addChild(nullprt_Node23.Id);// E13
		singleComparisonOperator_Node5.addChild(pointerAccess_Node25.Id);// E13
		singleComparisonOperator_Node5.addChild(ThisKeyword_Node27.Id);
		
		doubleComparisonOperator_Node7.addChild(literal_Node9.Id);// E14
		doubleComparisonOperator_Node7.addChild(identifier_Node8.Id);// E15
		doubleComparisonOperator_Node7.addChild(nullprt_Node23.Id);// E14
		doubleComparisonOperator_Node7.addChild(ThisKeyword_Node27.Id);
		
		literal_Node9.addChild(logicalOperator_Node10.Id);// E17
		literal_Node9.addChild(literal_Node9.Id);// E26
		
		nullprt_Node23.addChild(logicalOperator_Node10.Id);// E17
		pointerAccess_Node25.addChild(logicalOperator_Node10.Id);// E17

		
		identifier_Node8.addChild(logicalOperator_Node10.Id);// E18
		identifier_Node8.addChild(pointerAccess_Node25.Id);
		
		ThisKeyword_Node27.addChild(pointerAccess_Node25.Id);
		
		closeParenthesis_Node11.addChild(logicalOperator_Node10.Id);// E21
		
		logicalOperator_Node10.addChild(literal_Node4.Id);// E22
		logicalOperator_Node10.addChild(identifier_Node3.Id);// E23
		logicalOperator_Node10.addChild(openParenthesis_Node1.Id);// E24
		logicalOperator_Node10.addChild(logicalNot_Node2.Id);// E30
		logicalOperator_Node10.addChild(nullprt_Node22.Id);// E30
		logicalOperator_Node10.addChild(pointerAccess_Node24.Id);// E30
		logicalOperator_Node10.addChild(ThisKeyword_Node26.Id);// E30
		
		
		namespaceMember_Node21.addChild(logicalOperator_Node10.Id);// E18
		doubleComparisonOperator_Node7.addChild(namespaceMember_Node21.Id);// E15
		singleComparisonOperator_Node5.addChild(namespaceMember_Node21.Id);// E12
		doubleComparisonOperator_Node7.addChild(namespace_Node18.Id);// E15
		singleComparisonOperator_Node5.addChild(namespace_Node18.Id);// E12
		
		namespace_Node18.addChild(firstColon_Node19.Id);
		firstColon_Node19.addChild(secondColon_Node20.Id);
		secondColon_Node20.addChild(namespaceMember_Node21.Id);
		secondColon_Node20.addChild(namespace_Node18.Id);
		
		
		comparison_Node13.addChild(closeParenthesis_Node11.Id);

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(openParenthesis_Node1);
		grammarNodes.add(logicalNot_Node2);
		grammarNodes.add(identifier_Node3);
		grammarNodes.add(literal_Node4);
		grammarNodes.add(singleComparisonOperator_Node5);
		grammarNodes.add(doubleComparisonOperator_Node7);
		grammarNodes.add(identifier_Node8);
		grammarNodes.add(literal_Node9);
		grammarNodes.add(logicalOperator_Node10);
		grammarNodes.add(closeParenthesis_Node11);
		grammarNodes.add(comparison_Node13);
		grammarNodes.add(namespace_Node14);
		grammarNodes.add(firstColon_Node15);
		grammarNodes.add(secondColon_Node16);
		grammarNodes.add(namespaceMember_Node17);
		grammarNodes.add(namespace_Node18);
		grammarNodes.add(firstColon_Node19);
		grammarNodes.add(secondColon_Node20);
		grammarNodes.add(namespaceMember_Node21);
		grammarNodes.add(nullprt_Node22);
		grammarNodes.add(nullprt_Node23);
		grammarNodes.add(pointerAccess_Node24);
		grammarNodes.add(pointerAccess_Node25);
		grammarNodes.add(ThisKeyword_Node26);
		grammarNodes.add(ThisKeyword_Node27);
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
