package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.*;
import entities.TokenTypes.*;
import entities.TokenTypes.DataTypes.*;
import entities.TokenTypes.Identifiers.*;
import entities.TokenTypes.Keywords.BreakKeyword;
import entities.TokenTypes.Keywords.CaseKeyword;
import entities.TokenTypes.Keywords.CatchKeyword;
import entities.TokenTypes.Keywords.DefaultKeyword;
import entities.TokenTypes.Keywords.DeleteKeyword;
import entities.TokenTypes.Keywords.DoKeyword;
import entities.TokenTypes.Keywords.ElseKeyword;
import entities.TokenTypes.Keywords.ForKeyword;
import entities.TokenTypes.Keywords.IfKeyword;
import entities.TokenTypes.Keywords.NewKeyword;
import entities.TokenTypes.Keywords.NullKeyword;
import entities.TokenTypes.Keywords.NullptrKeyword;
import entities.TokenTypes.Keywords.ReturnKeyword;
import entities.TokenTypes.Keywords.SwitchKeyword;
import entities.TokenTypes.Keywords.TryKeyword;
import entities.TokenTypes.Keywords.WhileKeyword;
import entities.TokenTypes.Literals.*;
import entities.TokenTypes.Operations.*;
import entities.TokenTypes.Punctuations.*;
import enums.CommentMode;
import enums.TextMode;

public class TokenIdentifier {
	private CommentMode commentMode = CommentMode.none;
	private TextMode textMode = TextMode.none;
	private boolean identifierInProgress = false;
	private List<Identifier> identifiers = new ArrayList<Identifier>();
	private List<String> dataTypes = Arrays.asList("int", "short", "long", "float", "double", "char", "bool", "void",
			"string");
	private List<String> keywords = Arrays.asList("alignas", "alignof", "and", "and_e", "asm", "auto", "bitand",
			"bitor", "class", "compl", "concept", "const", "const_cast", "consteval", "constexpr", "constinit",
			"continue", "co_await", "co_return", "co_yield", "decltype", "dynamic_cast", "enum", "explicit",
			"export", "extern", "friend", "goto", "inline", "mutable", "namespace", "noexcept", "not", "not_eq",
			"operator", "or", "or_eq", "private", "protected", "public", "register", "reinterpret_cast", "requires",
			"signed", "sizeof", "static", "static_assert", "static_cast", "struct", "template", "this", "thread_local",
			"throw", "typedef", "typeid", "typename", "union", "unsigned", "using", "virtual", "volatile", "xor",
			"xor_eq");
	private List<String> directives = Arrays.asList("define", "undef", "include", "ifdef", "ifndef", "if", "else",
			"endif");
	private TokenType prevDataType;

	public Token identify(Token token) {

		if (isComment(token)) {
			token.tokenType = new Comment();
		} else if (isString(token)) {
			token.tokenType = new StringLiteral();
		} else if (isChar(token)) {
			token.tokenType = new CharLiteral();
		} else if (isWhiteSpace(token)) {
			token.tokenType = new WhiteSpace();
		} else if (isStarType(token)) {
			token.tokenType = new StarType();
		} else if (isOperation(token)) {
			token.tokenType = getOperationType(token);
		} else if (isDataType(token)) {
			token.tokenType = getDataType(token);
		} else if (isKeyword(token)) {
			token.tokenType = new Keyword();
		} else if (isBoolLiteral(token)) {
			token.tokenType = new BoolLiteral();
		} else if (isFloatingPointLiteral(token)) {
			token.tokenType = new FloatingPointLiteral();
		} else if (isShortLiteral(token)) {
			token.tokenType = new ShortLiteral();
		} else if (isIntLiteral(token)) {
			token.tokenType = new IntLiteral();
		} else if (isLongLiteral(token)) {
			token.tokenType = new LongLiteral();
		} else if (isComparisonOperation(token)) {
			token.tokenType = getComparisonOperatorType(token);
		} else if (isEqual(token)) {
			token.tokenType = new EqualType();
		} else if (isLogicalOperator(token)) {
			token.tokenType = new LogicalOperator();
		} else if (isLogicalNot(token)) {
			token.tokenType = new LogicalNotOperator();
		} else if (isComma(token)) {
			token.tokenType = new CommaType();
		} else if (isDot(token)) {
			token.tokenType = new DotType();
		} else if (isSemicolon(token)) {
			token.tokenType = new SemicolonType();
		} else if (isOpenBracket(token)) {
			token.tokenType = new OpenBracket();
		} else if (isCloseBracket(token)) {
			token.tokenType = new CloseBracket();
		} else if (isDeleteKeyword(token)) {
			token.tokenType = new DeleteKeyword();
		}else if (isIfKeyword(token)) {
			token.tokenType = new IfKeyword();
		} else if (isCaseKeyword(token)) {
			token.tokenType = new CaseKeyword();
		} else if (isBreakKeyword(token)) {
			token.tokenType = new BreakKeyword();
		} else if (isColonType(token)) {
			token.tokenType = new ColonType();
		} else if (isForKeyword(token)) {
			token.tokenType = new ForKeyword();
		} else if (isTryKeyword(token)) {
			token.tokenType = new TryKeyword();
		} else if (isCatchKeyword(token)) {
			token.tokenType = new CatchKeyword();
		} else if (isWhileKeyword(token)) {
			token.tokenType = new WhileKeyword();
		} else if (isDoKeyword(token)) {
			token.tokenType = new DoKeyword();
		} else if (isElse(token)) {
			token.tokenType = new ElseKeyword();
		} else if (isAmpersandType(token)) {
			token.tokenType = new AmpersandType();
		} else if (isSwitchKeyword(token)) {
			token.tokenType = new SwitchKeyword();
		} else if (isDefaultKeyword(token)) {
			token.tokenType = new DefaultKeyword();
		} else if (isNewKeyword(token)) {
			token.tokenType = new NewKeyword();
		} else if (isReturnKeyword(token)) {
			token.tokenType = new ReturnKeyword();
		} else if (isNullptrKeyword(token)) {
			token.tokenType = new NullptrKeyword();
		} else if (isNullKeyword(token)) {
			token.tokenType = new NullKeyword();
		} else if (isOpenParenthesis(token)) {
			token.tokenType = new OpenParenthesisType();
		} else if (isCloseParenthesis(token)) {
			token.tokenType = new CloseParenthesisType();
		} else if (isOpenCurlyBracket(token)) {
			token.tokenType = new OpenCurlyBracket();
		} else if (isCloseCurlyBracket(token)) {
			token.tokenType = new CloseCurlyBracket();
		} else if (isObjectIdentifier(token)) {
			token.tokenType = new ObjectIdentifier();
		} else if (isFunctionIdentifier(token)) {
			token.tokenType = new FunctionIdentifier();
		} else if (isDirective(token)) {
			token.tokenType = new Directive();
		}else if (isIdentifier(token)) {
			var identifier = getIdentifierType(token);
			token.tokenType = identifier;
			if (!identifiers.stream().anyMatch(s -> s.getName() == identifier.getName()))
				identifiers.add(identifier);
		}  else {
			token.tokenType = new UnknownType();
		}
		return token;
	}

	private Identifier getIdentifierType(Token token) {

		var identifier = identifiers.stream().filter(s -> s.getName().equals(token.value)).findAny().orElse(null);
		if (identifier != null) {
			return identifier;
		}
		if (prevDataType instanceof IntType) {
			return new IntIdentifier(token.value);
		} else if (prevDataType instanceof ShortType) {
			return new ShortIdentifier(token.value);
		} else if (prevDataType instanceof LongType) {
			return new LongIdentifier(token.value);
		} else if (prevDataType instanceof FloatType) {
			return new FloatIdentifier(token.value);
		} else if (prevDataType instanceof DoubleType) {
			return new DoubleIdentifier(token.value);
		} else if (prevDataType instanceof CharType) {
			return new CharIdentifier(token.value);
		} else if (prevDataType instanceof StringType) {
			return new StringIdentifier(token.value);
		} else if (prevDataType instanceof BoolType) {
			return new BoolIdentifier(token.value);
		} else {

			return new Identifier(token.value);

		}

	}

	private boolean isComment(Token token) {
		switch (commentMode) {
		// we are not in middle of writing a comment
		case none: {
			if (textMode != TextMode.none)// in text mode we can't have comments>>> skip
				return false;
			// detecting the start of a single-line Comment
			if (token.value.equals("/") && token.nextToken != null && token.nextToken.value.equals("/")) {
				commentMode = CommentMode.singleLine;
				return true;
			}
			// detecting the start of a multi-line comment
			if (token.value.equals("/") && token.nextToken != null && token.nextToken.value.equals("*")) {
				commentMode = CommentMode.multiLine;
				return true;
			}
			// no comment detected
			return false;
		}
		// we are writing a single-line comment
		case singleLine: {
			// detecting end of the single-line comment
			if (token.value.equals("\n") || token.value.equals("\r")) {
				commentMode = CommentMode.none;
				return false;
			}
			// the single-line comment continue
			return true;
		}
		// we are writing a multi-line comment
		case multiLine: {
			// detecting the end of the multi-line comment
			if (token.value.equals("/") && token.prevToken != null && token.prevToken.value.equals("*")) {
				commentMode = CommentMode.none;
			}
			// multi-line comment continue
			return true;
		}
		default:
			return false;
		}

	}

	private boolean isChar(Token token) {
		if (commentMode != CommentMode.none)// if comment is happening skip this>>> can't be a char
			return false;
		switch (textMode) {
		case none: {
			// detecting the start of a char
			if (token.value.equals("\'")) {
				textMode = TextMode.character;
				return true;
			}
			// no text detected
			return false;
		}
		// we are writing a string
		case string: {
			return false;
		}
		// we are writing a character
		case character: {
			// detecting the end of the character
			if (token.value.equals("\'") && token.prevToken != null && !token.prevToken.value.equals("\\")) {
				textMode = TextMode.none;
			}
			// character continue
			return true;
		}
		default:
			return false;
		}
	}

	private boolean isString(Token token) {
		if (commentMode != CommentMode.none)// if comment is happening skip this>>> can't be a char
			return false;
		switch (textMode) {
		case none: {
			// detecting the start of a string
			if (token.value.equals("\"")) {
				textMode = TextMode.string;
				return true;
			}
			// no text detected
			return false;
		}
		// we are writing a string
		case string: {
			// detecting end of the string
			if (token.value.equals("\"") && token.prevToken != null && !token.prevToken.value.equals("\\")) {
				textMode = TextMode.none;
			}
			// the string continue
			return true;
		}
		// we are writing a character
		case character: {
			return false;
		}
		default:
			return false;
		}
	}

	private boolean isDataType(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (dataTypes.contains(token.value)) {
			return true;
		}

		return false;

	}

	private DataType getDataType(Token token) {
		if (token.value.equals("int"))
			return new IntType();
		else if (token.value.equals("char"))
			return new CharType();
		else if (token.value.equals("string"))
			return new StringType();
		else if (token.value.equals("short"))
			return new ShortType();
		else if (token.value.equals("long"))
			return new LongType();
		else if (token.value.equals("float"))
			return new FloatType();
		else if (token.value.equals("double"))
			return new DoubleType();
		else if (token.value.equals("bool"))
			return new BoolType();
		else if (token.value.equals("void"))
			return new VoidType();
		else
			return new DataType();
	}

	private boolean isKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (keywords.contains(token.value)) {
			return true;
		}

		return false;

	}

	private boolean isFunctionIdentifier(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.absoluteNextToken != null && token.absoluteNextToken.value.equals("(")) {
			return true;
		}

		return false;

	}

	private boolean isObjectIdentifier(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.absolutePrevToken != null && token.absolutePrevToken.tokenType instanceof UnknownType) {
			return true;
		}

		return false;

	}

	private boolean isWhiteSpace(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals(" ") || token.value.equals("\n") || token.value.equals("\r")|| token.value.equals("\t")) {
			// after dataType
			if (token.prevToken != null && token.prevToken.tokenType instanceof DataType) {
				identifierInProgress = true;
				prevDataType = token.prevToken.tokenType;
			}

			// Pass the flag to next whiteSpace if the flag is true
			if (identifierInProgress && token.prevToken != null && token.prevToken.tokenType instanceof WhiteSpace) {
				identifierInProgress = true;
			}

			return true;
		}

		return false;

	}

	private boolean isSemicolon(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals(";")) {
			if (identifierInProgress) {
				identifierInProgress = false;
				prevDataType = null;
			}

			return true;
		}

		return false;

	}

	private boolean isIdentifier(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;

		}
		if (identifierInProgress) {
			if (Character.isDigit(token.value.charAt(0)))
				return false;
			return true;
		}
		if (identifiers.stream().anyMatch(s -> s.getName().equals(token.value)))
			return true;

		return false;

	}

	private boolean isDirective(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("#") && token.nextToken != null && directives.contains(token.nextToken.value)) {
			return true;
		}
		if (directives.contains(token.value) && token.prevToken != null && token.prevToken.value.equals("#")) {
			return true;
		}

		return false;

	}

	private boolean isEqual(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("=")) {
			return true;
		}

		return false;

	}

	private boolean isLogicalNot(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("!")) {
			return true;
		}

		return false;

	}

	private boolean isFloatingPointLiteral(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (isLongLiteral(token) && token.nextToken != null && isDot(token.nextToken)) {
			return true;
		}
		if (isDot(token) && token.prevToken != null && isLongLiteral(token.prevToken) && token.nextToken != null
				&& isLongLiteral(token.nextToken))
			return true;
		if (isDot(token) && token.prevToken != null && isLongLiteral(token.prevToken))
			return true;
		if (isLongLiteral(token) && token.prevToken != null && isDot(token.prevToken))
			return true;
		return false;
	}

	private boolean isDot(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals(".")) {
			return true;
		}
		return false;
	}

	private boolean isBoolLiteral(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("true") || token.value.equals("false")) {
			return true;
		}
		return false;

	}

	private boolean isShortLiteral(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		try {
			Short.parseShort(token.value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}

	}

	private boolean isIntLiteral(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		try {
			Integer.parseInt(token.value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}

	}

	private boolean isLongLiteral(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		try {
			Long.parseLong(token.value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}

	}

	private boolean isComma(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals(",")) {
//			if (token.prevToken != null && (token.prevToken.tokenType == TokenType.identifier
//					|| (identifierInProgress && token.prevToken.tokenType == TokenType.whiteSpace))) {
//				identifierInProgress = true;
//			}

			return true;
		}

		return false;

	}

	private boolean isOpenBracket(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("[")) {
			return true;
		}

		return false;

	}

	private boolean isCloseBracket(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("]")) {
			return true;
		}

		return false;

	}

	private boolean isOpenParenthesis(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("(")) {
			return true;
		}

		return false;

	}

	private boolean isElse(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("else")) {
			return true;
		}

		return false;

	}

	private boolean isCloseParenthesis(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals(")")) {
			return true;
		}

		return false;

	}

	private boolean isIfKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("if")) {
			return true;
		}

		return false;

	}

	private boolean isForKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("for")) {
			return true;
		}

		return false;

	}

	private boolean isWhileKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("while")) {
			return true;
		}

		return false;

	}

	private boolean isDoKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("do")) {
			return true;
		}

		return false;

	}

	private boolean isOpenCurlyBracket(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("{")) {
			return true;
		}

		return false;

	}

	private boolean isCloseCurlyBracket(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("}")) {
			return true;
		}

		return false;

	}

	private boolean isAmpersandType(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("&")) {
			return true;
		}

		return false;

	}

	private boolean isStarType(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("*") && token.prevToken != null && token.prevToken.tokenType instanceof DataType) {
			identifierInProgress = true;
			prevDataType = token.prevToken.tokenType;
			return true;
		}

		return false;

	}

	private boolean isNewKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("new")) {
			return true;
		}

		return false;

	}

	private boolean isCaseKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("case")) {
			return true;
		}

		return false;

	}

	private boolean isBreakKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("break")) {
			return true;
		}

		return false;

	}

	private boolean isColonType(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals(":")) {
			return true;
		}

		return false;

	}

	private boolean isNullptrKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("nullptr")) {
			return true;
		}

		return false;

	}

	private boolean isNullKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("NULL")) {
			return true;
		}

		return false;

	}

	private boolean isSwitchKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("switch")) {
			return true;
		}

		return false;

	}

	private boolean isDefaultKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("default")) {
			return true;
		}

		return false;

	}

	private boolean isComparisonOperation(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals(">") || token.value.equals("<")
				|| (token.value.equals("!") && token.nextToken != null && token.nextToken.value.equals("="))) {
			return true;
		}
		if (token.value.equals("=") && token.nextToken != null && token.nextToken.value.equals("="))
			return true;
		if (token.value.equals("=") && token.prevToken != null
				&& (token.prevToken.value.equals("=") || token.prevToken.value.equals("<")
						|| token.prevToken.value.equals(">") || token.prevToken.value.equals("!")))
			return true;

		return false;

	}

	private Operator getComparisonOperatorType(Token token) {
		if (token.value.equals("=") && token.nextToken != null && token.nextToken.value.equals("="))
			return new DoubleComparisonOperator();
		if (token.value.equals(">") && token.nextToken != null && token.nextToken.value.equals("="))
			return new DoubleComparisonOperator();
		if (token.value.equals("<") && token.nextToken != null && token.nextToken.value.equals("="))
			return new DoubleComparisonOperator();
		if (token.value.equals("!") && token.nextToken != null && token.nextToken.value.equals("="))
			return new DoubleComparisonOperator();
		if (token.value.equals("=") && token.prevToken != null
				&& (token.prevToken.value.equals("=") || token.prevToken.value.equals("<")
						|| token.prevToken.value.equals(">") || token.prevToken.value.equals("!")))
			return new DoubleComparisonOperator();
		if (token.value.equals(">"))
			return new SingleComparisonOperator();
		if (token.value.equals("<"))
			return new SingleComparisonOperator();
		else
			return new Operator();

	}

	private boolean isLogicalOperator(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("&") && token.nextToken != null && token.nextToken.value.equals("&"))
			return true;
		if (token.value.equals("|") && token.nextToken != null && token.nextToken.value.equals("|"))
			return true;
		if (token.value.equals("&") && token.prevToken != null && token.prevToken.value.equals("&"))
			return true;
		if (token.value.equals("|") && token.prevToken != null && token.prevToken.value.equals("|"))
			return true;

		return false;

	}

	private boolean isOperation(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("+") || token.value.equals("-") || token.value.equals("/") || token.value.equals("*")
				|| token.value.equals("%")) {
			return true;
		}

		return false;

	}

	private Operator getOperationType(Token token) {
		if (token.value.equals("+") && token.nextToken != null && token.nextToken.value.equals("+"))
			return new SingleOperandOperator();
		if (token.value.equals("-") && token.nextToken != null && token.nextToken.value.equals("-"))
			return new SingleOperandOperator();
		if (token.value.equals("+") && token.prevToken != null && token.prevToken.value.equals("+"))
			return new SingleOperandOperator();
		if (token.value.equals("-") && token.prevToken != null && token.prevToken.value.equals("-"))
			return new SingleOperandOperator();
		if (token.value.equals("+"))
			return new DoubleOperandOperator();
		if (token.value.equals("-"))
			return new DoubleOperandOperator();
		if (token.value.equals("/"))
			return new DoubleOperandOperator();
		if (token.value.equals("*"))
			return new DoubleOperandOperator();
		if (token.value.equals("%"))
			return new DoubleOperandOperator();

		return new Operator();
	}

	private boolean isTryKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("try")) {
			return true;
		}

		return false;

	}

	private boolean isCatchKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("catch")) {
			return true;
		}

		return false;

	}
	private boolean isReturnKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("return")) {
			return true;
		}

		return false;

	}
	private boolean isDeleteKeyword(Token token) {
		if (commentMode != CommentMode.none || textMode != TextMode.none) {
			return false;
		}
		if (token.value.equals("delete")) {
			return true;
		}

		return false;

	}

}
