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

		choice = new JComboBox<String>(new String[] { "Products", "Customers"});

		read = new JButton("조회");
		read.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReadTableFrame rt = new ReadTableFrame(choice.getSelectedItem().toString());
				rt.initFrame();
				f.dispose();
			}
		});

		create = new JButton("입력");
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InsertTableFrame it = new InsertTableFrame(choice.getSelectedItem().toString());
				it.initFrame();
				f.dispose();
			}
		});

		update = new JButton("수정");
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateTableFrame ut = new UpdateTableFrame(choice.getSelectedItem().toString());
				ut.initFrame();
				f.dispose();
			}
		});

		delete = new JButton("삭제");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteTableFrame dt = new DeleteTableFrame(choice.getSelectedItem().toString());
				dt.initFrame();
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
