package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
import entities.TokenTypes.DataType;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.PointerDataType;
import entities.TokenTypes.Punctuations.SemicolonType;
import entities.TokenTypes.Punctuations.StarType;

public class PointerDeclarationGrammar extends Grammar {
	public PointerDeclarationGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	public PointerDeclarationGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

//		TerminalNode dataType_Node1 = new TerminalNode(new DataType(), false);
//
//		TerminalNode star_Node2 = new TerminalNode(new StarType(), false);
		TerminalNode pointerType_Node1 = new TerminalNode(new PointerDataType(), false);
		TerminalNode identifier_Node3 = new TerminalNode(new Identifier(""), false);

		NonTerminalNode pointerEqualStatement_Node4 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfPointerEqualSubGrammar(),false);
		
		TerminalNode semicolon_Node5 = new TerminalNode(new SemicolonType(), true);

		// -----------------------------------------------------------------
		root.addChild(pointerType_Node1.Id);
//		dataType_Node1.addChild(star_Node2.Id);
		pointerType_Node1.addChild(identifier_Node3.Id);
		identifier_Node3.addChild(semicolon_Node5.Id);
		identifier_Node3.addChild(pointerEqualStatement_Node4.Id);
		
		pointerEqualStatement_Node4.addChild(semicolon_Node5.Id);


		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(pointerType_Node1);
//		grammarNodes.add(star_Node2);
		grammarNodes.add(identifier_Node3);
		grammarNodes.add(pointerEqualStatement_Node4);
		grammarNodes.add(semicolon_Node5);
		
	}

	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new PointerDeclarationGrammar(this.Id,nodes,this.rootNodeId);
	}
}


