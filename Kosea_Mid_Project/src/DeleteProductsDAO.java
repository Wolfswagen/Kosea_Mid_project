import java.sql.*;

public class DeleteProductsDAO extends ConnectDAO {

//	delete Äõ¸® ÀÛ¼º
	public void erase(int pcode) throws SQLException {
		connDB();
		try {
			String query = "DELETE FROM PRODUCTS WHERE PRODUCT_CODE = " + pcode;
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			throw e;
		}

	}
	
}
