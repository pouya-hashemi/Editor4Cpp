package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.NonTerminalNode;
import entities.TerminalNode;
import entities.TokenTypes.DataTypes.FundamentalDataType;
import entities.TokenTypes.DataTypes.GenericDataType;
import entities.TokenTypes.DataTypes.UserDefinedDataType;
import entities.TokenTypes.Operations.GreaterThanOperator;
import entities.TokenTypes.Operations.LessThanOperator;
import grammars.Grammar;

public class GenericDataTypeSubGrammar extends Grammar {

	public GenericDataTypeSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public GenericDataTypeSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		TerminalNode genericDataType_Node1 = new TerminalNode(new GenericDataType(""), false);
		NonTerminalNode preNamespaceSubGrammar_Node2 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfPreNamespaceSubGrammar(), false);

		TerminalNode lessThan_Node3 = new TerminalNode(new LessThanOperator(), false);

		TerminalNode fundamentalDatatype_Node4 = new TerminalNode(new FundamentalDataType(""), false);
		
		NonTerminalNode genericSubGrammar_Node5 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfGenericDataTypeSubGrammar(), false);

		TerminalNode greaterThan_Node6 = new TerminalNode(new GreaterThanOperator(), true);
		TerminalNode userDefinedDataType_Node7 = new TerminalNode(new UserDefinedDataType(""), false);
		NonTerminalNode preNamespaceSubGrammar_Node8 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfPreNamespaceSubGrammar(), false);
		
		

		// -----------------------------------------------------------------
		root.addChild(genericDataType_Node1.Id);
		root.addChild(preNamespaceSubGrammar_Node2.Id);
//		root.addChild(unknown_Node9.Id);
		
		preNamespaceSubGrammar_Node2.addChild(genericDataType_Node1.Id);
		preNamespaceSubGrammar_Node2.addChild(preNamespaceSubGrammar_Node2.Id);
		
		genericDataType_Node1.addChild(lessThan_Node3.Id);
		
		lessThan_Node3.addChild(fundamentalDatatype_Node4.Id);
		lessThan_Node3.addChild(userDefinedDataType_Node7.Id);
		lessThan_Node3.addChild(preNamespaceSubGrammar_Node8.Id);
		lessThan_Node3.addChild(genericSubGrammar_Node5.Id);
		
		preNamespaceSubGrammar_Node8.addChild(fundamentalDatatype_Node4.Id);
		preNamespaceSubGrammar_Node8.addChild(userDefinedDataType_Node7.Id);
		preNamespaceSubGrammar_Node8.addChild(preNamespaceSubGrammar_Node8.Id);
		preNamespaceSubGrammar_Node8.addChild(genericSubGrammar_Node5.Id);
		
		fundamentalDatatype_Node4.addChild(greaterThan_Node6.Id);
		userDefinedDataType_Node7.addChild(greaterThan_Node6.Id);
		genericSubGrammar_Node5.addChild(greaterThan_Node6.Id);
		

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(genericDataType_Node1);
		grammarNodes.add(preNamespaceSubGrammar_Node2);
		grammarNodes.add(lessThan_Node3);
		grammarNodes.add(fundamentalDatatype_Node4);
		grammarNodes.add(genericSubGrammar_Node5);
		grammarNodes.add(greaterThan_Node6);
		grammarNodes.add(userDefinedDataType_Node7);
		grammarNodes.add(preNamespaceSubGrammar_Node8);


	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new GenericDataTypeSubGrammar(this.Id, nodes, this.rootNodeId);
	}
}

