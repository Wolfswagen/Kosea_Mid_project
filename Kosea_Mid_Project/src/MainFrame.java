import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame {

	JFrame f;
	JComboBox<String> choice;
	JButton read;
	JButton create;

	public MainFrame() {
		f = new JFrame("Main");
		f.setLayout(new FlowLayout());
		f.setSize(500, 130);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		choice = new JComboBox<String>(new String[] { "Products", "Customer" });

		read = new JButton("Á¶È¸");
		read.addActionListener(new ActionListener() {
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

		create = new JButton("»ðÀÔ");
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (choice.getSelectedItem().equals("Products")) {
					InsertProducts ip = new InsertProducts();
					ip.initFrame();
				} else {
					InsertCustomers ic = new InsertCustomers();
					ic.initFrame();
				}
				f.dispose();
			}
		});

	}

	public void initFrame() {
		f.add(choice);
		f.add(read);
		f.add(create);

		f.setVisible(true);
	}

	public static void main(String[] args) {
		MainFrame sf = new MainFrame();
		sf.initFrame();

	}

}
