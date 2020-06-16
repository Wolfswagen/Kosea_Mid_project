import java.sql.*;
import java.util.*;

public class TableVO2 extends ConnectDAO {
	final protected ArrayList<String> column = new ArrayList<String>();
	final protected ArrayList<String> defrow = new ArrayList<String>();
	protected Vector<Object> tuple = new Vector<Object>();
	protected ResultSet md;

	public TableVO2(String tname) throws SQLException {
		connDB();
		String q = "SELECT Column_name FROM cols WHERE TABLE_name = '" + tname.toUpperCase() + "'";
		md = stmt.executeQuery(q);
		int i = 0;
		while (md.next()) {
			column.add(md.getString("column_name"));
			System.out.println(column.get(i++));
		}
	}

	public static void main(String[] args) throws SQLException {
		TableVO2 t = new TableVO2("Products");
	}
}