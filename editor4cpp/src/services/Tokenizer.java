package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.CommonCharacters;
import entities.Token;
import entities.TokenTypes.*;
import entities.TokenTypes.DataTypes.*;
import entities.TokenTypes.Keywords.*;
import entities.TokenTypes.Literals.*;
import entities.TokenTypes.Operations.*;
import entities.TokenTypes.Punctuations.*;
import interfaces.ITokenizer;

public class Tokenizer implements ITokenizer {
	private TokenBuffer buffer;
	private Map<String, DataType> dataTypes;
	private Map<String, NamespaceType> namespaces;
	private Map<String, NamespaceMemberType> namespaceMembers;
	private List<Identifier> identifiers;
	private List<String> keywords;
	private int genericInprogress=0;

	public Tokenizer() {
		dataTypes = new HashMap<>();
		namespaces = new HashMap<>();
		namespaceMembers = new HashMap<>();
		keywords = new ArrayList<String>();
		identifiers = new ArrayList<Identifier>();
		buffer = new TokenBuffer();
		initFundamentalDataTypes();
		initKeywords();
		initNamespaceTypes();
		initNamespaceMembers();
	}

	public void setTextAndRestart(String text) {
		identifiers.clear();
		buffer.setTextAndReset(text);
	}

	public Token getNextToken() {
		var next = buffer.peek();

		if (next == null)
			return new Token("", new EndOfText());
		switch (next) {
		case "\"":
			return processStringLiteral();
		case "\'":
			return processCharLiteral();
		case "/": {
			var nextChar = buffer.peek(1);
			if (nextChar == null || !(nextChar.equals("/") || nextChar.equals("*")))
				return processMathematicalOperator();
			if (nextChar.equals("/"))
				return processSingleLineComment();
			if (nextChar.equals("*"))
				return processMultiLineComment();
		}
		case "*":
		case "+":
		case "-":
		case "%":
			return processMathematicalOperator();
		case "<": {
			var nextChar = buffer.peek(1);
			if (nextChar != null && nextChar.equals("<")) {
				var token = new Token(buffer.consume(), new StreamInsertionType());
				token.value += buffer.consume();
				return token;
			}
			return processComparisonOperator();
		}
		case ">": {
			var nextChar = buffer.peek(1);
			if(genericInprogress==0) {
				if (nextChar != null && nextChar.equals(">")) {
					var token = new Token(buffer.consume(), new StreamExtractionType());
					token.value += buffer.consume();
					return token;
				}
			}
			else {
				genericInprogress--;
			}
			
			return processComparisonOperator();
		}
		case "!":
			return processComparisonOperator();
		case "=": {
			var nextChar = buffer.peek(1);
			if (nextChar == null || !nextChar.equals("=")) {
				return new Token(buffer.consume(), new EqualType());
			} else {
				return processComparisonOperator();
			}
		}
		case "0":
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
			return processNumericLiteral();
		case ";": {
			return new Token(buffer.consume(), new SemicolonType());
		}
		case "\r":
		case "\n":
		case "\t":
		case " ":
			return new Token(buffer.consume(), new WhiteSpace());
		case ",":
			return new Token(buffer.consume(), new CommaType());
		case ".":
			return new Token(buffer.consume(), new DotType());
		case "[":
			return new Token(buffer.consume(), new OpenBracket());
		case "]":
			return new Token(buffer.consume(), new CloseBracket());
		case ":":
			return new Token(buffer.consume(), new ColonType());
		case "&": {
			var nextChar = buffer.peek(1);
			if (nextChar != null && nextChar.equals("&")) {
				return processLogicalOperator();
			}
			return new Token(buffer.consume(), new AmpersandType());
		}
		case "|": {
			var nextChar = buffer.peek(1);
			if (nextChar != null && nextChar.equals("|")) {
				return processLogicalOperator();
			}
			return new Token(buffer.consume(), new UnknownType());
		}

		case "(":
			return new Token(buffer.consume(), new OpenParenthesisType());
		case ")":
			return new Token(buffer.consume(), new CloseParenthesisType());
		case "{":
			return new Token(buffer.consume(), new OpenCurlyBracket());
		case "}":
			return new Token(buffer.consume(), new CloseCurlyBracket());
		default:
			return processDefault();
		}

	}


	private Token processDefault() {

		var token = new Token(buffer.consume());

		var nextChar = buffer.peek();

		while (nextChar != null && (Character.isAlphabetic(nextChar.charAt(0)) || Character.isDigit(nextChar.charAt(0))
				|| nextChar.equals("_"))) {
			token.value += buffer.consume();
			nextChar = buffer.peek();
		}

		processWords(token);

		//if it is last token
		if (nextChar == null) {
			if (token.tokenType == null)
				return processIdentifier(token);
			else
				return token;
		}

		switch (nextChar) {
		case " ":
		case "\r":
		case "\n":
		case "\t":
		default: {

			if (token.tokenType == null) {

				return processIdentifier(token);
			}
			return token;
		}
		case "*": {
			if (token.tokenType instanceof FundamentalDataType) {
				token.tokenType = new PointerDataType(((DataType)token.tokenType).getName()+"*");
				token.value += buffer.consume();
				return token;
			}
			return processIdentifier(token);
		}
		case ":": {
			if (token.tokenType == null) {
				var secondChar = buffer.peek(1);
				if (secondChar != null && secondChar.equals(":")) {
					// found new namespace, add to namespaces
					var namespace=new NamespaceType(token.value);
					namespaces.putIfAbsent(namespace.getName(), namespace);
					token.tokenType = namespace;
					return token;
				} else
					return processIdentifier(token);
			}
			return token;
		}
		case ".": {
			token.tokenType = new ObjectIdentifier();
			return token;
		}
		case "(": {
			if (token.tokenType == null) {
				token.tokenType = new FunctionIdentifier();
			}

			return token;
		}
		case "<": {
			if (token.tokenType == null) {
				var generic= new GenericDataType(token.value);
				token.tokenType =generic;
				genericInprogress++;
				dataTypes.putIfAbsent(token.value, generic);
			}

			return token;
		}

		}
	}

	private Token processIdentifier(Token token) {
		if (identifiers.stream().anyMatch(s -> s.getName().equals(token.value)))
			token.tokenType = identifiers.stream().filter(s -> s.getName().equals(token.value)).findFirst().get();
		else {
				var identifier = new Identifier(token.value);
//				token.errors.add("Identifier does not exists in this context.");
				token.tokenType = identifier;
				identifiers.add(identifier);
		}
		return token;
	}

	private void processWords(Token token) {
		var namespace=namespaces.getOrDefault(token.value, null);
		if(namespace!=null) {
			token.tokenType=namespace;
			return;
		}
		var dataType = dataTypes.getOrDefault(token.value, null);
		if (dataType != null) {
			token.tokenType = dataType;
			if(dataType instanceof GenericDataType)
				genericInprogress++;
			return;
		} 
		var namespaceMember=namespaceMembers.getOrDefault(token.value, null);
		if (namespaceMember != null) {
			token.tokenType = namespaceMember;
			return;
		} 
		else if (token.value.equals("delete"))
			token.tokenType = new DeleteKeyword();
		else if (token.value.equals("if"))
			token.tokenType = new IfKeyword();
		else if (token.value.equals("auto")) {
			token.tokenType = new AutoKeyword();		
		}
		else if (token.value.equals("case"))
			token.tokenType = new CaseKeyword();
		else if (token.value.equals("break"))
			token.tokenType = new BreakKeyword();
		else if (token.value.equals("for"))
			token.tokenType = new ForKeyword();
		else if (token.value.equals("try"))
			token.tokenType = new TryKeyword();
		else if (token.value.equals("this"))
			token.tokenType = new ThisKeyword();
		else if (token.value.equals("catch"))
			token.tokenType = new CatchKeyword();
		else if (token.value.equals("while"))
			token.tokenType = new WhileKeyword();
		else if (token.value.equals("do"))
			token.tokenType = new DoKeyword();
		else if (token.value.equals("else"))
			token.tokenType = new ElseKeyword();
		else if (token.value.equals("switch"))
			token.tokenType = new SwitchKeyword();
		else if (token.value.equals("default"))
			token.tokenType = new DefaultKeyword();
		else if (token.value.equals("new"))
			token.tokenType = new NewKeyword();
		else if (token.value.equals("return"))
			token.tokenType = new ReturnKeyword();
		else if (token.value.equals("nullptr"))
			token.tokenType = new NullptrKeyword();
		else if (token.value.equals("NULL"))
			token.tokenType = new NullKeyword();
		else if (token.value.equals("const"))
			token.tokenType = new ConstKeyword();
		else if (token.value.equals("true") || token.value.equals("false"))
			token.tokenType = new BoolLiteral();
		else if (keywords.contains(token.value))
			token.tokenType = new Keyword();


	}

	private Token processStringLiteral() {

		var token = new Token(buffer.consume(), new StringLiteral());
		var currentChar = "";
		do {
			currentChar = buffer.peek();
			if (currentChar == null) {
				token.errors.add("expected \"");
				return token;
			}
			token.value += buffer.consume();
		} while (!currentChar.equals("\""));

		return token;
	}

	private Token processCharLiteral() {
		var token = new Token(buffer.consume(), new CharLiteral());
		var currentChar = "";
		do {
			currentChar = buffer.peek();
			if (currentChar == null) {
				token.errors.add("expected '");
				return token;
			}
			token.value += buffer.consume();
		} while (!currentChar.equals("\'"));

		return token;
	}

	private Token processMathematicalOperator() {
		var token = new Token(buffer.consume());
		switch (token.value) {
		case "/": {
			token.tokenType = new DivisionOperator();
			return token;
		}
		case "*": {
			token.tokenType = new MultiplyOperator();
			return token;
		}
		case "%": {
			token.tokenType = new RemainderOperator();
			return token;
		}
		case "+": {
			var nextChar = buffer.peek();
			if (nextChar != null && nextChar.equals("+")) {
				token.value += buffer.consume();
				token.tokenType = new SingleOperandOperator();
				return token;
			}
			token.tokenType = new PlusOperator();
			return token;
		}
		case "-": {
			var nextChar = buffer.peek();
			if (nextChar != null && nextChar.equals("-")) {
				token.value += buffer.consume();
				token.tokenType = new SingleOperandOperator();
				return token;
			}
			else if(nextChar != null && nextChar.equals(">")) {
				token.value += buffer.consume();
				token.tokenType = new AccessPointer();
				return token;
			}
			token.tokenType = new MinusOperator();
			return token;
		}
		default:
			return null;
		}
	}

	private Token processSingleLineComment() {
		var token = new Token(buffer.consume(), new Comment());
		var currentChar = "";
		while (!CommonCharacters.isNextLine(buffer.peek())) {
			currentChar = buffer.consume();
			if (currentChar == null) {
				return token;
			}
			token.value += currentChar;
		}

		return token;
	}

	private Token processMultiLineComment() {
		var token = new Token(buffer.consume(), new Comment());
		var currentChar = "";
		do {
			currentChar = buffer.consume();
			if (currentChar == null) {
				return token;
			}
			token.value += currentChar;
		} while (!(currentChar.equals("*") && buffer.peek() != null && buffer.peek().equals("/")));
		token.value += buffer.consume();
		return token;
	}

	private Token processNumericLiteral() {
		var token = new Token(buffer.consume());
		var nextChar = buffer.peek();
		if (nextChar == null)
			return resolveNumericLiteralType(token);
		while (Character.isDigit(nextChar.charAt(0))) {
			token.value += buffer.consume();
			nextChar = buffer.peek();
			if (nextChar == null)
				return resolveNumericLiteralType(token);
		}
		if (!nextChar.equals("."))
			return resolveNumericLiteralType(token);

		token.value += buffer.consume();

		nextChar = buffer.peek();
		if (nextChar == null) {
			token.errors.add("expected numeric literal");
			return resolveNumericLiteralType(token);
		}
		while (Character.isDigit(nextChar.charAt(0))) {
			token.value += buffer.consume();
			nextChar = buffer.peek();
			if (nextChar == null)
				return resolveNumericLiteralType(token);
		}
		return resolveNumericLiteralType(token);
	}

	private Token resolveNumericLiteralType(Token token) {
		try {
			Short.parseShort(token.value);
			token.tokenType = new ShortLiteral();
			return token;
		} catch (NumberFormatException e1) {
			try {
				Integer.parseInt(token.value);
				token.tokenType = new IntLiteral();
				return token;
			} catch (NumberFormatException e2) {
				try {
					Long.parseLong(token.value);
					token.tokenType = new LongLiteral();
					return token;
				} catch (NumberFormatException e3) {
					try {
						Double.parseDouble(token.value);
						token.tokenType = new FloatingPointLiteral();
						return token;
					} catch (NumberFormatException e4) {
						token.tokenType = new NumericLiteral();
						return token;
					}
				}
			}
		}
	}

	private Token processComparisonOperator() {
		var token = new Token(buffer.consume());

		switch (token.value) {
		case ">": {
			var nextChar = buffer.peek();
			if (nextChar == null || !nextChar.equals("=")) {
				token.tokenType = new GreaterThanOperator();
				return token;
			}
			token.value += buffer.consume();
			token.tokenType = new DoubleComparisonOperator();
			return token;
		}
		case "<": {
			var nextChar = buffer.peek();
			if (nextChar == null || !nextChar.equals("=")) {
				token.tokenType = new LessThanOperator();
				return token;
			}
			token.value += buffer.consume();
			token.tokenType = new DoubleComparisonOperator();
			return token;
		}
		case "!": {
			var nextChar = buffer.peek();
			if (nextChar == null || !nextChar.equals("=")) {
				token.tokenType = new LogicalNotOperator();
				return token;
			}
			token.value += buffer.consume();
			token.tokenType = new DoubleComparisonOperator();
			return token;
		}
		case "=": {
			var nextChar = buffer.peek();
			if (nextChar != null && nextChar.equals("=")) {
				token.value += buffer.consume();
				token.tokenType = new DoubleComparisonOperator();
				return token;
			}

			return token;
		}
		default: {
			token.tokenType = new Operator();
			return token;
		}
		}

	}
	private Token processLogicalOperator() {
		var token = new Token(buffer.consume(), new LogicalOperator());
		token.value += buffer.consume();
		return token;
	}
	private void initFundamentalDataTypes() {
		dataTypes.putIfAbsent("int", new FundamentalDataType("int"));
		dataTypes.putIfAbsent("char", new FundamentalDataType("char"));
		dataTypes.putIfAbsent("short", new FundamentalDataType("short"));
		dataTypes.putIfAbsent("long", new FundamentalDataType("long"));
		dataTypes.putIfAbsent("float",new FundamentalDataType("float"));
		dataTypes.putIfAbsent("double", new FundamentalDataType("double"));
		dataTypes.putIfAbsent("bool", new FundamentalDataType("bool"));
		dataTypes.putIfAbsent("string", new FundamentalDataType("string"));
		dataTypes.putIfAbsent("vector", new GenericDataType("vector"));
		dataTypes.putIfAbsent("dynamic_pointer_cast", new GenericDataType("dynamic_pointer_cast"));
		dataTypes.putIfAbsent("shared_ptr", new GenericDataType("shared_ptr"));
//		
//		dataTypes.putIfAbsent("ActivityNodeActivation", new UserDefinedDataType("ActivityNodeActivation"));
//		dataTypes.putIfAbsent("ActivityEdgeInstance", new UserDefinedDataType("ActivityEdgeInstance"));
//		dataTypes.putIfAbsent("Action", new UserDefinedDataType("Action"));
//		dataTypes.putIfAbsent("InputPin", new UserDefinedDataType("InputPin"));
//		dataTypes.putIfAbsent("ActionActivation", new UserDefinedDataType("ActionActivation"));
	}
private void initNamespaceMembers() {
	namespaceMembers.putIfAbsent("cout", new NamespaceMemberType("cout"));
	namespaceMembers.putIfAbsent("cin", new NamespaceMemberType("cin"));
	namespaceMembers.putIfAbsent("endl", new NamespaceMemberType("endl"));
	
//	namespaceMembers.putIfAbsent("INPUTPINACTIVATION_CLASS", new NamespaceMemberType("INPUTPINACTIVATION_CLASS"));
//	namespaceMembers.putIfAbsent("OUTPUTPINACTIVATION_CLASS", new NamespaceMemberType("OUTPUTPINACTIVATION_CLASS"));
//	namespaceMembers.putIfAbsent("EXPANSIONNODEACTIVATION_CLASS", new NamespaceMemberType("EXPANSIONNODEACTIVATION_CLASS"));
}
	private void initNamespaceTypes() {
		namespaces.putIfAbsent("std", new NamespaceType("std"));
		
//		namespaces.putIfAbsent("fUML", new NamespaceType("fUML"));
//		namespaces.putIfAbsent("Semantics", new NamespaceType("Semantics"));
//		namespaces.putIfAbsent("Activities", new NamespaceType("Activities"));


	}
	private void initKeywords() {
		keywords = Arrays.asList("alignas", "alignof", "and", "and_e", "asm", "bitand", "bitor", "class",
				"compl", "concept", "const", "const_cast", "consteval", "constexpr", "constinit", "continue",
				"co_await", "co_return", "co_yield", "decltype", "dynamic_cast", "enum", "explicit", "export", "extern",
				"friend", "goto", "inline", "mutable", "namespace", "noexcept", "not", "not_eq", "operator", "or",
				"or_eq", "private", "protected", "public", "register", "reinterpret_cast", "requires", "signed",
				"sizeof", "static", "static_assert", "static_cast", "struct", "template", "this", "thread_local",
				"throw", "typedef", "typeid", "typename", "union", "unsigned", "using", "virtual", "volatile", "xor",
				"xor_eq");
	}

}
