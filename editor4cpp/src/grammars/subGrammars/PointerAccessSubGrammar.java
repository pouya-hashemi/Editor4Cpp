package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.NonTerminalNode;
import entities.TerminalNode;
import entities.TokenTypes.DataType;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Identifiers.PointerIdentifier;
import entities.TokenTypes.Keywords.NewKeyword;
import entities.TokenTypes.Keywords.NullKeyword;
import entities.TokenTypes.Keywords.NullptrKeyword;
import entities.TokenTypes.Punctuations.AccessPointer;
import entities.TokenTypes.Punctuations.AmpersandType;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.EqualType;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import grammars.Grammar;

public class PointerAccessSubGrammar extends Grammar {
	public PointerAccessSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public PointerAccessSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		TerminalNode pointerIdentifier_Node1 = new TerminalNode(new PointerIdentifier(""), false);

		TerminalNode accessPointer_Node2 = new TerminalNode(new AccessPointer(), false);
		
		NonTerminalNode functionCall_Node3 = new NonTerminalNode(
				() -> GrammarLibrary.getParsingObjectsOfFunctionCallSubGrammar(), true);

		// -----------------------------------------------------------------
		
		root.addChild(pointerIdentifier_Node1.Id);
		
		pointerIdentifier_Node1.addChild(accessPointer_Node2.Id);
		accessPointer_Node2.addChild(functionCall_Node3.Id);

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(pointerIdentifier_Node1);
		grammarNodes.add(accessPointer_Node2);
		grammarNodes.add(functionCall_Node3);

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

