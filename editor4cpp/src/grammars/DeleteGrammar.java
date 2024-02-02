package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.GrammarNode;
import entities.TerminalNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Keywords.DeleteKeyword;
import entities.TokenTypes.Punctuations.CloseBracket;
import entities.TokenTypes.Punctuations.OpenBracket;
import entities.TokenTypes.Punctuations.SemicolonType;

public class DeleteGrammar extends Grammar {
	public DeleteGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	
	
	public DeleteGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		TerminalNode delete_Node1 = new TerminalNode(new DeleteKeyword(), false);
		
		TerminalNode openBracket_Node2 = new TerminalNode(new OpenBracket(), false);
		
		TerminalNode closeBracket_Node3 = new TerminalNode(new CloseBracket(), false);
		
		TerminalNode identifier_Node4 = new TerminalNode(new Identifier(""), false);
		
		TerminalNode semicolon_Node5 = new TerminalNode(new SemicolonType(), true);



		// -----------------------------------------------------------------
		root.addChild(delete_Node1.Id);
		delete_Node1.addChild(openBracket_Node2.Id);
		openBracket_Node2.addChild(closeBracket_Node3.Id);		
		closeBracket_Node3.addChild(identifier_Node4.Id);	
		identifier_Node4.addChild(semicolon_Node5.Id);	



		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(delete_Node1);
		grammarNodes.add(openBracket_Node2);
		grammarNodes.add(closeBracket_Node3);
		grammarNodes.add(identifier_Node4);
		grammarNodes.add(semicolon_Node5);
	}
	
	
	
	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new DeleteGrammar(this.Id,nodes,this.rootNodeId);
	}
}
