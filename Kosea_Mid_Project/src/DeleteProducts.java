import java.util.ArrayList;

public class DeleteProducts extends DeleteTableFrame {

	public DeleteProducts() {
		super();
	}

//	update Äõ¸® È£Ãâ
	public void delete() throws Exception {
		DeleteProductsDAO dao = new DeleteProductsDAO();
		while (table.getRowCount() > 0) {
			int pcode = Integer.parseInt(table.getValueAt(0, 0).toString());
			try {
				dao.erase(pcode);
				readModel2.removeRow(0);
			} catch (Exception e) {
				throw e;
			}
		}
	}

	public void setColumn() {
		this.column = ProductsVO.COLUMN;

	}

	public void select() {
		readModel.setNumRows(0);
		ReadProductsDAO dao = new ReadProductsDAO();
		ArrayList<ProductsVO> products = new ArrayList<ProductsVO>();
		products = dao.list(cmb.getSelectedItem().toString(), inp.getText(), chk.isSelected());

		for (int i = 0; i < products.size(); i++) {
			readModel.addRow(products.get(i).toArray());
		}
	}

	public String toString() {
		return "Products";
	}

}
