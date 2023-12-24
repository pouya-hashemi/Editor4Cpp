package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.Test;

import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
import grammars.ComparisonGrammar;
import grammars.IfGrammar;

public class CommonTests {

	@Test
	public void GrammarIdGeneratorTest() {
		var grammar1=new IfGrammar();
		var grammar2=new ComparisonGrammar();
		var grammar3=grammar1.clone();
		
		assertTrue(grammar1.Id==1);
		assertTrue(grammar2.Id==2);
		assertTrue(grammar3.Id==3);
	}
	

	@Test
	public void GrammarBreakdownTest() {
		var grammar1=new IfGrammar();
		
		var node= grammar1.grammarNodes.stream().filter(a->a.getClass()==SingleNode.class && ((SingleNode)a).tokenType.getClass()==OpenCurlyBracket.class).findFirst();
		UUID destId=null;
		
		for(UUID id:node.get().getChildIds()) {
			if(grammar1.getGrammarNodeById(id).get().getClass()==StatementNode.class) {
				destId=id;
				break;
			}
		}
		
		var newGrammars= grammar1.breakDown(node.get().Id, destId);
		
		assertTrue(newGrammars.size()==36);

		

	}
	
}
