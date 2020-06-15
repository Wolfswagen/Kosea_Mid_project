import java.util.*;

public abstract class TableVO {
	final protected ArrayList<String> column = new ArrayList<String>();
	final protected ArrayList<String> defrow = new ArrayList<String>();
	protected Vector<Object> tuple = new Vector<Object>();

	
	
	public TableVO() {
		setColumn();
		setDefrow();
	}

//	getter Ω√¿€
	public ArrayList<String> getColumn() {
		return column;
	}

	public ArrayList<String> getDefrow() {
		return defrow;
	}

	public Vector<Object> getTuple() {
		return tuple;
	}
//	getter ≥°

	public abstract void setColumn();

	public abstract void setDefrow();


}