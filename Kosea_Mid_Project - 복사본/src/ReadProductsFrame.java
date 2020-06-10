import java.util.*;

public class ReadProductsFrame extends ReadTableFrame {
	public ReadProductsFrame() {
		super();

	}

//	SELECT 결과 조회
	public void select() {
		ReadProductsDAO dao = new ReadProductsDAO();
		ArrayList<ProductsVO> products = new ArrayList<ProductsVO>();
		products = dao.list(cmb.getSelectedItem().toString(), inp.getText(), chk.isSelected());

		for (int i = 0; i < products.size(); i++) {
			model.addRow(products.get(i).toList());
		}
	}

	public String toString() {
		return "product";
	}

	public void setColumn() { 
		this.column = ProductsVO.COLUMN;
	}

}
