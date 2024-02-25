package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.NonTerminalNode;
import entities.TerminalNode;
import entities.TokenTypes.Punctuations.AccessPointer;
import grammars.Grammar;

public class PointerAccessWithoutIdentifierSubGrammar extends Grammar {
	public PointerAccessWithoutIdentifierSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public PointerAccessWithoutIdentifierSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;

		TerminalNode accessPointer_Node2 = new TerminalNode(new AccessPointer(), false);
		
		NonTerminalNode functionCall_Node3 = new NonTerminalNode(
				() -> GrammarLibrary.getParsingObjectsOfFunctionCallSubGrammar(), true);

		// -----------------------------------------------------------------
		
		
		root.addChild(accessPointer_Node2.Id);

		accessPointer_Node2.addChild(functionCall_Node3.Id);
		
		functionCall_Node3.addChild(accessPointer_Node2.Id);

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(accessPointer_Node2);
		grammarNodes.add(functionCall_Node3);

	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new PointerAccessWithoutIdentifierSubGrammar(this.Id, nodes, this.rootNodeId);
	}
}

