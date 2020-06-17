import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

public class ReadSalesFrame extends SalesFrame {
//  ��� �г�(ī�װ�/�˻�â/�˻���ư/�κа˻�)
	JComboBox<String> cmb;
	JTextField inp;
	JButton src;
	JCheckBox chk;
	JPanel up;

	public ReadSalesFrame(String name) throws SQLException {
		super(name);
//		�ʱ�ȭ ��
		/* ��� �гκ� �ʱ�ȭ */
		cmb = new JComboBox<String>(column);
		inp = new JTextField("", 40);
		src = new JButton("�˻�");
		chk = new JCheckBox("�κ� �˻�");
		up = new JPanel();
		back = new JButton("�ڷ�");
		back.setEnabled(false);

		/* ���̺� �� �ʱ�ȭ */
		table.setModel(readModel);
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
				if (e.getClickCount() > 1) {
					try {
						readModel2.setNumRows(0);
						selectDetails(String.valueOf(readModel.getValueAt(table.getSelectedRow(), 0)));
						table.setModel(readModel2);
						back.setEnabled(true);
						JOptionPane.showMessageDialog(sp, readModel2.getRowCount() + "�� ���� ���õǾ����ϴ�.");
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
					back.setEnabled(false);
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
		up.add(back);

		/* ������ ��� */
		f.add(up, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);

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
			readModel2.addRow(products.get(i));
		}

	}

	public String toString() {
		return "Read " + this.name;
	}

	public static void main(String[] args) throws SQLException {
		ReadSalesFrame a = new ReadSalesFrame("sales");
		a.initFrame();
	}

}
