package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.TokenTypes.EndOfText;
import entities.TokenTypes.Punctuations.SemicolonType;


public class SemicolonGrammar extends Grammar {
	public SemicolonGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	
	
	public SemicolonGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		TerminalNode semicolon_Node1 = new TerminalNode(new SemicolonType(), false);


		// -----------------------------------------------------------------
		root.addChild(semicolon_Node1.Id);


		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(semicolon_Node1);

	}
	
	
	
	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new SemicolonGrammar(this.Id,nodes,this.rootNodeId);
	}

}
