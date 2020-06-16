import java.sql.*;
import java.util.*;

public class TableVO2 {
	final private String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final private String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	final private String USER = "kosea";
	final private String PASSWORD = "kosea2019a";

	private Connection con;
	protected Statement stmt;
	protected ResultSet rs;
	protected ResultSet md;

	final protected Vector<String> column = new Vector<String>();
	final protected Vector<String> defrow = new Vector<String>();
	protected Vector<Object> tuple = new Vector<Object>();

	public TableVO2(String tname) throws SQLException {
		connDB(tname);
		md = stmt.executeQuery("SELECT Column_name FROM cols WHERE TABLE_name = '" + tname.toUpperCase() + "'");
		while (md.next()) {
			column.add(md.getString("column_name"));
		}

		defrow.add("자동입력");
		if (tname.equals("Products")) {
			defrow.add("OUTER");
			defrow.add("");
			defrow.add("판매중");
			defrow.add("0");
			defrow.add("0");
			defrow.add("0");
			defrow.add("0");
			defrow.add("0");
			defrow.add("자동입력");
			defrow.add("조건부 무료");
		}
	}

	public void connDB(String tname) {
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vector<String> getColumn() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.column;
	}
}