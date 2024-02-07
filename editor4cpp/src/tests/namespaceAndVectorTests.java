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

public class namespaceAndVectorTests {
	public static String[] namespaceCout_Data() {
		return new String[] { "std::cout << \"hello world\";","std::cout << \"hello world\"<<std::endl;"
				,"int a=2; std::cout << a <<std::endl;"};
	}

	@ParameterizedTest
	@MethodSource(value = "namespaceCout_Data")
	public void namespaceCout(String text) {
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
	public static String[] namespaceCin_Data() {
		return new String[] { "int a; std::cin << a;", "int a,b,c; std::cin << a<<b<<c;",};
	}

	@ParameterizedTest
	@MethodSource(value = "namespaceCin_Data")
	public void namespaceCin(String text) {
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
	
	public static String[] namespaceVector_Data() {
		return new String[] { "std::vector<int> a;", "std::vector<int> a={1,2,3};", "std::vector<int> a();"};
	}

	@ParameterizedTest
	@MethodSource(value = "namespaceVector_Data")
	public void namespaceVector_Data(String text) {
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
	
	
	
	public static String[] vectorInstantiation_Data() {
		return new String[] { "vector<int> a;", "vector<int> a={1,2,3};", "vector<int> a();" };
	}

	@ParameterizedTest
	@MethodSource(value = "vectorInstantiation_Data")
	public void vectorInstantiation_Data(String text) {
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
	
}
