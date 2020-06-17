import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReadSalesFrame2 extends SalesFrame {
//  ��� �г�(ī�װ���/�˻�â/�˻���ư/�κа˻�)
	JComboBox<String> cmb;
	JTextField inp;
	JButton src;
	JButton ins;
	JCheckBox chk;
	JPanel up;

//	�ϴ� �г�(����/����/���)
	JPanel dp;
	JButton add;
	JButton del;
	JButton cfm;

	DefaultTableModel insertModel;
	String scode;

	public ReadSalesFrame2(String name) throws SQLException {
		super(name);
		this.name2 = name + "_details";
		this.column2 = setColumn(name2);
		this.defrow2 = setDefrow(name2);

//		�ʱ�ȭ ����
		/* ��� �гκ� �ʱ�ȭ */
		cmb = new JComboBox<String>(column);
		inp = new JTextField("", 40);
		src = new JButton("�˻�");
		chk = new JCheckBox("�κ� �˻�");
		ins = new JButton("�ű� �ŷ���");
		back = new JButton("�ڷ�");
		up = new JPanel();

		/* �ϴ� �гκ� �ʱ�ȭ */
		add = new JButton("���߰�");
		add.setEnabled(false);
		del = new JButton("�����");
		del.setEnabled(false);
		cfm = new JButton("�Է�");
		cfm.setEnabled(false);
		dp = new JPanel();
		dp.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));

		/* ���̺� �� �ʱ�ȭ */
		table.setModel(readModel);

		insertModel = new DefaultTableModel(column2, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* ���̺� ���� �Ұ� ���� */
			public boolean isCellEditable(int i, int c) {
				if (defrow2[c].equals("�ڵ��Է�") || defrow2[c].equals("�˻�")) {
					return false;
				} else {
					return true;
				}
			}
		};
//		�ʱ�ȭ ���� ��

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
		/* �ŷ��� �߰� ��ư */
		ins.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					InsertSalesFrame is = new InsertSalesFrame("Sales");
					is.f.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							try {
								select();
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
								e1.printStackTrace();
							}
						}
					});
					is.initFrame();
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
				if (table.getModel() == readModel) {
					if (e.getClickCount() > 1) {
						try {
							insertModel.setNumRows(0);
							selectDetails(String.valueOf(readModel.getValueAt(table.getSelectedRow(), 0)));
							scode = String.valueOf(readModel.getValueAt(table.getSelectedRow(), 0));
							table.setModel(insertModel);
							back.setEnabled(true);
							add.setEnabled(true);
							del.setEnabled(true);
							cfm.setEnabled(true);
							JOptionPane.showMessageDialog(sp, insertModel.getRowCount() + "�� ���� ���õǾ����ϴ�.");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
							e1.printStackTrace();
						}
					}
				} else {
					if (e.getClickCount() > 1) {
						try {
							ReadTableFrame rt = new ReadTableFrame("Products");
							rt.back.setEnabled(false);
							rt.noExit();
							rt.initFrame();
							rt.f.addWindowListener(new WindowAdapter() {
								public void windowClosed(WindowEvent e) {
									insertModel.setValueAt(rt.getCode(), table.getSelectedRow(), 1);
								}
							});

						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
							e1.printStackTrace();
						}
					}
				}
			}

		});

		/* ���߰� ��ư */
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertModel.addRow(defrow2);
				insertModel.setValueAt(scode, insertModel.getRowCount() - 1, 0);
			}
		});

		/* ����� ��ư */
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getRowCount() > 0) {
					if (table.getSelectedRowCount() > 0) {
						while (table.getSelectedRowCount() > 0) {
							insertModel.removeRow(table.getSelectedRow());
						}
					} else {
						insertModel.removeRow(insertModel.getRowCount() - 1);
					}
				}
			}
		});

		/* �Է� ��ư */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				result = JOptionPane.showConfirmDialog(sp, "�Է��Ͻðڽ��ϱ�?", "Ȯ��", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					try {
						insertDetails(insertModel.getValueAt(0, 0).toString());
						insertModel.setNumRows(0);
						insertModel.addRow(defrow);
						back.setEnabled(false);
						add.setEnabled(false);
						del.setEnabled(false);
						cfm.setEnabled(false);
						readModel.setNumRows(0);
						select();
						table.setModel(readModel);

					} catch (SQLException e1) {
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
				try {
					readModel.setNumRows(0);
					select();
					table.setModel(readModel);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
					e1.printStackTrace();
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
		up.add(ins);
		up.add(back);

		dp.add(add);
		dp.add(del);
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

	public void selectDetails(String code) throws SQLException {
		ReadDAO dao = new ReadDAO(this.name2);
		ArrayList<Vector<Object>> products = dao.list(column.get(0), code, false);
		for (int i = 0; i < products.size(); i++) {
			insertModel.addRow(products.get(i));
		}
	}

	public void insertDetails(String code) throws SQLException {
		InsertDAO dao = new InsertDAO(this.name2);
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
		return "Read " + this.name;
	}

	public static void main(String[] args) throws SQLException {
		ReadSalesFrame2 a = new ReadSalesFrame2("Sales");
		a.initFrame();
	}

}