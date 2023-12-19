package services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entities.*;
import entities.TokenTypes.*;
import entities.TokenTypes.DataTypes.*;
import entities.TokenTypes.Identifiers.*;
import entities.TokenTypes.Literals.*;
import entities.TokenTypes.Punctuations.SemicolonType;
import interfaces.IParser;
import parsers.CurlyBracketParser;
import parsers.IfStatementParser;
import parsers.VariableDeclaration;

public class ErrorDetecter {
	private List<IParser> parsingTrees;
	private List<IParser> currentParsingTrees;

	public ErrorDetecter() {
		parsingTrees = new ArrayList<IParser>();
		parsingTrees.add(new VariableDeclaration(new ShortType(), new ShortIdentifier(""), new ShortLiteral(), false));

		parsingTrees.add(new VariableDeclaration(new IntType(), new IntIdentifier(""), new ShortLiteral(), false));
		parsingTrees.add(new VariableDeclaration(new IntType(), new IntIdentifier(""), new IntLiteral(), false));

		parsingTrees.add(new VariableDeclaration(new LongType(), new LongIdentifier(""), new ShortLiteral(), false));
		parsingTrees.add(new VariableDeclaration(new LongType(), new LongIdentifier(""), new IntLiteral(), false));
		parsingTrees.add(new VariableDeclaration(new LongType(), new LongIdentifier(""), new LongLiteral(), false));

		parsingTrees.add(new VariableDeclaration(new FloatType(), new FloatIdentifier(""), new ShortLiteral(), false));
		parsingTrees.add(new VariableDeclaration(new FloatType(), new FloatIdentifier(""), new IntLiteral(), false));
		parsingTrees.add(new VariableDeclaration(new FloatType(), new FloatIdentifier(""), new LongLiteral(), false));
		parsingTrees.add(
				new VariableDeclaration(new FloatType(), new FloatIdentifier(""), new FloatingPointLiteral(), false));

		parsingTrees
				.add(new VariableDeclaration(new DoubleType(), new DoubleIdentifier(""), new ShortLiteral(), false));
		parsingTrees.add(new VariableDeclaration(new DoubleType(), new DoubleIdentifier(""), new IntLiteral(), false));
		parsingTrees.add(new VariableDeclaration(new DoubleType(), new DoubleIdentifier(""), new LongLiteral(), false));
		parsingTrees.add(
				new VariableDeclaration(new DoubleType(), new DoubleIdentifier(""), new FloatingPointLiteral(), false));

		parsingTrees
				.add(new VariableDeclaration(new StringType(), new StringIdentifier(""), new StringLiteral(), false));
		parsingTrees.add(new VariableDeclaration(new CharType(), new CharIdentifier(""), new CharLiteral(), false));

		parsingTrees.add(new VariableDeclaration(new BoolType(), new BoolIdentifier(""), new BoolLiteral(), false));

		// -----------------------------------------------------------------------------------------------
//		parsingTrees.add(new SimpleAssignmentParser(new IntIdentifier(""),new ShortLiteral()));
//		parsingTrees.add(new SimpleAssignmentParser(new IntIdentifier(""),new IntLiteral()));
		parsingTrees.add(new VariableDeclaration(new ShortType(), new ShortIdentifier(""), new ShortLiteral(), true));

		parsingTrees.add(new VariableDeclaration(new IntType(), new IntIdentifier(""), new ShortLiteral(), true));
		parsingTrees.add(new VariableDeclaration(new IntType(), new IntIdentifier(""), new IntLiteral(), true));

		parsingTrees.add(new VariableDeclaration(new LongType(), new LongIdentifier(""), new ShortLiteral(), true));
		parsingTrees.add(new VariableDeclaration(new LongType(), new LongIdentifier(""), new IntLiteral(), true));
		parsingTrees.add(new VariableDeclaration(new LongType(), new LongIdentifier(""), new LongLiteral(), true));

		parsingTrees.add(new VariableDeclaration(new FloatType(), new FloatIdentifier(""), new ShortLiteral(), true));
		parsingTrees.add(new VariableDeclaration(new FloatType(), new FloatIdentifier(""), new IntLiteral(), true));
		parsingTrees.add(new VariableDeclaration(new FloatType(), new FloatIdentifier(""), new LongLiteral(), true));
		parsingTrees.add(
				new VariableDeclaration(new FloatType(), new FloatIdentifier(""), new FloatingPointLiteral(), true));

		parsingTrees.add(new VariableDeclaration(new DoubleType(), new DoubleIdentifier(""), new ShortLiteral(), true));
		parsingTrees.add(new VariableDeclaration(new DoubleType(), new DoubleIdentifier(""), new IntLiteral(), true));
		parsingTrees.add(new VariableDeclaration(new DoubleType(), new DoubleIdentifier(""), new LongLiteral(), true));
		parsingTrees.add(
				new VariableDeclaration(new DoubleType(), new DoubleIdentifier(""), new FloatingPointLiteral(), true));

		parsingTrees
				.add(new VariableDeclaration(new StringType(), new StringIdentifier(""), new StringLiteral(), true));
		parsingTrees.add(new VariableDeclaration(new CharType(), new CharIdentifier(""), new CharLiteral(), true));

		parsingTrees.add(new VariableDeclaration(new BoolType(), new BoolIdentifier(""), new BoolLiteral(), true));
		parsingTrees.add(new IfStatementParser());
		parsingTrees.add(new CurlyBracketParser());
	}

	public Token CheckForError(Token token) {

		if (currentParsingTrees == null || currentParsingTrees.isEmpty()) {
			currentParsingTrees = new ArrayList<>(parsingTrees);
		}
		var treesBefore = new ArrayList<>(currentParsingTrees);
		Iterator<IParser> parsingIterator = currentParsingTrees.iterator();
		String error = null;
		while (parsingIterator.hasNext()) {
			var parsingTree = parsingIterator.next();
			error = parsingTree.parseToken(token);
			if (error != null && error.length() > 0 || parsingTree.isEnd()) {
				parsingIterator.remove();

			}
		}
		if (currentParsingTrees.isEmpty() && error != null) {
			token.error = error;
		}
//		if (token.tokenType instanceof SemicolonType||token.error !=null||(currentParsingTrees.isEmpty()&& error==null) ) {
//			currentParsingTrees =new ArrayList<>( parsingTrees);
//			for(IParser tree:currentParsingTrees) {
//				tree.resetTree();
//			}
//		}
		if (currentParsingTrees.isEmpty() && treesBefore.stream().anyMatch(s -> s.isEnd())) {
			currentParsingTrees = new ArrayList<>(parsingTrees);
			for (IParser tree : currentParsingTrees) {
				tree.resetTree();
			}
		}

		return token;
	}
}
