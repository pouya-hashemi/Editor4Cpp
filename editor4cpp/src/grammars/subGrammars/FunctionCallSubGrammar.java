package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
import entities.TokenTypes.FunctionIdentifier;
import entities.TokenTypes.NamespaceType;
import entities.TokenTypes.Punctuations.ColonType;
import grammars.Grammar;

public class FunctionCallSubGrammar  extends Grammar {

	public FunctionCallSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public FunctionCallSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		TerminalNode funcIdentifier_Node1 = new TerminalNode(new FunctionIdentifier(), false);
		
		NonTerminalNode functionParam_Node2 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfFunctionParametersSubGrammar(), true);
		
		TerminalNode namespace_Node6 = new TerminalNode(new NamespaceType(""), false);

		TerminalNode firstColon_Node7 = new TerminalNode(new ColonType(), false);
		
		TerminalNode secondColon_Node8 = new TerminalNode(new ColonType(), false);

		
		

		// -----------------------------------------------------------------
		root.addChild(funcIdentifier_Node1.Id);
		root.addChild(namespace_Node6.Id);
		
		funcIdentifier_Node1.addChild(functionParam_Node2.Id);

		namespace_Node6.addChild(firstColon_Node7.Id);
		firstColon_Node7.addChild(secondColon_Node8.Id);
		secondColon_Node8.addChild(namespace_Node6.Id);
		secondColon_Node8.addChild(funcIdentifier_Node1.Id);


		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(funcIdentifier_Node1);
		grammarNodes.add(functionParam_Node2);
		grammarNodes.add(namespace_Node6);
		grammarNodes.add(firstColon_Node7);
		grammarNodes.add(secondColon_Node8);

	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new FunctionCallSubGrammar(this.Id, nodes, this.rootNodeId);
	}
}

