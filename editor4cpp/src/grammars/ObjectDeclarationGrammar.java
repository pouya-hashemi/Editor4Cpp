package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
import entities.TokenTypes.ObjectIdentifier;
import entities.TokenTypes.UnknownType;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.CommaType;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import entities.TokenTypes.Punctuations.SemicolonType;

public class ObjectDeclarationGrammar extends Grammar {
	public ObjectDeclarationGrammar(int id,List<GrammarNode> grammarNodes,UUID rootNodeId) {
		super(id,grammarNodes,rootNodeId);

	}
	public ObjectDeclarationGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId=root.Id;

		TerminalNode unknown_Node4 = new TerminalNode(new UnknownType(), false);

		TerminalNode objectIdentifier_Node5 = new TerminalNode(new ObjectIdentifier(), false);

		TerminalNode openParenthesis_Node6 = new TerminalNode(new OpenParenthesisType(), false);
		
		NonTerminalNode parameters_Node7 = new NonTerminalNode(()->GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(),false);

		TerminalNode comma_Node8 = new TerminalNode(new CommaType(), false);
		
		TerminalNode closeParenthesis_Node9 = new TerminalNode(new CloseParenthesisType(), false);
		
		TerminalNode semicolon_Node10 = new TerminalNode(new SemicolonType(), true);
		
		

		// -----------------------------------------------------------------
		root.addChild(unknown_Node4.Id);
		
		unknown_Node4.addChild(objectIdentifier_Node5.Id);
		
		objectIdentifier_Node5.addChild(semicolon_Node10.Id);
		
		objectIdentifier_Node5.addChild(openParenthesis_Node6.Id);
		
		openParenthesis_Node6.addChild(closeParenthesis_Node9.Id);
		
		openParenthesis_Node6.addChild(parameters_Node7.Id);
		parameters_Node7.addChild(closeParenthesis_Node9.Id);
		parameters_Node7.addChild(comma_Node8.Id);
		
		comma_Node8.addChild(parameters_Node7.Id);
		
		closeParenthesis_Node9.addChild(semicolon_Node10.Id);



		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(unknown_Node4);
		grammarNodes.add(objectIdentifier_Node5);
		grammarNodes.add(openParenthesis_Node6);
		grammarNodes.add(parameters_Node7);
		grammarNodes.add(comma_Node8);
		grammarNodes.add(closeParenthesis_Node9);
		grammarNodes.add(semicolon_Node10);
		
	}

	@Override
	public Grammar clone() {
		var nodes=new ArrayList<GrammarNode>();
		for(GrammarNode node:this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new ObjectDeclarationGrammar(this.Id,nodes,this.rootNodeId);
	}
}
