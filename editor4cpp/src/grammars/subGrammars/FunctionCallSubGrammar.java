package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
import entities.TokenTypes.FunctionIdentifier;
import entities.TokenTypes.NumericLiteral;
import entities.TokenTypes.TextLiteral;
import entities.TokenTypes.UnknownType;
import entities.TokenTypes.Keywords.BreakKeyword;
import entities.TokenTypes.Keywords.CaseKeyword;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.ColonType;
import entities.TokenTypes.Punctuations.CommaType;
import entities.TokenTypes.Punctuations.DotType;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import entities.TokenTypes.Punctuations.SemicolonType;
import grammars.Grammar;

public class FunctionCallSubGrammar  extends Grammar {

	public FunctionCallSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public FunctionCallSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		SingleNode funcIdentifier_Node1 = new SingleNode(new FunctionIdentifier(), false);

		SingleNode openParenthesis_Node2 = new SingleNode(new OpenParenthesisType(), false);

		StatementNode topLevel_Node3 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(), false);

		SingleNode comma_Node4 = new SingleNode(new CommaType(), false);
		
		SingleNode closeParenthesis_Node5 = new SingleNode(new CloseParenthesisType(), true);
		
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

