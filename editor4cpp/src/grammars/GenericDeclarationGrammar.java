package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.NonTerminalNode;
import entities.TerminalNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Literal;
import entities.TokenTypes.Keywords.ConstKeyword;
import entities.TokenTypes.Punctuations.AmpersandType;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.CommaType;
import entities.TokenTypes.Punctuations.EqualType;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
import entities.TokenTypes.Punctuations.SemicolonType;

public class GenericDeclarationGrammar extends Grammar {
	
	public GenericDeclarationGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	public GenericDeclarationGrammar() {
		super();

		initGrammar(true);
		
	}
	public GenericDeclarationGrammar(boolean hasSemicolon) {
		super();

		initGrammar(hasSemicolon);
		
	}
	private void initGrammar(boolean hasSemicolon) {

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		NonTerminalNode genericSubGrammar_Node1 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfGenericDataTypeSubGrammar(), false);

		TerminalNode constKeyword_Node2 = new TerminalNode(new ConstKeyword(), true);
		
		TerminalNode identifier_Node5 = new TerminalNode(new Identifier(""), true);
		

		TerminalNode equeal_Node6 = new TerminalNode(new EqualType(), false);

		TerminalNode openCurlyBracket_Node7 = new TerminalNode(new OpenCurlyBracket(), false);

		TerminalNode literal_Node8 = new TerminalNode(new Literal(), false);

		TerminalNode comma_Node9 = new TerminalNode(new CommaType(), false);

		TerminalNode closeCurlyBracket_Node10 = new TerminalNode(new CloseCurlyBracket(), true);

		NonTerminalNode functionSubGrammar_Node11 = new NonTerminalNode(
				() -> GrammarLibrary.getParsingObjectsOfFunctionCallSubGrammar(), true);
		
		TerminalNode semicolon_Node12 = new TerminalNode(new SemicolonType(), true);
		
		TerminalNode ampersand_Node20 = new TerminalNode(new AmpersandType(), false);
		
		NonTerminalNode pointerAccess_Node21 = new NonTerminalNode(
				() -> GrammarLibrary.getParsingObjectsOfPointerAccessSubGrammar(), true);

		NonTerminalNode functionParam_Node22 = new NonTerminalNode(
				() -> GrammarLibrary.getParsingObjectsOfFunctionParametersSubGrammar(), true);
		NonTerminalNode rightSideGenericSubGrammar_Node23 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfGenericDataTypeSubGrammar(), false);
		
		// -----------------------------------------------------------------
		root.addChild(constKeyword_Node2.Id);
		root.addChild(genericSubGrammar_Node1.Id);

		constKeyword_Node2.addChild(genericSubGrammar_Node1.Id);
		
		genericSubGrammar_Node1.addChild(ampersand_Node20.Id);
		ampersand_Node20.addChild(identifier_Node5.Id);
		
		genericSubGrammar_Node1.addChild(identifier_Node5.Id);
		genericSubGrammar_Node1.addChild(functionSubGrammar_Node11.Id);

		identifier_Node5.addChild(equeal_Node6.Id);

		equeal_Node6.addChild(openCurlyBracket_Node7.Id);
		equeal_Node6.addChild(pointerAccess_Node21.Id);
		equeal_Node6.addChild(rightSideGenericSubGrammar_Node23.Id);
		equeal_Node6.addChild(functionParam_Node22.Id);

		rightSideGenericSubGrammar_Node23.addChild(functionParam_Node22.Id);
		
		openCurlyBracket_Node7.addChild(literal_Node8.Id);

		literal_Node8.addChild(closeCurlyBracket_Node10.Id);
		literal_Node8.addChild(comma_Node9.Id);
		
		comma_Node9.addChild(literal_Node8.Id);
		
		
		if(hasSemicolon) {
			identifier_Node5.addChild(semicolon_Node12.Id);
			functionSubGrammar_Node11.addChild(semicolon_Node12.Id);
			closeCurlyBracket_Node10.addChild(semicolon_Node12.Id);
			functionParam_Node22.addChild(semicolon_Node12.Id);
			pointerAccess_Node21.addChild(semicolon_Node12.Id);
			
			identifier_Node5.canBeEnd=false;
			functionSubGrammar_Node11.canBeEnd=false;
			closeCurlyBracket_Node10.canBeEnd=false;
			pointerAccess_Node21.canBeEnd=false;
			functionParam_Node22.canBeEnd=false;
		}
		

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(genericSubGrammar_Node1);
		grammarNodes.add(constKeyword_Node2);
		grammarNodes.add(identifier_Node5);
		grammarNodes.add(equeal_Node6);
		grammarNodes.add(openCurlyBracket_Node7);
		grammarNodes.add(literal_Node8);
		grammarNodes.add(comma_Node9);
		grammarNodes.add(closeCurlyBracket_Node10);
		grammarNodes.add(functionSubGrammar_Node11);
		grammarNodes.add(semicolon_Node12);
		grammarNodes.add(ampersand_Node20);
		grammarNodes.add(pointerAccess_Node21);
		grammarNodes.add(functionParam_Node22);
		grammarNodes.add(rightSideGenericSubGrammar_Node23);
	}

	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new GenericDeclarationGrammar(this.Id,nodes,this.rootNodeId);
	}
}


