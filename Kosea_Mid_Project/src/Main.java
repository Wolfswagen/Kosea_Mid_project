import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
	static JFrame f;
	JButton table;
	JButton sales;
	JButton exit;

	public Main() {
		f = new JFrame("Main");
		f.setLayout(new FlowLayout());
		f.setSize(500, 100);
		f.setLocationRelativeTo(null);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		table = new JButton("상품/고객 정보");
		table.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableMain tm = new TableMain();
				tm.initFrame();
				f.dispose();
			}
		});

		sales = new JButton("거래 정보");
		sales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SalesMain sm = new SalesMain();
				sm.initFrame();

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
		f.add(table);
		f.add(sales);
		f.add(exit);

		f.setVisible(true);
	}

	public static void main(String[] args) {
		Main sf = new Main();
		sf.initFrame();
	}
}
