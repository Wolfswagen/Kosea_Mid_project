import java.util.ArrayList;

public class DeleteCustomers extends DeleteTableFrame {

	public DeleteCustomers() {
		super();
	}

//	delete Äõ¸® È£Ãâ
	public void delete() throws Exception {
		DeleteCustomersDAO dao = new DeleteCustomersDAO();
		while (table.getRowCount() > 0) {
			int ccode = Integer.parseInt(table.getValueAt(0, 0).toString());
			try {
				dao.erase(ccode);
				readModel2.removeRow(0);
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
