import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RefundSalesFrame extends SalesFrame {
//  ��� �г�(ī�װ�/�˻�â/�˻���ư/�κа˻�)
	JComboBox<String> cmb;
	JTextField inp;
	JButton src;
	JCheckBox chk;
	JPanel up;

//	�ϴ� �г�(�߰�/����/���)
	JPanel dp;
	JComboBox<String> cho;
	JButton cfm;
	JButton add;

	DefaultTableModel readModel2;
	Vector<String> column2;
	String[] defrow2;
	String name2;

	Vector<Object> list;

	public RefundSalesFrame(String name) throws SQLException {
		super(name);
		this.name = name + "_Customers_Details";
		this.column = setColumn(this.name);
		this.name2 = name + "_Products_Details";
		this.column2 = setColumn(this.name2);

//		�ʱ�ȭ ��
		/* ��� �гκ� �ʱ�ȭ */
		cmb = new JComboBox<String>(column);
		inp = new JTextField("", 40);
		src = new JButton("�˻�");
		chk = new JCheckBox("�κ� �˻�");
		up = new JPanel();
		back = new JButton("�ڷ�");

		/* ��� �гκ� �ʱ�ȭ */
		cho = new JComboBox<String>(new String[] { "ȯ��", "��ȯ" });
		cho.setEnabled(false);
		cfm = new JButton("Ȯ��");
		cfm.setEnabled(false);
		dp = new JPanel();
		dp.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));

		/* ���̺� �� �ʱ�ȭ */
		readModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* ���̺� ���� �Ұ� ���� */
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		table.setModel(readModel);

		readModel2 = new DefaultTableModel(column2, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* ���̺� ���� �Ұ� ���� */
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
//		�ʱ�ȭ �� ��

//		�̺�Ʈ ����
		/* �˻� ��ư �̺�Ʈ */
		src.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readModel.setNumRows(0);
				try {
					select();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
					e1.printStackTrace();
				}
			}
		});

		/* ���콺 ���� */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getModel().equals(readModel)) {
					if (e.getClickCount() > 1) {
						try {
							readModel2.setNumRows(0);
							selectDetails(String.valueOf(readModel.getValueAt(table.getSelectedRow(), 0)), name2);
							table.setModel(readModel2);
							JOptionPane.showMessageDialog(sp, readModel2.getRowCount() + "�� ���� ���õǾ����ϴ�.");
							cho.setEnabled(true);
							cfm.setEnabled(true);
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
							e1.printStackTrace();
						}
					}
				}
			}

		});
		/* �ڷ� ���� */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Vector<Object> param = new Vector<Object>();
				param.add(readModel2.getValueAt(table.getSelectedRow(), 2));
				param.add(readModel2.getValueAt(table.getSelectedRow(), 3));
				param.add(readModel2.getValueAt(table.getSelectedRow(), 4));
				param.add(readModel2.getValueAt(table.getSelectedRow(), 6));
				param.add(cho.getSelectedItem());
				RefundPopUp rp = new RefundPopUp(param);
				rp.initFrame();
				list = new Vector<Object>();
				rp.f.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						for (int i = 0; i < rp.data.size(); i++) {
							list.add(rp.data.get(i));
						}
						System.out.println(list);
					}
					
				});
			}

		});

		/* �ڷ� ���� */
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getModel().equals(readModel)) {
//					SalesMain.f.setVisible(true);
//					f.dispose();
				} else {
					try {
						readModel.setNumRows(0);
						select();
						table.setModel(readModel);
						cho.setEnabled(false);
						cfm.setEnabled(false);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
						e1.printStackTrace();
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

		dp.add(cho);
		dp.add(cfm);

		/* ������ ��� */
		f.add(up, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);
		f.add(dp, BorderLayout.SOUTH);

		f.setVisible(true);
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

	public void selectDetails(String code, String name) throws SQLException {
		ReadDAO dao = new ReadDAO(name);
		ArrayList<Vector<Object>> list = dao.list(column.get(0), code, false);
		for (int i = 0; i < list.size(); i++) {
			readModel2.addRow(list.get(i));
		}
	}

	public String toString() {
		return "Refunds";
	}

	public static void main(String[] args) throws SQLException {
		RefundSalesFrame r = new RefundSalesFrame("Refunds");
		r.initFrame();
	}
}
