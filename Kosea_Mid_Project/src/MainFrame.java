import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

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

		choice = new JComboBox<String>(new String[] { "Products", "Customers" });

		read = new JButton("조회");
		read.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ReadTableFrame2 rt = new ReadTableFrame2(choice.getSelectedItem().toString());
					rt.initFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				f.dispose();
			}
		});

		create = new JButton("입력");
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					InsertTableFrame2 it = new InsertTableFrame2(choice.getSelectedItem().toString());
					it.initFrame();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				f.dispose();
			}
		});

		update = new JButton("수정");
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateTableFrame2 ut = new UpdateTableFrame2(choice.getSelectedItem().toString());
					ut.initFrame();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				f.dispose();
			}
		});

		delete = new JButton("삭제");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DeleteTableFrame2 dt = new DeleteTableFrame2(choice.getSelectedItem().toString());
					dt.initFrame();
				} catch (SQLException e1) {
					e1.printStackTrace();
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
