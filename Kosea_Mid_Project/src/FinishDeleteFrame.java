import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FinishDeleteFrame extends SalesFrame {
//  ��� �г�(ī�װ�/�˻�â/�˻���ư/�κа˻�)
	JComboBox<String> cmb;
	JTextField inp;
	JButton src;
	JCheckBox chk;
	JPanel up;

//	�ϴ� �г�(����/����/���)
	JPanel dp;
	JButton sel;
	JButton fin;
	JButton cfm;
	JButton can;

	DefaultTableModel readModel2;
	String scode;

	public FinishDeleteFrame(String name) throws SQLException {
		super(name);
		readModel2 = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* ���̺� ���� �Ұ� ���� */
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
//		�ʱ�ȭ ��
		/* ��� �гκ� �ʱ�ȭ */
		cmb = new JComboBox<String>(column);
		inp = new JTextField("", 40);
		src = new JButton("�˻�");
		chk = new JCheckBox("�κ� �˻�");
		back = new JButton("�ڷ�");
		up = new JPanel();

		/* �ϴ� �гκ� �ʱ�ȭ */
		fin = new JButton("����");
		fin.setEnabled(false);
		cfm = new JButton("����");
		cfm.setEnabled(false);
		sel = new JButton("����");
		can = new JButton("���");
		can.setEnabled(false);
		dp = new JPanel();
		dp.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));

		/* ���̺� �� �ʱ�ȭ */
		table.setModel(readModel);
//		�ʱ�ȭ �� ��

//		�̺�Ʈ ����
		/* �˻� ��ư �̺�Ʈ */
		src.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					readModel.setNumRows(0);
					select();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
					e1.printStackTrace();
				}
			}
		});
		/* ���� ��ư */
		sel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() > 0) {
					while (table.getSelectedRowCount() > 0) {
						readModel2.addRow(readModel.getDataVector().get(table.getSelectedRow()));
						readModel.removeRow(table.getSelectedRow());
					}
					table.setModel(readModel2);
					cfm.setEnabled(true);
					fin.setEnabled(true);
					can.setEnabled(true);
					sel.setEnabled(false);
					cmb.setEnabled(false);
					inp.setEditable(false);
					src.setEnabled(false);
				}
				JOptionPane.showMessageDialog(sp, readModel2.getRowCount() + "�� ���� ���õǾ����ϴ�.");
			}
		});

		/* ���� ��ư */
		fin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(sp,
						"���õ� �ŷ� " + readModel2.getRowCount() + " ���� �Ϸ� ó���մϴ�. �����Ͻðڽ��ϱ�?", "Ȯ��",
						JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					try {
						finish();
						select();
						table.setModel(readModel);
						cfm.setEnabled(false);
						fin.setEnabled(false);
						can.setEnabled(false);
						sel.setEnabled(true);
						cmb.setEnabled(true);
						inp.setEditable(true);
						src.setEnabled(true);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
						e1.printStackTrace();
					}
				}
			}
		});

		/* ���� ��ư */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(sp,
						"���õ� �ŷ� " + readModel2.getRowCount() + " ���� ��� �����˴ϴ�. �����Ͻðڽ��ϱ�?", "Ȯ��",
						JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					int result2 = JOptionPane.showConfirmDialog(sp, "���� �����Ͻðڽ��ϱ�?", "Ȯ��", JOptionPane.YES_NO_OPTION);
					if (result2 == 0) {
						try {
							delete();
							select();
							table.setModel(readModel);
							cfm.setEnabled(false);
							fin.setEnabled(false);
							can.setEnabled(false);
							sel.setEnabled(true);
							cmb.setEnabled(true);
							inp.setEditable(true);
							src.setEnabled(true);
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
							e1.printStackTrace();
						}
					}
				}
			}
		});

		/* ��� ��ư */
		can.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					readModel2.setNumRows(0);
					select();
					table.setModel(readModel);

					cfm.setEnabled(false);
					fin.setEnabled(false);
					can.setEnabled(false);
					sel.setEnabled(true);
					cmb.setEnabled(true);
					inp.setEditable(true);
					src.setEnabled(true);
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
				SalesMain.f.setVisible(true);
				f.dispose();
			}
		});
		/* ���콺 ���� */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getModel().equals(readModel)) {
					if (e.getClickCount() > 1) {
						try {
							DeletePopUp dp = new DeletePopUp(name + "_details",
									table.getValueAt(table.getSelectedRow(), 0).toString());
							dp.initFrame();
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
							e1.printStackTrace();
						}

					}
				}
			}
		});

		select();
	}

	public void initFrame() {
		/* �гκ� ��� */
		up.add(cmb);
		up.add(inp);
		up.add(src);
		up.add(chk);
		up.add(back);

		dp.add(sel);
		dp.add(fin);
		dp.add(cfm);
		dp.add(can);

		/* ������ ��� */
		f.add(up, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);
		f.add(dp, BorderLayout.SOUTH);

		f.setVisible(true);
	}

	public void delete() throws SQLException {
		DeleteDAO dao = new DeleteDAO(this.name);
		while (table.getRowCount() > 0) {
			int ccode = Integer.parseInt(table.getValueAt(0, 0).toString());
			dao.erase(ccode);
			readModel2.removeRow(0);
		}
	}

	public void finish() throws SQLException {
		FinishDAO dao = new FinishDAO(this.name);
		while (table.getRowCount() > 0) {
			int ccode = Integer.parseInt(table.getValueAt(0, 0).toString());
			dao.end(ccode);
			readModel2.removeRow(0);
		}
	}

	public void select() throws SQLException {
		readModel.setNumRows(0);
		ReadDAO dao = new ReadDAO(this.name);
		ArrayList<Vector<Object>> products = dao.list(cmb.getSelectedItem().toString(), inp.getText(),
				chk.isSelected());
		for (int i = 0; i < products.size(); i++) {
			readModel.addRow(products.get(i));
		}
	}

	public String toString() {
		return "Delete " + this.name;
	}

}
