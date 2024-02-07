package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.NonTerminalNode;
import entities.TerminalNode;
import entities.TokenTypes.DataType;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Literal;
import entities.TokenTypes.ObjectIdentifier;
import entities.TokenTypes.VectorType;
import entities.TokenTypes.Keywords.ReturnKeyword;
import entities.TokenTypes.Operations.GreaterThanOperator;
import entities.TokenTypes.Operations.LessThanOperator;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.CommaType;
import entities.TokenTypes.Punctuations.EqualType;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
import entities.TokenTypes.Punctuations.SemicolonType;

public class VectorDeclarationGrammar extends Grammar {
	
	public VectorDeclarationGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	public VectorDeclarationGrammar() {
		super();

		initGrammar(true);
		
	}
	public VectorDeclarationGrammar(boolean hasSemicolon) {
		super();

		initGrammar(hasSemicolon);
		
	}
	private void initGrammar(boolean hasSemicolon) {

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		TerminalNode vector_Node1 = new TerminalNode(new VectorType(), false);

		TerminalNode lessThan_Node2 = new TerminalNode(new LessThanOperator(), false);

		TerminalNode datatype_Node3 = new TerminalNode(new DataType(), false);

		TerminalNode greaterThan_Node4 = new TerminalNode(new GreaterThanOperator(), false);

		TerminalNode identifier_Node5 = new TerminalNode(new Identifier(""), true);

		TerminalNode equeal_Node6 = new TerminalNode(new EqualType(), false);

		TerminalNode openCurlyBracket_Node7 = new TerminalNode(new OpenCurlyBracket(), false);

		TerminalNode literal_Node8 = new TerminalNode(new Literal(), false);

		TerminalNode comma_Node9 = new TerminalNode(new CommaType(), false);

		TerminalNode closeCurlyBracket_Node10 = new TerminalNode(new CloseCurlyBracket(), true);

		NonTerminalNode functionSubGrammar_Node11 = new NonTerminalNode(
				() -> GrammarLibrary.getParsingObjectsOfFunctionCallSubGrammar(), true);
		
		TerminalNode semicolon_Node12 = new TerminalNode(new SemicolonType(), true);

		// -----------------------------------------------------------------
		root.addChild(vector_Node1.Id);

		vector_Node1.addChild(lessThan_Node2.Id);
		
		lessThan_Node2.addChild(datatype_Node3.Id);
		
		datatype_Node3.addChild(greaterThan_Node4.Id);
		
		greaterThan_Node4.addChild(identifier_Node5.Id);
		greaterThan_Node4.addChild(functionSubGrammar_Node11.Id);

		identifier_Node5.addChild(equeal_Node6.Id);

		equeal_Node6.addChild(openCurlyBracket_Node7.Id);

		openCurlyBracket_Node7.addChild(literal_Node8.Id);

		literal_Node8.addChild(closeCurlyBracket_Node10.Id);
		literal_Node8.addChild(comma_Node9.Id);
		
		comma_Node9.addChild(literal_Node8.Id);
		
		if(hasSemicolon) {
			identifier_Node5.addChild(semicolon_Node12.Id);
			functionSubGrammar_Node11.addChild(semicolon_Node12.Id);
			closeCurlyBracket_Node10.addChild(semicolon_Node12.Id);
			
			identifier_Node5.canBeEnd=false;
			functionSubGrammar_Node11.canBeEnd=false;
			closeCurlyBracket_Node10.canBeEnd=false;
		}

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(vector_Node1);
		grammarNodes.add(lessThan_Node2);
		grammarNodes.add(datatype_Node3);
		grammarNodes.add(greaterThan_Node4);
		grammarNodes.add(identifier_Node5);
		grammarNodes.add(equeal_Node6);
		grammarNodes.add(openCurlyBracket_Node7);
		grammarNodes.add(literal_Node8);
		grammarNodes.add(comma_Node9);
		grammarNodes.add(closeCurlyBracket_Node10);
		grammarNodes.add(functionSubGrammar_Node11);
		grammarNodes.add(semicolon_Node12);
	}

	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new VectorDeclarationGrammar(this.Id,nodes,this.rootNodeId);
	}
}


