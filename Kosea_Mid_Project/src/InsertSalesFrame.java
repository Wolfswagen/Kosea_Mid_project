import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InsertSalesFrame extends SalesFrame {
//  �г�(����/�� �߰�/�� ����)
	JPanel p;
	JButton cfm;
	DefaultTableModel insertModel;

	public InsertSalesFrame(String name) throws SQLException {
		super(name);

		this.name = name;
		column = setColumn(this.name);
		this.defrow = setDefrow(this.name);

		f.setSize(1000, 400);

		insertModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* ���̺� ���� �Ұ� ���� */
			public boolean isCellEditable(int i, int c) {
				String cname = insertModel.getColumnName(c);
				if (cname.equals("Sales_code")) {
					return false;
				} else {
					return true;
				}
			}
		};

		back = new JButton("�ڷ�");

//		�ʱ�ȭ ��
		/* �гκ� �ʱ�ȭ */
		cfm = new JButton("�Է�");
		p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));
		/* ���̺� �� �ʱ�ȭ */
		table.setModel(insertModel);
		insertModel.addRow(defrow);

//		�̺�Ʈ ����
		/* �Է� ��ư */
		cfm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				result = JOptionPane.showConfirmDialog(sp, "�Է��Ͻðڽ��ϱ�?", "Ȯ��", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					try {
						insert();
						insertModel.setNumRows(0);
						insertModel.addRow(defrow);
						table.setModel(insertModel);
						JOptionPane.showMessageDialog(null, "�Է��� �Ϸ�Ǿ����ϴ�.", "Ȯ��", 1);
						f.dispose();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
						e1.printStackTrace();
					}

				}
			}
		});

		/* �ڷ� ���� */
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}

		});

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});
	}

	public void initFrame() {
		/* �гκ� ��� */

		p.add(cfm);
		p.add(back);

		/* ������ ��� */
		f.add(sp, BorderLayout.CENTER);
		f.add(p, BorderLayout.NORTH);

		f.setVisible(true);
	}

//	insert ���� �ۼ�
	public void insert() throws SQLException {
		InsertDAO dao = new InsertDAO(this.name);
		while (table.getRowCount() > 0) {
			Vector<Object> data = new Vector<Object>();

			for (int i = 0; i < dao.column.size(); i++) {
				data.add(table.getValueAt(0, i));
			}
			dao.write(data);
			insertModel.removeRow(0);
		}
	}

	public String toString() {
		return "Insert " + this.name;
	}

	public static void main(String[] args) throws SQLException {
		InsertSalesFrame i = new InsertSalesFrame("Sales");
		i.initFrame();
	}

}
