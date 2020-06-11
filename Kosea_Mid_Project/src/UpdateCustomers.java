import java.util.ArrayList;

public class UpdateCustomers extends UpdateTableFrame {

	public UpdateCustomers() {
		super();
	}

//	update Äõ¸® È£Ãâ
	public void update() throws Exception {
		UpdateCustomersDAO dao = new UpdateCustomersDAO();
		while (table.getRowCount() > 0) {
			int ccode = Integer.parseInt(table.getValueAt(0, 0).toString());
			String cname = table.getValueAt(0, 1).toString();
			String cid = table.getValueAt(0, 2).toString();
			String address = table.getValueAt(0, 3).toString();
			String phone = table.getValueAt(0, 4).toString();
			String zip = table.getValueAt(0, 5).toString();
			String ccc = table.getValueAt(0, 6).toString();

			CustomersVO data = new CustomersVO(ccode, cname, cid, address, phone, zip, ccc);
			try {
				dao.set(data);
				insertModel.removeRow(0);
			} catch (Exception e) {
				throw e;
			}
		}
	}

	public void setColumn() {
		this.column = CustomersVO.COLUMN;

	}

	public void select() {
		readModel.setNumRows(0);
		ReadCustomersDAO dao = new ReadCustomersDAO();
		ArrayList<CustomersVO> customers = new ArrayList<CustomersVO>();
		customers = dao.list(cmb.getSelectedItem().toString(), inp.getText(), chk.isSelected());

		for (int i = 0; i < customers.size(); i++) {
			readModel.addRow(customers.get(i).toArray());
		}
	}

	public String toString() {
		return "Customers";
	}

}
