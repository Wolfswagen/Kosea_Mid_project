import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class SalesMain {
	static JFrame f;
	JButton read;
	JButton create;
	JButton update;
	JButton delete;
	JButton refund;

	JButton exit;

	public SalesMain() {
		f = new JFrame("Sales");
		f.setLayout(new FlowLayout());
		f.setSize(800, 100);
		f.setLocationRelativeTo(null);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		read = new JButton("�ŷ��� ��ȸ");
		read.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ReadSalesFrame rt = new ReadSalesFrame("Sales");
					rt.initFrame();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
					e1.printStackTrace();
				}
				f.dispose();
			}
		});

		create = new JButton("�ŷ��� �߰�");
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					InsertSalesFrame it = new InsertSalesFrame("Sales");
					it.initFrame();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
					e1.printStackTrace();
				}
				f.dispose();
			}
		});

		update = new JButton("�ŷ��� ����");
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateSalesFrame ut = new UpdateSalesFrame("Sales");
					ut.initFrame();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
					e1.printStackTrace();
				}
				f.dispose();
			}
		});

		delete = new JButton("�ŷ��� ����/����");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					FinishDeleteFrame dt = new FinishDeleteFrame("Sales");
					dt.initFrame();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", 0);
					e1.printStackTrace();
				}

				f.dispose();
			}
		});

		refund = new JButton("ȯ��/��ȯ");
		refund.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					RefundSalesFrame dt = new RefundSalesFrame("Refunds");
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
		f.add(read);
		f.add(create);
		f.add(update);
		f.add(delete);
		f.add(refund);
		f.add(exit);

		f.setVisible(true);
	}

}
