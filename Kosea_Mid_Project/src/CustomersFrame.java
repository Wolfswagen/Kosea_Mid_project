import java.util.*;

public class CustomersFrame extends TableFrame {

	public CustomersFrame() {
		super();

	}

//	SELECT ��� ��ȸ
	public void search() {
		CustomersDAO dao = new CustomersDAO();
		ArrayList<CustomersVO> products = new ArrayList<CustomersVO>();
		products = dao.list(cmb.getSelectedItem().toString(), inp.getText(), chk.isSelected());

		for (int i = 0; i < products.size(); i++) {
			model.addRow(products.get(i).toList());
		}
	}

	public String toString() {
		return "customer";
	}

	public void setColumn() {
		this.column = CustomersVO.COLUMN;
	}

}
