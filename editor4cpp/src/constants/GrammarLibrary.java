package constants;

import java.util.ArrayList;
import java.util.List;
import Dtos.ParsingObject;
import grammars.ArrayGrammar;
import grammars.AssignmentGrammar;
import grammars.BooleanAssignmentGrammar;
import grammars.BracketGrammar;
import grammars.ComparisonGrammar;
import grammars.DeleteGrammar;
import grammars.DoWhileGrammar;
import grammars.SemicolonGrammar;
import grammars.ForGrammar;
import grammars.FunctionCallGrammar;
import grammars.IfGrammar;
//import grammars.NamespaceGrammar;
//import grammars.NamespaceSubGrammar;
import grammars.PointerAccessGrammar;
import grammars.PointerAssignmentGrammar;
import grammars.PointerDeclarationGrammar;
import grammars.ReturnGrammar;
import grammars.SwitchGrammar;
import grammars.TryCatchGrammar;
import grammars.VariableDeclarationGrammar;
import grammars.GenericDeclarationGrammar;
import grammars.WhileGrammar;
import grammars.subGrammars.ArrayValueSubGrammar;
import grammars.subGrammars.CaseSubGrammar;
import grammars.subGrammars.DeclartionSubGrammar;
import grammars.subGrammars.DynamicAllocationSubGrammar;
import grammars.subGrammars.EqualSubGrammar;
import grammars.subGrammars.FunctionCallSubGrammar;
import grammars.subGrammars.FunctionParametersSubGrammar;
import grammars.subGrammars.GenericDataTypeSubGrammar;
import grammars.subGrammars.MathematikOperationSubGrammar;
import grammars.subGrammars.MathematikOperationTopLayerSubGrammar;
import grammars.subGrammars.PointerAccessSubGrammar;
import grammars.subGrammars.PointerAccessWithoutIdentifierSubGrammar;
import grammars.subGrammars.PointerEqualSubGrammar;
import grammars.subGrammars.PreNamespaceSubGrammar;

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
	public static List<ParsingObject> getParsingObjectsOfDeclartionSubGrammar(boolean hasSemicolon) {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new DeclartionSubGrammar(hasSemicolon)));
		return list;
	}
	
	public static List<ParsingObject> getParsingObjectsOfIf() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new IfGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfFor() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new ForGrammar()));
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


	public static List<ParsingObject> getParsingObjectsOfAssignment(boolean hasSemicolon) {
		var list = new ArrayList<ParsingObject>();

		list.add(new ParsingObject(new AssignmentGrammar(hasSemicolon)));

		return list;
	}

	public static List<ParsingObject> getParsingObjectsOfVariableDeclaration(boolean hasSemicolon) {
		var list = new ArrayList<ParsingObject>();

		list.add(new ParsingObject(new VariableDeclarationGrammar(hasSemicolon)));

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
	public static List<ParsingObject> getParsingObjectsOfFunctionCallSubGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new FunctionCallSubGrammar()));
		return list;
	}
	
	public static List<ParsingObject> getParsingObjectsOfPointerEqualSubGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new PointerEqualSubGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfPointerAssignmentGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new PointerAssignmentGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfPointerDeclarationGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new PointerDeclarationGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfCaseSubGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new CaseSubGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfSwitchGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new SwitchGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfArrayValueSubGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new ArrayValueSubGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfArrayGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new ArrayGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfFunctionCallGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new FunctionCallGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfTryCatchGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new TryCatchGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfReturnGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new ReturnGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfDeleteGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new DeleteGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfSemicolonGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new SemicolonGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfVectorGrammar(boolean hasSemicolon) {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new GenericDeclarationGrammar(hasSemicolon)));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfBracketGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new BracketGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfPointerAccessSubGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new PointerAccessSubGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfPointerAccessSGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new PointerAccessGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfPreNamespaceSubGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new PreNamespaceSubGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfGenericDataTypeSubGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new GenericDataTypeSubGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfDynamicAllocationSubGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new DynamicAllocationSubGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfBooleanAssignmentGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new BooleanAssignmentGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfFunctionParametersSubGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new FunctionParametersSubGrammar()));
		return list;
	}
	public static List<ParsingObject> getParsingObjectsOfPointerAccessWithoutIdentifierSubGrammar() {
		var list = new ArrayList<ParsingObject>();
		list.add(new ParsingObject(new PointerAccessWithoutIdentifierSubGrammar()));
		return list;
	}

	public static List<ParsingObject> getParsingObjectsOfAll() {
		var list = new ArrayList<ParsingObject>();

		list.addAll(getParsingObjectsOfAssignment(true));
		list.addAll(getParsingObjectsOfVariableDeclaration(true));
		list.addAll(getParsingObjectsOfIf());
		list.addAll(getParsingObjectsOfWhile());
		list.addAll(getParsingObjectsOfDoWhile());
		list.addAll(getParsingObjectsOfFor());
		list.addAll(getParsingObjectsOfPointerAssignmentGrammar());
		list.addAll(getParsingObjectsOfPointerDeclarationGrammar());
		list.addAll(getParsingObjectsOfSwitchGrammar());
		list.addAll(getParsingObjectsOfArrayGrammar());
		list.addAll(getParsingObjectsOfFunctionCallGrammar());
		list.addAll(getParsingObjectsOfTryCatchGrammar());
		list.addAll(getParsingObjectsOfReturnGrammar());
		list.addAll(getParsingObjectsOfDeleteGrammar());
		list.addAll(getParsingObjectsOfSemicolonGrammar());
//		list.addAll(getParsingObjectsOfNamespaceGrammar());
		list.addAll(getParsingObjectsOfVectorGrammar(true));
		list.addAll(getParsingObjectsOfBracketGrammar());
		list.addAll(getParsingObjectsOfPointerAccessSGrammar());
		list.addAll(getParsingObjectsOfBooleanAssignmentGrammar());
		
		return list;
	}
}
