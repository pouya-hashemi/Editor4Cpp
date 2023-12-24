package grammars;

public class GrammarIdGenerator {
	private int currentId;
	private static GrammarIdGenerator instance;

	private GrammarIdGenerator() {
		currentId = 0;
	}
	public static GrammarIdGenerator GetInstance(){
		if(instance ==null) {
			instance=new GrammarIdGenerator();
		}
		return instance;
	}
	public int generateId() {
		currentId+=1;
		return currentId;
	}
}
