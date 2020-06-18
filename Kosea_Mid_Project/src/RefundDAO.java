import java.sql.*;

public class RefundDAO extends TableVO {

	public RefundDAO(String tname) throws SQLException {
		super(tname);
	}

	// delete Äõ¸® ÀÛ¼º
	public void erase(int ccode) throws SQLException {
		String query = "DELETE FROM " + name + " WHERE " + column.get(0) + " = " + ccode;
		stmt.executeQuery(query);
	}

	public void erase(String scode, String pcode) throws SQLException {
		String query = "DELETE FROM " + name + " WHERE " + column.get(0) + " = " + scode + " and " + column.get(1) + " = "
				+ pcode;
		stmt.executeQuery(query);
	}
}
