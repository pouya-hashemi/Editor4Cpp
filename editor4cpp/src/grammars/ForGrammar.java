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

		
		SingleNode semicolon_Node4= new SingleNode(new SemicolonType(), false);
		
		StatementNode comparesionStatement_Node5 = new StatementNode(
				() -> GrammarLibrary.getParsingObjectsOfComparison(), false);
		
		SingleNode semicolon_Node6= new SingleNode(new SemicolonType(), false);
		
		StatementNode assignment_Node7 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfAssignment(false), false);

		SingleNode comma_Node8= new SingleNode(new CommaType(), false);
		
		SingleNode closeParenthesis_Node9 = new SingleNode(new CloseParenthesisType(), false, false);

		SingleNode openCurlyBracket_Node10 = new SingleNode(new OpenCurlyBracket(), false);

		StatementNode singleStatement_Node11 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfAll(),
				true);

		StatementNode multiStatement_Node12 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfAll(),
				false);

		SingleNode closeCurlyBracket_Node13 = new SingleNode(new CloseCurlyBracket(), true);

		

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
