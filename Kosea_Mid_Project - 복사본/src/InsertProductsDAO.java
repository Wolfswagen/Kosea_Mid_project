import java.sql.SQLException;

public class InsertProductsDAO extends ConnectDAO {

//	insert Äõ¸® ÀÛ¼º
	public void write(ProductsVO pvo) {
		connDB();

		String query = "SELECT max(PRODUCT_CODE) FROM PRODUCTS WHERE category = '" + pvo.getCategory() + "'";

		try {
			rs = stmt.executeQuery(query);
			int max = 0;
			while (rs.next()) {
				max = rs.getInt("max(product_code)") + 1;
			}
			query = "INSERT INTO PRODUCTS VALUES(" + max + ", '" + pvo.getCategory() + "', '" + pvo.getPname() + "', '"
					+ pvo.getStatus() + "', " + pvo.getAmount() + ", " + pvo.getOrg_price() + ", " + pvo.getDiscount()
					+ ", " + pvo.getMul_dc() + ", " + pvo.getDc_rate() + ", SYSDATE, '" + pvo.getShipping() + "')";
			rs = stmt.executeQuery(query);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(query);
		}

	}

}
