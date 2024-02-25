package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.NonTerminalNode;
import entities.TerminalNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Keywords.ThisKeyword;
import grammars.Grammar;

public class PointerAccessSubGrammar extends Grammar {
	public PointerAccessSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public PointerAccessSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		TerminalNode pointerIdentifier_Node1 = new TerminalNode(new Identifier(""), false);
		TerminalNode this_Node4 = new TerminalNode(new ThisKeyword(), false);
		
		NonTerminalNode functionCall_Node3 = new NonTerminalNode(
				() -> GrammarLibrary.getParsingObjectsOfPointerAccessWithoutIdentifierSubGrammar(), true);

		// -----------------------------------------------------------------
		
		root.addChild(pointerIdentifier_Node1.Id);
		root.addChild(this_Node4.Id);
		
		pointerIdentifier_Node1.addChild(functionCall_Node3.Id);
		this_Node4.addChild(functionCall_Node3.Id);

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(pointerIdentifier_Node1);
		grammarNodes.add(functionCall_Node3);
		grammarNodes.add(this_Node4);

	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new PointerAccessSubGrammar(this.Id, nodes, this.rootNodeId);
	}
}

