import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public abstract class InsertTableFrame {

	JFrame f;
//  패널(수정/행 추가/행 삭제/뒤로)
	JPanel p;
	JButton cfm;
	JButton add;
	JButton del;
	JButton back;

//  입력 테이블	
	DefaultTableModel model;
	JScrollPane sp;
	JTable table;
	String[] column;
	String[] defrow;

	public InsertTableFrame() {
//		초기화 블럭 시작
		setColumn();

		f = new JFrame(this.toString());
		f.setSize(1200, 500);

		/* 패널부 초기화 */

		cfm = new JButton("입력");
		add = new JButton("행 추가");
		del = new JButton("행 삭제");
		back = new JButton("뒤로");
		p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));

		/* 테이블 초기화 */

		model = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				String name = model.getColumnName(c);
				if (name.equals("product_code") || name.equals("register_date") || name.equals("customer_code")) {
					return false;
				} else {
					return true;
				}
			}
		};
		model.addRow(defrow);

		table = new JTable(model);

		table.getTableHeader().setReorderingAllowed(false);
		sp = new JScrollPane(table);
//		초기화 블럭 끝

//		이벤트 설정
		/* 행추가 버튼 */
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.addRow(defrow);
			}
		});
		/* 행삭제 버튼 */
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.getRowCount() > 0) {
					model.removeRow(model.getRowCount() - 1);
				}
			}
		});
		/* 수정 버튼 */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				result = JOptionPane.showConfirmDialog(sp, "입력하시겠습니까?", "확인창", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					try {
						insert();
					} catch (Exception r) {
						System.out.println(r.getMessage());
						r.printStackTrace();
						return;
					}
					model.setNumRows(0);
					model.addRow(defrow);
				}
			}
		});
		/* 뒤로 버튼 */
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame sf = new MainFrame();
				sf.initFrame();
				f.dispose();
			}
		});

		/* 종료 버튼 */
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	public void initFrame() {
		/* 패널부 출력 */
		p.add(add);
		p.add(del);
		p.add(cfm);
		p.add(back);

		/* 프레임 출력 */
		f.add(sp, BorderLayout.CENTER);
		f.add(p, BorderLayout.NORTH);

		f.setVisible(true);
	}

//  테이블 칼럼 설정
	public abstract void setColumn();

//	insert 쿼리 작성
	public abstract void insert();

}
