package enums;

public enum DataTypes {
	Short, Int, Long, Float, Double, Char, String, Bool, Pointer,Auto;

	public String getError() {
		switch (this) {
		case Short:
			return "Expected a short";
		case Int:
			return "Expected a int";
		case Long:
			return "Expected a long";
		case Float:
			return "Expected a float";
		case Double:
			return "Expected a double";
		case Char:
			return "Expected a char";
		case String:
			return "Expected a string";
		case Bool:
			return "Expected a bool";
		case Pointer:
			return "Expected a pointer";
		case Auto:
			return "Expected a auto";
		default:
			return "";

		}
	}

	public boolean canBe(DataTypes dataType) {
		switch (this) {
		case Short: {
			if (dataType == DataTypes.Short)
				return true;
			return false;
		}
		case Int: {
			if (dataType == DataTypes.Short)
				return true;
			if (dataType == DataTypes.Int)
				return true;
			return false;
		}
		case Long: {
			if (dataType == DataTypes.Short)
				return true;
			if (dataType == DataTypes.Int)
				return true;
			if (dataType == DataTypes.Long)
				return true;
			return false;
		}
		case Float:
		{
			if (dataType == DataTypes.Short)
				return true;
			if (dataType == DataTypes.Int)
				return true;
			if (dataType == DataTypes.Long)
				return true;
			if (dataType == DataTypes.Float)
				return true;
			return false;
		}
		case Double:
		{
			if (dataType == DataTypes.Short)
				return true;
			if (dataType == DataTypes.Int)
				return true;
			if (dataType == DataTypes.Long)
				return true;
			if (dataType == DataTypes.Float)
				return true;
			if (dataType == DataTypes.Double)
				return true;
			return false;
		}
		case Char:
		{
			if (dataType == DataTypes.Char)
				return true;
			return false;
		}
		case String:
		{
			if (dataType == DataTypes.String)
				return true;
			return false;
		}
		case Bool:
		{
			if (dataType == DataTypes.Bool)
				return true;
			return false;
		}
		case Pointer:
		{
			if (dataType == DataTypes.Pointer)
				return true;
			return false;
		}
		case Auto:
		{
			if (dataType == DataTypes.Auto)
				return true;
			return false;
		}
		default:
			return false;

		}
	}
}
