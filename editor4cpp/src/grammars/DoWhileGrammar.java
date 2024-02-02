package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
import entities.TokenTypes.Keywords.DoKeyword;
import entities.TokenTypes.Keywords.WhileKeyword;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import entities.TokenTypes.Punctuations.SemicolonType;

public class DoWhileGrammar extends Grammar {
	public DoWhileGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	
	
	public DoWhileGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		TerminalNode do_Node1 = new TerminalNode(new DoKeyword(), false);

		NonTerminalNode singleStatementForIf_Node2 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfAll(),false);
		
		TerminalNode openCurlyBracketForIf_Node3 = new TerminalNode(new OpenCurlyBracket(), false);
		
		NonTerminalNode multiStatementForIf_Node4 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfAll(),false);

		TerminalNode closeCurlyBracketForIf_Node5 = new TerminalNode(new CloseCurlyBracket(), false);
		
		TerminalNode while_Node6 = new TerminalNode(new WhileKeyword(), false);
		
		TerminalNode openParenthesis_Node7 = new TerminalNode(new OpenParenthesisType(), false);

		NonTerminalNode comparesionStatement_Node8 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfComparison(),false);

		TerminalNode closeParenthesis_Node9 = new TerminalNode(new CloseParenthesisType(), false);
		
		TerminalNode semicolon_Node10 = new TerminalNode(new SemicolonType(), true);

		
		

		// -----------------------------------------------------------------
		root.addChild(do_Node1.Id);
		
		do_Node1.addChild(openCurlyBracketForIf_Node3.Id);
		do_Node1.addChild(singleStatementForIf_Node2.Id);
		
		openCurlyBracketForIf_Node3.addChild(multiStatementForIf_Node4.Id);
		
		multiStatementForIf_Node4.addChild(closeCurlyBracketForIf_Node5.Id);
		multiStatementForIf_Node4.addChild(multiStatementForIf_Node4.Id);

		closeCurlyBracketForIf_Node5.addChild(while_Node6.Id);
		
		singleStatementForIf_Node2.addChild(while_Node6.Id);
		
		while_Node6.addChild(openParenthesis_Node7.Id);
		openParenthesis_Node7.addChild(comparesionStatement_Node8.Id);
		comparesionStatement_Node8.addChild(closeParenthesis_Node9.Id);
		closeParenthesis_Node9.addChild(semicolon_Node10.Id);
		



		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(do_Node1);
		grammarNodes.add(singleStatementForIf_Node2);
		grammarNodes.add(openCurlyBracketForIf_Node3);
		grammarNodes.add(multiStatementForIf_Node4);
		grammarNodes.add(closeCurlyBracketForIf_Node5);
		grammarNodes.add(while_Node6);
		grammarNodes.add(openParenthesis_Node7);
		grammarNodes.add(comparesionStatement_Node8);
		grammarNodes.add(closeParenthesis_Node9);
		grammarNodes.add(semicolon_Node10);
		
	}
	
	
	
	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new DoWhileGrammar(this.Id,nodes,this.rootNodeId);
	}
}
