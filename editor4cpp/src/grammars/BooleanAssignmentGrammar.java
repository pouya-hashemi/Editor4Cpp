package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.NonTerminalNode;
import entities.TerminalNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Punctuations.EqualType;
import entities.TokenTypes.Punctuations.SemicolonType;

public class BooleanAssignmentGrammar extends Grammar {
	
	public BooleanAssignmentGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	
	
	public BooleanAssignmentGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		TerminalNode identifier_Node1 = new TerminalNode(new Identifier(""), false);

		TerminalNode equal_Node2 = new TerminalNode(new EqualType(), false);

		NonTerminalNode comparison_Node3 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfComparison(),false);

		TerminalNode semicolon_Node4 = new TerminalNode(new SemicolonType(), true);

		// -----------------------------------------------------------------
		root.addChild(identifier_Node1.Id);
		
		identifier_Node1.addChild(equal_Node2.Id);
		equal_Node2.addChild(comparison_Node3.Id);
		comparison_Node3.addChild(semicolon_Node4.Id);


		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(identifier_Node1);
		grammarNodes.add(equal_Node2);
		grammarNodes.add(comparison_Node3);
		grammarNodes.add(semicolon_Node4);
		
	}

	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new BooleanAssignmentGrammar(this.Id,nodes,this.rootNodeId);
	}
}
