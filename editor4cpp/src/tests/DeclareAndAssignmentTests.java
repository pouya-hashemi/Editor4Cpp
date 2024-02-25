package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import entities.Token;
import services.Parser;
import services.ParsingFacade;
import services.TextFormatting;
import services.TokenHighlighter;
import services.Tokenizer;

public class DeclareAndAssignmentTests {

	public static String[] DeclarationSubGrammarTests_Data() {
		return new String[] { "int a;", "int myLongNamedVaiable;", "int variableNameWithUnderScore_InTheMiddle;",
				"int _StartWithUnderScore;", "int WithNumbers12InMiddle;", "int WithNumbersAtEnd12;", "short a;",
				"long a;", "char b;", "string a;", "bool a;", "float a;", "double a;", "int a,b;", "int a,b,c,d,e,f;",
				"int a,b; short c;double d;", "int a,b; short c,d;double e,f;","int* a;"," auto endIter=activations->end();" ,
				"std::shared_ptr<Bag<fUML::Semantics::Activities::ActivityNodeActivation>> enabledActivations(new Bag<fUML::Semantics::Activities::ActivityNodeActivation>());",
				"const int a;"};
	}

	@ParameterizedTest
	@MethodSource(value = "DeclarationSubGrammarTests_Data")
	public void DeclarationSubGrammarTests(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text,false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue( tokens.get(i).errors.size() == 0,"index:" + i );
		}

	}

	public static Object[][] DeclarationSubGrammarErrorTests_Data() {
		return new Object[][] { { "int 2a;", 2 }, { "int a,2;", 4 }, { "int a,,;", 4 }, { "int a,;", 4 },
				{ "int 2;", 2 }, { "int a++;", 3 }, { "int ++a;", 2 } };

	}

	@ParameterizedTest
	@MethodSource(value = "DeclarationSubGrammarErrorTests_Data")
	public void DeclarationSubGrammarErrorTests(String text, int index) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text,false);
		// Assert
		assertTrue( tokens.get(index).errors.size() > 0,"");

	}

	///////////////////////////////////////////////////////////////////
	public static String[] EqualSubGrammarTests_Data() {
		return new String[] { "int a=2;", "int a+=2;", "int a-=2;", "int a*=2;", "int a/=2;", "int a%=2;" };
	}

	@ParameterizedTest
	@MethodSource(value = "EqualSubGrammarTests_Data")
	public void EqualSubGrammarTests(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text,false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue( tokens.get(i).errors.size() == 0,
					"index:" + i );
		}

	}

	public static Object[][] EqualSubGrammarErrorTests_Data() {
		return new Object[][] { { "int a==2;", 3 }, { "int a++=2;", 3 }, { "int a=+2;", 4 }, };

	}

	@ParameterizedTest
	@MethodSource(value = "EqualSubGrammarErrorTests_Data")
	public void EqualSubGrammarErrorTests(String text, int index) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text,false);
		// Assert
		assertTrue(tokens.get(index).errors.size() > 0,"");

	}

////////////////////////////////////////////////////////////////////////////////

	public static String[] MathematicTopLayerTests_Data() {
		return new String[] { "int a=2;", "int a=(2);", "int a=(2)++;", "int a=(2)++ +2;", "int a=(2)+2;",
				"int a=++(2)+2;" };
	}

	@ParameterizedTest
	@MethodSource(value = "MathematicTopLayerTests_Data")
	public void MathematicTopLayerTests(String text) {
// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
// Act
		List<Token> tokens = parsingFacade.ParseText(text,false);
// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue( tokens.get(i).errors.size() == 0,
					"index:" + i );
		}

	}

	public static Object[][] MathematicTopLayerErrorsTests_Data() {
		return new Object[][] { { "int a=(2;", 6 }, { "int a=((2);", 8 }, };

	}

	@ParameterizedTest
	@MethodSource(value = "MathematicTopLayerErrorsTests_Data")
	public void MathematicTopLayerErrorsTests(String text, int index) {
// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
// Act
		List<Token> tokens = parsingFacade.ParseText(text,false);
// Assert
		assertTrue(tokens.get(index).errors.size() > 0,"");

	}
////////////////////////////////////////////////////////////////////////////////

	public static String[] MathematicOperationTests_Data() {
		return new String[] { "int a=2;", "string a=\"hello\";", "char a='a';", "float a=2.3;", "double a=2.2;",
				"bool a=true;", "int a=2+2;", "int a=2-2;", "int a=2*2;", "int a=2/2;", "int a=2%2;", "float a=2.3*2;",
				"int a=2+(3*3);", "int a=2+(3*3)/2;", "int a=++2+(3*3);", "int a,b=b++ +(3*3);", "int a,b=a[2];",
				"int a,b=a[2]*8;", "int a,b=a[2]++;", "int a,b=a[2][2]*3;", "int a,b=myFunc()*2;" };
	}

	@ParameterizedTest
	@MethodSource(value = "MathematicOperationTests_Data")
	public void MathematicOperationTests(String text) {
// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
// Act
		List<Token> tokens = parsingFacade.ParseText(text,false);
// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0,
					"index:" + i );
		}

	}

/////////////////////////////////////////////////////////////////////////////////////

	public static String[] AssignmentTests_Data() {
		return new String[] { "int a; a=2;", "int a,b; a=++b;", "int a,b; a=b++;", "int a,b; a=b[1]++;",
				"int a,b; a=++b[1];","int a,b; a=b[2];",  };
	}

	@ParameterizedTest
	@MethodSource(value = "AssignmentTests_Data")
	public void AssignmentTests(String text) {
// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
// Act
		List<Token> tokens = parsingFacade.ParseText(text,false);
// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0,
					"index:" + i );
		}

	}
	public static Object[][] AssignmentErrorsTests_Data() {
		return new Object[][] { { "int a; a+;", 7 },};

	}

	@ParameterizedTest
	@MethodSource(value = "AssignmentErrorsTests_Data")
	public void AssignmentErrorsTests_Data(String text, int index) {
// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
// Act
		List<Token> tokens = parsingFacade.ParseText(text,false);
// Assert
		assertTrue(tokens.get(index).errors.size() > 0,"");

	}
	
}
