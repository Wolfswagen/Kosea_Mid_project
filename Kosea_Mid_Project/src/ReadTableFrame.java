import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class ReadTableFrame extends TableFrame {

//  �г�(�˻� ī�װ� ����/�˻��� �Է�/�˻� ��ư/�κ� �˻� ����)
	JPanel p;
	JComboBox<String> cmb;
	JTextField inp;
	JButton cfm;
	JCheckBox chk;

	public ReadTableFrame(String name) {
		super(name);

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
	public void select() {
		ReadDAO dao = new ReadDAO();
		ArrayList<TableVO> products;
		products = dao.list(this.name, cmb.getSelectedItem().toString(), inp.getText(), chk.isSelected());

		for (int i = 0; i < products.size(); i++) {
			readModel.addRow(products.get(i).tuple);
		}
	}

	@Override
	public void setColumn() {
		if (this.name.equals("Products")) {
			this.column = ProductsVO.COLUMN;
		} else {
			this.column = CustomersVO.COLUMN;
		}

	}

	public String toString() {
		return "Read " + this.name;
	}

}
