import java.util.*;

public class ProductsFrame extends TableFrame {
	public ProductsFrame() {
		super();

	}

//	SELECT 결과 조회
	public void search() {
		ProductsDAO dao = new ProductsDAO();
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
