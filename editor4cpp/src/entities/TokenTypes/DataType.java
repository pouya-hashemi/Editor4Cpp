package entities.TokenTypes;


public class DataType extends Keyword {
	private String name;

	public DataType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getError() {
		
		return "Expected DataType";
	}

}
