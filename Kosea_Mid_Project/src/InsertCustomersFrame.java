
public class InsertCustomersFrame extends InsertTableFrame {

	public InsertCustomersFrame() {
		super();
	}

//  테이블 칼럼 설정
	public void setColumn() {
		this.column = CustomersVO.COLUMN;
		this.defrow = CustomersVO.DEFROW;
	}

//	테이블 입력 DB insert
	public void insert() {
		InsertCustomersDAO dao = new InsertCustomersDAO();
		for (int i = 0; i < table.getRowCount(); i++) {
			String cname = table.getValueAt(i, 1).toString();
			String cid = table.getValueAt(i, 2).toString();
			String address = table.getValueAt(i, 3).toString();
			String phone = table.getValueAt(i, 4).toString();
			String zip = table.getValueAt(i, 5).toString();
			String ccc = table.getValueAt(i, 6).toString();

			CustomersVO data = new CustomersVO(0, cname, cid, address, phone, zip, ccc);
			dao.write(data);

		}

	}
}
