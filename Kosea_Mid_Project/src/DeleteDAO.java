import java.sql.*;

public class DeleteDAO extends ConnectDAO {

//	delete Äõ¸® ÀÛ¼º
	public void erase(String tname, int ccode) throws SQLException {
		connDB();
		String[] col;
		if (tname.equals("Products")) {
			col = ProductsVO.COLUMN;
		} else {
			col = CustomersVO.COLUMN;
		}
		try {
			String query = "DELETE FROM " + tname + " WHERE "+ col[0] +" = " + ccode;
			stmt.executeQuery(query);
		} catch (SQLException e) {
			throw e;
		}
	}
}
