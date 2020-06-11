import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class InsertTableFrame extends TableFrame {
//  �г�(����/�� �߰�/�� ����)
	JPanel p;
	JButton cfm;
	JButton add;
	JButton del;

//  �Է� ���̺�
	String[] defrow;

	public InsertTableFrame() {
		super();

//		�ʱ�ȭ �� ��
		/* �гκ� �ʱ�ȭ */
		cfm = new JButton("�Է�");
		add = new JButton("�� �߰�");
		del = new JButton("�� ����");
		p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));
		/* ���̺� �� �ʱ�ȭ */
		table.setModel(insertModel);
		insertModel.addRow(defrow);
//		�ʱ�ȭ �� ��

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
	public abstract void insert() throws Exception;

}
