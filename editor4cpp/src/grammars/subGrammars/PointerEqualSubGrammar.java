package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
import entities.TokenTypes.DataType;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Keywords.NewKeyword;
import entities.TokenTypes.Keywords.NullKeyword;
import entities.TokenTypes.Keywords.NullptrKeyword;
import entities.TokenTypes.Punctuations.AmpersandType;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.EqualType;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import grammars.Grammar;

public class PointerEqualSubGrammar extends Grammar {
	public PointerEqualSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public PointerEqualSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		TerminalNode equal_Node1 = new TerminalNode(new EqualType(), false);

		TerminalNode ampersand_Node2 = new TerminalNode(new AmpersandType(), false);
		
		TerminalNode newKeyword_Node3 = new TerminalNode(new NewKeyword(), false);
		
		TerminalNode nullptrKeyword_Node4 = new TerminalNode(new NullptrKeyword(), true);
		
		TerminalNode nullKeyword_Node5 = new TerminalNode(new NullKeyword(), true);
		
		TerminalNode identifier_Node6 = new TerminalNode(new Identifier(""), true);
		
		TerminalNode dataType_Node7 = new TerminalNode(new DataType(""), false);
		
		TerminalNode openParenthesis_Node8 = new TerminalNode(new OpenParenthesisType(), false);

		NonTerminalNode mathTopLayer_Node9 = new NonTerminalNode(
				() -> GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(), false);
		
		TerminalNode closeParenthesis_Node10 = new TerminalNode(new CloseParenthesisType(), true);
		


		// -----------------------------------------------------------------
		root.addChild(equal_Node1.Id);
		
		equal_Node1.addChild(ampersand_Node2.Id);
		equal_Node1.addChild(newKeyword_Node3.Id);
		equal_Node1.addChild(nullptrKeyword_Node4.Id);
		equal_Node1.addChild(nullKeyword_Node5.Id);
		
		ampersand_Node2.addChild(identifier_Node6.Id);
		
		newKeyword_Node3.addChild(dataType_Node7.Id);
		
		dataType_Node7.addChild(openParenthesis_Node8.Id);
		
		openParenthesis_Node8.addChild(mathTopLayer_Node9.Id);
		
		mathTopLayer_Node9.addChild(closeParenthesis_Node10.Id);
		

		

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(equal_Node1);
		grammarNodes.add(ampersand_Node2);
		grammarNodes.add(newKeyword_Node3);
		grammarNodes.add(nullptrKeyword_Node4);
		grammarNodes.add(nullKeyword_Node5);
		grammarNodes.add(identifier_Node6);
		grammarNodes.add(dataType_Node7);
		grammarNodes.add(openParenthesis_Node8);
		grammarNodes.add(mathTopLayer_Node9);
		grammarNodes.add(closeParenthesis_Node10);
		

	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new PointerEqualSubGrammar(this.Id, nodes, this.rootNodeId);
	}
}
