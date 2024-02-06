package services;

import Dtos.FormattingResult;
import entities.Token;
import entities.TokenTypes.Keyword;
import entities.TokenTypes.WhiteSpace;
import entities.TokenTypes.Keywords.DefaultKeyword;
import entities.TokenTypes.Keywords.ForKeyword;
import entities.TokenTypes.Keywords.IfKeyword;
import entities.TokenTypes.Keywords.SwitchKeyword;
import entities.TokenTypes.Keywords.WhileKeyword;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.ColonType;
import entities.TokenTypes.Punctuations.EqualType;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import entities.TokenTypes.Punctuations.SemicolonType;
import interfaces.ITextFormatting;
import interfaces.ITokenHighlighter;

public class TextFormatting implements ITextFormatting {
	private boolean equalFlag;
	private boolean arrayFlag;
	private boolean forFlag;
	private boolean ifFlag;
	private boolean whileFlag;
	private boolean nextLineFlag;
	private boolean tabFlag;
	private boolean switchFlag;
	private int openParenthesisCount;
	private int tabCount;
	private ITokenHighlighter tokenHighlighter;

	public TextFormatting(ITokenHighlighter tokenHighlighter) {
		this.tokenHighlighter = tokenHighlighter;
		equalFlag = false;
		arrayFlag = false;
		forFlag = false;
		ifFlag = false;
		whileFlag = false;
		nextLineFlag = false;
		tabFlag = false;
		switchFlag = false;
		openParenthesisCount = 0;
		tabCount = 0;
	}

	public FormattingResult FormatToken(Token token) {

		var result = new FormattingResult();
		if (token.tokenType instanceof EqualType) {
			equalFlag = true;
		}
		if (token.tokenType instanceof ForKeyword) {
			forFlag = true;
		}
		if (token.tokenType instanceof IfKeyword) {
			ifFlag = true;
		}
		if (token.tokenType instanceof WhileKeyword) {
			whileFlag = true;
		}
		if (token.tokenType instanceof SwitchKeyword) {
			switchFlag = true;
		}
		if (token.tokenType instanceof OpenCurlyBracket && equalFlag) {
			arrayFlag = true;
		}
		if (forFlag && token.tokenType instanceof OpenParenthesisType) {
			openParenthesisCount++;
		}
		if (forFlag && token.tokenType instanceof CloseParenthesisType) {
			openParenthesisCount--;
			if (openParenthesisCount == 0) {
				forFlag = false;
				result.addInsertAfter(createToken("\r"));
				nextLineFlag = true;
				for (int i = 0; i < tabCount + 1; i++) {
					result.addInsertAfter(createToken("\t"));
				}
				tabFlag = true;
				return FlagCheckBeforeReturn(token, result);
			}
		}

		if (ifFlag && token.tokenType instanceof OpenParenthesisType) {
			openParenthesisCount++;
		}
		if (ifFlag && token.tokenType instanceof CloseParenthesisType) {
			openParenthesisCount--;
			if (openParenthesisCount == 0) {
				ifFlag = false;
				result.addInsertAfter(createToken("\r"));
				nextLineFlag = true;
				for (int i = 0; i < tabCount + 1; i++) {
					result.addInsertAfter(createToken("\t"));
				}
				tabFlag = true;
				return FlagCheckBeforeReturn(token, result);
			}
		}

		if (whileFlag && token.tokenType instanceof OpenParenthesisType) {
			openParenthesisCount++;
		}
		if (whileFlag && token.tokenType instanceof CloseParenthesisType) {
			openParenthesisCount--;
			if (openParenthesisCount == 0) {
				whileFlag = false;
				result.addInsertAfter(createToken("\r"));
				nextLineFlag = true;
				for (int i = 0; i < tabCount + 1; i++) {
					result.addInsertAfter(createToken("\t"));
				}
				tabFlag = true;
				return FlagCheckBeforeReturn(token, result);
			}
		}

//		if (token.tokenType instanceof DoubleOperandOperator) {
//			result.addInsertBefore(createToken(" "));
//			result.addInsertAfter(createToken(" "));
//			return FlagCheckBeforeReturn(token, result);
//		}
		if (token.tokenType instanceof Keyword) {
			result.addInsertAfter(createToken(" "));
			return FlagCheckBeforeReturn(token, result);
		}
		if (token.tokenType instanceof ColonType && switchFlag) {
			result.addInsertAfter(createToken("\r"));
			for (int i = 0; i < tabCount + 1; i++) {
				result.addInsertAfter(createToken("\t"));
			}
			tabFlag = true;
			return FlagCheckBeforeReturn(token, result);
		}

		if (token.tokenType instanceof SemicolonType && !forFlag) {
			result.addInsertAfter(createToken("\r"));
			nextLineFlag = true;
			for (int i = 0; i < tabCount; i++) {
				result.addInsertAfter(createToken("\t"));
			}
			tabFlag = true;
			return FlagCheckBeforeReturn(token, result);
		}

		if (token.tokenType instanceof OpenCurlyBracket && !equalFlag) {
			if (!nextLineFlag) {
				result.addInsertBefore(createToken("\r"));
				for (int i = 0; i < tabCount; i++) {
					result.addInsertBefore(createToken("\t"));
				}

			}
			if (tabFlag) {
				result.setRemoveBefore(1);
			}
			tabCount++;
			result.addInsertAfter(createToken("\r"));
			nextLineFlag = true;
			for (int i = 0; i < tabCount; i++) {
				result.addInsertAfter(createToken("\t"));
			}

			tabFlag = true;
			return FlagCheckBeforeReturn(token, result);
		}
		if (token.tokenType instanceof CloseCurlyBracket && !arrayFlag) {
			tabCount--;
			if (!nextLineFlag) {
				result.addInsertBefore(createToken("\r"));
			}
			if (!tabFlag) {
				for (int i = 0; i < tabCount; i++) {
					result.addInsertBefore(createToken("\t"));
				}
			} else {
				result.setRemoveBefore(1);
			}
			result.addInsertAfter(createToken("\r"));
			nextLineFlag = true;

			return FlagCheckBeforeReturn(token, result);
		}

		return FlagCheckBeforeReturn(token, result);
	}

	private FormattingResult FlagCheckBeforeReturn(Token token, FormattingResult result) {
		if (!(token.tokenType instanceof EqualType)) {
			equalFlag = false;
		}
		if ((token.tokenType instanceof DefaultKeyword)) {
			switchFlag = false;
		}
		if (token.tokenType instanceof CloseCurlyBracket) {
			arrayFlag = false;
		}
		if (!(token.tokenType instanceof SemicolonType) && !(token.tokenType instanceof OpenCurlyBracket)
				&& !(token.tokenType instanceof CloseCurlyBracket)
				&& !(token.tokenType instanceof CloseParenthesisType)) {
			nextLineFlag = false;
		}
		if (!(token.tokenType instanceof SemicolonType) && !(token.tokenType instanceof OpenCurlyBracket)
				&& !(token.tokenType instanceof CloseParenthesisType)) {
			tabFlag = false;
		}

		return result;
	}

	private Token createToken(String value) {
		var token = new Token(value, new WhiteSpace());
		tokenHighlighter.HighlightToken(token);
		return token;
	}
}
