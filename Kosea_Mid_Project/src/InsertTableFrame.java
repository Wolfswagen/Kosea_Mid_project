import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public abstract class InsertTableFrame {

	JFrame f;
//  �г�(����/�� �߰�/�� ����/�ڷ�)
	JPanel p;
	JButton cfm;
	JButton add;
	JButton del;
	JButton back;

//  �Է� ���̺�	
	DefaultTableModel model;
	JScrollPane sp;
	JTable table;
	String[] column;
	String[] defrow;

	public InsertTableFrame() {
//		�ʱ�ȭ �� ����
		setColumn();

		f = new JFrame(this.toString());
		f.setSize(1200, 500);

		/* �гκ� �ʱ�ȭ */

		cfm = new JButton("�Է�");
		add = new JButton("�� �߰�");
		del = new JButton("�� ����");
		back = new JButton("�ڷ�");
		p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));

		/* ���̺� �ʱ�ȭ */

		model = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* ���̺� ���� �Ұ� ���� */
			public boolean isCellEditable(int i, int c) {
				String name = model.getColumnName(c);
				if (name.equals("product_code") || name.equals("register_date") || name.equals("customer_code")) {
					return false;
				} else {
					return true;
				}
			}
		};
		model.addRow(defrow);

		table = new JTable(model);

		table.getTableHeader().setReorderingAllowed(false);
		sp = new JScrollPane(table);
//		�ʱ�ȭ �� ��

//		�̺�Ʈ ����
		/* ���߰� ��ư */
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.addRow(defrow);
			}
		});
		/* ����� ��ư */
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.getRowCount() > 0) {
					model.removeRow(model.getRowCount() - 1);
				}
			}
		});
		/* ���� ��ư */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				result = JOptionPane.showConfirmDialog(sp, "�Է��Ͻðڽ��ϱ�?", "Ȯ��â", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					try {
						insert();
					} catch (Exception r) {
						System.out.println(r.getMessage());
						r.printStackTrace();
						return;
					}
					model.setNumRows(0);
					model.addRow(defrow);
				}
			}
		});
		/* �ڷ� ��ư */
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame sf = new MainFrame();
				sf.initFrame();
				f.dispose();
			}
		});

		/* ���� ��ư */
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
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

//  ���̺� Į�� ����
	public abstract void setColumn();

//	insert ���� �ۼ�
	public abstract void insert();

}
