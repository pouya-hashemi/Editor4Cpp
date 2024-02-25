package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.NonTerminalNode;
import entities.TerminalNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Punctuations.AccessPointer;
import entities.TokenTypes.Punctuations.SemicolonType;

public class PointerAccessGrammar extends Grammar {
	public PointerAccessGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	
	
	public PointerAccessGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;		

		TerminalNode identifier_Node1 = new TerminalNode(new Identifier(""), false);
		TerminalNode accessPointer_Node2 = new TerminalNode(new AccessPointer(), false);
		NonTerminalNode functionCallSubGrammar_Node3 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfFunctionCallSubGrammar(), false);
		TerminalNode semicolon_Node4 = new TerminalNode(new SemicolonType(), true);
		

		// -----------------------------------------------------------------
		root.addChild(identifier_Node1.Id);
		identifier_Node1.addChild(accessPointer_Node2.Id);
		
		accessPointer_Node2.addChild(functionCallSubGrammar_Node3.Id);
		
		functionCallSubGrammar_Node3.addChild(semicolon_Node4.Id);
		functionCallSubGrammar_Node3.addChild(accessPointer_Node2.Id);

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(identifier_Node1);
		grammarNodes.add(accessPointer_Node2);
		grammarNodes.add(functionCallSubGrammar_Node3);
		grammarNodes.add(semicolon_Node4);


		
	}
	
	
	
	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new PointerAccessGrammar(this.Id,nodes,this.rootNodeId);
	}
}