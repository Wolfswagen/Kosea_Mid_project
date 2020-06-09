import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class InsertFrame extends DAO {

	JFrame f;
//  패널(검색 카테고리 선택/검색어 입력/검색 버튼/부분 검색 여부)
	JPanel p;
	JButton cfm;

//  조회 테이블 
	DefaultTableModel model;
	JScrollPane sp;
	JTable table;
	String[] column;

	public InsertFrame() {
//		초기화 블럭 시작
		setColumn();

		f = new JFrame(this.toString());
		f.setSize(1000, 500);

		/* 패널부 초기화 */
		cfm = new JButton("수정");
		p = new JPanel();

		/* 테이블 초기화 */
		model = new DefaultTableModel(column, 100) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				if (c == 0) {
					model.setValueAt("자동입력", i, c);
					return false;

				} else {
					return true;
				}
			}
		};
		table = new JTable(model);
		sp = new JScrollPane(table);

//		초기화 블럭 끝

//		이벤트 설정

		cfm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getData();

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
		p.add(cfm);

		/* 프레임 출력 */
		f.add(sp, BorderLayout.CENTER);
		f.add(p, BorderLayout.SOUTH);

		f.setVisible(true);
	}

//  테이블 칼럼 설정
	public void setColumn() {
		column = new String[] { "product_code", "category", "product_name", "status", "amount", "original_price",
				"discount", "multi_purchase_discount", "discount_rate", "register_date", "shipping" };

	}

	public void getData() {
		int cnt = 0;

	}

	public void insertData() {

	}

	public static void main(String[] args) {
		InsertFrame i = new InsertFrame();
		i.initFrame();
	}

}
