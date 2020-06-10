import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public abstract class ReadTableFrame {
	JFrame f;
//  �г�(�˻� ī�װ� ����/�˻��� �Է�/�˻� ��ư/�κ� �˻� ����)
	JPanel p;
	JComboBox<String> cmb;
	JTextField inp;
	JButton cfm;
	JCheckBox chk;
	JButton back;

//  ��ȸ ���̺� 
	DefaultTableModel model;
	JScrollPane sp;
	JTable table;
	String[] column;

	public ReadTableFrame() {
//		�ʱ�ȭ �� ����
		setColumn();

		f = new JFrame(this.toString());
		f.setSize(1000, 500);

		/* �гκ� �ʱ�ȭ */
		inp = new JTextField("", 40);
		cfm = new JButton("�˻�");
		chk = new JCheckBox("�κ� �˻�");
		back = new JButton("�ڷ�");
		p = new JPanel();
		cmb = new JComboBox<String>(column);
		/* ���̺� �ʱ�ȭ */
		model = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* ���̺� ���� �Ұ� ���� */
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		select();
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		sp = new JScrollPane(table);

//		�ʱ�ȭ �� ��

//		�̺�Ʈ ����
		/* �˻� ��ư �̺�Ʈ */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model = (DefaultTableModel) table.getModel();
				model.setNumRows(0);
				select();

				table.setModel(model);
				table.repaint();
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
		p.add(cmb);
		p.add(inp);
		p.add(cfm);
		p.add(chk);
		p.add(back);

		/* ������ ��� */
		f.add(p, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);

		f.setVisible(true);
	}

//  ���̺� Į�� ����
	public abstract void setColumn();

//	SELECT ��� ��ȸ
	public abstract void select();

}
