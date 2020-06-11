import java.sql.*;

public class UpdateProductsDAO extends ConnectDAO {

//	insert Äõ¸® ÀÛ¼º
	public void set(ProductsVO data) throws SQLException {
		connDB();
		String query = "UPDATE PRODUCTS SET CATEGORY = '" + data.getCategory() + "', PRODUCT_NAME = '" + data.getPname()
				+ "', STATUS = '" + data.getStatus() + "', AMOUNT = " + data.getAmount() + ", ORIGINAL_PRICE = "
				+ data.getOrg_price() + ", DISCOUNT= " + data.getDiscount() + ", MULTI_PURCHASE_DISCOUNT = "
				+ data.getMul_dc() + ", DISCOUNT_RATE = " + data.getDc_rate() + ", SHIPPING = '" + data.getShipping()
				+ "' WHERE PRODUCT_CODE = " + String.valueOf(data.getPcode());
		
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			throw e;
		}

	}
}
