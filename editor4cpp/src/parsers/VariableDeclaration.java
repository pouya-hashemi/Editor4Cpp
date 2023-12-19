package parsers;

import entities.*;
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

public class VariableDeclaration extends Parser {

	public VariableDeclaration(DataType dataType, Identifier identifier, Literal literal, boolean isAssignment) {
		initiateTree(dataType, identifier, literal, isAssignment);
	}

	private void initiateTree(DataType dataType, Identifier identifier, Literal literal, boolean isAssignment) {
		root = new ParsingNode();

		ParsingNode dataType_Node1 = new ParsingNode();
		dataType_Node1.setTokenType(dataType);

		ParsingNode identifier_Node2 = new ParsingNode();
		identifier_Node2.setTokenType(identifier);

		ParsingNode semicolon_Node3 = new ParsingNode();
		semicolon_Node3.setTokenType(new SemicolonType());

		ParsingNode comma_Node15 = new ParsingNode();
		comma_Node15.setTokenType(new CommaType());

		ParsingNode equal_Node4 = new ParsingNode();
		equal_Node4.setTokenType(new EqualType());

		ParsingNode openParenthesis_Node5 = new ParsingNode();
		openParenthesis_Node5.setTokenType(new OpenParenthesisType());

		ParsingNode closeParenthesis_Node9 = new ParsingNode();
		closeParenthesis_Node9.setTokenType(new CloseParenthesisType());

		ParsingNode literal_Node7 = new ParsingNode();
		literal_Node7.setTokenType(literal);

		ParsingNode doubleOperandOperation_Node8 = new ParsingNode();
		doubleOperandOperation_Node8.setTokenType(new DoubleOperandOperator());

		ParsingNode identifierAssignee_Node6 = new ParsingNode();
		identifierAssignee_Node6.setTokenType(identifier);

		ParsingNode DoubleOperandOperation_Node12 = new ParsingNode();
		DoubleOperandOperation_Node12.setTokenType(new DoubleOperandOperator());

		ParsingNode firstSingleOperandOperation_Node11 = new ParsingNode();
		firstSingleOperandOperation_Node11.setTokenType(new SingleOperandOperator());

		ParsingNode secondSingleOperandOperation_Node10 = new ParsingNode();
		secondSingleOperandOperation_Node10.setTokenType(new SingleOperandOperator());

		if (isAssignment) {
			root.addNode(identifier_Node2);
		} else {
			root.addNode(dataType_Node1);
		}

		dataType_Node1.addNode(identifier_Node2);// E1
		// ----------------------

		identifier_Node2.addNode(semicolon_Node3);// E2
		// --------------------

		identifier_Node2.addNode(comma_Node15);// E3

		comma_Node15.addNode(identifier_Node2);// E4
		// ------------------

		identifier_Node2.addNode(equal_Node4);// E5

		identifier_Node2.addNode(doubleOperandOperation_Node8);// E6

		doubleOperandOperation_Node8.addNode(equal_Node4);// E7

		equal_Node4.addNode(openParenthesis_Node5);// E8

		equal_Node4.addNode(literal_Node7);// E9

		equal_Node4.addNode(identifierAssignee_Node6);// E10

		openParenthesis_Node5.addNode(identifierAssignee_Node6);// E11

		identifierAssignee_Node6.addNode(semicolon_Node3);// E12

		openParenthesis_Node5.addNode(literal_Node7);// E13

		identifierAssignee_Node6.addNode(closeParenthesis_Node9);// E14

		identifierAssignee_Node6.addNode(comma_Node15);// E18

		identifierAssignee_Node6.addNode(DoubleOperandOperation_Node12);// E26

		identifierAssignee_Node6.addNode(firstSingleOperandOperation_Node11);// E27

		DoubleOperandOperation_Node12.addNode(identifierAssignee_Node6);// E28

		DoubleOperandOperation_Node12.addNode(literal_Node7);// E29

		closeParenthesis_Node9.addNode(DoubleOperandOperation_Node12);// E31

		closeParenthesis_Node9.addNode(closeParenthesis_Node9);// E32

		closeParenthesis_Node9.addNode(firstSingleOperandOperation_Node11);// E33

		closeParenthesis_Node9.addNode(semicolon_Node3);// E36

		secondSingleOperandOperation_Node10.addNode(closeParenthesis_Node9);// E37

		secondSingleOperandOperation_Node10.addNode(comma_Node15);// E41

		secondSingleOperandOperation_Node10.addNode(semicolon_Node3);// E43

		openParenthesis_Node5.addNode(openParenthesis_Node5);// E44

		DoubleOperandOperation_Node12.addNode(openParenthesis_Node5);// E45

		firstSingleOperandOperation_Node11.addNode(secondSingleOperandOperation_Node10);// E46

		closeParenthesis_Node9.addNode(comma_Node15);// E34

		if (literal instanceof TextLiteral) {
			literal_Node7.addNode(literal_Node7);// E15
			literal_Node7.addNode(closeParenthesis_Node9);// E16
			literal_Node7.addNode(semicolon_Node3);// E25
			literal_Node7.addNode(comma_Node15);// E17
		}
		if (literal instanceof BoolLiteral) {

			literal_Node7.addNode(closeParenthesis_Node9);// E49
			literal_Node7.addNode(semicolon_Node3);// E48
			literal_Node7.addNode(comma_Node15);// E47
		}

		if (literal instanceof FloatingPointLiteral) {
			ParsingNode Dot_Node13 = new ParsingNode();
			Dot_Node13.setTokenType(new FloatingPointLiteral());

			literal_Node7.addNode(Dot_Node13);// E19

			ParsingNode floatingPoint_Node14 = new ParsingNode();
			floatingPoint_Node14.setTokenType(new FloatingPointLiteral());

			Dot_Node13.addNode(floatingPoint_Node14);// 30
			floatingPoint_Node14.addNode(comma_Node15);// E42
			floatingPoint_Node14.addNode(semicolon_Node3);// E40

			floatingPoint_Node14.addNode(DoubleOperandOperation_Node12);// E38
			floatingPoint_Node14.addNode(firstSingleOperandOperation_Node11);// E39
			floatingPoint_Node14.addNode(closeParenthesis_Node9);// E35
		}
		if (literal instanceof NumericLiteral) {
			literal_Node7.addNode(comma_Node15);// E20
			literal_Node7.addNode(semicolon_Node3);// E21
			literal_Node7.addNode(DoubleOperandOperation_Node12);// E23
			literal_Node7.addNode(firstSingleOperandOperation_Node11);// E24
			literal_Node7.addNode(closeParenthesis_Node9);// E22
		}

		// -------------------------------
//		ParsingNode openBracket = new ParsingNode();
//		openBracket.setTokenType(new OpenBracket());
//		identifier.addNode(openBracket);
//		
//		ParsingNode closeBracket = new ParsingNode();
//		closeBracket.setTokenType(new CloseBracket());
//		openBracket.addNode(closeBracket);
//		
//		ParsingNode equalArray = new ParsingNode();
//		equalArray.setTokenType(new EqualType());
//		closeBracket.addNode(equalArray);
//		
//		ParsingNode openCurlyBracket = new ParsingNode();
//		openCurlyBracket.setTokenType(new OpenCurlyBracket());
//		equalArray.addNode(openCurlyBracket);
//		
//		ParsingNode numericLiteral_ArrayValue = new ParsingNode();
//		numericLiteral_ArrayValue.setTokenType(new NumericLiteral());
//		openCurlyBracket.addNode(numericLiteral_ArrayValue);
//		
//		ParsingNode comma_ArrayNumericValue = new ParsingNode();
//		comma_ArrayNumericValue.setTokenType(new CommaType());
//		numericLiteral_ArrayValue.addNode(comma_ArrayNumericValue);
//		comma_ArrayNumericValue.addNode(numericLiteral_ArrayValue);
//		
//		ParsingNode textLiteral_ArrayValue = new ParsingNode();
//		textLiteral_ArrayValue.setTokenType(new TextLiteral());
//		openCurlyBracket.addNode(textLiteral_ArrayValue);
//		textLiteral_ArrayValue.addNode(textLiteral_ArrayValue);
//		
//		ParsingNode comma_ArrayTextValue = new ParsingNode();
//		comma_ArrayTextValue.setTokenType(new CommaType());
//		textLiteral_ArrayValue.addNode(comma_ArrayTextValue);
//		comma_ArrayTextValue.addNode(textLiteral_ArrayValue);
//			
//		ParsingNode closeCurlyBracket = new ParsingNode();
//		closeCurlyBracket.setTokenType(new CloseCurlyBracket());
//		numericLiteral_ArrayValue.addNode(closeCurlyBracket);
//		closeCurlyBracket.addNode(semicolon);
//		
//		textLiteral_ArrayValue.addNode(closeCurlyBracket);
//		
//		ParsingNode numericLiteral_ArraySize = new ParsingNode();
//		numericLiteral_ArraySize.setTokenType(new NumericLiteral());
//		openBracket.addNode(numericLiteral_ArraySize);
//
//		
//		ParsingNode closeBracket_WithSize = new ParsingNode();
//		closeBracket_WithSize.setTokenType(new CloseBracket());
//		numericLiteral_ArraySize.addNode(closeBracket_WithSize);
//		
//		closeBracket_WithSize.addNode(semicolon);
//		closeBracket_WithSize.addNode(equalArray);

		currentNode = root;

	}
	@Override
	public boolean isEnd() {

		return currentNode.getTokenType() instanceof SemicolonType;
	}

}
