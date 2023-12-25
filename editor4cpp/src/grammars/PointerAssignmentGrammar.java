package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Keywords.ElseKeyword;
import entities.TokenTypes.Keywords.IfKeyword;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import entities.TokenTypes.Punctuations.SemicolonType;

public class PointerAssignmentGrammar extends Grammar {
	public PointerAssignmentGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	public PointerAssignmentGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		SingleNode identifier_Node1 = new SingleNode(new Identifier(""), false);

		SingleNode semicolon_Node2 = new SingleNode(new SemicolonType(), true);

		StatementNode pointerEqualStatement_Node3 = new StatementNode(()->GrammarLibrary.getParsingObjectsOfPointerEqualSubGrammar(),false);


		// -----------------------------------------------------------------
		root.addChild(identifier_Node1.Id);
		identifier_Node1.addChild(pointerEqualStatement_Node3.Id);
		pointerEqualStatement_Node3.addChild(semicolon_Node2.Id);


		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(identifier_Node1);
		grammarNodes.add(semicolon_Node2);
		grammarNodes.add(pointerEqualStatement_Node3);
		
	}

	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new PointerAssignmentGrammar(this.Id,nodes,this.rootNodeId);
	}
}

