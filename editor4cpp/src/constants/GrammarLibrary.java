package constants;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import Dtos.ParsingObject;
import entities.TokenTypes.DataType;
import entities.TokenTypes.FloatingPointLiteral;
import entities.TokenTypes.DataTypes.BoolType;
import entities.TokenTypes.DataTypes.CharType;
import entities.TokenTypes.DataTypes.DoubleType;
import entities.TokenTypes.DataTypes.FloatType;
import entities.TokenTypes.DataTypes.IntType;
import entities.TokenTypes.DataTypes.LongType;
import entities.TokenTypes.DataTypes.ShortType;
import entities.TokenTypes.DataTypes.StringType;
import entities.TokenTypes.Identifiers.BoolIdentifier;
import entities.TokenTypes.Identifiers.CharIdentifier;
import entities.TokenTypes.Identifiers.DoubleIdentifier;
import entities.TokenTypes.Identifiers.FloatIdentifier;
import entities.TokenTypes.Identifiers.IntIdentifier;
import entities.TokenTypes.Identifiers.LongIdentifier;
import entities.TokenTypes.Identifiers.ShortIdentifier;
import entities.TokenTypes.Identifiers.StringIdentifier;
import entities.TokenTypes.Literals.BoolLiteral;
import entities.TokenTypes.Literals.CharLiteral;
import entities.TokenTypes.Literals.IntLiteral;
import entities.TokenTypes.Literals.LongLiteral;
import entities.TokenTypes.Literals.ShortLiteral;
import entities.TokenTypes.Literals.StringLiteral;
import grammars.AssignmentGrammar;
import grammars.ComparisonGrammar;
import grammars.Grammar;
import grammars.IfGrammar;
import grammars.VariableDeclarationGrammar;

public class GrammarLibrary {
	public static List<Grammar> grammars = Arrays.asList(
			new AssignmentGrammar(new ShortType(), new ShortIdentifier(""), new ShortLiteral()),
			new AssignmentGrammar(new IntType(), new IntIdentifier(""), new ShortLiteral()),
			new AssignmentGrammar(new IntType(), new IntIdentifier(""), new IntLiteral()),
			new AssignmentGrammar(new LongType(), new LongIdentifier(""), new ShortLiteral()),
			new AssignmentGrammar(new LongType(), new LongIdentifier(""), new IntLiteral()),
			new AssignmentGrammar(new LongType(), new LongIdentifier(""), new LongLiteral()),
			new AssignmentGrammar(new FloatType(), new FloatIdentifier(""), new ShortLiteral()),
			new AssignmentGrammar(new FloatType(), new FloatIdentifier(""), new IntLiteral()),
			new AssignmentGrammar(new FloatType(), new FloatIdentifier(""), new LongLiteral()),
			new AssignmentGrammar(new FloatType(), new FloatIdentifier(""), new FloatingPointLiteral()),
			new AssignmentGrammar(new DoubleType(), new DoubleIdentifier(""), new ShortLiteral()),
			new AssignmentGrammar(new DoubleType(), new DoubleIdentifier(""), new IntLiteral()),
			new AssignmentGrammar(new DoubleType(), new DoubleIdentifier(""), new LongLiteral()),
			new AssignmentGrammar(new DoubleType(), new DoubleIdentifier(""), new FloatingPointLiteral()),
			new AssignmentGrammar(new StringType(), new StringIdentifier(""), new StringLiteral()),
			new AssignmentGrammar(new CharType(), new CharIdentifier(""), new CharLiteral()),
			new AssignmentGrammar(new BoolType(), new BoolIdentifier(""), new BoolLiteral()),
			new VariableDeclarationGrammar(new ShortType(), new ShortIdentifier(""), new ShortLiteral()),
			new VariableDeclarationGrammar(new IntType(), new IntIdentifier(""), new ShortLiteral()),
			new VariableDeclarationGrammar(new IntType(), new IntIdentifier(""), new IntLiteral()),
			new VariableDeclarationGrammar(new LongType(), new LongIdentifier(""), new ShortLiteral()),
			new VariableDeclarationGrammar(new LongType(), new LongIdentifier(""), new IntLiteral()),
			new VariableDeclarationGrammar(new LongType(), new LongIdentifier(""), new LongLiteral()),
			new VariableDeclarationGrammar(new FloatType(), new FloatIdentifier(""), new ShortLiteral()),
			new VariableDeclarationGrammar(new FloatType(), new FloatIdentifier(""), new IntLiteral()),
			new VariableDeclarationGrammar(new FloatType(), new FloatIdentifier(""), new LongLiteral()),
			new VariableDeclarationGrammar(new FloatType(), new FloatIdentifier(""), new FloatingPointLiteral()),
			new VariableDeclarationGrammar(new DoubleType(), new DoubleIdentifier(""), new ShortLiteral()),
			new VariableDeclarationGrammar(new DoubleType(), new DoubleIdentifier(""), new IntLiteral()),
			new VariableDeclarationGrammar(new DoubleType(), new DoubleIdentifier(""), new LongLiteral()),
			new VariableDeclarationGrammar(new DoubleType(), new DoubleIdentifier(""), new FloatingPointLiteral()),
			new VariableDeclarationGrammar(new StringType(), new StringIdentifier(""), new StringLiteral()),
			new VariableDeclarationGrammar(new CharType(), new CharIdentifier(""), new CharLiteral()),
			new VariableDeclarationGrammar(new BoolType(), new BoolIdentifier(""), new BoolLiteral()),
			new ComparisonGrammar(), new IfGrammar());

	public static List<ParsingObject> getParsingObjectsOfComparison() {
		return grammars.stream().filter(s -> s.getClass() == ComparisonGrammar.class )
				.map(a -> new ParsingObject(a.clone(), a.rootNodeId)).collect(Collectors.toList());
	}

	public static Optional<Grammar> getGrammarById(int id) {
		return grammars.stream().filter(s -> s.Id == id).findFirst();
	}

	public static List<ParsingObject> getParsingObjectsOfAssignment(DataType datatype) {
		var list=grammars.stream().filter(s -> s.getClass() == AssignmentGrammar.class && ((AssignmentGrammar)s).dataType.getClass()==datatype.getClass())
		.map(a -> new ParsingObject(a.clone(), a.rootNodeId)).collect(Collectors.toList());
		return list;
	}

	public static List<ParsingObject> getParsingObjectsOfAll() {
		var list=grammars.stream().map(a -> new ParsingObject(a.clone(), a.rootNodeId)).collect(Collectors.toList());
		return list;
	}
}
