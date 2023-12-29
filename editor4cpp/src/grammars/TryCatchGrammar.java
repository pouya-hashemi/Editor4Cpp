package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
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
		SingleNode try_Node1 = new SingleNode(new TryKeyword(), false);

		SingleNode openCurlyBracket_Node2 = new SingleNode(new OpenCurlyBracket(), false);

		StatementNode multiStatement_Node3 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfAll(), false);

		SingleNode closeCurlyBracket_Node4 = new SingleNode(new CloseCurlyBracket(), false);
		
		SingleNode catch_Node5 = new SingleNode(new CatchKeyword(), false);
		
		SingleNode openParenthesis_Node6 = new SingleNode(new OpenParenthesisType(), false);
		
		SingleNode unknown_Node7 = new SingleNode(new UnknownType(), false);
		
		SingleNode objectIdentifier_Node8 = new SingleNode(new ObjectIdentifier(), false);

		SingleNode closeParenthesis_Node9 = new SingleNode(new CloseParenthesisType(), false);
		
		SingleNode firstDot_Node10 = new SingleNode(new DotType(), false);
		SingleNode secondDot_Node11 = new SingleNode(new DotType(), false);
		SingleNode thirdDot_Node12 = new SingleNode(new DotType(), false);
		
		
		
		SingleNode openCurlyBracket_Node13 = new SingleNode(new OpenCurlyBracket(), false);
		
		StatementNode multiStatement_Node15 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfAll(), false);
		
		SingleNode closeCurlyBracket_Node14 = new SingleNode(new CloseCurlyBracket(), true);
		

		// -----------------------------------------------------------------
		root.addChild(try_Node1.Id);
		
		try_Node1.addChild(openCurlyBracket_Node2.Id);
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
