package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.NonTerminalNode;
import entities.TerminalNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.NamespaceMemberType;
import entities.TokenTypes.NamespaceType;
import entities.TokenTypes.StreamExtractionType;
import entities.TokenTypes.StreamInsertionType;
import entities.TokenTypes.Punctuations.ColonType;
import entities.TokenTypes.Punctuations.SemicolonType;

public class NamespaceSubGrammar extends Grammar {
	public NamespaceSubGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	
	
	public NamespaceSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		TerminalNode namespace_Node1 = new TerminalNode(new NamespaceType(), false);

		TerminalNode firstColon_Node2 = new TerminalNode(new ColonType(), false);
		
		TerminalNode secondColon_Node3 = new TerminalNode(new ColonType(), false);
		
		TerminalNode namespaceMember_Node4 = new TerminalNode(new NamespaceMemberType(), true);
		
		NonTerminalNode functionStatement_Node5 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfFunctionCallSubGrammar(), true);
		
		TerminalNode streamInsertion_Node6 = new TerminalNode(new StreamInsertionType(), false);
		
		TerminalNode streamExtraction_Node7 = new TerminalNode(new StreamExtractionType(), false);
		
		NonTerminalNode mathTopLayer_Node8 = new NonTerminalNode(
				() -> GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(), true);
		
		TerminalNode identifier_Node9 = new TerminalNode(new Identifier(""), true);
		
		NonTerminalNode vector_Node10 = new NonTerminalNode(
				() -> GrammarLibrary.getParsingObjectsOfVectorGrammar(false), true);



		

		// -----------------------------------------------------------------
		root.addChild(namespace_Node1.Id);
		namespace_Node1.addChild(firstColon_Node2.Id);
		
		firstColon_Node2.addChild(secondColon_Node3.Id);
		
		secondColon_Node3.addChild(namespace_Node1.Id);
		secondColon_Node3.addChild(namespaceMember_Node4.Id);
		secondColon_Node3.addChild(functionStatement_Node5.Id);
		secondColon_Node3.addChild(vector_Node10.Id);

		
		
		namespaceMember_Node4.addChild(streamInsertion_Node6.Id);
		namespaceMember_Node4.addChild(streamExtraction_Node7.Id);
		
		streamInsertion_Node6.addChild(namespace_Node1.Id);
		streamInsertion_Node6.addChild(mathTopLayer_Node8.Id);
		
		mathTopLayer_Node8.addChild(streamInsertion_Node6.Id);
		
		streamExtraction_Node7.addChild(identifier_Node9.Id);
		
		identifier_Node9.addChild(streamExtraction_Node7.Id);

		
		

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(namespace_Node1);
		grammarNodes.add(firstColon_Node2);
		grammarNodes.add(secondColon_Node3);
		grammarNodes.add(namespaceMember_Node4);
		grammarNodes.add(functionStatement_Node5);
		grammarNodes.add(streamInsertion_Node6);
		grammarNodes.add(streamExtraction_Node7);
		grammarNodes.add(mathTopLayer_Node8);
		grammarNodes.add(identifier_Node9);
		grammarNodes.add(vector_Node10);

		
	}
	
	
	
	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new NamespaceSubGrammar(this.Id,nodes,this.rootNodeId);
	}
}
