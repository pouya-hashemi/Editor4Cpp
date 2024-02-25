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

public class ErrorDetecterTest {

	public static String[] newData() {
		return new String[] { "int a;" };
	}

	@ParameterizedTest
	@MethodSource(value = "newData")
	public void newD(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
		}

	}

	public static String[] noError_Data() {
		return new String[] { "int a;", "int  a;", "int\n a;", "int a;", "int a=2;", "int a =2;", "int a = 2;",
				"int a= 2;", "int a=2 ;", "char a='a';", "char a ='a';", "char a= 'a';", "char a='a' ;",
				"char a = ' a ' ;", "int a,b;", "int a, b;", "int a,  b;", "int a ,b;", "int a  ,b;", "int a,b,c;",
				"int a,b=2;", "int a,b=2,c;", "int a , b=2 , c;", "int a;int a;", "float a=2;", "float a=2.2;",
				"double a=2;", "double a=2;", "int a; a=2;", "int a;a=a;", "int a;int b=2;a=b;",
				"int a=1;int b=2;int c =a+b;", "int a=1;int b=2;int c =3;", "int a+=2;" };
	}

	@ParameterizedTest
	@MethodSource(value = "noError_Data")
	public void noError(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
		}

	}

	public static Object[][] detectError_Data() {
		return new Object[][] { { "int a:", 3 }, { "int a, b:", 6 }, { "int a , b:", 7 },
				{ "int a , b=2:", 9 }, { "int a,b=2,c:", 9 }, { "int a,2", 4 }, { "int a,'", 4 },
				{ "int a;int a:", 7 } };

	}

	@ParameterizedTest
	@MethodSource(value = "detectError_Data")
	public void detectError(String text, int index) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert

		assertTrue(tokens.get(index).errors.size() > 0, "");

	}

	public static String[] variableDeclaration_Data() {
		return new String[] { "int a;", "int  a;", "int\n a;", "int\ra;", "short a;", "short  a;", "short\n a;",
				"short\ra;", "long a;", "long  a;", "long\n a;", "long\ra;", "float a;", "float  a;", "float\n a;",
				"float\ra;", "double a;", "double  a;", "double\n a;", "double\ra;", "bool a;", "bool  a;", "bool\n a;",
				"bool\ra;", "char a;", "char  a;", "char\n a;", "char\ra;", "string a;", "string  a;", "string\n a;",
				"string\ra;", };
	}

	@ParameterizedTest
	@MethodSource(value = "variableDeclaration_Data")
	public void variableDeclaration(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
		}

	}

	public static Object[][] variableDeclarationError_Data() {
		return new Object[][] { { "int 2a;", 2 }, { "short 2a;", 2 }, { "long 2a;", 2 }, { "float 2a;", 2 },
				{ "double 2a;", 2 }, { "char 2a;", 2 }, { "string 2a;", 2 }, { "bool 2a;", 2 } };

	}

	@ParameterizedTest
	@MethodSource(value = "variableDeclarationError_Data")
	public void variableDeclarationError(String text, int index) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		assertTrue(tokens.get(index).errors.size() > 0, "");

	}

	public static String[] VariableDeclarationAndAssignmentWithLiteralOnly_Data() {
		return new String[] { "short a=2;", "int a=2;", "long a=2;", "float a=2.3;", "double a=2.3;", "char a='s';",
				"string a=\"asd\";", "bool a=false;", "float a=2;", "double a=2;", "int a=2; bool b=true;" };
	}

	@ParameterizedTest
	@MethodSource(value = "VariableDeclarationAndAssignmentWithLiteralOnly_Data")
	public void VariableDeclarationAndAssignmentWithLiteralOnly(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static String[] VariableAssignmentWithIdentifierOnly_Data() {
		return new String[] { "short a=2;short b=a;", "int a=2;int b=a;", "long a=2;long b=a;",
				"float a=2.3;float b=a;", "double a=2.3;double b=2.3;", "char a='a';char b=a;",
				"string a=\"a\";string b=a;", "bool a=true;bool b=a;", };
	}

	@ParameterizedTest
	@MethodSource(value = "VariableAssignmentWithIdentifierOnly_Data")
	public void VariableAssignmentWithIdentifierOnly(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static String[] VariableAssignmentWithAritmeticOperations_Data() {
		return new String[] { "short a=2+1;", "int a=2+1;", "long a=2+1;", "float a=2.3+6.25;", "double a=2.3+6.25;",
				"short a=2-1;", "short a=2/1;", "short a=2*1;", "short a=2%1;", "int a=2+1*8;", "int a+=2;",
				"int a=2;int b=2+a;" };
	}

	@ParameterizedTest
	@MethodSource(value = "VariableAssignmentWithAritmeticOperations_Data")
	public void VariableAssignmentWithAritmeticOperations(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static String[] VariableAssignmentWithAritmeticOperationsAndParenthesis_Data() {
		return new String[] { "short a=(2);", "short a=(2+1);", "short a=2+(1);", "short a=2+(1*2);",
				"int a=2+((1-2)*4);", "int a=2; int b=(2+(a*1)-2);" };
	}

	@ParameterizedTest
	@MethodSource(value = "VariableAssignmentWithAritmeticOperationsAndParenthesis_Data")
	public void VariableAssignmentWithAritmeticOperationsAndParenthesis_Data(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static Object[][] VariableAssignmentWithAritmeticOperationsAndParenthesisErrors_Data() {
		return new Object[][] { { "int a=2(", 5 }, { "int a=2+(+", 7 }, { "int a=2+(1*)", 9 }, { "int a=)", 4 },
				{ "int a=2)", 5 }, { "int a=(2+2;", 8 }, };

	}

	@ParameterizedTest
	@MethodSource(value = "VariableAssignmentWithAritmeticOperationsAndParenthesisErrors_Data")
	public void VariableAssignmentWithAritmeticOperationsAndParenthesisErrors(String text, int index) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		assertTrue(tokens.get(index).errors.size() > 0, "");

	}

	public static String[] VariableAssignmentWithLiteralOnly_Data() {
		return new String[] { "short a;a=2;", "int a;a=2;", "long a;a=2;", "float a;a=2.3;", "double a;a=2.3;",
				"char a;a='s';", "string a;a=\"asd\";", "bool a;a=false;", "float a;a=2;", "double a;a=2;" };
	}

	@ParameterizedTest
	@MethodSource(value = "VariableAssignmentWithLiteralOnly_Data")
	public void VariableAssignmentWithLiteralOnly(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static String[] IfStatement_Data() {
		return new String[] { "if(2>3){int a=2;}", "if('a'>'a'){}", "if(\"ba\">\"asd\"){}", "if(true>false){}", "if(2>=3){}",
				"if(2<=3){}", "if(2==3){}", "if(2!=3){}", "if(2<3){}", "if(!2<3){}", "if((2<3)){}", "if(!(2<3)){}",
				"if((2<3)&&(2>3)){}", "if((2<3)&&(2>3)||!(2==3)){}", "if(1>2){int a=2;bool b=false;}" };
	}

	@ParameterizedTest
	@MethodSource(value = "IfStatement_Data")
	public void IfStatement(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static String[] CompeleteIfStatement_Data() {
		return new String[] { "if(2>3){ int a=3; }", "if((2<3)&&(2>3)){ int a=3;double b=2.4; }" };
	}

	@ParameterizedTest
	@MethodSource(value = "CompeleteIfStatement_Data")
	public void CompeleteIfStatement_Data(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static String[] WhileStatement_Data() {
		return new String[] { "while(2>3){ int a=3; }", "while(2>3) int a=3; ",
				"while(2>3){ int a=3;\nint a=3;\nint a=3; }" };
	}

	@ParameterizedTest
	@MethodSource(value = "WhileStatement_Data")
	public void WhileStatement(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static String[] DoWhileStatement_Data() {
		return new String[] { "do int a=2; while(2>3);", "do {int a=2;} while(2>3);",
				"do {int a=2;\r int b=2;} while(2>3);", };
	}

	@ParameterizedTest
	@MethodSource(value = "DoWhileStatement_Data")
	public void DoWhileStatement_Data(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static String[] ForStatement_Data() {
		return new String[] { "for(int i=0;i<=2;i++){ int a=3; }", "int i; for(i=0;i<=2;i++){ int a=3; }",
				"for(int i=0, b=2;i<=2 && b>6;i++,b--){ int a=3;\nint a=3;\nint a=3; }",
				"int i; for(i=0;i<=2;i++){ if(2>3){int a=2;}}", "for(int i=0;i<=2;++i){ int a=2;}" };
	}

	@ParameterizedTest
	@MethodSource(value = "ForStatement_Data")
	public void ForStatement(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static String[] PointerTest_Data() {
		return new String[] { "int* a;", "int a=2;int* b=&a;", "int* b=new int(2);", "int* b=nullptr;",
				"int* b=NULL;" };
	}

	@ParameterizedTest
	@MethodSource(value = "PointerTest_Data")
	public void PointerTest(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static String[] SwitchTest_Data() {
		return new String[] {
				"int day = 3;\r\n" + "switch (day) {\r\n" + "case 1:\r\n" + "int a=1;\r\n" + "break;\r\n"
						+ "default:\r\n" + "int a=1;\r\n" + "break;\r\n" + "}",
				"int num = 5;\r\n int numb = 3;char operation = '+'; switch (operation) {\r\n" + "case '+':\r\n"
						+ "num = num + numb;\r\n" + "break;\r\n" + "case '-':\r\n" + "num = num - numb;\r\n"
						+ "break;\r\n" + "default:\r\n" + "break;\r\n" + "}" };
	}

	@ParameterizedTest
	@MethodSource(value = "SwitchTest_Data")
	public void SwitchTest(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static String[] ArrayTest_Data() {
		return new String[] { "int a[5];", "int a[]={2,2};", "int a[][2]={2,2};", "int a[][2]={{2,2},{2,2}};",
				"int array[3]={1, 2, 3};" };
	}

	@ParameterizedTest
	@MethodSource(value = "ArrayTest_Data")
	public void ArrayTest(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static String[] FunctionTest_Data() {
		return new String[] { "myFunc();", "myFunc(2,1);", "int a=2;myFunc(a,1);", "int a=myFunc();",
				"myFunc(secFunc());", };
	}

	@ParameterizedTest
	@MethodSource(value = "FunctionTest_Data")
	public void FunctionTest(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}


	public static String[] TryCatchTest_Data() {
		return new String[] { "try{ int a=2;}catch(...){int b=2;}" };
	}

	@ParameterizedTest
	@MethodSource(value = "TryCatchTest_Data")
	public void TryCatchTest(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static String[] ReturnTest_Data() {
		return new String[] { "return 2;" };
	}

	@ParameterizedTest
	@MethodSource(value = "ReturnTest_Data")
	public void ReturnTest_Data(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static String[] DeleteTest_Data() {
		return new String[] { "int a; delete[] a;" };
	}

	@ParameterizedTest
	@MethodSource(value = "DeleteTest_Data")
	public void DeleteTest(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}

	public static String[] AssignmentWithArraysTest_Data() {
		return new String[] { "int a[5]; int b=a[2];", "int a[5]; a[2]=3;" };
	}

	@ParameterizedTest
	@MethodSource(value = "AssignmentWithArraysTest_Data")
	public void AssignmentWithArraysTest_Data(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text, false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0, "index:" + i );
					
		}

	}
	

}
