package Dtos;

import java.util.UUID;

import enums.DataTypes;
import enums.GrammarStatus;
import grammars.Grammar;

public class ParsingObject implements Cloneable {
	public Grammar grammar;
	public UUID currentNodeId;
	public GrammarStatus grammarStatus;
	public String error;
	public int progressCounter;
	public DataTypes dataType;


	public ParsingObject(Grammar grammar) {
		this.grammar = grammar;
		this.currentNodeId = grammar.rootNodeId;
		this.grammarStatus = GrammarStatus.processing;
		this.progressCounter = 0;
		dataType=null;

	}
	public ParsingObject(Grammar grammar, UUID currentNodeId) {
		this.grammar = grammar;
		this.currentNodeId = currentNodeId;
		this.grammarStatus = GrammarStatus.processing;
		this.progressCounter = 0;
		dataType=null;

	}

	public ParsingObject(Grammar grammar, UUID currentNodeId, GrammarStatus grammarStatus, String error,
			int progressCounter, DataTypes dataType) {
		this.grammar = grammar;
		this.currentNodeId = currentNodeId;
		this.grammarStatus = GrammarStatus.processing;
		this.grammarStatus = grammarStatus;
		this.error = error;
		this.progressCounter = progressCounter;
		this.dataType=dataType;

	}

	public int getProgressCounter() {
		return progressCounter;
	}

	@Override
	public ParsingObject clone() {
		return new ParsingObject(this.grammar.clone(), this.currentNodeId, this.grammarStatus, this.error,
				this.progressCounter,this.dataType);
	}
}
