package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.NumericLiteral;
import entities.TokenTypes.Operations.SingleOperandOperator;
import entities.TokenTypes.Punctuations.CloseBracket;
import entities.TokenTypes.Punctuations.OpenBracket;
import entities.TokenTypes.Punctuations.SemicolonType;

public class AssignmentGrammar extends Grammar {

	public AssignmentGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public AssignmentGrammar() {
		super();

		initGrammar(true);

	}

	public AssignmentGrammar(boolean hasSemicolon) {
		super();

		initGrammar(hasSemicolon);

	}

	private void initGrammar(boolean hasSemicolon) {
		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;

		SingleNode identifier_Node1 = new SingleNode(new Identifier(""), false);

		StatementNode equalStatement_Node2 = new StatementNode(
				() -> GrammarLibrary.getParsingObjectsOfEqualSubGrammar(), true);

		SingleNode semicolon_Node3 = new SingleNode(new SemicolonType(), false);

		SingleNode firstSingleOperator_Node4 = new SingleNode(new SingleOperandOperator(), false);

		SingleNode secondSingleOperator_Node5 = new SingleNode(new SingleOperandOperator(), true);

		SingleNode firstPreSingleOperator_Node6 = new SingleNode(new SingleOperandOperator(), false);

		SingleNode secondPreSingleOperator_Node7 = new SingleNode(new SingleOperandOperator(), false);

		SingleNode identifier_Node8 = new SingleNode(new Identifier(""), true);

		SingleNode openBracket_Node9 = new SingleNode(new OpenBracket(), false);

		SingleNode numericLiteral_Node10 = new SingleNode(new NumericLiteral(), false);

		SingleNode closeBracket_Node11 = new SingleNode(new CloseBracket(), false);

		SingleNode openBracket_Node12 = new SingleNode(new OpenBracket(), false);

		SingleNode numericLiteral_Node13 = new SingleNode(new NumericLiteral(), false);

		SingleNode closeBracket_Node14 = new SingleNode(new CloseBracket(), true);

		// -----------------------------------------------------------------
		root.addChild(identifier_Node1.Id);
		root.addChild(firstPreSingleOperator_Node6.Id);

		firstPreSingleOperator_Node6.addChild(secondPreSingleOperator_Node7.Id);

		secondPreSingleOperator_Node7.addChild(identifier_Node8.Id);

		
		identifier_Node1.addChild(openBracket_Node9.Id);
		identifier_Node1.addChild(firstSingleOperator_Node4.Id);
		identifier_Node1.addChild(equalStatement_Node2.Id);

		

		openBracket_Node9.addChild(numericLiteral_Node10.Id);

		numericLiteral_Node10.addChild(closeBracket_Node11.Id);

		closeBracket_Node11.addChild(equalStatement_Node2.Id);
		closeBracket_Node11.addChild(firstSingleOperator_Node4.Id);
		closeBracket_Node11.addChild(openBracket_Node9.Id);

		

		firstSingleOperator_Node4.addChild(secondSingleOperator_Node5.Id);

		identifier_Node8.addChild(openBracket_Node12.Id);
		
		openBracket_Node12.addChild(numericLiteral_Node13.Id);
		
		numericLiteral_Node13.addChild(closeBracket_Node14.Id);
		
		closeBracket_Node14.addChild(openBracket_Node12.Id);
		
		
		
		if (hasSemicolon) {
			equalStatement_Node2.addChild(semicolon_Node3.Id);
			secondSingleOperator_Node5.addChild(semicolon_Node3.Id);
			identifier_Node8.addChild(semicolon_Node3.Id);
			closeBracket_Node14.addChild(semicolon_Node3.Id);
			equalStatement_Node2.canBeEnd = false;
			secondSingleOperator_Node5.canBeEnd = false;
			identifier_Node8.canBeEnd = false;
			closeBracket_Node14.canBeEnd=false;
			semicolon_Node3.canBeEnd = true;

		}

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(identifier_Node1);
		grammarNodes.add(equalStatement_Node2);
		grammarNodes.add(semicolon_Node3);
		grammarNodes.add(firstSingleOperator_Node4);
		grammarNodes.add(secondSingleOperator_Node5);
		grammarNodes.add(firstPreSingleOperator_Node6);
		grammarNodes.add(secondPreSingleOperator_Node7);
		grammarNodes.add(identifier_Node8);
		grammarNodes.add(openBracket_Node9);
		grammarNodes.add(numericLiteral_Node10);
		grammarNodes.add(closeBracket_Node11);
		grammarNodes.add(openBracket_Node12);
		grammarNodes.add(numericLiteral_Node13);
		grammarNodes.add(closeBracket_Node14);
	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new AssignmentGrammar(this.Id, nodes, this.rootNodeId);
	}
}
