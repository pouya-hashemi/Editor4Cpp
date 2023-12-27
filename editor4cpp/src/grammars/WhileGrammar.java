package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
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

		SingleNode while_Node1 = new SingleNode(new WhileKeyword(), false);

		SingleNode openParenthesis_Node2 = new SingleNode(new OpenParenthesisType(), false);

		StatementNode comparesionStatement_Node3 = new StatementNode(()->GrammarLibrary.getParsingObjectsOfComparison(),false);

		SingleNode closeParenthesis_Node4 = new SingleNode(new CloseParenthesisType(), false,false);

		SingleNode openCurlyBracketForIf_Node5 = new SingleNode(new OpenCurlyBracket(), false);

		StatementNode singleStatementForIf_Node6 = new StatementNode(()->GrammarLibrary.getParsingObjectsOfAll(),true);

		StatementNode multiStatementForIf_Node7 = new StatementNode(()->GrammarLibrary.getParsingObjectsOfAll(),false);

		SingleNode closeCurlyBracketForIf_Node8 = new SingleNode(new CloseCurlyBracket(), true);
		
		

		
		
		

		// -----------------------------------------------------------------
		root.addChild(while_Node1.Id);
		while_Node1.addChild(openParenthesis_Node2.Id);
		openParenthesis_Node2.addChild(comparesionStatement_Node3.Id);
		comparesionStatement_Node3.addChild(closeParenthesis_Node4.Id);
		closeParenthesis_Node4.addChild(openCurlyBracketForIf_Node5.Id);
		closeParenthesis_Node4.addChild(singleStatementForIf_Node6.Id);
		openCurlyBracketForIf_Node5.addChild(multiStatementForIf_Node7.Id);
		multiStatementForIf_Node7.addChild(closeCurlyBracketForIf_Node8.Id);
		multiStatementForIf_Node7.addChild(multiStatementForIf_Node7.Id);


		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(while_Node1);
		grammarNodes.add(openParenthesis_Node2);
		grammarNodes.add(comparesionStatement_Node3);
		grammarNodes.add(closeParenthesis_Node4);
		grammarNodes.add(openCurlyBracketForIf_Node5);
		grammarNodes.add(singleStatementForIf_Node6);
		grammarNodes.add(multiStatementForIf_Node7);
		grammarNodes.add(closeCurlyBracketForIf_Node8);
		
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
