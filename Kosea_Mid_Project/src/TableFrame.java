
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

public abstract class TableFrame {
	JFrame f;
	JButton back;
//  ���̺� 
	DefaultTableModel readModel;
	DefaultTableModel insertModel;
	JScrollPane sp;
	JTable table;
	Vector<String> column;
	Vector<String> defrow;
	String name;

	public TableFrame(String name) throws SQLException {
//		�ʱ�ȭ ��
		this.name = name;
		setColumn();

		f = new JFrame(this.toString());
		f.setSize(1000, 500);

		/* ���̺� �ʱ�ȭ */
		readModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* ���̺� ���� �Ұ� ���� */
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		insertModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* ���̺� ���� �Ұ� ���� */
			public boolean isCellEditable(int i, int c) {
				String name = insertModel.getColumnName(c);
				if (name.equals("product_code") || name.equals("register_date") || name.equals("customer_code")) {
					return false;
				} else {
					return true;
				}
			}
		};

		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		sp = new JScrollPane(table);

//		�̺�Ʈ ����
		/* �ڷ� ��ư */
		back = new JButton("�ڷ�");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame sf = new MainFrame();
				sf.initFrame();
				f.dispose();
			}
		});
		/* ������ ���� ��ư */
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

//	frame ����
	public abstract void initFrame();

	public void setColumn() throws SQLException {
		TableVO col;
		col = new TableVO(name);
		this.column = col.getColumn();
		this.defrow = col.defrow;
	}

//	table combobox �߰�
	public void addCellComboBox(TableColumn column, String[] tpl) {
		JComboBox<String> comboBox = new JComboBox<String>(tpl);
		column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		column.setCellRenderer(renderer);
	}
}
