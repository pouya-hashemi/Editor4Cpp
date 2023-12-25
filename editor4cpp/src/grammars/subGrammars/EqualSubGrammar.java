package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
import entities.TokenTypes.Operations.DoubleOperandOperator;
import entities.TokenTypes.Punctuations.EqualType;
import grammars.Grammar;

public class EqualSubGrammar extends Grammar {
	public EqualSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public EqualSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		SingleNode equal_Node1 = new SingleNode(new EqualType(), false);

		SingleNode singleOperator_Node2 = new SingleNode(new DoubleOperandOperator(), false);

		StatementNode mathTopLayer_Node3 = new StatementNode(
				() -> GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(), true);

		// -----------------------------------------------------------------
		root.addChild(equal_Node1.Id);
		root.addChild(singleOperator_Node2.Id);

		singleOperator_Node2.addChild(equal_Node1.Id);

		equal_Node1.addChild(mathTopLayer_Node3.Id);

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(equal_Node1);
		grammarNodes.add(singleOperator_Node2);
		grammarNodes.add(mathTopLayer_Node3);


	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new EqualSubGrammar(this.Id, nodes, this.rootNodeId);
	}
}
