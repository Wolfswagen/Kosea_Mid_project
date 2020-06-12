import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame {

	JFrame f;
	JComboBox<String> choice;
	JButton read;
	JButton create;
	JButton update;
	JButton delete;
	JButton exit;

	public MainFrame() {
		f = new JFrame("Main");
		f.setLayout(new FlowLayout());
		f.setSize(500, 100);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		choice = new JComboBox<String>(new String[] { "Products", "Customer" });

		read = new JButton("조회");
		read.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (choice.getSelectedItem().equals("Products")) {
					ReadProducts pf = new ReadProducts();
					pf.initFrame();
				} else {
					ReadCustomers cf = new ReadCustomers();
					cf.initFrame();
				}
				f.dispose();
			}
		});

		create = new JButton("입력");
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

		update = new JButton("수정");
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (choice.getSelectedItem().equals("Products")) {
					UpdateProducts up = new UpdateProducts();
					up.initFrame();
				} else {
					UpdateCustomers uc = new UpdateCustomers();
					uc.initFrame();
				}
				f.dispose();
			}
		});

		delete = new JButton("삭제");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (choice.getSelectedItem().equals("Products")) {
					DeleteProducts dp = new DeleteProducts();
					dp.initFrame();
				} else {
					DeleteCustomers dc = new DeleteCustomers();
					dc.initFrame();
				}
				f.dispose();
			}
		});

		exit = new JButton("종료");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	public void initFrame() {
		f.add(choice);
		f.add(read);
		f.add(create);
		f.add(update);
		f.add(delete);
		f.add(exit);

		f.setVisible(true);
	}

	public static void main(String[] args) {
		MainFrame sf = new MainFrame();
		sf.initFrame();
	}

}
