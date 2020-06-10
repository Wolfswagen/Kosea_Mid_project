
import java.sql.SQLException;

public class InsertCustomers extends InsertFrame {

	public InsertCustomers() {
		super();
	}

//  테이블 칼럼 설정
	public void setColumn() {
		this.column = CustomersVO.COLUMN;
		this.defrow = CustomersVO.DEFROW;
	}

	public void insertData() {
		connDB();
		for (int i = 0; i < table.getRowCount(); i++) {

			String cname = table.getValueAt(0, 1).toString();
			String cid = table.getValueAt(0, 2).toString();
			String address = table.getValueAt(0, 3).toString();
			String phone = table.getValueAt(0, 4).toString();
			String zip = table.getValueAt(0, 5).toString();
			String ccc = table.getValueAt(0, 6).toString();

			String query = "SELECT max(CUSTOMER_CODE) fROM CUSTOMERS";

			try {
				rs = stmt.executeQuery(query);
				int max = 0;
				while (rs.next()) {
					max = rs.getInt("max(CUSTOMER_CODE)") + 1;
				}
				query = "INSERT INTO CUSTOMERS VALUES(" + max + ", '" + cname + "', '" + cid + "', '" + address + "', '"
						+ phone + "', '" + zip + "', '" + ccc + "')";
				rs = stmt.executeQuery(query);

			} catch (SQLException e) {
				System.out.println(e.getMessage());
				System.out.println(query);
			}

		}

	}
}
