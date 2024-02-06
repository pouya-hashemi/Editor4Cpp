package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
import entities.TokenTypes.ObjectIdentifier;
import entities.TokenTypes.UnknownType;
import entities.TokenTypes.Keywords.CatchKeyword;
import entities.TokenTypes.Keywords.TryKeyword;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.DotType;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
import entities.TokenTypes.Punctuations.OpenParenthesisType;

public class TryCatchGrammar extends Grammar {

	public TryCatchGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public TryCatchGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		TerminalNode try_Node1 = new TerminalNode(new TryKeyword(), false);

		TerminalNode openCurlyBracket_Node2 = new TerminalNode(new OpenCurlyBracket(), false);

		NonTerminalNode multiStatement_Node3 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfAll(), false);

		TerminalNode closeCurlyBracket_Node4 = new TerminalNode(new CloseCurlyBracket(), false);
		
		TerminalNode catch_Node5 = new TerminalNode(new CatchKeyword(), false);
		
		TerminalNode openParenthesis_Node6 = new TerminalNode(new OpenParenthesisType(), false);
		
		TerminalNode unknown_Node7 = new TerminalNode(new UnknownType(), false);
		
		TerminalNode objectIdentifier_Node8 = new TerminalNode(new ObjectIdentifier(), false);

		TerminalNode closeParenthesis_Node9 = new TerminalNode(new CloseParenthesisType(), false);
		
		TerminalNode firstDot_Node10 = new TerminalNode(new DotType(), false);
		TerminalNode secondDot_Node11 = new TerminalNode(new DotType(), false);
		TerminalNode thirdDot_Node12 = new TerminalNode(new DotType(), false);
		
		
		
		TerminalNode openCurlyBracket_Node13 = new TerminalNode(new OpenCurlyBracket(), false);
		
		NonTerminalNode multiStatement_Node15 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfAll(), false);
		
		TerminalNode closeCurlyBracket_Node14 = new TerminalNode(new CloseCurlyBracket(), true);
		

		// -----------------------------------------------------------------
		root.addChild(try_Node1.Id);
		
		try_Node1.addChild(openCurlyBracket_Node2.Id);
		openCurlyBracket_Node2.addChild(closeCurlyBracket_Node4.Id);
		openCurlyBracket_Node2.addChild(multiStatement_Node3.Id);
		
		multiStatement_Node3.addChild(closeCurlyBracket_Node4.Id);
		multiStatement_Node3.addChild(multiStatement_Node3.Id);
		
		closeCurlyBracket_Node4.addChild(catch_Node5.Id);

		catch_Node5.addChild(openParenthesis_Node6.Id);
		
		openParenthesis_Node6.addChild(firstDot_Node10.Id);
		openParenthesis_Node6.addChild(unknown_Node7.Id);
		
		unknown_Node7.addChild(objectIdentifier_Node8.Id);

		firstDot_Node10.addChild(secondDot_Node11.Id);
		
		secondDot_Node11.addChild(thirdDot_Node12.Id);
		thirdDot_Node12.addChild(closeParenthesis_Node9.Id);
		
		unknown_Node7.addChild(closeParenthesis_Node9.Id);
		
		
		
		closeParenthesis_Node9.addChild(openCurlyBracket_Node13.Id);
		
		openCurlyBracket_Node13.addChild(multiStatement_Node15.Id);
		
		multiStatement_Node15.addChild(closeCurlyBracket_Node14.Id);
		multiStatement_Node15.addChild(multiStatement_Node15.Id);
		
		closeCurlyBracket_Node14.addChild(catch_Node5.Id);
		
		
		
		

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(try_Node1);
		grammarNodes.add(openCurlyBracket_Node2);
		grammarNodes.add(multiStatement_Node3);
		grammarNodes.add(closeCurlyBracket_Node4);
		grammarNodes.add(catch_Node5);
		grammarNodes.add(openParenthesis_Node6);
		grammarNodes.add(unknown_Node7);
		grammarNodes.add(objectIdentifier_Node8);
		grammarNodes.add(closeParenthesis_Node9);
		grammarNodes.add(firstDot_Node10);
		grammarNodes.add(secondDot_Node11);
		grammarNodes.add(thirdDot_Node12);
		grammarNodes.add(openCurlyBracket_Node13);
		grammarNodes.add(closeCurlyBracket_Node14);
		grammarNodes.add(multiStatement_Node15);


	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new TryCatchGrammar(this.Id, nodes, this.rootNodeId);
	}
}
