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
import entities.TokenTypes.Operations.DoubleOperandOperator;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.ColonType;
import entities.TokenTypes.Punctuations.EqualType;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import entities.TokenTypes.Punctuations.SemicolonType;

public class TextFormatting {
	private boolean equalFlag = false;
	private boolean arrayFlag = false;
	private boolean forFlag = false;
	private boolean ifFlag = false;
	private boolean whileFlag = false;
	private boolean nextLineFlag = false;
	private boolean tabFlag = false;
	private boolean switchFlag = false;
	private int openParenthesisCount=0;
	private int tabCount = 0;
	private TokenHighlighter tokenHighlighter = new TokenHighlighter();

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
		if(forFlag && token.tokenType instanceof OpenParenthesisType) {
			openParenthesisCount++;
		}
		if(forFlag && token.tokenType instanceof CloseParenthesisType) {
			openParenthesisCount--;
			if(openParenthesisCount==0) {
				forFlag=false;
				result.addInsertAfter(createToken("\r"));
				for (int i = 0; i < tabCount+1; i++) {
					result.addInsertAfter(createToken("\t"));
				}
				tabFlag = true;
				return FlagCheckBeforeReturn(token, result);				
			}
		}

		
		
		if(ifFlag && token.tokenType instanceof OpenParenthesisType) {
			openParenthesisCount++;
		}
		if(ifFlag && token.tokenType instanceof CloseParenthesisType) {
			openParenthesisCount--;
			if(openParenthesisCount==0) {
				ifFlag=false;
				result.addInsertAfter(createToken("\r"));
				for (int i = 0; i < tabCount+1; i++) {
					result.addInsertAfter(createToken("\t"));
				}
				tabFlag = true;
				return FlagCheckBeforeReturn(token, result);				
			}
		}
		
		
		if(whileFlag && token.tokenType instanceof OpenParenthesisType) {
			openParenthesisCount++;
		}
		if(whileFlag && token.tokenType instanceof CloseParenthesisType) {
			openParenthesisCount--;
			if(openParenthesisCount==0) {
				whileFlag=false;
				result.addInsertAfter(createToken("\r"));
				for (int i = 0; i < tabCount+1; i++) {
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
			for (int i = 0; i < tabCount+1; i++) {
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
			}
			else {
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
				&& !(token.tokenType instanceof CloseCurlyBracket)) {
			nextLineFlag = false;
		}
		if (!(token.tokenType instanceof SemicolonType) && !(token.tokenType instanceof OpenCurlyBracket)) {
			tabFlag = false;
		}

		
		
		return result;
	}

	private Token createToken(String value) {
		var token = new Token(value, new WhiteSpace());
		token = tokenHighlighter.HighlightToken(token);
		return token;
	}
}
