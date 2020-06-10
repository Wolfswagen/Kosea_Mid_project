import java.util.*;

public class ProductsDAO extends DAO {

	public ArrayList<ProductsVO> list(String atr, String tpl, boolean distinct) {
		ArrayList<ProductsVO> list = new ArrayList<ProductsVO>();
		String query = "";
		try {
			connDB();

			if (tpl.isEmpty()) {
				query = "SELECT * FROM products ORDER BY product_code";
			} else {
				switch (atr) {
				case "product_code":
				case "amount":
				case "original_price":
				case "discount":
				case "multi_purchase_discount":
				case "discount_rate":
					if (distinct) {
						query = "SELECT * FROM products WHERE (" + atr + " LIKE '%" + tpl + "' OR " + atr + " LIKE '"
								+ tpl + "%' OR " + atr + " LIKE '%" + tpl + "%') ORDER BY product_code";
					} else {
						query = "SELECT * FROM products WHERE " + atr + " = " + tpl + " ORDER BY product_code";
					}

					break;
				case "category":
				case "product_name":
				case "status":
				case "register_date":
				case "shipping":
					if (distinct) {
						query = "SELECT * FROM products WHERE (" + atr + " LIKE '%" + tpl + "' OR " + atr + " LIKE '"
								+ tpl + "%' OR " + atr + " LIKE '%" + tpl + "%') ORDER BY product_code";

					} else {
						query = "SELECT * FROM products WHERE " + atr + " = '" + tpl.toUpperCase()
								+ "' ORDER BY product_code";
					}
					break;
				default:
				}
			}

			rs = stmt.executeQuery(query);

			while (rs.next()) {
				int pcode = rs.getInt("product_code");
				String category = rs.getString("category");
				String pname = rs.getString("product_name");
				String status = rs.getString("status");
				int amount = rs.getInt("amount");
				int org_price = rs.getInt("original_price");
				int discount = rs.getInt("discount");
				int mul_dc = rs.getInt("multi_purchase_discount");
				double dc_rate = rs.getDouble("discount_rate");
				String reg_date = rs.getString("register_date");
				String shipping = rs.getString("shipping");
				ProductsVO data = new ProductsVO(pcode, category, pname, status, amount, org_price, discount, mul_dc,
						dc_rate, reg_date, shipping);
				list.add(data);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("SQL> " + query); /* 오류시 쿼리 확인 */
		}
		return list;
	}

}
