package constants;

import java.util.ArrayList;
import java.util.List;
import Dtos.ParsingObject;
import grammars.AssignmentGrammar;
import grammars.ComparisonGrammar;
import grammars.DoWhileGrammar;
import grammars.IfGrammar;
import grammars.VariableDeclarationGrammar;
import grammars.WhileGrammar;
import grammars.subGrammars.DeclartionSubGrammar;
import grammars.subGrammars.EqualSubGrammar;
import grammars.subGrammars.MathematikOperationSubGrammar;
import grammars.subGrammars.MathematikOperationTopLayerSubGrammar;

public class GrammarLibrary {


	public static List<ParsingObject> getParsingObjectsOfComparison() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new ComparisonGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfEqualSubGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new EqualSubGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfDeclartionSubGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new DeclartionSubGrammar()));
		return list;
	}
	
	public static List<ParsingObject> getParsingObjectsOfIf() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new IfGrammar()));
		return list;
	}

	public static List<ParsingObject> getParsingObjectsOfWhile() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new WhileGrammar()));
		return list;
	}

	public static List<ParsingObject> getParsingObjectsOfDoWhile() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new DoWhileGrammar()));
		return list;
	}

//	public static Optional<Grammar> getGrammarById(int id) {
//		return grammars.stream().filter(s -> s.Id == id).findFirst();
//	}

	public static List<ParsingObject> getParsingObjectsOfAssignment() {
		var list = new ArrayList<ParsingObject>();

		list.add(new ParsingObject(new AssignmentGrammar()));

		return list;
	}

	public static List<ParsingObject> getParsingObjectsOfVariableDeclaration() {
		var list = new ArrayList<ParsingObject>();

		list.add(new ParsingObject(new VariableDeclarationGrammar()));

		return list;
	}

	public static List<ParsingObject> getParsingObjectsOfMathematikOperationSubGrammar() {

		var list = new ArrayList<ParsingObject>();

		list.add(new ParsingObject(new MathematikOperationSubGrammar()));

		return list;

	}

	public static List<ParsingObject> getParsingObjectsOfMathematikOperationTopLayerSubGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new MathematikOperationTopLayerSubGrammar()));
		return list;
	}
//	public static List<ParsingObject> getParsingObjectsOfAssignmentSubGrammar(DataType dataType) {
//
//		var list = new ArrayList<ParsingObject>();
//
//		if (dataType == null || dataType.getClass() == ShortType.class) {
//			list.add(new ParsingObject(new AssignmentSubGrammar(new ShortType(),
//					new ShortIdentifier(""), new ShortLiteral())));
//		}
//
//		if (dataType == null || dataType.getClass() == IntType.class) {
//			list.add(new ParsingObject(new AssignmentSubGrammar(new IntType(), new IntIdentifier(""),
//					new ShortLiteral())));
//			list.add(new ParsingObject(
//					new AssignmentSubGrammar(new IntType(), new IntIdentifier(""), new IntLiteral())));
//		}
//
//		if (dataType == null || dataType.getClass() == LongType.class) {
//			list.add(new ParsingObject(new AssignmentSubGrammar(new LongType(), new LongIdentifier(""),
//					new ShortLiteral())));
//			list.add(new ParsingObject(new AssignmentSubGrammar(new LongType(), new LongIdentifier(""),
//					new IntLiteral())));
//			list.add(new ParsingObject(new AssignmentSubGrammar(new LongType(), new LongIdentifier(""),
//					new LongLiteral())));
//		}
//
//		if (dataType == null || dataType.getClass() == FloatType.class) {
//			list.add(new ParsingObject(new AssignmentSubGrammar(new FloatType(),
//					new FloatIdentifier(""), new ShortLiteral())));
//			list.add(new ParsingObject(new AssignmentSubGrammar(new FloatType(),
//					new FloatIdentifier(""), new IntLiteral())));
//			list.add(new ParsingObject(new AssignmentSubGrammar(new FloatType(),
//					new FloatIdentifier(""), new LongLiteral())));
//			list.add(new ParsingObject(new AssignmentSubGrammar(new FloatType(),
//					new FloatIdentifier(""), new FloatingPointLiteral())));
//		}
//
//		if (dataType == null || dataType.getClass() == DoubleType.class) {
//			list.add(new ParsingObject(new AssignmentSubGrammar(new DoubleType(),
//					new DoubleIdentifier(""), new ShortLiteral())));
//			list.add(new ParsingObject(new AssignmentSubGrammar(new DoubleType(),
//					new DoubleIdentifier(""), new IntLiteral())));
//			list.add(new ParsingObject(new AssignmentSubGrammar(new DoubleType(),
//					new DoubleIdentifier(""), new LongLiteral())));
//			list.add(new ParsingObject(new AssignmentSubGrammar(new DoubleType(),
//					new DoubleIdentifier(""), new FloatingPointLiteral())));
//		}
//
//		if (dataType == null || dataType.getClass() == StringType.class) {
//			list.add(new ParsingObject(new AssignmentSubGrammar(new StringType(),
//					new StringIdentifier(""), new StringLiteral())));
//		}
//		if (dataType == null || dataType.getClass() == CharType.class) {
//			list.add(new ParsingObject(new AssignmentSubGrammar(new CharType(), new CharIdentifier(""),
//					new CharLiteral())));
//		}
//
//		if (dataType == null || dataType.getClass() == CharType.class) {
//			list.add(new ParsingObject(new AssignmentSubGrammar(new BoolType(), new BoolIdentifier(""),
//					new BoolLiteral())));
//		}
//
//		return list;
//
//	}

//	public static List<ParsingObject> getParsingObjectsOfAssignmentSubGrammar(DataType datatype) {
//		var list = grammars.stream()
//				.filter(s -> s.getClass() == AssignmentSubGrammar.class
//						&& ((AssignmentSubGrammar) s).dataType.getClass() == datatype.getClass())
//				.map(a -> new ParsingObject(a.clone(), a.rootNodeId)).collect(Collectors.toList());
//		return list;
//	}
//	

	public static List<ParsingObject> getParsingObjectsOfAll() {
		var list = new ArrayList<ParsingObject>();

		list.addAll(getParsingObjectsOfAssignment());
		list.addAll(getParsingObjectsOfVariableDeclaration());
		list.addAll(getParsingObjectsOfIf());
		list.addAll(getParsingObjectsOfWhile());
		list.addAll(getParsingObjectsOfDoWhile());

		return list;
	}
}
