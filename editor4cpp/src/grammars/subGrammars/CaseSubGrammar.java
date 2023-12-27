package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
import entities.TokenTypes.NumericLiteral;
import entities.TokenTypes.TextLiteral;
import entities.TokenTypes.Keywords.BreakKeyword;
import entities.TokenTypes.Keywords.CaseKeyword;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.ColonType;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
import entities.TokenTypes.Punctuations.SemicolonType;
import grammars.Grammar;

public class CaseSubGrammar extends Grammar {

	public CaseSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public CaseSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		SingleNode case_Node1 = new SingleNode(new CaseKeyword(), false);

		SingleNode textLiteral_Node2 = new SingleNode(new TextLiteral(), false);

		SingleNode numericLiteral_Node3 = new SingleNode(new NumericLiteral(), false);

		SingleNode colon_Node4 = new SingleNode(new ColonType(), false);

		StatementNode singleStatement_Node5 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfAll(), true);

		SingleNode openCurlyBracket_Node6 = new SingleNode(new OpenCurlyBracket(), false);
		
		StatementNode multiStatement_Node7 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfAll(), false);
		
		SingleNode singleBreak_Node8= new SingleNode(new BreakKeyword(), false);
		
		SingleNode singleSemicolon_Node9= new SingleNode(new SemicolonType(), true);
		
		SingleNode multiBreak_Node10= new SingleNode(new BreakKeyword(), false);
		
		SingleNode multiSemicolon_Node11= new SingleNode(new SemicolonType(), false);
		
		SingleNode closeCurlyBracket_Node12 = new SingleNode(new CloseCurlyBracket(), true);

		// -----------------------------------------------------------------
		root.addChild(case_Node1.Id);
		
		case_Node1.addChild(textLiteral_Node2.Id);
		case_Node1.addChild(numericLiteral_Node3.Id);
		
		textLiteral_Node2.addChild(textLiteral_Node2.Id);
		textLiteral_Node2.addChild(colon_Node4.Id);
		
		numericLiteral_Node3.addChild(colon_Node4.Id);

		colon_Node4.addChild(openCurlyBracket_Node6.Id);
		colon_Node4.addChild(singleStatement_Node5.Id);
		
		singleStatement_Node5.addChild(singleBreak_Node8.Id);
		
		singleBreak_Node8.addChild(singleSemicolon_Node9.Id);

		openCurlyBracket_Node6.addChild(multiStatement_Node7.Id);
		
		multiStatement_Node7.addChild(multiBreak_Node10.Id);
		multiStatement_Node7.addChild(multiStatement_Node7.Id);
		
		multiBreak_Node10.addChild(multiSemicolon_Node11.Id);
		
		multiSemicolon_Node11.addChild(closeCurlyBracket_Node12.Id);
		

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(case_Node1);
		grammarNodes.add(textLiteral_Node2);
		grammarNodes.add(numericLiteral_Node3);
		grammarNodes.add(colon_Node4);
		grammarNodes.add(singleStatement_Node5);
		grammarNodes.add(openCurlyBracket_Node6);
		grammarNodes.add(multiStatement_Node7);
		grammarNodes.add(singleBreak_Node8);
		grammarNodes.add(singleSemicolon_Node9);
		grammarNodes.add(multiBreak_Node10);
		grammarNodes.add(multiSemicolon_Node11);
		grammarNodes.add(closeCurlyBracket_Node12);

	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new CaseSubGrammar(this.Id, nodes, this.rootNodeId);
	}
}
