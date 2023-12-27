package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import entities.Token;
import services.Tokenizer;

//"int a[5];", "int a[]={2};", "int a[]={2,1};","int a[]={\"a\"};","int a[]={\"a\",\"b\"};", "int a[2]={2,1};",
public class ErrorDetecterTest {

	public static String[] newData() {
		return new String[] { "int a;" };
	}

	@ParameterizedTest
	@MethodSource(value = "newData")
	public void newD(String text) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}

	public static String[] noError_Data() {
		return new String[] { "int a;", "int  a;", "int\n a;", "int a", "int a=2;", "int a =2;", "int a = 2;",
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
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}

	public static Object[][] detectError_Data() {
		return new Object[][] { { "int a:", 3 }, { "int a='a';", 4 }, { "int a, b:", 6 }, { "int a , b:", 7 },
				{ "int a , b=2:", 9 }, { "int a,b=2,c:", 9 }, { "int a,2", 4 }, { "int a,'", 4 },
				{ "int a;int a:", 7 } };

	}

	@ParameterizedTest
	@MethodSource(value = "detectError_Data")
	public void detectError(String text, int index) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert

		assertTrue(tokens.get(index).error != null && tokens.get(index).error.length() > 0, tokens.get(index).error);

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
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
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
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		assertTrue(tokens.get(index).error != null && tokens.get(index).error.length() > 0, tokens.get(index).error);

	}

	public static String[] VariableDeclarationAndAssignmentWithLiteralOnly_Data() {
		return new String[] { "short a=2;", "int a=2;", "long a=2;", "float a=2.3;", "double a=2.3;", "char a='s';",
				"string a=\"asd\";", "bool a=false;", "float a=2;", "double a=2;", "int a=2; bool b=true;" };
	}

	@ParameterizedTest
	@MethodSource(value = "VariableDeclarationAndAssignmentWithLiteralOnly_Data")
	public void VariableDeclarationAndAssignmentWithLiteralOnly(String text) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}

	public static Object[][] VariableAssignmentWithLiteralOnlyErrors_Data() {
		return new Object[][] { { "short a=32768;", 4 }, { "int a=2147483648;", 4 }, { "short a=2.3;", 4 },
				{ "int a=2.3;", 4 }, { "long a=2.3;", 4 }, { "char a=2;", 4 }, { "string a=2;", 4 },
				{ "char a=\"ss\";", 4 }, { "string a='a';", 4 }, { "long a=\"asd\";", 4 }, { "int a=\"asd\";", 4 },
				{ "short a=\"asd\";", 4 }, { "long a='a';", 4 }, { "short a='a';", 4 }, { "int a='a';", 4 },
				{ "float a='a';", 4 }, { "double a='a';", 4 }, { "float a=\"asd\";", 4 }, { "double a=\"asd\";", 4 },
				{ "bool a=\"asd\";", 4 }, { "bool a='a';", 4 }, { "bool a=2;", 4 }, { "bool a=2.3;", 4 } };

	}

	@ParameterizedTest
	@MethodSource(value = "VariableAssignmentWithLiteralOnlyErrors_Data")
	public void VariableAssignmentWithLiteralOnlyErrors(String text, int index) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		assertTrue(tokens.get(index).error != null && tokens.get(index).error.length() > 0, tokens.get(index).error);

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
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}

	public static Object[][] VariableAssignmentWithIdentifiersOnlyErrors_Data() {
		return new Object[][] { { "float a=2.3;short b=a;", 12 }, { "float a=2.3;int b=a;", 12 },
				{ "float a=2.3;long b=a;", 12 }, { "double a=2.3;short b=a;", 12 }, { "double a=2.3;int b=a;", 12 },
				{ "double a=2.3;long b=a;", 12 }, { "int a=2;char b=a;", 10 }, { "long a=2;char b=a;", 10 },
				{ "short a=2;char b=a;", 10 }, { "int a=2;string b=a;", 10 }, { "int a=2;bool b=a;", 10 }, };

	}

	@ParameterizedTest
	@MethodSource(value = "VariableAssignmentWithIdentifiersOnlyErrors_Data")
	public void VariableAssignmentWithIdentifiersOnlyErrors(String text, int index) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		assertTrue(tokens.get(index).error != null && tokens.get(index).error.length() > 0, tokens.get(index).error);

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
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
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
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
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
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		assertTrue(tokens.get(index).error != null && tokens.get(index).error.length() > 0, tokens.get(index).error);

	}

	public static String[] VariableAssignmentWithLiteralOnly_Data() {
		return new String[] { "short a;a=2;", "int a;a=2;", "long a;a=2;", "float a;a=2.3;", "double a;a=2.3;",
				"char a;a='s';", "string a;a=\"asd\";", "bool a;a=false;", "float a;a=2;", "double a;a=2;" };
	}

	@ParameterizedTest
	@MethodSource(value = "VariableAssignmentWithLiteralOnly_Data")
	public void VariableAssignmentWithLiteralOnly(String text) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}

	public static String[] IfStatement_Data() {
		return new String[] { "if(2>3){int a=2;}", "if('a'>'a')", "if(\"ba\">\"asd\")", "if(true>false)", "if(2>=3)",
				"if(2<=3)", "if(2==3)", "if(2!=3)", "if(2<3)", "if(!2<3)", "if((2<3))", "if(!(2<3))",
				"if((2<3)&&(2>3))", "if((2<3)&&(2>3)||!(2==3))","if(1>2){int a=2;bool b=false;}" };
	}

	@ParameterizedTest
	@MethodSource(value = "IfStatement_Data")
	public void IfStatement(String text) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}

	public static String[] CompeleteIfStatement_Data() {
		return new String[] { "if(2>3){ int a=3; }", "if((2<3)&&(2>3)){ int a=3;double b=2.4; }" };
	}

	@ParameterizedTest
	@MethodSource(value = "CompeleteIfStatement_Data")
	public void CompeleteIfStatement_Data(String text) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
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
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
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
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}

	public static String[] ForStatement_Data() {
		return new String[] { "for(int i=0;i<=2;i++){ int a=3; }", "int i; for(i=0;i<=2;i++){ int a=3; }",
				"for(int i=0, b=2;i<=2 && b>6;i++,b--){ int a=3;\nint a=3;\nint a=3; }",
				"int i; for(i=0;i<=2;i++){ if(2>3){int a=2;}}",
				"for(int i=0;i<=2;++i){ int a=2;}" };
	}

	@ParameterizedTest
	@MethodSource(value = "ForStatement_Data")
	public void ForStatement(String text) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
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
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}

	public static String[] SwitchTest_Data() {
		return new String[] {
				"int day = 3;\r\n" + "switch (day) {\r\n" + "case 1:\r\n" + "int a=1;\r\n" + "break;\r\n"
						+ "default:\r\n" + "int a=1;\r\n" + "break;\r\n" + "}",
				"int num1 = 5;\r\n int num2 = 3;char operation = '+'; switch (operation) {\r\n" + "case '+':\r\n" + "num1 = num1 + num2;\r\n"
						+ "break;\r\n" + "case '-':\r\n" + "num1 = num1 - num2;\r\n" + "break;\r\n" + "default:\r\n"
						+ "break;\r\n" + "}" };
	}

	@ParameterizedTest
	@MethodSource(value = "SwitchTest_Data")
	public void SwitchTest(String text) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
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
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}

	public static String[] FunctionTest_Data() {
		return new String[] { "myFunc();", "myFunc(2,1);", "int a=2;myFunc(a,1);", "int a=myFunc();",
				"myFunc(secFunc());", "std::cout.myFunc();" };
	}

	@ParameterizedTest
	@MethodSource(value = "FunctionTest_Data")
	public void FunctionTest(String text) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}

	public static String[] ObjectDeclareTest_Data() {
		return new String[] { "className myClass;", "className myClass(1);" };
	}

	@ParameterizedTest
	@MethodSource(value = "ObjectDeclareTest_Data")
	public void ObjectDeclareTest(String text) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}

	public static String[] TryCatchTest_Data() {
		return new String[] { "try{ int a=2;}catch(...){int b=2;}", "try{ int a=2;}catch(asd e){int b=2;}",
				"try{ int a=2;}catch(asd e){int b=2;}catch(...){int b=2;}" };
	}

	@ParameterizedTest
	@MethodSource(value = "TryCatchTest_Data")
	public void TryCatchTest(String text) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}
	public static String[] ReturnTest_Data() {
		return new String[] { "return 2;" };
	}

	@ParameterizedTest
	@MethodSource(value = "ReturnTest_Data")
	public void ReturnTest_Data(String text) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}
	public static String[] DeleteTest_Data() {
		return new String[] { "int a; delete[] a;" };
	}

	@ParameterizedTest
	@MethodSource(value = "DeleteTest_Data")
	public void DeleteTest(String text) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}
	public static String[] AssignmentWithArraysTest_Data() {
		return new String[] { "int a[5]; int b=a[2];","int a[5]; a[2]=3;" };
	}

	@ParameterizedTest
	@MethodSource(value = "AssignmentWithArraysTest_Data")
	public void AssignmentWithArraysTest_Data(String text) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}

}
