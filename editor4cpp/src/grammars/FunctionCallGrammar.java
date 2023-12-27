package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
import entities.TokenTypes.Punctuations.SemicolonType;


public class FunctionCallGrammar extends Grammar {

	public FunctionCallGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public FunctionCallGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		StatementNode functionSub_Node1 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfFunctionCallSubGrammar(), false);

		SingleNode semicolon_Node2 = new SingleNode(new SemicolonType(), true);

		

		
		

		// -----------------------------------------------------------------
		root.addChild(functionSub_Node1.Id);
		
		functionSub_Node1.addChild(semicolon_Node2.Id);	
		

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(functionSub_Node1);
		grammarNodes.add(semicolon_Node2);


	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new FunctionCallGrammar(this.Id, nodes, this.rootNodeId);
	}
}

