import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;

import javax.swing.*;

public class ReadTableFrame extends TableFrame {

//  �г�(�˻� ī�װ� ����/�˻��� �Է�/�˻� ��ư/�κ� �˻� ����)
	JPanel p;
	JComboBox<String> cmb;
	JTextField inp;
	JButton cfm;
	JCheckBox chk;
	JButton imp;

	String p_code;

	public ReadTableFrame(String name) throws SQLException {
		super(name);

//		�ʱ�ȭ �� ����
		/* �гκ� �ʱ�ȭ */
		inp = new JTextField("", 30);
		cfm = new JButton("�˻�");
		chk = new JCheckBox("�κ� �˻�");
		p = new JPanel();
		cmb = new JComboBox<String>(column);
		imp = new JButton("�԰�");
		imp.setEnabled(false);

		/* ���̺� �𵨼��� */
		table.setModel(readModel);

//		�ʱ�ȭ �� ��

//		�̺�Ʈ ����
		/* �˻� ��ư �̺�Ʈ */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					readModel.setNumRows(0);
					select();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
					e1.printStackTrace();
				}
			}
		});
		/* �԰� ��ư �̺�Ʈ */
		if (this.name.equals("Products")) {
			imp.setEnabled(true);
			imp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						ImportTablePopUp im = new ImportTablePopUp("import_" + name);
						im.initFrame();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
						e1.printStackTrace();
					}
				}
			});
		}
		select();
	}

//	������ ���
	public void initFrame() {
		/* �гκ� ��� */
		p.add(imp);
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

//	SELECT ��� ��ȸ
	public void select() throws SQLException {
		ReadDAO dao = new ReadDAO(this.name);
		ArrayList<Vector<Object>> products = dao.list(cmb.getSelectedItem().toString(), inp.getText(),
				chk.isSelected());
		for (int i = 0; i < products.size(); i++) {
			readModel.addRow(products.get(i));
		}
	}

	public String toString() {
		return "Read " + this.name;
	}

	public String getCode() {
		return p_code;
	}

	public void noExit() {
		f.removeWindowListener(f.getWindowListeners()[0]);
		back.removeActionListener(back.getActionListeners()[0]);

		/* ������ ���� ��ư */
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}

		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					p_code = table.getValueAt(table.getSelectedRow(), 0).toString();
					f.dispose();
				}
			}
		});
		imp.setEnabled(false);
	}
}