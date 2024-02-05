package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.TokenTypes.EndOfText;


public class EndOfTextGrammar extends Grammar {
	public EndOfTextGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	
	
	public EndOfTextGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		TerminalNode endOfText_Node1 = new TerminalNode(new EndOfText(), false);


		// -----------------------------------------------------------------
		root.addChild(endOfText_Node1.Id);


		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(endOfText_Node1);

	}
	
	
	
	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new EndOfTextGrammar(this.Id,nodes,this.rootNodeId);
	}

}
