package enums;

public enum TokenType {
	keyword, dataType, identifier, numericLiteral, textLiteral, operator, punctuation, whiteSpace, comment, undefined,
	directive, semicolon, comma, equal, openBracket, closeBracket, openCurlyBracket, closeCurlyBracket;

	public String getError() {
		switch (this) {
		case keyword:
			return "Expected a keyword";
		case dataType:
			return "Expected a dataType";
		case identifier:
			return "Expected a identifier";
		case numericLiteral:
			return "Expected a number";
		case textLiteral:
			return "Expected a text";
		case operator:
			return "Expected a operator";
		case punctuation:
			return "Expected a punctuation";
		case whiteSpace:
			return "Expected a whiteSpace";
		case comment:
			return "Expected a comment";
		case semicolon:
			return "missing ;";
		case comma:
			return "missing ,";
		case equal:
			return "missing =";
		case openBracket:
			return "missing [";
		case closeBracket:
			return "missing ]";
		case openCurlyBracket:
			return "missing {";
		case closeCurlyBracket:
			return "missing }";
		default:
			return "";

		}

	}
}
