
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

	JButton back;
	JScrollPane sp;
	JTable table;

	Vector<String> column;
	String[] defrow;
	String name;

	public SalesFrame(String name) throws SQLException {
//		초기화 블럭
		this.name = name;
		column = setColumn(this.name);
		defrow = setDefrow(this.name);

		f = new JFrame(this.toString());
		f.setSize(1000, 500);
		f.setLocationRelativeTo(null);

		/* 테이블 초기화 */
		readModel = new DefaultTableModel(column, 0) {
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
				SalesMain.f.setVisible(true);
				f.dispose();
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
