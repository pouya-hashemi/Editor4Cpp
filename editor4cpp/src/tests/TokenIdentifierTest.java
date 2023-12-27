package tests;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import entities.*;
import entities.TokenTypes.Comment;
import entities.TokenTypes.DataType;
import entities.TokenTypes.Directive;
import entities.TokenTypes.FloatingPointLiteral;
import entities.TokenTypes.FunctionIdentifier;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Keyword;
import entities.TokenTypes.ObjectIdentifier;
import entities.TokenTypes.Operator;
import entities.TokenTypes.WhiteSpace;
import entities.TokenTypes.Literals.CharLiteral;
import entities.TokenTypes.Literals.StringLiteral;
import entities.TokenTypes.Operations.DoubleComparisonOperator;
import entities.TokenTypes.Operations.LogicalNotOperator;
import entities.TokenTypes.Operations.LogicalOperator;
import entities.TokenTypes.Operations.SingleComparisonOperator;
import services.NewTokenizer;
import services.Tokenizer;

public class TokenIdentifierTest {

	public static Object[][] IdentifyComments_Data() {
		return new Object[][] { { "//this should be comment", 0, 8 }, { "not comment //this should be comment", 4, 12 },
				{ "not comment //this should be comment \n not comment", 4, 13 },
				{ "not comment \n not comment//this should be comment", 9, 18 },
				{ "/*this should be comment*/", 0, 10 }, { "Not comment /*this should be comment*/", 4, 14 },
				{ "Not comment /*this should be comment*/ not comment", 4, 14 },
				{ "Not comment /*this should be\ncomment*/ not comment", 4, 14 },
				{ "Not comment /*this should be//comment*/ not comment", 4, 15 },
				{ "Not comment //this should be/*comment not comment\n not comment */", 4, 17 }, };
	}

	@ParameterizedTest
	@MethodSource(value = "IdentifyComments_Data")

	public void IdentifyComments(String text, int startIndex, int endIndex) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			if (i >= startIndex && i <= endIndex) {
				assertInstanceOf(Comment.class, tokens.get(i).tokenType, "index:" + i);
			} else {
				assertFalse(tokens.get(i).tokenType instanceof Comment, "index:" + i);
			}
		}

	}

	public static Object[][] IdentifyStringLiteral_Data() {
		return new Object[][] { { "\"this is string\"", 0, 6 }, { "not string \"this is string\"", 4, 10 },
				{ "not string \"this is string\" not string", 4, 10 },
				{ "not string \"this is\n string\" not string", 4, 11 },
				{ "not string \"this is\' string\" not string", 4, 11 },

		};
	}

	@ParameterizedTest
	@MethodSource(value = "IdentifyStringLiteral_Data")

	public void IdentifyStringLiteral(String text, int startIndex, int endIndex) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			if (i >= startIndex && i <= endIndex) {
				assertInstanceOf(StringLiteral.class, tokens.get(i).tokenType, "index:" + i);
			} else {
				assertFalse(tokens.get(i).tokenType instanceof StringLiteral, "index:" + i);
			}
		}

	}

	public static Object[][] IdentifyCharLiteral_Data() {
		return new Object[][] { { "\'this is char\'", 0, 6 }, { "not char \'this is char\'", 4, 10 },
				{ "not char \'this is char\' not char", 4, 10 }, { "not char \'this is\n char\' not char", 4, 11 },
				{ "not char \'this is\" char\' not char", 4, 11 },

		};
	}

	@ParameterizedTest
	@MethodSource(value = "IdentifyCharLiteral_Data")

	public void IdentifyCharLiteral(String text, int startIndex, int endIndex) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			if (i >= startIndex && i <= endIndex) {
				assertInstanceOf(CharLiteral.class, tokens.get(i).tokenType, "index:" + i);
			} else {
				assertFalse(tokens.get(i).tokenType instanceof CharLiteral, "index:" + i);
			}
		}

	}

	public static Object[][] IdentifyDataType_Data() {
		return new Object[][] { { "int", 0, 0 }, { "int a", 0, 0 }, { "for(int", 2, 2 }, { "for(int a", 2, 2 },
				{ "\nint a", 1, 1 }, };
	}

	@ParameterizedTest
	@MethodSource(value = "IdentifyDataType_Data")

	public void IdentifyDataType(String text, int startIndex, int endIndex) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			if (i >= startIndex && i <= endIndex) {
				assertInstanceOf(DataType.class, tokens.get(i).tokenType, "index:" + i);
			} else {
				assertFalse(tokens.get(i).tokenType instanceof DataType, "index:" + i);
			}
		}

	}

	public static Object[][] IdentifyKeyword_Data() {
		return new Object[][] { { "class", 0, 0 }, { "class a", 0, 0 }, { "some class", 2, 2 },
				{ "some class a", 2, 2 }, { "\nclass a", 1, 1 }, };
	}

	@ParameterizedTest
	@MethodSource(value = "IdentifyKeyword_Data")

	public void IdentifyKeyword(String text, int startIndex, int endIndex) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			if (i >= startIndex && i <= endIndex) {
				assertInstanceOf(Keyword.class, tokens.get(i).tokenType, "index:" + i);
			} else {
				assertFalse(tokens.get(i).tokenType instanceof Keyword, "index:" + i);
			}
		}

	}

	public static Object[][] IdentifyWhiteSpace_Data() {
		return new Object[][] { { "int a", 1, 1 }, { "int\na", 1, 1 }, { "int\ra", 1, 1 }, { "int\r\na", 1, 1 }, };
	}

	@ParameterizedTest
	@MethodSource(value = "IdentifyWhiteSpace_Data")

	public void IdentifyWhiteSpace(String text, int startIndex, int endIndex) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			if (i >= startIndex && i <= endIndex) {
				assertInstanceOf(WhiteSpace.class, tokens.get(i).tokenType, "index:" + i);
			} else {
				assertFalse(tokens.get(i).tokenType instanceof WhiteSpace, "index:" + i);
			}
		}

	}

	public static Object[][] IdentifyDirective_Data() {
		return new Object[][] { { "#define a", 0, 1 }, { "\n#define", 1, 2 }, { " #define", 1, 2 } };
	}

	@ParameterizedTest
	@MethodSource(value = "IdentifyDirective_Data")
	public void IdentifyDirective(String text, int startIndex, int endIndex) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			if (i >= startIndex && i <= endIndex) {
				assertInstanceOf(Directive.class, tokens.get(i).tokenType, "index:" + i);
			} else {
				assertFalse(tokens.get(i).tokenType instanceof Directive, "index:" + i);
			}
		}

	}

	public static Object[][] IdentifyFloatingPointLiteral_Data() {
		return new Object[][] { { "12.13", 0, 2 }, { "12.", 0, 1 } };
	}

	@ParameterizedTest
	@MethodSource(value = "IdentifyFloatingPointLiteral_Data")
	public void IdentifyFloatingPointLiteral(String text, int startIndex, int endIndex) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			if (i >= startIndex && i <= endIndex) {
				assertInstanceOf(FloatingPointLiteral.class, tokens.get(i).tokenType, "index:" + i);
			} else {
				assertFalse(tokens.get(i).tokenType instanceof FloatingPointLiteral, "index:" + i);
			}
		}

	}

	public static Object[][] IdentifyIdentifier_Data() {
		return new Object[][] { { "int a", 2, 2 }, { "int  a", 3, 3 }, { "int a,b", 4, 4 } };
	}

	@ParameterizedTest
	@MethodSource(value = "IdentifyIdentifier_Data")
	public void IdentifyIdentifier(String text, int startIndex, int endIndex) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			if (i >= startIndex && i <= endIndex) {
				assertInstanceOf(Identifier.class, tokens.get(i).tokenType, "index:" + i);
			}
		}

	}

	public static Object[][] IdentifyOperation_Data() {
		return new Object[][] { { "int a=1+2;", 5, 5 }, { "int a=1++;", 5, 6 }, };
	}

	@ParameterizedTest
	@MethodSource(value = "IdentifyOperation_Data")
	public void IdentifyOperation(String text, int startIndex, int endIndex) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			if (i >= startIndex && i <= endIndex) {
				assertInstanceOf(Operator.class, tokens.get(i).tokenType, "index:" + i);
			}
		}

	}
	public static Object[][] IdentifyLogicalOperation_Data() {
		return new Object[][] { { "int a=1!2;", 5, 5 } };
	}

	@ParameterizedTest
	@MethodSource(value = "IdentifyLogicalOperation_Data")
	public void IdentifyLogicalOperation(String text, int startIndex, int endIndex) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			if (i >= startIndex && i <= endIndex) {
				assertInstanceOf(LogicalNotOperator.class, tokens.get(i).tokenType, "index:" + i);
			}
		}

	}
	public static Object[][] IdentifyLogicalNotOperation_Data() {
		return new Object[][] { { "int a=1&&2;", 5, 6 }, { "int a=1||;", 5, 6 }, };
	}

	@ParameterizedTest
	@MethodSource(value = "IdentifyLogicalNotOperation_Data")
	public void IdentifyLogicalNotOperation(String text, int startIndex, int endIndex) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			if (i >= startIndex && i <= endIndex) {
				assertInstanceOf(LogicalOperator.class, tokens.get(i).tokenType, "index:" + i);
			}
		}

	}
	
	public static Object[][] IdentifyFunction_Data() {
		return new Object[][] { { "myFunc();", 0, 0 },{ "myFunc  ();", 0, 0 }, };
	}

	@ParameterizedTest
	@MethodSource(value = "IdentifyFunction_Data")
	public void IdentifyFunction_Data(String text, int startIndex, int endIndex) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			if (i >= startIndex && i <= endIndex) {
				assertInstanceOf(FunctionIdentifier.class, tokens.get(i).tokenType, "index:" + i);
			}
		}

	}

	public static Object[][] IdentifyComparisonOperation_Data() {
		return new Object[][] { { "int a=1>2;", 5, 5, new SingleComparisonOperator() },
				{ "int a=1<2;", 5, 5, new SingleComparisonOperator() },
				{ "int a=1<=2;", 5, 6, new DoubleComparisonOperator() },
				{ "int a=1<=2;", 5, 6, new DoubleComparisonOperator() },
				{ "int a=1==2;", 5, 6, new DoubleComparisonOperator() },
				{ "int a=1!=2;", 5, 5, new DoubleComparisonOperator() }, };
	}

	@ParameterizedTest
	@MethodSource(value = "IdentifyComparisonOperation_Data")
	public void IdentifyComparisonOperation(String text, int startIndex, int endIndex, Operator operator) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			if (i >= startIndex && i <= endIndex) {
				assertInstanceOf(operator.getClass(), tokens.get(i).tokenType, "index:" + i);
			}
		}

	}
	
	public static Object[][] IdentifyObject_Data() {
		return new Object[][] { { "className myClass", 2, 2 } ,{ "className myClass()", 2, 2 },{ "std::className myClass()", 5, 5 }};
	}

	@ParameterizedTest
	@MethodSource(value = "IdentifyObject_Data")
	public void IdentifyObject(String text, int startIndex, int endIndex) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			if (i >= startIndex && i <= endIndex) {
				assertInstanceOf(ObjectIdentifier.class, tokens.get(i).tokenType, "index:" + i);
			}
		}

	}
	

}
