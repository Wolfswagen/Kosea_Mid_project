import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchFrame {

	JFrame f;
	JComboBox<String> choice;
	JButton btn;

	public SearchFrame() {
		f = new JFrame("search");
		f.setLayout(new FlowLayout());
		f.setSize(300, 100);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		choice = new JComboBox<String>(new String[] { "Products", "Customer" });

		btn = new JButton("Á¶È¸");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (choice.getSelectedItem().equals("Products")) {
					ProductsFrame pf = new ProductsFrame();
					pf.initFrame();
				} else {
					CustomersFrame cf = new CustomersFrame();
					cf.initFrame();
				}
				f.dispose();
			}
		});

	}

	public void initFrame() {
		f.add(choice);
		f.add(btn);

		f.setVisible(true);
	}

	public static void main(String[] args) {
		SearchFrame sf = new SearchFrame();
		sf.initFrame();

	}

}
