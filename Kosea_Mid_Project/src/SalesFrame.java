import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

public abstract class SalesFrame {
	JFrame f;
//  테이블 
	DefaultTableModel readModel;
	DefaultTableModel readModel2;
	JButton back;
	JScrollPane sp;
	JTable table;

	Vector<String> column;
	String[] defrow;
	String name;

	Vector<String> column2;
	String[] defrow2;
	String name2;

	public SalesFrame(String name) throws SQLException {
//		초기화 블럭
		this.name = name + "_Customers_Details";
		name2 = name + "_Products_Details";
		column = setColumn(this.name);
		column2 = setColumn(name2);

		defrow = setDefrow(this.name);
		defrow2 = setDefrow(name2);

		f = new JFrame(this.toString());
		f.setSize(1500, 500);

		/* 테이블 초기화 */
		readModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		readModel2 = new DefaultTableModel(column2, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		sp = new JScrollPane(table);

//		이벤트 설정

		/* 윈도우 종료 버튼 */
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

//	frame 시작
	public abstract void initFrame();

	public Vector<String> setColumn(String table) throws SQLException {
		TableVO col = new TableVO(table);
		return col.getColumn();
	}

	public String[] setDefrow(String name) throws SQLException {
		TableVO col = new TableVO(name);
		String[] row = new String[col.defrow.size()];
		for (int i = 0; i < col.defrow.size(); i++) {
			row[i] = col.defrow.get(i);
		}
		return row;
	}
}
