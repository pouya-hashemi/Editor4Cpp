package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
import entities.TokenTypes.ObjectIdentifier;
import entities.TokenTypes.UnknownType;
import entities.TokenTypes.Keywords.ElseKeyword;
import entities.TokenTypes.Keywords.IfKeyword;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.ColonType;
import entities.TokenTypes.Punctuations.CommaType;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
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

//		SingleNode unknown_Node1 = new SingleNode(new UnknownType(), false);
//
//		SingleNode colon_Node2 = new SingleNode(new ColonType(), false);
//
//		SingleNode colon_Node3 = new SingleNode(new ColonType(), false);

		SingleNode unknown_Node4 = new SingleNode(new UnknownType(), false);

		SingleNode objectIdentifier_Node5 = new SingleNode(new ObjectIdentifier(), false);

		SingleNode openParenthesis_Node6 = new SingleNode(new OpenParenthesisType(), false);
		
		StatementNode parameters_Node7 = new StatementNode(()->GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(),false);

		SingleNode comma_Node8 = new SingleNode(new CommaType(), false);
		
		SingleNode closeParenthesis_Node9 = new SingleNode(new CloseParenthesisType(), false);
		
		SingleNode semicolon_Node10 = new SingleNode(new SemicolonType(), true);
		
		

		// -----------------------------------------------------------------
//		root.addChild(unknown_Node1.Id);
		root.addChild(unknown_Node4.Id);
		
		
//		unknown_Node1.addChild(colon_Node2.Id);
//		
//		colon_Node2.addChild(colon_Node3.Id);
//		
//		colon_Node3.addChild(unknown_Node4.Id);
		
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
//		grammarNodes.add(unknown_Node1);
//		grammarNodes.add(colon_Node2);
//		grammarNodes.add(colon_Node3);
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
