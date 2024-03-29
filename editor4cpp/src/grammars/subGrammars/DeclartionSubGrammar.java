package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Punctuations.CommaType;
import entities.TokenTypes.Punctuations.SemicolonType;
import grammars.Grammar;

public class DeclartionSubGrammar extends Grammar {

	public DeclartionSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public DeclartionSubGrammar() {
		super();
		initGrammar(true);
	}

	public DeclartionSubGrammar(boolean hasSemicolon) {
		super();
		initGrammar(hasSemicolon);
	}

	private void initGrammar(boolean hasSemicolon) {
		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		TerminalNode identifier_Node1 = new TerminalNode(new Identifier(""), true);

		TerminalNode semicolon_Node2 = new TerminalNode(new SemicolonType(), false);

		TerminalNode comma_Node3 = new TerminalNode(new CommaType(), false);

		NonTerminalNode equalStatement_Node4 = new NonTerminalNode(
				() -> GrammarLibrary.getParsingObjectsOfEqualSubGrammar(), true);

		NonTerminalNode declartionSubGrammar_Node5 = new NonTerminalNode(
				() -> GrammarLibrary.getParsingObjectsOfDeclartionSubGrammar(false), true);

		// -----------------------------------------------------------------
		root.addChild(identifier_Node1.Id);
		
		if (hasSemicolon) {
			identifier_Node1.addChild(semicolon_Node2.Id);
			equalStatement_Node4.addChild(semicolon_Node2.Id);
			declartionSubGrammar_Node5.addChild(semicolon_Node2.Id);
			identifier_Node1.canBeEnd = false;
			equalStatement_Node4.canBeEnd = false;
			declartionSubGrammar_Node5.canBeEnd = false;
			semicolon_Node2.canBeEnd = true;

		}

		identifier_Node1.addChild(comma_Node3.Id);
		identifier_Node1.addChild(equalStatement_Node4.Id);

		comma_Node3.addChild(declartionSubGrammar_Node5.Id);

		equalStatement_Node4.addChild(comma_Node3.Id);

		

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(identifier_Node1);
		grammarNodes.add(semicolon_Node2);
		grammarNodes.add(comma_Node3);
		grammarNodes.add(equalStatement_Node4);
		grammarNodes.add(declartionSubGrammar_Node5);
	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new DeclartionSubGrammar(this.Id, nodes, this.rootNodeId);
	}
}
