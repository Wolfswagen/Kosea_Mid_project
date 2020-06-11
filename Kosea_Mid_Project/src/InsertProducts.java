public class InsertProducts extends InsertTableFrame {

	public InsertProducts() {
		super();
		setCellComboBox();
	}

//  테이블 칼럼 설정
	public void setColumn() {
		this.column = ProductsVO.COLUMN;
		this.defrow = ProductsVO.DEFROW;
	}

	public void setCellComboBox() {
		addCellComboBox(table.getColumnModel().getColumn(1),
				new String[] { "OUTER", "TOP", "BOTTOM", "ONEPIECE", "SHOES", "ACC", "SUMMER" });
		addCellComboBox(table.getColumnModel().getColumn(3), new String[] { "판매중", "품절" });
		addCellComboBox(table.getColumnModel().getColumn(10), new String[] { "조건부 무료", "무료" });
	}

//	테이블 입력 DB insert
	public void insert() throws Exception {
		InsertProductsDAO dao = new InsertProductsDAO();
		while (table.getRowCount() > 0) {
			String category = table.getValueAt(0, 1).toString();
			String pname = table.getValueAt(0, 2).toString();
			String status = table.getValueAt(0, 3).toString();
			int amount = Integer.parseInt(table.getValueAt(0, 4).toString());
			int org_price = Integer.parseInt(table.getValueAt(0, 5).toString());
			int discount = Integer.parseInt(table.getValueAt(0, 6).toString());
			int mul_dc = Integer.parseInt(table.getValueAt(0, 7).toString());
			double dc_rate = Double.parseDouble(table.getValueAt(0, 8).toString());
			String shipping = table.getValueAt(0, 10).toString();
			ProductsVO data = new ProductsVO(0, category, pname, status, amount, org_price, discount, mul_dc, dc_rate,
					"", shipping);
			try {
				dao.write(data);
				insertModel.removeRow(0);
			} catch (Exception e) {
				throw e;
			}
		}

	}

	public String toString() {
		return "Products";
	}

}
