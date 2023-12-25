package constants;

import java.util.ArrayList;
import java.util.List;
import Dtos.ParsingObject;
import grammars.AssignmentGrammar;
import grammars.ComparisonGrammar;
import grammars.DoWhileGrammar;
import grammars.ForGrammar;
import grammars.IfGrammar;
import grammars.PointerAssignmentGrammar;
import grammars.PointerDeclarationGrammar;
import grammars.SwitchGrammar;
import grammars.VariableDeclarationGrammar;
import grammars.WhileGrammar;
import grammars.subGrammars.CaseSubGrammar;
import grammars.subGrammars.DeclartionSubGrammar;
import grammars.subGrammars.EqualSubGrammar;
import grammars.subGrammars.MathematikOperationSubGrammar;
import grammars.subGrammars.MathematikOperationTopLayerSubGrammar;
import grammars.subGrammars.PointerEqualSubGrammar;

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
		

		return list;
	}
}
