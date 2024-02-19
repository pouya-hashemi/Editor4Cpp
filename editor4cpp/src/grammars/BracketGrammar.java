package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.NonTerminalNode;
import entities.TerminalNode;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;


public class BracketGrammar extends Grammar {
	
	public BracketGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	
	
	public BracketGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		

		TerminalNode openCurlyBracket_Node1 = new TerminalNode(new OpenCurlyBracket(), false);

		NonTerminalNode multiStatement_Node2 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfAll(),false);

		TerminalNode closeCurlyBracket_Node3 = new TerminalNode(new CloseCurlyBracket(), true);
		

		// -----------------------------------------------------------------
		root.addChild(openCurlyBracket_Node1.Id);
		
		openCurlyBracket_Node1.addChild(closeCurlyBracket_Node3.Id);
		openCurlyBracket_Node1.addChild(multiStatement_Node2.Id);
		
		multiStatement_Node2.addChild(closeCurlyBracket_Node3.Id);
		multiStatement_Node2.addChild(multiStatement_Node2.Id);


		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(openCurlyBracket_Node1);
		grammarNodes.add(multiStatement_Node2);
		grammarNodes.add(closeCurlyBracket_Node3);
		
	}
	
	
	
	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new BracketGrammar(this.Id,nodes,this.rootNodeId);
	}
}
