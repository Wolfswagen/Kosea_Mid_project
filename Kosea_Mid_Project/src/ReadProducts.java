import java.util.*;

public class ReadProducts extends ReadTableFrame {
	public ReadProducts() {
		super();

	}

//	SELECT 결과 조회
	public void select() {
		ReadProductsDAO dao = new ReadProductsDAO();
		ArrayList<ProductsVO> products = new ArrayList<ProductsVO>();
		products = dao.list(cmb.getSelectedItem().toString(), inp.getText(), chk.isSelected());

		for (int i = 0; i < products.size(); i++) {
			readModel.addRow(products.get(i).toArray());
		}
	}

	public String toString() {
		return "ReadProducts";
	}

	public void setColumn() {
		this.column = ProductsVO.COLUMN;
	}

}
