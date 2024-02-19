package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
import entities.TokenTypes.DataType;
import entities.TokenTypes.Keywords.AutoKeyword;



public class VariableDeclarationGrammar extends Grammar {
	public VariableDeclarationGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	public VariableDeclarationGrammar() {
		super();

		initGrammar(true);
		
	}
	public VariableDeclarationGrammar(boolean hasSemicolon) {
		super();

		initGrammar(hasSemicolon);
		
	}
	private void initGrammar(boolean hasSemicolon) {

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		
		TerminalNode dataType_Node1 = new TerminalNode(new DataType(),false);

		NonTerminalNode assignment_Node2=new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfDeclartionSubGrammar(hasSemicolon),true);
		TerminalNode auto_Node3 = new TerminalNode(new AutoKeyword(),false);

		root.addChild(dataType_Node1.Id);
		root.addChild(auto_Node3.Id);
		
		dataType_Node1.addChild(assignment_Node2.Id);
		auto_Node3.addChild(assignment_Node2.Id);

		
		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(dataType_Node1);
		grammarNodes.add(assignment_Node2);
		grammarNodes.add(auto_Node3);
	}
	
	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new VariableDeclarationGrammar(this.Id,nodes,this.rootNodeId);
	}
}
