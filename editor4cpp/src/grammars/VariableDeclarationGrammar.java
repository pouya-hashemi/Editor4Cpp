package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
import entities.TokenTypes.DataType;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Literal;



public class VariableDeclarationGrammar extends Grammar {
	public VariableDeclarationGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	public VariableDeclarationGrammar(DataType dataType, Identifier identifier, Literal literal) {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		
		
		SingleNode dataType_Node1 = new SingleNode(dataType,false);


		StatementNode assignment_Node4=new StatementNode(()->GrammarLibrary.getParsingObjectsOfAssignment(dataType),true);
		

		root.addChild(dataType_Node1.Id);
		
		dataType_Node1.addChild(assignment_Node4.Id);
		
		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(dataType_Node1);
		grammarNodes.add(assignment_Node4);
		
		
	}
//	
	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new VariableDeclarationGrammar(this.Id,nodes,this.rootNodeId);
	}
}
