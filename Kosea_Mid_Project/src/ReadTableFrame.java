import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class ReadTableFrame extends TableFrame {

//  �г�(�˻� ī�װ� ����/�˻��� �Է�/�˻� ��ư/�κ� �˻� ����)
	JPanel p;
	JComboBox<String> cmb;
	JTextField inp;
	JButton cfm;
	JCheckBox chk;

	public ReadTableFrame() {
		super();
//		�ʱ�ȭ �� ����
		/* �гκ� �ʱ�ȭ */
		inp = new JTextField("", 40);
		cfm = new JButton("�˻�");
		chk = new JCheckBox("�κ� �˻�");
		p = new JPanel();
		cmb = new JComboBox<String>(column);
		/* ���̺� �𵨼��� */
		table.setModel(readModel);

//		�ʱ�ȭ �� ��

//		�̺�Ʈ ����
		/* �˻� ��ư �̺�Ʈ */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readModel.setNumRows(0);
				select();
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
		select();

		/* ������ ��� */
		f.add(p, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);

		f.setVisible(true);
	}

//	SELECT ��� ��ȸ
	public abstract void select();

}
