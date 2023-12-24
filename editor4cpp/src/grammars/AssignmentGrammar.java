package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.GrammarNode;
import entities.SingleNode;
import entities.TokenTypes.DataType;
import entities.TokenTypes.FloatingPointLiteral;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Literal;
import entities.TokenTypes.NumericLiteral;
import entities.TokenTypes.TextLiteral;
import entities.TokenTypes.Literals.BoolLiteral;
import entities.TokenTypes.Operations.DoubleOperandOperator;
import entities.TokenTypes.Operations.SingleOperandOperator;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.CommaType;
import entities.TokenTypes.Punctuations.EqualType;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import entities.TokenTypes.Punctuations.SemicolonType;

public class AssignmentGrammar extends Grammar {
	public DataType dataType;

	public AssignmentGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId,DataType dataType) {
		super(id, grammarNodes, rootNodeId);
		this.dataType = dataType;
	}

	public AssignmentGrammar(DataType dataType, Identifier identifier, Literal literal) {
		super();
		this.dataType = dataType;
		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;

		SingleNode identifier_Node2 = new SingleNode(identifier, false);

		SingleNode semicolon_Node3 = new SingleNode(new SemicolonType(), true, true);

		SingleNode comma_Node15 = new SingleNode(new CommaType(), false);

		SingleNode equal_Node4 = new SingleNode(new EqualType(), false);

		SingleNode openParenthesis_Node5 = new SingleNode(new OpenParenthesisType(), false);

		SingleNode closeParenthesis_Node9 = new SingleNode(new CloseParenthesisType(), false);

		SingleNode literal_Node7 = new SingleNode(literal, false);

		SingleNode doubleOperandOperation_Node8 = new SingleNode(new DoubleOperandOperator(), false);

		SingleNode identifierAssignee_Node6 = new SingleNode(identifier, false);

		SingleNode DoubleOperandOperation_Node12 = new SingleNode(new DoubleOperandOperator(), false);

		SingleNode firstSingleOperandOperation_Node11 = new SingleNode(new SingleOperandOperator(), false);

		SingleNode secondSingleOperandOperation_Node10 = new SingleNode(new SingleOperandOperator(), false);
		SingleNode Dot_Node13 = new SingleNode(new FloatingPointLiteral(), false);
		SingleNode floatingPoint_Node14 = new SingleNode(new FloatingPointLiteral(), false);

		root.addChild(identifier_Node2.Id);

		// ----------------------

		identifier_Node2.addChild(semicolon_Node3.Id);// E2
		// --------------------

		identifier_Node2.addChild(comma_Node15.Id);// E3

		comma_Node15.addChild(identifier_Node2.Id);// E4
		// ------------------

		identifier_Node2.addChild(equal_Node4.Id);// E5

		identifier_Node2.addChild(doubleOperandOperation_Node8.Id);// E6

		doubleOperandOperation_Node8.addChild(equal_Node4.Id);// E7

		equal_Node4.addChild(openParenthesis_Node5.Id);// E8

		equal_Node4.addChild(literal_Node7.Id);// E9

		equal_Node4.addChild(identifierAssignee_Node6.Id);// E10

		openParenthesis_Node5.addChild(identifierAssignee_Node6.Id);// E11

		identifierAssignee_Node6.addChild(semicolon_Node3.Id);// E12

		openParenthesis_Node5.addChild(literal_Node7.Id);// E13

		identifierAssignee_Node6.addChild(closeParenthesis_Node9.Id);// E14

		identifierAssignee_Node6.addChild(comma_Node15.Id);// E18

		identifierAssignee_Node6.addChild(DoubleOperandOperation_Node12.Id);// E26

		identifierAssignee_Node6.addChild(firstSingleOperandOperation_Node11.Id);// E27

		DoubleOperandOperation_Node12.addChild(identifierAssignee_Node6.Id);// E28

		DoubleOperandOperation_Node12.addChild(literal_Node7.Id);// E29

		closeParenthesis_Node9.addChild(DoubleOperandOperation_Node12.Id);// E31

		closeParenthesis_Node9.addChild(closeParenthesis_Node9.Id);// E32

		closeParenthesis_Node9.addChild(firstSingleOperandOperation_Node11.Id);// E33

		closeParenthesis_Node9.addChild(semicolon_Node3.Id);// E36

		secondSingleOperandOperation_Node10.addChild(closeParenthesis_Node9.Id);// E37

		secondSingleOperandOperation_Node10.addChild(comma_Node15.Id);// E41

		secondSingleOperandOperation_Node10.addChild(semicolon_Node3.Id);// E43

		openParenthesis_Node5.addChild(openParenthesis_Node5.Id);// E44

		DoubleOperandOperation_Node12.addChild(openParenthesis_Node5.Id);// E45

		firstSingleOperandOperation_Node11.addChild(secondSingleOperandOperation_Node10.Id);// E46

		closeParenthesis_Node9.addChild(comma_Node15.Id);// E34

		if (literal instanceof TextLiteral) {
			literal_Node7.addChild(literal_Node7.Id);// E15
			literal_Node7.addChild(closeParenthesis_Node9.Id);// E16
			literal_Node7.addChild(semicolon_Node3.Id);// E25
			literal_Node7.addChild(comma_Node15.Id);// E17
		}
		if (literal instanceof BoolLiteral) {

			literal_Node7.addChild(closeParenthesis_Node9.Id);// E49
			literal_Node7.addChild(semicolon_Node3.Id);// E48
			literal_Node7.addChild(comma_Node15.Id);// E47
		}

		if(this.Id==10)
		{
			int a=2;
		}
		if (literal instanceof FloatingPointLiteral) {
			
			
			literal_Node7.addChild(Dot_Node13.Id);// E19
			Dot_Node13.addChild(floatingPoint_Node14.Id);// 30
			floatingPoint_Node14.addChild(comma_Node15.Id);// E42
			floatingPoint_Node14.addChild(semicolon_Node3.Id);// E40

			floatingPoint_Node14.addChild(DoubleOperandOperation_Node12.Id);// E38
			floatingPoint_Node14.addChild(firstSingleOperandOperation_Node11.Id);// E39
			floatingPoint_Node14.addChild(closeParenthesis_Node9.Id);// E35
		}
		if (literal instanceof NumericLiteral) {
			literal_Node7.addChild(comma_Node15.Id);// E20
			literal_Node7.addChild(semicolon_Node3.Id);// E21
			literal_Node7.addChild(DoubleOperandOperation_Node12.Id);// E23
			literal_Node7.addChild(firstSingleOperandOperation_Node11.Id);// E24
			literal_Node7.addChild(closeParenthesis_Node9.Id);// E22
		}

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(identifier_Node2);
		grammarNodes.add(semicolon_Node3);
		grammarNodes.add(equal_Node4);
		grammarNodes.add(openParenthesis_Node5);
		grammarNodes.add(identifierAssignee_Node6);
		grammarNodes.add(literal_Node7);
		grammarNodes.add(doubleOperandOperation_Node8);
		grammarNodes.add(closeParenthesis_Node9);
		grammarNodes.add(secondSingleOperandOperation_Node10);
		grammarNodes.add(firstSingleOperandOperation_Node11);
		grammarNodes.add(DoubleOperandOperation_Node12);
		grammarNodes.add(Dot_Node13);
		grammarNodes.add(floatingPoint_Node14);
		grammarNodes.add(comma_Node15);

	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new AssignmentGrammar(this.Id, nodes, this.rootNodeId,this.dataType);
	}
}
