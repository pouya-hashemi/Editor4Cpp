package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.TokenTypes.NamespaceType;
import entities.TokenTypes.Punctuations.ColonType;
import grammars.Grammar;

public class PreNamespaceSubGrammar extends Grammar {
	public PreNamespaceSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public PreNamespaceSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		TerminalNode namespace_Node1 = new TerminalNode(new NamespaceType(""), false);

		TerminalNode firstColon_Node2 = new TerminalNode(new ColonType(), false);
		
		TerminalNode secondColon_Node3 = new TerminalNode(new ColonType(), true);

		// -----------------------------------------------------------------
		
		root.addChild(namespace_Node1.Id);
		
		namespace_Node1.addChild(firstColon_Node2.Id);
		firstColon_Node2.addChild(secondColon_Node3.Id);

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(namespace_Node1);
		grammarNodes.add(firstColon_Node2);
		grammarNodes.add(secondColon_Node3);

	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new PreNamespaceSubGrammar(this.Id, nodes, this.rootNodeId);
	}
}

