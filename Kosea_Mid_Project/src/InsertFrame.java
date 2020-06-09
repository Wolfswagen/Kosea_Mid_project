import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class InsertFrame extends DAO {

	JFrame f;
//  �г�(�˻� ī�װ� ����/�˻��� �Է�/�˻� ��ư/�κ� �˻� ����)
	JPanel p;
	JButton cfm;

//  ��ȸ ���̺� 
	DefaultTableModel model;
	JScrollPane sp;
	JTable table;
	String[] column;

	public InsertFrame() {
//		�ʱ�ȭ �� ����
		setColumn();

		f = new JFrame(this.toString());
		f.setSize(1000, 500);

		/* �гκ� �ʱ�ȭ */
		cfm = new JButton("����");
		p = new JPanel();

		/* ���̺� �ʱ�ȭ */
		model = new DefaultTableModel(column, 100) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* ���̺� ���� �Ұ� ���� */
			public boolean isCellEditable(int i, int c) {
				if (c == 0) {
					model.setValueAt("�ڵ��Է�", i, c);
					return false;

				} else {
					return true;
				}
			}
		};
		table = new JTable(model);
		sp = new JScrollPane(table);

//		�ʱ�ȭ �� ��

//		�̺�Ʈ ����

		cfm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getData();

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
		p.add(cfm);

		/* ������ ��� */
		f.add(sp, BorderLayout.CENTER);
		f.add(p, BorderLayout.SOUTH);

		f.setVisible(true);
	}

//  ���̺� Į�� ����
	public void setColumn() {
		column = new String[] { "product_code", "category", "product_name", "status", "amount", "original_price",
				"discount", "multi_purchase_discount", "discount_rate", "register_date", "shipping" };

	}

	public void getData() {
		int cnt = 0;

	}

	public void insertData() {

	}

	public static void main(String[] args) {
		InsertFrame i = new InsertFrame();
		i.initFrame();
	}

}
