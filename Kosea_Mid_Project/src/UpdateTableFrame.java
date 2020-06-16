import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;

import javax.swing.*;

public class UpdateTableFrame extends TableFrame {
//  ��� �г�(ī�װ�/�˻�â/�˻���ư/�κа˻�)
	JComboBox<String> cmb;
	JTextField inp;
	JButton src;
	JCheckBox chk;
	JPanel up;

//	�ϴ� �г�(����/����/���)
	JPanel dp;
	JButton sel;
	JButton cfm;
	JButton can;

	public UpdateTableFrame(String name) throws SQLException {
		super(name);
//		�ʱ�ȭ �� ��
		/* ��� �гκ� �ʱ�ȭ */
		cmb = new JComboBox<String>(column);
		inp = new JTextField("", 40);
		src = new JButton("�˻�");
		chk = new JCheckBox("�κ� �˻�");
		up = new JPanel();

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
//		�ʱ�ȭ �� ��

//		�̺�Ʈ ����
		/* �˻� ��ư �̺�Ʈ */
		src.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readModel.setNumRows(0);
				select();
			}
		});
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
					if (name.equals("Products")) {
						setCellComboBox();
					}
					cfm.setEnabled(true);
					can.setEnabled(true);
					sel.setEnabled(false);
					cmb.setEnabled(false);
					inp.setEditable(false);
					src.setEnabled(false);
				}
				JOptionPane.showMessageDialog(sp, insertModel.getRowCount() + "�� ���� ���õǾ����ϴ�.");
			}
		});

		/* ���� ��ư */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				result = JOptionPane.showConfirmDialog(sp, "�����Ͻðڽ��ϱ�?", "Ȯ��", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					update();
					select();
					table.setModel(readModel);
					cfm.setEnabled(false);
					can.setEnabled(false);
					sel.setEnabled(true);
					cmb.setEnabled(true);
					inp.setEditable(true);
					src.setEnabled(true);
				}
			}
		});
		/* ��� ��ư */
		can.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertModel.setNumRows(0);
				select();
				table.setModel(readModel);
				cfm.setEnabled(false);
				can.setEnabled(false);
				sel.setEnabled(true);
				cmb.setEnabled(true);
				inp.setEditable(true);
				src.setEnabled(true);
			}
		});
	}

	public void initFrame() {
		/* �гκ� ��� */
		up.add(cmb);
		up.add(inp);
		up.add(src);
		up.add(chk);
		up.add(back);
		select();

		dp.add(sel);
		dp.add(cfm);
		dp.add(can);

		/* ������ ��� */
		f.add(up, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);
		f.add(dp, BorderLayout.SOUTH);

		f.setVisible(true);
	}

//	update ���� �ۼ�
	public void update() {
		try {
			UpdateDAO dao = new UpdateDAO(this.name);
			while (table.getRowCount() > 0) {
				Vector<Object> data = new Vector<Object>();
				for (int i = 0; i < column.size(); i++) {
					data.add(table.getValueAt(0, i));
				}
				dao.set(this.name, data);
				insertModel.removeRow(0);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����", 0);
			e.printStackTrace();
		}

	}

	public void select() {
		readModel.setNumRows(0);
		try {
			ReadDAO dao = new ReadDAO(this.name);
			ArrayList<Vector<Object>> products = dao.list(this.name, cmb.getSelectedItem().toString(), inp.getText(),
					chk.isSelected());

			for (int i = 0; i < products.size(); i++) {
				readModel.addRow(products.get(i));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����", 0);
			e.printStackTrace();
		}

	}

	public void setCellComboBox() {
		addCellComboBox(table.getColumnModel().getColumn(1),
				new String[] { "OUTER", "TOP", "BOTTOM", "ONEPIECE", "SHOES", "ACC", "SUMMER" });
		addCellComboBox(table.getColumnModel().getColumn(3), new String[] { "�Ǹ���", "ǰ��" });
		addCellComboBox(table.getColumnModel().getColumn(10), new String[] { "���Ǻ� ����", "����" });
	}

	public String toString() {
		return "Update " + this.name;
	}
}
