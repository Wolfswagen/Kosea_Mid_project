import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InsertTableFrame extends TableFrame {
//  �г�(����/�� �߰�/�� ����)
	JPanel p;
	JButton cfm;
	JButton add;
	JButton del;

//  �Է� ���̺�
	String[] defrow;

	public InsertTableFrame(String name) {
		super(name);

//		�ʱ�ȭ ��
		/* �гκ� �ʱ�ȭ */
		cfm = new JButton("�Է�");
		add = new JButton("�� �߰�");
		del = new JButton("�� ����");
		p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));
		/* ���̺� �� �ʱ�ȭ */
		table.setModel(insertModel);
		insertModel.addRow(defrow);

		if (name.equals("Products")) {
			setCellComboBox();
		}

//		�̺�Ʈ ����
		/* ���߰� ��ư */
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertModel.addRow(defrow);
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
						insert();
						insertModel.setNumRows(0);
						insertModel.addRow(defrow);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(table, e1.getMessage(), "����", 0);
					}
				}
			}
		});
	}

	public void initFrame() {
		/* �гκ� ��� */
		p.add(add);
		p.add(del);
		p.add(cfm);
		p.add(back);

		/* ������ ��� */
		f.add(sp, BorderLayout.CENTER);
		f.add(p, BorderLayout.NORTH);

		f.setVisible(true);
	}

//	insert ���� �ۼ�
	public void insert() throws Exception {
		InsertDAO dao = new InsertDAO();

		while (table.getRowCount() > 0) {
			TableVO data;
			if (this.name.equals("Products")) {
				data = new ProductsVO();
			} else {
				data = new CustomersVO();
			}
			for (int i = 0; i < data.column.size(); i++) {
				data.tuple.add(table.getValueAt(0, i));
			}
			try {
				dao.write(this.name, data.tuple);
				insertModel.removeRow(0);
			} catch (Exception e) {
				throw e;
			}
		}

	}

	@Override
	public void setColumn() {
		if (this.name.equals("Products")) {
			this.column = ProductsVO.COLUMN;
			this.defrow = ProductsVO.DEFROW;
		} else {
			this.column = CustomersVO.COLUMN;
			this.defrow = CustomersVO.DEFROW;
		}

	}

	public void setCellComboBox() {
		addCellComboBox(table.getColumnModel().getColumn(1),
				new String[] { "OUTER", "TOP", "BOTTOM", "ONEPIECE", "SHOES", "ACC", "SUMMER" });
		addCellComboBox(table.getColumnModel().getColumn(3), new String[] { "�Ǹ���", "ǰ��" });
		addCellComboBox(table.getColumnModel().getColumn(10), new String[] { "���Ǻ� ����", "����" });
	}

	public String toString() {
		return "Insert " + this.name;
	}

}
