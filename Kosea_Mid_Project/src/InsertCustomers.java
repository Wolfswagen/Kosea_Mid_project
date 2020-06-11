
public class InsertCustomers extends InsertTableFrame {

	public InsertCustomers() {
		super();
	}

//  테이블 칼럼 설정
	public void setColumn() {
		this.column = CustomersVO.COLUMN;
		this.defrow = CustomersVO.DEFROW;
	}

//	테이블 입력 DB insert
	public void insert() throws Exception {
		InsertCustomersDAO dao = new InsertCustomersDAO();
		while (table.getRowCount() > 0) {
			String cname = table.getValueAt(0, 1).toString();
			String cid = table.getValueAt(0, 2).toString();
			String address = table.getValueAt(0, 3).toString();
			String phone = table.getValueAt(0, 4).toString();
			String zip = table.getValueAt(0, 5).toString();
			String ccc = table.getValueAt(0, 6).toString();

			CustomersVO data = new CustomersVO(0, cname, cid, address, phone, zip, ccc);
			try {
				dao.write(data);
				insertModel.removeRow(0);
			} catch (Exception e) {
				throw e;
			}
		}

	}

	public String toString() {
		return "Customers";
	}
}
