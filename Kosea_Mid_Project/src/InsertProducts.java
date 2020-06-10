
import java.sql.SQLException;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class InsertProducts extends InsertFrame {

	public InsertProducts() {
		super();
		addCellComboBox();
	}

//  테이블 칼럼 설정
	public void setColumn() {
		this.column = ProductsVO.COLUMN;
		this.defrow = ProductsVO.DEFROW;
	}

	public void insertData() {
		connDB();
		for (int i = 0; i < table.getRowCount(); i++) {

			String category = table.getValueAt(0, 1).toString();
			String pname = table.getValueAt(0, 2).toString();
			String status = table.getValueAt(0, 3).toString();
			int amount = Integer.parseInt(table.getValueAt(0, 4).toString());
			int org_price = Integer.parseInt(table.getValueAt(0, 5).toString());
			int discount = Integer.parseInt(table.getValueAt(0, 6).toString());
			int mul_dc = Integer.parseInt(table.getValueAt(0, 7).toString());
			double dc_rate = Double.parseDouble(table.getValueAt(0, 8).toString());
			String shipping = table.getValueAt(0, 10).toString();

			String query = "SELECT max(PRODUCT_CODE) FROM PRODUCTS WHERE category = '" + category + "'";

			try {
				rs = stmt.executeQuery(query);
				int max = 0;
				while (rs.next()) {
					max = rs.getInt("max(product_code)") + 1;

				}
				query = "INSERT INTO PRODUCTS VALUES(" + max + ", '" + category + "', '" + pname + "', '" + status
						+ "', " + amount + ", " + org_price + ", " + discount + ", " + mul_dc + ", " + dc_rate
						+ ", SYSDATE, '" + shipping + "')";
				rs = stmt.executeQuery(query);

			} catch (SQLException e) {
				System.out.println(e.getMessage());
				System.out.println(query);
			}

		}

	}

//	table combobox 생성
	public void changeCellEditor(JTable table, TableColumn column, String[] tpl) {
		JComboBox<String> comboBox = new JComboBox<String>(tpl);
		column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		column.setCellRenderer(renderer);
	}

	public void addCellComboBox() {
		changeCellEditor(table, table.getColumnModel().getColumn(1),
				new String[] { "OUTER", "TOP", "BOTTOM", "ONEPIECE", "SHOES", "ACC", "SUMMER" });
		changeCellEditor(table, table.getColumnModel().getColumn(3), new String[] { "판매중", "품절" });
		changeCellEditor(table, table.getColumnModel().getColumn(10), new String[] { "조건부 무료", "무료" });
	}

	public static void main(String[] args) {
		InsertProducts i = new InsertProducts();
		i.initFrame();
	}
}
