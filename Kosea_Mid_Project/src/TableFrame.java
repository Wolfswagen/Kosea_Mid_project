
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

public abstract class TableFrame {
	JFrame f;
	JButton back;
//  테이블 
	DefaultTableModel readModel;
	DefaultTableModel insertModel;
	JScrollPane sp;
	JTable table;
	Vector<String> column;
	String[] defrow;
	String name;

	public TableFrame(String name) throws SQLException {
//		초기화 블럭
		this.name = name;
		this.column = setColumn();
		this.defrow = setDefrow();

		f = new JFrame(this.toString());
		f.setSize(1200, 500);
		f.setLocationRelativeTo(null);

		/* 테이블 초기화 */
		readModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		insertModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				String name = insertModel.getColumnName(c);
				if (name.equals("product_code") || name.equals("register_date") || name.equals("customer_code")) {
					return false;
				} else {
					return true;
				}
			}
		};
		
		insertModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				if (defrow[c].equals("자동입력") || defrow[c].equals("날짜")) {
					return false;
				} else {
					return true;
				}
			}
		};

		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		sp = new JScrollPane(table);

//		이벤트 설정
		/* 뒤로 버튼 */
		back = new JButton("뒤로");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableMain.f.setVisible(true);
				f.dispose();
			}
		});
		/* 윈도우 종료 버튼 */
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				TableMain.f.setVisible(true);
				f.dispose();
			}
		});

	}

//	frame 시작
	public abstract void initFrame();

	public Vector<String> setColumn() throws SQLException {
		return setColumn(this.name);

	}

	public Vector<String> setColumn(String table) throws SQLException {
		TableVO col = new TableVO(table);
		return col.getColumn();
	}

	public String[] setDefrow() throws SQLException {
		TableVO col = new TableVO(this.name);
		String[] row = new String[col.defrow.size()];
		for (int i = 0; i < col.defrow.size(); i++) {
			row[i] = col.defrow.get(i);
		}
		return row;
	}

//	table combobox 추가
	public void addCellComboBox(TableColumn column, String[] tpl) {
		JComboBox<String> comboBox = new JComboBox<String>(tpl);
		column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		column.setCellRenderer(renderer);
	}
}
