package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Punctuations.SemicolonType;

public class AssignmentGrammar extends Grammar {


	public AssignmentGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public AssignmentGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;


		SingleNode identifier_Node1 = new SingleNode(new Identifier(""), false);

		StatementNode equalStatement_Node2=new StatementNode(()->GrammarLibrary.getParsingObjectsOfEqualSubGrammar(),false);
		
		SingleNode semicolon_Node3 = new SingleNode(new SemicolonType(), true);

		// -----------------------------------------------------------------
		root.addChild(identifier_Node1.Id);

		identifier_Node1.addChild(equalStatement_Node2.Id);
		
		equalStatement_Node2.addChild(semicolon_Node3.Id);

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(identifier_Node1);
		grammarNodes.add(equalStatement_Node2);
		grammarNodes.add(semicolon_Node3);

	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new AssignmentGrammar(this.Id, nodes, this.rootNodeId);
	}
}
