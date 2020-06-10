import javax.swing.*;
import javax.swing.table.*;

public class InsertProductsFrame extends InsertTableFrame {

	public InsertProductsFrame() {
		super();
		setCellComboBox();
	}

//  테이블 칼럼 설정
	public void setColumn() {
		this.column = ProductsVO.COLUMN;
		this.defrow = ProductsVO.DEFROW;
	}

//	table combobox 추가
	public void addCellComboBox(TableColumn column, String[] tpl) {
		JComboBox<String> comboBox = new JComboBox<String>(tpl);
		column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		column.setCellRenderer(renderer);
	}

	public void setCellComboBox() {
		addCellComboBox(table.getColumnModel().getColumn(1),
				new String[] { "OUTER", "TOP", "BOTTOM", "ONEPIECE", "SHOES", "ACC", "SUMMER" });
		addCellComboBox(table.getColumnModel().getColumn(3), new String[] { "판매중", "품절" });
		addCellComboBox(table.getColumnModel().getColumn(10), new String[] { "조건부 무료", "무료" });
	}

//	테이블 입력 DB insert
	public void insert() {
		InsertProductsDAO dao = new InsertProductsDAO();
		for (int i = 0; i < table.getRowCount(); i++) {
			String category = table.getValueAt(i, 1).toString();
			String pname = table.getValueAt(i, 2).toString();
			String status = table.getValueAt(i, 3).toString();
			int amount = Integer.parseInt(table.getValueAt(i, 4).toString());
			int org_price = Integer.parseInt(table.getValueAt(i, 5).toString());
			int discount = Integer.parseInt(table.getValueAt(i, 6).toString());
			int mul_dc = Integer.parseInt(table.getValueAt(i, 7).toString());
			double dc_rate = Double.parseDouble(table.getValueAt(i, 8).toString());
			String shipping = table.getValueAt(i, 10).toString();

			ProductsVO data = new ProductsVO(0, category, pname, status, amount, org_price, discount, mul_dc, dc_rate,
					"", shipping);
			dao.write(data);
		}

	}

}
