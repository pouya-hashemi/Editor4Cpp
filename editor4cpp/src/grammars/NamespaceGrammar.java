package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.NonTerminalNode;
import entities.TerminalNode;
import entities.TokenTypes.DataType;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.NamespaceMemberType;
import entities.TokenTypes.NamespaceType;
import entities.TokenTypes.NumericLiteral;
import entities.TokenTypes.StreamExtractionType;
import entities.TokenTypes.StreamInsertionType;
import entities.TokenTypes.Punctuations.CloseBracket;
import entities.TokenTypes.Punctuations.ColonType;
import entities.TokenTypes.Punctuations.EqualType;
import entities.TokenTypes.Punctuations.OpenBracket;
import entities.TokenTypes.Punctuations.SemicolonType;

public class NamespaceGrammar extends Grammar {
	public NamespaceGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	
	
	public NamespaceGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		NonTerminalNode namespaceSubGrammar_Node1 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfNamespaceSubGrammar(), false);

		

		TerminalNode semicolon_Node2 = new TerminalNode(new SemicolonType(), true);

		

		// -----------------------------------------------------------------
		root.addChild(namespaceSubGrammar_Node1.Id);
		namespaceSubGrammar_Node1.addChild(semicolon_Node2.Id);
		
		
		

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(namespaceSubGrammar_Node1);
		grammarNodes.add(semicolon_Node2);


		
	}
	
	
	
	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new NamespaceGrammar(this.Id,nodes,this.rootNodeId);
	}
}