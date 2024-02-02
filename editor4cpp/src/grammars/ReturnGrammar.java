package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
import entities.TokenTypes.ObjectIdentifier;
import entities.TokenTypes.Keywords.ReturnKeyword;
import entities.TokenTypes.Punctuations.SemicolonType;

public class ReturnGrammar extends Grammar {
	
	public ReturnGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	public ReturnGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		TerminalNode return_Node1 = new TerminalNode(new ReturnKeyword(), false);

		NonTerminalNode topLevel_Node2 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfFunctionCallGrammar(),false);

		TerminalNode objectIdentifier_Node3 = new TerminalNode(new ObjectIdentifier(), false);

		NonTerminalNode pointerEqualStatement_Node4 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(),false);

		TerminalNode semicolon_Node5 = new TerminalNode(new SemicolonType(), true);
		// -----------------------------------------------------------------
		root.addChild(return_Node1.Id);
		
		
		return_Node1.addChild(objectIdentifier_Node3.Id);
		return_Node1.addChild(pointerEqualStatement_Node4.Id);
		return_Node1.addChild(topLevel_Node2.Id);
		
		topLevel_Node2.addChild(semicolon_Node5.Id);
		objectIdentifier_Node3.addChild(semicolon_Node5.Id);
		pointerEqualStatement_Node4.addChild(semicolon_Node5.Id);


		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(return_Node1);
		grammarNodes.add(topLevel_Node2);
		grammarNodes.add(objectIdentifier_Node3);
		grammarNodes.add(pointerEqualStatement_Node4);
		grammarNodes.add(semicolon_Node5);
		
	}

	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new ReturnGrammar(this.Id,nodes,this.rootNodeId);
	}
}

