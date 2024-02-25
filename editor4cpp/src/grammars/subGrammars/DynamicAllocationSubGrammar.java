package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.NonTerminalNode;
import entities.TerminalNode;
import entities.TokenTypes.DataTypes.FundamentalDataType;
import entities.TokenTypes.DataTypes.UserDefinedDataType;
import entities.TokenTypes.Keywords.NewKeyword;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.CommaType;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import grammars.Grammar;

public class DynamicAllocationSubGrammar extends Grammar {

	public DynamicAllocationSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public DynamicAllocationSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		TerminalNode new_Node1 = new TerminalNode(new NewKeyword(), false);

		TerminalNode fundamental_Node2 = new TerminalNode(new FundamentalDataType(""), true);

		TerminalNode userDefined_Node3 = new TerminalNode(new UserDefinedDataType(""), true);

		NonTerminalNode generic_Node4 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfGenericDataTypeSubGrammar(), false);

		NonTerminalNode fundtionCall_Node5 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfFunctionCallSubGrammar(), true);

		TerminalNode openParenthesis_Node6 = new TerminalNode(new OpenParenthesisType(), false);

		NonTerminalNode topLevel_Node7 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(), false);

		TerminalNode comma_Node8 = new TerminalNode(new CommaType(), false);
		
		TerminalNode closeParenthesis_Node9 = new TerminalNode(new CloseParenthesisType(), true);

		// -----------------------------------------------------------------
		root.addChild(new_Node1.Id);
		
		new_Node1.addChild(fundamental_Node2.Id);
		new_Node1.addChild(userDefined_Node3.Id);
		new_Node1.addChild(generic_Node4.Id);
		new_Node1.addChild(fundtionCall_Node5.Id);
		
		generic_Node4.addChild(openParenthesis_Node6.Id);
		

		openParenthesis_Node6.addChild(closeParenthesis_Node9.Id);
		openParenthesis_Node6.addChild(topLevel_Node7.Id);
		
		topLevel_Node7.addChild(closeParenthesis_Node9.Id);
		topLevel_Node7.addChild(comma_Node8.Id);
		
		comma_Node8.addChild(topLevel_Node7.Id);
		
		

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(new_Node1);
		grammarNodes.add(fundamental_Node2);
		grammarNodes.add(userDefined_Node3);
		grammarNodes.add(generic_Node4);
		grammarNodes.add(fundtionCall_Node5);
		grammarNodes.add(openParenthesis_Node6);
		grammarNodes.add(topLevel_Node7);
		grammarNodes.add(comma_Node8);
		grammarNodes.add(closeParenthesis_Node9);

	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new DynamicAllocationSubGrammar(this.Id, nodes, this.rootNodeId);
	}
}
