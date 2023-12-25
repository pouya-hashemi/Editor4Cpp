package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Operations.SingleOperandOperator;
import entities.TokenTypes.Punctuations.SemicolonType;

public class AssignmentGrammar extends Grammar {


	public AssignmentGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public AssignmentGrammar() {
		super();

		initGrammar(true);

	}
	public AssignmentGrammar(boolean hasSemicolon) {
		super();

		initGrammar(hasSemicolon);

	}
	private void initGrammar(boolean hasSemicolon) {
		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;


		SingleNode identifier_Node1 = new SingleNode(new Identifier(""), false);

		StatementNode equalStatement_Node2=new StatementNode(()->GrammarLibrary.getParsingObjectsOfEqualSubGrammar(),true);
		
		
		SingleNode semicolon_Node3 = new SingleNode(new SemicolonType(), false);
		
		SingleNode firstSingleOperator_Node4 = new SingleNode(new SingleOperandOperator(), false);
		
		SingleNode secondSingleOperator_Node5 = new SingleNode(new SingleOperandOperator(), true);

		// -----------------------------------------------------------------
		root.addChild(identifier_Node1.Id);

		identifier_Node1.addChild(equalStatement_Node2.Id);
		
		identifier_Node1.addChild(firstSingleOperator_Node4.Id);
		
		firstSingleOperator_Node4.addChild(secondSingleOperator_Node5.Id);
		
		if(hasSemicolon) {
			equalStatement_Node2.addChild(semicolon_Node3.Id);
			secondSingleOperator_Node5.addChild(semicolon_Node3.Id);
			equalStatement_Node2.canBeEnd=false;
			secondSingleOperator_Node5.canBeEnd=false;
			semicolon_Node3.canBeEnd=true;

		}
		
	
		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(identifier_Node1);
		grammarNodes.add(equalStatement_Node2);
		grammarNodes.add(semicolon_Node3);
		grammarNodes.add(firstSingleOperator_Node4);
		grammarNodes.add(secondSingleOperator_Node5);
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
