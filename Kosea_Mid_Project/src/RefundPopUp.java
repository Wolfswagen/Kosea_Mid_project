import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class RefundPopUp {
	JFrame f;
	JTextField[] list;
	JLabel lcolor;
	JLabel lsize;
	JLabel lamount;

	JLabel lfal;
	JComboBox<String> fal;
	JButton cfm;
	Vector<Object> data;

	public RefundPopUp(Vector<Object> par) {
		f = new JFrame("�Է�");
		f.setSize(800, 100);
		f.setLayout(new FlowLayout());
		list = new JTextField[4];
		for (int i = 0; i < list.length; i++) {
			list[i] = new JTextField(par.get(i).toString(), 10);
		}
		list[0].setEditable(false);
		lcolor = new JLabel("����");
		lsize = new JLabel("������");
		lamount = new JLabel("����");
		lfal = new JLabel("��ۺ� �δ�");
		fal = new JComboBox<String>(new String[] { "��ü", "��" });
		if (par.get(4).equals("ȯ��")) {
			list[1].setEditable(false);
			list[2].setEditable(false);
		}
		cfm = new JButton(par.get(4).toString());
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setData();
				f.dispose();
			}

		});
	}

	public void initFrame() {
		f.add(list[0]);
		f.add(lcolor);
		f.add(list[1]);
		f.add(lsize);
		f.add(list[2]);
		f.add(lamount);
		f.add(list[3]);
		f.add(lfal);
		f.add(fal);
		f.add(cfm);
		f.setVisible(true);
	}

	public void setData() {
		data = new Vector<Object>();
		for (int i = 0; i < list.length; i++) {
			data.add(list[i].getText());
		}
		data.add(fal.getSelectedItem());
	};

}
