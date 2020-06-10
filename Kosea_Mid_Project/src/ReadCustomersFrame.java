import java.util.*;

public class ReadCustomersFrame extends ReadTableFrame {

	public ReadCustomersFrame() {
		super();

	}

//	SELECT 결과 조회
	public void select() {
		ReadCustomersDAO dao = new ReadCustomersDAO();
		ArrayList<CustomersVO> products = new ArrayList<CustomersVO>();
		products = dao.list(cmb.getSelectedItem().toString(), inp.getText(), chk.isSelected());

		for (int i = 0; i < products.size(); i++) {
			model.addRow(products.get(i).toArray());
		}
	}

	public String toString() {
		return "customer";
	}

	public void setColumn() {
		this.column = CustomersVO.COLUMN;
	}

}
