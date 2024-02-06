package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
import entities.TokenTypes.Keywords.WhileKeyword;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
import entities.TokenTypes.Punctuations.OpenParenthesisType;

public class WhileGrammar extends Grammar {
	
	public WhileGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	
	
	public WhileGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		TerminalNode while_Node1 = new TerminalNode(new WhileKeyword(), false);

		TerminalNode openParenthesis_Node2 = new TerminalNode(new OpenParenthesisType(), false);

		NonTerminalNode comparesionStatement_Node3 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfComparison(),false);

		TerminalNode closeParenthesis_Node4 = new TerminalNode(new CloseParenthesisType(), false);

		TerminalNode openCurlyBracket_Node5 = new TerminalNode(new OpenCurlyBracket(), false);

		NonTerminalNode singleStatement_Node6 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfAll(),true);

		NonTerminalNode multiStatement_Node7 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfAll(),false);

		TerminalNode closeCurlyBracket_Node8 = new TerminalNode(new CloseCurlyBracket(), true);
		

		// -----------------------------------------------------------------
		root.addChild(while_Node1.Id);
		while_Node1.addChild(openParenthesis_Node2.Id);
		openParenthesis_Node2.addChild(comparesionStatement_Node3.Id);
		comparesionStatement_Node3.addChild(closeParenthesis_Node4.Id);
		
		closeParenthesis_Node4.addChild(openCurlyBracket_Node5.Id);
		closeParenthesis_Node4.addChild(singleStatement_Node6.Id);
		
		openCurlyBracket_Node5.addChild(closeCurlyBracket_Node8.Id);
		openCurlyBracket_Node5.addChild(multiStatement_Node7.Id);
		
		multiStatement_Node7.addChild(closeCurlyBracket_Node8.Id);
		multiStatement_Node7.addChild(multiStatement_Node7.Id);


		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(while_Node1);
		grammarNodes.add(openParenthesis_Node2);
		grammarNodes.add(comparesionStatement_Node3);
		grammarNodes.add(closeParenthesis_Node4);
		grammarNodes.add(openCurlyBracket_Node5);
		grammarNodes.add(singleStatement_Node6);
		grammarNodes.add(multiStatement_Node7);
		grammarNodes.add(closeCurlyBracket_Node8);
		
	}
	
	
	
	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new WhileGrammar(this.Id,nodes,this.rootNodeId);
	}
}
