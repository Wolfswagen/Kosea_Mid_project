import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class TableMain {
	static JFrame f;
	JComboBox<String> choice;
	JButton read;
	JButton create;
	JButton update;
	JButton delete;
	JButton exit;

	public TableMain() {
		f = new JFrame("Table");
		f.setLayout(new FlowLayout());
		f.setSize(800, 100);
		f.setLocationRelativeTo(null);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		choice = new JComboBox<String>(new String[] { "Products", "Customers" });

		read = new JButton("��ȸ");
		read.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ReadTableFrame rt = new ReadTableFrame(choice.getSelectedItem().toString());
					rt.initFrame();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
					e1.printStackTrace();
				}
				f.dispose();
			}
		});

		create = new JButton("�Է�");
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					InsertTableFrame it = new InsertTableFrame(choice.getSelectedItem().toString());
					it.initFrame();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
					e1.printStackTrace();
				}

				f.dispose();
			}
		});

		update = new JButton("����");
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateTableFrame ut = new UpdateTableFrame(choice.getSelectedItem().toString());
					ut.initFrame();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
					e1.printStackTrace();
				}
				f.dispose();
			}
		});

		delete = new JButton("����");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DeleteTableFrame dt = new DeleteTableFrame(choice.getSelectedItem().toString());
					dt.initFrame();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
					e1.printStackTrace();
				}

				f.dispose();
			}
		});

		exit = new JButton("�ڷ�");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.f.setVisible(true);
				f.dispose();
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

}
