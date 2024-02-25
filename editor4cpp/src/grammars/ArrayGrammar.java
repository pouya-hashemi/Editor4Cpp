package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.NumericLiteral;
import entities.TokenTypes.DataTypes.FundamentalDataType;
import entities.TokenTypes.Punctuations.CloseBracket;
import entities.TokenTypes.Punctuations.EqualType;
import entities.TokenTypes.Punctuations.OpenBracket;
import entities.TokenTypes.Punctuations.SemicolonType;

public class ArrayGrammar extends Grammar {
	public ArrayGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	
	
	public ArrayGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		TerminalNode dataType_Node1 = new TerminalNode(new FundamentalDataType(""), false);

		TerminalNode identifier_Node2 = new TerminalNode(new Identifier(""), false);
		
		TerminalNode openBracketForOmit_Node3 = new TerminalNode(new OpenBracket(), false);
		
		TerminalNode openBracket_Node4 = new TerminalNode(new OpenBracket(), false);
		
		TerminalNode numLiteralForOmit_Node5 = new TerminalNode(new NumericLiteral(), false);
		
		TerminalNode closeBracketForOmit_Node6 = new TerminalNode(new CloseBracket(), false);
		
		TerminalNode closeBracket_Node7 = new TerminalNode(new CloseBracket(), false);
		
		TerminalNode openBracket_Node8 = new TerminalNode(new OpenBracket(), false);
		
		TerminalNode numLiteral_Node9 = new TerminalNode(new NumericLiteral(), false);
		
		TerminalNode closeBracket_Node10 = new TerminalNode(new CloseBracket(), false);
		
		TerminalNode Equal_Node11 = new TerminalNode(new EqualType(), false);

		NonTerminalNode arrayValue_Node12 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfArrayValueSubGrammar(),false);

		TerminalNode semicolon_Node13 = new TerminalNode(new SemicolonType(), true);

		

		// -----------------------------------------------------------------
		root.addChild(dataType_Node1.Id);
		dataType_Node1.addChild(identifier_Node2.Id);
		
		identifier_Node2.addChild(openBracket_Node4.Id);
		
		openBracketForOmit_Node3.addChild(numLiteralForOmit_Node5.Id);
		
		numLiteralForOmit_Node5.addChild(closeBracketForOmit_Node6.Id);
		
		closeBracketForOmit_Node6.addChild(openBracketForOmit_Node3.Id);
		closeBracketForOmit_Node6.addChild(semicolon_Node13.Id);
		closeBracketForOmit_Node6.addChild(Equal_Node11.Id);
		
		openBracket_Node4.addChild(closeBracket_Node7.Id);
		openBracket_Node4.addChild(numLiteralForOmit_Node5.Id);
		
		closeBracket_Node7.addChild(Equal_Node11.Id);
		closeBracket_Node7.addChild(openBracket_Node8.Id);
		
		openBracket_Node8.addChild(numLiteral_Node9.Id);
		numLiteral_Node9.addChild(closeBracket_Node10.Id);
		
		closeBracket_Node10.addChild(openBracket_Node8.Id);
		closeBracket_Node10.addChild(Equal_Node11.Id);
		
		Equal_Node11.addChild(arrayValue_Node12.Id);
		arrayValue_Node12.addChild(semicolon_Node13.Id);
		

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(dataType_Node1);
		grammarNodes.add(identifier_Node2);
		grammarNodes.add(openBracketForOmit_Node3);
		grammarNodes.add(openBracket_Node4);
		grammarNodes.add(numLiteralForOmit_Node5);
		grammarNodes.add(closeBracketForOmit_Node6);
		grammarNodes.add(closeBracket_Node7);
		grammarNodes.add(openBracket_Node8);
		grammarNodes.add(numLiteral_Node9);
		grammarNodes.add(closeBracket_Node10);
		grammarNodes.add(Equal_Node11);
		grammarNodes.add(arrayValue_Node12);
		grammarNodes.add(semicolon_Node13);
		
	}
	
	
	
	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new ArrayGrammar(this.Id,nodes,this.rootNodeId);
	}
}

