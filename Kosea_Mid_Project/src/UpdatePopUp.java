import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UpdatePopUp extends SalesFrame {
	JPanel up;

//	�ϴ� �г�(����/����/���)
	JPanel dp;
	JButton sel;
	JButton cfm;
	JButton can;

	DefaultTableModel insertModel;
	String scode;

	public UpdatePopUp(String name, String scode) throws SQLException {
		super(name);

//		�ʱ�ȭ ��
		/* ��� �гκ� �ʱ�ȭ */
		back = new JButton("�ڷ�");
		up = new JPanel();
		up.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));

		/* �ϴ� �гκ� �ʱ�ȭ */
		cfm = new JButton("����");
		cfm.setEnabled(false);
		sel = new JButton("����");
		can = new JButton("���");
		can.setEnabled(false);
		dp = new JPanel();
		dp.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));

		/* ���̺� �� �ʱ�ȭ */
		table.setModel(readModel);

		this.scode = scode;
		insertModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* ���̺� ���� �Ұ� ���� */
			public boolean isCellEditable(int i, int c) {
				if (defrow[c].equals("�ڵ��Է�") || defrow[c].equals("�˻�") || defrow[c].equals("�ŷ���ȣ")) {
					return false;
				} else {
					return true;
				}
			}
		};
//		�ʱ�ȭ �� ��

//		�̺�Ʈ ����
		/* ���� ��ư */
		sel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() > 0) {
					while (table.getSelectedRowCount() > 0) {
						insertModel.addRow(readModel.getDataVector().get(table.getSelectedRow()));
						readModel.removeRow(table.getSelectedRow());
					}
					table.setModel(insertModel);
					cfm.setEnabled(true);
					can.setEnabled(true);
					sel.setEnabled(false);
				}
				JOptionPane.showMessageDialog(sp, insertModel.getRowCount() + "�� ���� ���õǾ����ϴ�.");
			}
		});

		/* ���� ��ư */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(sp, "�����Ͻðڽ��ϱ�?", "Ȯ��", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					try {
						update();
						selectDetails();
						table.setModel(readModel);
						cfm.setEnabled(false);
						can.setEnabled(false);
						sel.setEnabled(true);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
						e1.printStackTrace();
					}
				}
			}

		});
		/* ��� ��ư */
		can.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					insertModel.setNumRows(0);
					selectDetails();
					table.setModel(readModel);

					cfm.setEnabled(false);
					can.setEnabled(false);
					sel.setEnabled(true);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
					e1.printStackTrace();
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

		/* ������ ���� ��ư */
		f.removeWindowListener(f.getWindowListeners()[0]);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});

		selectDetails();
	}

	public void initFrame() {
		/* �гκ� ��� */
		up.add(back);

		dp.add(sel);
		dp.add(cfm);
		dp.add(can);

		/* ������ ��� */
		f.add(up, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);
		f.add(dp, BorderLayout.SOUTH);

		f.setVisible(true);
	}

	public void update() throws SQLException {
		UpdateDAO dao = new UpdateDAO(this.name);
		while (table.getRowCount() > 0) {
			Vector<Object> data = new Vector<Object>();
			for (int i = 0; i < column.size(); i++) {
				data.add(table.getValueAt(0, i));
			}
			dao.set(data);
			insertModel.removeRow(0);
		}
	}

	public void selectDetails() throws SQLException {
		readModel.setNumRows(0);
		ReadDAO dao = new ReadDAO(this.name);
		ArrayList<Vector<Object>> products = dao.list(column.get(0), scode, false);
		for (int i = 0; i < products.size(); i++) {
			readModel.addRow(products.get(i));
		}
	}

	public String toString() {
		return "Update " + this.name;
	}

}
