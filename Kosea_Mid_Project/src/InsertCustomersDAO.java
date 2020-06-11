import java.sql.*;

public class InsertCustomersDAO extends ConnectDAO {

//	insert Äõ¸® ÀÛ¼º
	public void write(CustomersVO cvo) throws SQLException {
		connDB();
		String query = "SELECT max(CUSTOMER_CODE) fROM CUSTOMERS";
		try {
			rs = stmt.executeQuery(query);
			int max = 0;
			while (rs.next()) {
				max = rs.getInt("max(CUSTOMER_CODE)") + 1;
			}
			query = "INSERT INTO CUSTOMERS VALUES(" + max + ", '" + cvo.getCname() + "', '" + cvo.getCid() + "', '"
					+ cvo.getAddress() + "', '" + cvo.getPhone() + "', '" + cvo.getZip() + "', '" + cvo.getCcc() + "')";
			rs = stmt.executeQuery(query);

		} catch (SQLException e) {
			throw e;
		}

	}

}
