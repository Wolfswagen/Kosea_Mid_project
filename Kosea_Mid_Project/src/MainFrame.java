import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends ConnectDAO {

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
					ReadProductsFrame pf = new ReadProductsFrame();
					pf.initFrame();
				} else {
					ReadCustomersFrame cf = new ReadCustomersFrame();
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
					InsertProductsFrame ip = new InsertProductsFrame();
					ip.initFrame();
				} else {
					InsertCustomersFrame ic = new InsertCustomersFrame();
					ic.initFrame();
				}
				f.dispose();
			}
		});

		update = new JButton("수정");
		delete = new JButton("삭제");

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
