import java.sql.*;

public class UpdateCustomersDAO extends ConnectDAO {

//	insert Äõ¸® ÀÛ¼º
	public void set(CustomersVO data) throws SQLException {
		connDB();

		String query = "UPDATE CUSTOMERS SET customer_name = '" + data.getCname() + "', address = '" + data.getAddress()
				+ "', phone = '" + data.getPhone() + "', zip_code = '" + data.getZip() + "', custom_clearance_code = '"
				+ data.getCcc() + "' WHERE CUSTOMER_CODE = " + String.valueOf(data.getCcode());
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			throw e;
		}

	}
}
