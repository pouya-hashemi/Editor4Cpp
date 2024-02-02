package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
import entities.TokenTypes.Keywords.ForKeyword;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.CommaType;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import entities.TokenTypes.Punctuations.SemicolonType;

public class ForGrammar extends Grammar {
	public ForGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public ForGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;

		TerminalNode for_Node1 = new TerminalNode(new ForKeyword(), false);

		TerminalNode openParenthesis_Node2 = new TerminalNode(new OpenParenthesisType(), false);

		NonTerminalNode assignment_Node3 = new NonTerminalNode(() -> {
			var list = GrammarLibrary.getParsingObjectsOfAssignment(false);
			list.addAll(GrammarLibrary.getParsingObjectsOfVariableDeclaration(false));
			return list;
		}, false);

		
		TerminalNode semicolon_Node4= new TerminalNode(new SemicolonType(), false);
		
		NonTerminalNode comparesionStatement_Node5 = new NonTerminalNode(
				() -> GrammarLibrary.getParsingObjectsOfComparison(), false);
		
		TerminalNode semicolon_Node6= new TerminalNode(new SemicolonType(), false);
		
		NonTerminalNode assignment_Node7 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfAssignment(false), false);

		TerminalNode comma_Node8= new TerminalNode(new CommaType(), false);
		
		TerminalNode closeParenthesis_Node9 = new TerminalNode(new CloseParenthesisType(), false);

		TerminalNode openCurlyBracket_Node10 = new TerminalNode(new OpenCurlyBracket(), false);

		NonTerminalNode singleStatement_Node11 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfAll(),
				true);

		NonTerminalNode multiStatement_Node12 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfAll(),
				false);

		TerminalNode closeCurlyBracket_Node13 = new TerminalNode(new CloseCurlyBracket(), true);

		

		// -----------------------------------------------------------------
		root.addChild(for_Node1.Id);
		for_Node1.addChild(openParenthesis_Node2.Id);
		openParenthesis_Node2.addChild(assignment_Node3.Id);
		
		assignment_Node3.addChild(semicolon_Node4.Id);
		
		semicolon_Node4.addChild(comparesionStatement_Node5.Id);
		comparesionStatement_Node5.addChild(semicolon_Node6.Id);
		semicolon_Node6.addChild(assignment_Node7.Id);
		
		
		assignment_Node7.addChild(comma_Node8.Id);
		assignment_Node7.addChild(closeParenthesis_Node9.Id);
		
		comma_Node8.addChild(assignment_Node7.Id);
		
		
		closeParenthesis_Node9.addChild(openCurlyBracket_Node10.Id);
		closeParenthesis_Node9.addChild(singleStatement_Node11.Id);
		
		openCurlyBracket_Node10.addChild(multiStatement_Node12.Id);
		
		multiStatement_Node12.addChild(closeCurlyBracket_Node13.Id);
		
		multiStatement_Node12.addChild(multiStatement_Node12.Id);
		
		


		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(for_Node1);
		grammarNodes.add(openParenthesis_Node2);
		grammarNodes.add(assignment_Node3);
		grammarNodes.add(semicolon_Node4);
		grammarNodes.add(comparesionStatement_Node5);
		grammarNodes.add(semicolon_Node6);
		grammarNodes.add(assignment_Node7);
		grammarNodes.add(comma_Node8);
		grammarNodes.add(closeParenthesis_Node9);
		grammarNodes.add(openCurlyBracket_Node10);
		grammarNodes.add(singleStatement_Node11);
		grammarNodes.add(multiStatement_Node12);
		grammarNodes.add(closeCurlyBracket_Node13);

	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new ForGrammar(this.Id, nodes, this.rootNodeId);
	}
}
