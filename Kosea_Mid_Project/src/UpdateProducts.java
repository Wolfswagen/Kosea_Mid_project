import java.util.ArrayList;

public class UpdateProducts extends UpdateTableFrame {

	public UpdateProducts() {
		super();
	}

//	update 쿼리 호출
	public void update() throws Exception {
		UpdateProductsDAO dao = new UpdateProductsDAO();
		while (table.getRowCount() > 0) {
			int pcode = Integer.parseInt(table.getValueAt(0, 0).toString());
			String category = table.getValueAt(0, 1).toString();
			String pname = table.getValueAt(0, 2).toString();
			String status = table.getValueAt(0, 3).toString();
			int amount = Integer.parseInt(table.getValueAt(0, 4).toString());
			int org_price = Integer.parseInt(table.getValueAt(0, 5).toString());
			int discount = Integer.parseInt(table.getValueAt(0, 6).toString());
			int mul_dc = Integer.parseInt(table.getValueAt(0, 7).toString());
			double dc_rate = Double.parseDouble(table.getValueAt(0, 8).toString());
			String shipping = table.getValueAt(0, 10).toString();
			ProductsVO data = new ProductsVO(pcode, category, pname, status, amount, org_price, discount, mul_dc,
					dc_rate, "", shipping);
			try {
				dao.set(data);
				insertModel.removeRow(0);
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
		return "UpdateProducts";
	}

	public void setCellComboBox() {
		addCellComboBox(table.getColumnModel().getColumn(1),
				new String[] { "OUTER", "TOP", "BOTTOM", "ONEPIECE", "SHOES", "ACC", "SUMMER" });
		addCellComboBox(table.getColumnModel().getColumn(3), new String[] { "판매중", "품절" });
		addCellComboBox(table.getColumnModel().getColumn(10), new String[] { "조건부 무료", "무료" });
	}

}
