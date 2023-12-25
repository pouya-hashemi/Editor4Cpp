package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
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
		SingleNode equal_Node1 = new SingleNode(new EqualType(), false);

		SingleNode ampersand_Node2 = new SingleNode(new AmpersandType(), false);
		
		SingleNode newKeyword_Node3 = new SingleNode(new NewKeyword(), false);
		
		SingleNode nullptrKeyword_Node4 = new SingleNode(new NullptrKeyword(), true);
		
		SingleNode nullKeyword_Node5 = new SingleNode(new NullKeyword(), true);
		
		SingleNode identifier_Node6 = new SingleNode(new Identifier(""), true);
		
		SingleNode dataType_Node7 = new SingleNode(new DataType(), false);
		
		SingleNode openParenthesis_Node8 = new SingleNode(new OpenParenthesisType(), false);

		StatementNode mathTopLayer_Node9 = new StatementNode(
				() -> GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(), false);
		
		SingleNode closeParenthesis_Node10 = new SingleNode(new CloseParenthesisType(), true);
		


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
