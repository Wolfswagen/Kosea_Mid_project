
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

public abstract class SalesFrame {
	JFrame f;
//  ���̺� 
	DefaultTableModel readModel;

	JButton back;
	JScrollPane sp;
	JTable table;

	Vector<String> column;
	String[] defrow;
	String name;

	public SalesFrame(String name) throws SQLException {
//		�ʱ�ȭ ��
		this.name = name;
		column = setColumn(this.name);
		defrow = setDefrow(this.name);

		f = new JFrame(this.toString());
		f.setSize(1000, 500);
		f.setLocationRelativeTo(null);

		/* ���̺� �ʱ�ȭ */
		readModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* ���̺� ���� �Ұ� ���� */
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		sp = new JScrollPane(table);

//		�̺�Ʈ ����

		/* ������ ���� ��ư */
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				SalesMain.f.setVisible(true);
				f.dispose();
			}
		});

	}

//	frame ����
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
