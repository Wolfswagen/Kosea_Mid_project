import java.util.*;

public class ReadCustomersDAO extends ConnectDAO {

	public ArrayList<CustomersVO> list(String atr, String tpl, boolean chk) {
		ArrayList<CustomersVO> list = new ArrayList<CustomersVO>();
		String query = "";
		try {
			connDB();
			if (tpl.isEmpty()) {
				query = "SELECT * FROM customers ORDER BY customer_code";
			} else {

				if (atr.equals("customer_code")) {
					if (chk) {
						query = "SELECT * FROM customers WHERE (" + atr + " LIKE '%" + tpl + "' OR " + atr + " LIKE '"
								+ tpl + "%' OR " + atr + " LIKE '%" + tpl + "%') ORDER BY customer_code";
					} else {
						query = "SELECT * FROM customers WHERE " + atr + " = " + tpl + " ORDER BY customer_code";
					}
				} else {

					if (chk) {
						query = "SELECT * FROM customers WHERE (" + atr + " LIKE '%" + tpl + "' OR " + atr + " LIKE '"
								+ tpl + "%' OR " + atr + " LIKE '%" + tpl + "%') ORDER BY customer_code";

					} else {
						query = "SELECT * FROM customers WHERE " + atr + " = '" + tpl.toUpperCase()
								+ "' ORDER BY customer_code";
					}

				}
			}

			rs = stmt.executeQuery(query);

			while (rs.next()) {

				int ccode = rs.getInt("customer_code");
				String cname = rs.getString("customer_name");
				String cid = rs.getString("customer_ID");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String zip = rs.getString("zip_code");
				String ccc = rs.getString("custom_clearance_code");
				CustomersVO data = new CustomersVO(ccode, cname, cid, address, phone, zip, ccc);
				list.add(data);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("SQL> " + query); /* 오류시 쿼리 확인 */
		}
		return list;
	}

}
