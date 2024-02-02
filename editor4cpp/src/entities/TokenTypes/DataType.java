package entities.TokenTypes;

import enums.DataTypes;
import interfaces.HasType;

public class DataType extends Keyword implements HasType{

	protected DataTypes dataType;
	@Override
	public String getError() {
		
		return "Expected DataType";
	}
	@Override
	public DataTypes getDataType() {
		return dataType;
	}
}
