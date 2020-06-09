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
		column = new String[] { "product_code", "category", "product_name", "status", "amount", "original_price",
				"discount", "multi_purchase_discount", "discount_rate", "register_date", "shipping" };
	}

}
