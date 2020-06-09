import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public abstract class TableFrame {
	JFrame f;
//  패널(검색 카테고리 선택/검색어 입력/검색 버튼/부분 검색 여부)
	JPanel p;
	JComboBox<String> cmb;
	JTextField inp;
	JButton cfm;
	JCheckBox chk;
	JButton back;

//  조회 테이블 
	DefaultTableModel model;
	JScrollPane sp;
	JTable table;
	String[] column;

	public TableFrame() {
//		초기화 블럭 시작
		setColumn();

		f = new JFrame(this.toString());
		f.setSize(1000, 500);

		/* 패널부 초기화 */
		inp = new JTextField("", 40);
		cfm = new JButton("검색");
		chk = new JCheckBox("부분 검색");
		back = new JButton("뒤로");
		p = new JPanel();
		cmb = new JComboBox<String>(column);
		/* 테이블 초기화 */
		model = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		search();
		table = new JTable(model);
		sp = new JScrollPane(table);

//		초기화 블럭 끝

//		이벤트 설정
		/* 검색 버튼 이벤트 */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model = (DefaultTableModel) table.getModel();
				model.setNumRows(0);
				search();

				table.setModel(model);
				table.repaint();
			}
		});
		/* 뒤로 버튼 */
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchFrame sf = new SearchFrame();
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
		p.add(cmb);
		p.add(inp);
		p.add(cfm);
		p.add(chk);
		p.add(back);

		/* 프레임 출력 */
		f.add(p, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);

		f.setVisible(true);
	}

//  테이블 칼럼 설정
	public abstract void setColumn();

//	SELECT 결과 조회
	public abstract void search();

}
