import java.sql.*;

public class DeleteCustomersDAO extends ConnectDAO {

//	delete Äõ¸® ÀÛ¼º
	public void erase(int ccode) throws SQLException {
		connDB();
		try {
			String query = "DELETE FROM CUSTOMERS WHERE CUSTOMER_CODE = " + ccode;
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			throw e;
		}

	}

}
