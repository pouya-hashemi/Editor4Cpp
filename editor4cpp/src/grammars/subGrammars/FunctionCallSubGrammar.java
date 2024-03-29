package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
import entities.TokenTypes.FunctionIdentifier;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.CommaType;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
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

		TerminalNode openParenthesis_Node2 = new TerminalNode(new OpenParenthesisType(), false);

		NonTerminalNode topLevel_Node3 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(), false);

		TerminalNode comma_Node4 = new TerminalNode(new CommaType(), false);
		
		TerminalNode closeParenthesis_Node5 = new TerminalNode(new CloseParenthesisType(), true);
		
//		SingleNode unknown_Node6 = new SingleNode(new UnknownType(), false);
//		SingleNode colon_Node7 = new SingleNode(new ColonType(), false);
//		SingleNode colon_Node8 = new SingleNode(new ColonType(), false);
//		SingleNode unknown_Node9 = new SingleNode(new UnknownType(), false);
//		SingleNode dot_Node10 = new SingleNode(new DotType(), false);
		
		

		// -----------------------------------------------------------------
		root.addChild(funcIdentifier_Node1.Id);
//		root.addChild(unknown_Node6.Id);
//		root.addChild(unknown_Node9.Id);
		
		funcIdentifier_Node1.addChild(openParenthesis_Node2.Id);
		openParenthesis_Node2.addChild(closeParenthesis_Node5.Id);
		openParenthesis_Node2.addChild(topLevel_Node3.Id);
		topLevel_Node3.addChild(comma_Node4.Id);
		topLevel_Node3.addChild(closeParenthesis_Node5.Id);
		comma_Node4.addChild(topLevel_Node3.Id);
		
	
//		unknown_Node6.addChild(colon_Node7.Id);
//		colon_Node7.addChild(colon_Node8.Id);
//		
//		colon_Node8.addChild(funcIdentifier_Node1.Id);
//		colon_Node8.addChild(unknown_Node9.Id);
//		
//		unknown_Node9.addChild(dot_Node10.Id);
//		dot_Node10.addChild(funcIdentifier_Node1.Id);

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(funcIdentifier_Node1);
		grammarNodes.add(openParenthesis_Node2);
		grammarNodes.add(topLevel_Node3);
		grammarNodes.add(comma_Node4);
		grammarNodes.add(closeParenthesis_Node5);
//		grammarNodes.add(unknown_Node6);
//		grammarNodes.add(colon_Node7);
//		grammarNodes.add(colon_Node8);
//		grammarNodes.add(unknown_Node9);
//		grammarNodes.add(dot_Node10);

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

