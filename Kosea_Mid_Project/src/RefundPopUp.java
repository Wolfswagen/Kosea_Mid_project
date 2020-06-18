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
		f = new JFrame("입력");
		f.setSize(800, 100);
		f.setLayout(new FlowLayout());
		list = new JTextField[4];
		for (int i = 0; i < list.length; i++) {
			list[i] = new JTextField(par.get(i).toString(), 10);
		}
		list[0].setEditable(false);
		lcolor = new JLabel("색상");
		lsize = new JLabel("사이즈");
		lamount = new JLabel("수량");
		lfal = new JLabel("배송비 부담");
		fal = new JComboBox<String>(new String[] { "업체", "고객" });
		if (par.get(4).equals("환불")) {
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
