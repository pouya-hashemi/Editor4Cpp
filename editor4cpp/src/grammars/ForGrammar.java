package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
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

		SingleNode for_Node1 = new SingleNode(new ForKeyword(), false);

		SingleNode openParenthesis_Node2 = new SingleNode(new OpenParenthesisType(), false);

		StatementNode assignment_Node3 = new StatementNode(() -> {
			var list = GrammarLibrary.getParsingObjectsOfAssignment(false);
			list.addAll(GrammarLibrary.getParsingObjectsOfVariableDeclaration(false));
			return list;
		}, false);

		
		SingleNode semicolon_Node5= new SingleNode(new SemicolonType(), false);
		
		StatementNode comparesionStatement_Node6 = new StatementNode(
				() -> GrammarLibrary.getParsingObjectsOfComparison(), false);
		
		SingleNode semicolon_Node7= new SingleNode(new SemicolonType(), false);
		
		StatementNode assignment_Node8 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfAssignment(false), false);

		SingleNode comma_Node9= new SingleNode(new CommaType(), false);
		
		SingleNode closeParenthesis_Node10 = new SingleNode(new CloseParenthesisType(), false, false);

		SingleNode openCurlyBracket_Node11 = new SingleNode(new OpenCurlyBracket(), false);

		StatementNode singleStatement_Node12 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfAll(),
				true);

		StatementNode multiStatement_Node13 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfAll(),
				false);

		SingleNode closeCurlyBracket_Node14 = new SingleNode(new CloseCurlyBracket(), true);

		

		// -----------------------------------------------------------------
		root.addChild(for_Node1.Id);
		for_Node1.addChild(openParenthesis_Node2.Id);
		openParenthesis_Node2.addChild(assignment_Node3.Id);
		
		assignment_Node3.addChild(semicolon_Node5.Id);
		
		semicolon_Node5.addChild(comparesionStatement_Node6.Id);
		comparesionStatement_Node6.addChild(semicolon_Node7.Id);
		semicolon_Node7.addChild(assignment_Node8.Id);
		
		
		assignment_Node8.addChild(comma_Node9.Id);
		assignment_Node8.addChild(closeParenthesis_Node10.Id);
		
		comma_Node9.addChild(assignment_Node8.Id);
		
		
		closeParenthesis_Node10.addChild(openCurlyBracket_Node11.Id);
		
		closeParenthesis_Node10.addChild(singleStatement_Node12.Id);
		
		openCurlyBracket_Node11.addChild(multiStatement_Node13.Id);
		
		multiStatement_Node13.addChild(multiStatement_Node13.Id);
		
		
		multiStatement_Node13.addChild(closeCurlyBracket_Node14.Id);

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(for_Node1);
		grammarNodes.add(openParenthesis_Node2);
		grammarNodes.add(assignment_Node3);
		grammarNodes.add(semicolon_Node5);
		grammarNodes.add(comparesionStatement_Node6);
		grammarNodes.add(semicolon_Node7);
		grammarNodes.add(assignment_Node8);
		grammarNodes.add(comma_Node9);
		grammarNodes.add(closeParenthesis_Node10);
		grammarNodes.add(openCurlyBracket_Node11);
		grammarNodes.add(singleStatement_Node12);
		grammarNodes.add(multiStatement_Node13);
		grammarNodes.add(closeCurlyBracket_Node14);

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
