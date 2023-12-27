package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Keywords.BreakKeyword;
import entities.TokenTypes.Keywords.DefaultKeyword;
import entities.TokenTypes.Keywords.SwitchKeyword;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.ColonType;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import entities.TokenTypes.Punctuations.SemicolonType;


public class SwitchGrammar extends Grammar {

	public SwitchGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public SwitchGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		SingleNode switch_Node1 = new SingleNode(new SwitchKeyword(), false);

		SingleNode openParenthesis_Node2 = new SingleNode(new OpenParenthesisType(), false);

		SingleNode identifier_Node3 = new SingleNode(new Identifier(""), false);

		SingleNode closeParenthesis_Node4 = new SingleNode(new CloseParenthesisType(), false);
		
		SingleNode openCurlyBracket_Node5 = new SingleNode(new OpenCurlyBracket(), false);

		StatementNode caseStatement_Node6 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfCaseSubGrammar(), false);

		SingleNode default_Node7 = new SingleNode(new DefaultKeyword(), false);
		
		SingleNode colon_Node8= new SingleNode(new ColonType(), false);
		
		StatementNode singleStatement_Node9 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfAll(), false);
		
		SingleNode openCurlyBracket_Node10 = new SingleNode(new OpenCurlyBracket(), false);
		
		StatementNode multiStatement_Node11 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfAll(), false);
		
		SingleNode closeCurlyBracket_Node12 = new SingleNode(new CloseCurlyBracket(), false);
		SingleNode closeCurlyBracket_Node13 = new SingleNode(new CloseCurlyBracket(), true);
		
		SingleNode singleBreak_Node14 = new SingleNode(new BreakKeyword(), false);
		
		SingleNode singleSemicolon_Node15 = new SingleNode(new SemicolonType(), false);
		
		SingleNode multiBreak_Node16 = new SingleNode(new BreakKeyword(), false);
		
		SingleNode multiSemicolon_Node17 = new SingleNode(new SemicolonType(), false);

		// -----------------------------------------------------------------
		root.addChild(switch_Node1.Id);
		
		switch_Node1.addChild(openParenthesis_Node2.Id);
		openParenthesis_Node2.addChild(identifier_Node3.Id);
		
		identifier_Node3.addChild(closeParenthesis_Node4.Id);
		closeParenthesis_Node4.addChild(openCurlyBracket_Node5.Id);
		
		openCurlyBracket_Node5.addChild(caseStatement_Node6.Id);

		caseStatement_Node6.addChild(default_Node7.Id);
		caseStatement_Node6.addChild(caseStatement_Node6.Id);
		
		default_Node7.addChild(colon_Node8.Id);
		colon_Node8.addChild(singleBreak_Node14.Id);
		colon_Node8.addChild(openCurlyBracket_Node10.Id);
		colon_Node8.addChild(singleStatement_Node9.Id);

		
		openCurlyBracket_Node10.addChild(multiStatement_Node11.Id);
		
		multiStatement_Node11.addChild(closeCurlyBracket_Node12.Id);
		multiStatement_Node11.addChild(multiStatement_Node11.Id);
		
		
		closeCurlyBracket_Node12.addChild(closeCurlyBracket_Node13.Id);
		
		singleStatement_Node9.addChild(closeCurlyBracket_Node13.Id);
		
		singleStatement_Node9.addChild(singleBreak_Node14.Id);
		singleBreak_Node14.addChild(singleSemicolon_Node15.Id);
		
		singleSemicolon_Node15.addChild(closeCurlyBracket_Node13.Id);
		
		multiStatement_Node11.addChild(multiBreak_Node16.Id);
		multiBreak_Node16.addChild(multiSemicolon_Node17.Id);
		multiSemicolon_Node17.addChild(closeCurlyBracket_Node12.Id);
		
		

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(switch_Node1);
		grammarNodes.add(openParenthesis_Node2);
		grammarNodes.add(identifier_Node3);
		grammarNodes.add(closeParenthesis_Node4);
		grammarNodes.add(openCurlyBracket_Node5);
		grammarNodes.add(caseStatement_Node6);
		grammarNodes.add(default_Node7);
		grammarNodes.add(colon_Node8);
		grammarNodes.add(singleStatement_Node9);
		grammarNodes.add(openCurlyBracket_Node10);
		grammarNodes.add(multiStatement_Node11);
		grammarNodes.add(closeCurlyBracket_Node12);
		grammarNodes.add(closeCurlyBracket_Node13);
		grammarNodes.add(singleBreak_Node14);
		grammarNodes.add(singleSemicolon_Node15);
		grammarNodes.add(multiBreak_Node16);
		grammarNodes.add(multiSemicolon_Node17);

	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new SwitchGrammar(this.Id, nodes, this.rootNodeId);
	}
}
