import java.util.*;

import javax.swing.JOptionPane;

public class ReadDAO extends ConnectDAO {

	public ArrayList<TableVO> list(String tname, String atr, String inp, boolean partial) {
		ArrayList<TableVO> list = new ArrayList<TableVO>();
		String query = "";
		String[] column;

		if (tname.equals("Products")) {
			column = ProductsVO.COLUMN;
		} else if(tname.equals("Customers")) {
			column = CustomersVO.COLUMN;
		} else {
			column = Sales_Customers_DetailsVO.COLUMN;
		}

		try {
			connDB();
			if (inp.isEmpty()) {
				query = "SELECT * FROM " + tname + " ORDER BY " + column[0];
			} else {
				if (partial) {
					query = "SELECT * FROM " + tname + " WHERE (" + atr + " LIKE '%" + inp + "' OR " + atr + " LIKE '"
							+ inp + "%' OR " + atr + " LIKE '%" + inp + "%') ORDER BY " + column[0];
				} else {
					query = "SELECT * FROM " + tname + " WHERE " + atr + " = '" + inp + "' ORDER BY " + column[0];
				}
			}

			rs = stmt.executeQuery(query);
			while (rs.next()) {
				TableVO data;
				if (tname.equals("Products")) {
					data = new ProductsVO();
				} else if(tname.equals("Customers")) {
					data = new CustomersVO();
				} else {
					data = new Sales_Customers_DetailsVO();
				}
				for (int i = 0; i < data.column.size(); i++) {
					data.tuple.add(rs.getObject(data.column.get(i)));
				}
				list.add(data);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "¿À·ù", 0);
			e.printStackTrace();
		}

		return list;
	}

}
