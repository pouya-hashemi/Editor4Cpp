//package parsers;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import Dtos.GrammarCheckResult;
//import entities.Token;
//import grammars.Grammar;
//
//
//public class GrammarParser {
//
//	private List<Grammar> defaultGrammars;
//	private List<Grammar> grammars;
//
//	public GrammarParser(List<Grammar> defaultGrammars) {
//		this.defaultGrammars=new ArrayList<> (defaultGrammars);
//		initGrammars();
//	}
//
//	public GrammarCheckResult ParseToken(Token token) {
//
//		var resultHistory = new ArrayList<GrammarCheckResult>();
//
//		Iterator<Grammar> grammarIterator = grammars.iterator();
//
//		while (grammarIterator.hasNext()) {
//			var grammar = grammarIterator.next();
//			var result = grammar.check(token);
//			resultHistory.add(result);
//			if (!result.canProceed) {
//				grammarIterator.remove();
//			}
//		}
//
//		var result = new GrammarCheckResult();
//
//		if (!grammars.isEmpty()) {
//
//			result.canProceed = true;
//			result.error = null;
//			result.isEndOfStatement = false;
//
//		} else {
//			if (resultHistory.stream().anyMatch(s -> s.isEndOfStatement)) {
//				result.canProceed = false;
//				result.error = null;
//				result.isEndOfStatement = true;
//			} else {
//				var faultyResult = resultHistory.stream().filter(s -> s.error != null && s.error.length() > 0)
//						.findFirst();
//				if (faultyResult.isPresent()) {
//					result.canProceed = false;
//					result.error = faultyResult.get().error;
//					result.isEndOfStatement = false;
//				}
//			}
//			initGrammars();
//		}
//		return result;
//
//	}
//
//	public void initGrammars() {
//		grammars=new ArrayList<Grammar>();
//		for(Grammar grammar:defaultGrammars)
//			grammars.add(grammar.clone());
//	}
//}
