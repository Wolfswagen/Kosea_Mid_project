import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;

import javax.swing.*;

public class ImportTablePopUp extends TableFrame {
//  상단 패널(카테고리/검색창/검색버튼/부분검색)
	JComboBox<String> cmb;
	JTextField inp;
	JButton src;
	JCheckBox chk;
	JPanel up;

	public ImportTablePopUp(String name) throws SQLException {
		super(name);
//		초기화 블럭 시
		/* 상단 패널부 초기화 */
		cmb = new JComboBox<String>(column);
		inp = new JTextField("", 40);
		src = new JButton("검색");
		chk = new JCheckBox("부분 검색");
		up = new JPanel();

		/* 테이블 모델 초기화 */
		table.setModel(readModel);

//		초기화 블럭 끝

//		이벤트 설정
		/* 검색 버튼 이벤트 */
		src.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readModel.setNumRows(0);
				try {
					select();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
					e1.printStackTrace();
				}
			}
		});

		/* 마우스 선택 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					String amount = JOptionPane.showInputDialog("입고 수량");
					try {
						importProduct(table.getValueAt(table.getSelectedRow(), 0).toString(), amount);
						select();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
						e1.printStackTrace();
					}
				}
			}
		});

		f.removeWindowListener(f.getWindowListeners()[0]);
		back.removeActionListener(back.getActionListeners()[0]);

		/* 윈도우 종료 버튼 */
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		select();
	}

	public void initFrame() {
		/* 패널부 출력 */
		up.add(cmb);
		up.add(inp);
		up.add(src);
		up.add(chk);
		up.add(back);

		/* 프레임 출력 */
		f.add(up, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);

		f.setVisible(true);
	}

//	import 쿼리 작성
	public void importProduct(String code, String amount) throws SQLException {
		UpdateDAO dao = new UpdateDAO(this.name);
		dao.getIn(code, amount);
	}

	public void select() throws SQLException {
		readModel.setNumRows(0);
		ReadDAO dao = new ReadDAO(this.name);
		ArrayList<Vector<Object>> products = dao.list(cmb.getSelectedItem().toString(), inp.getText(),
				chk.isSelected());

		for (int i = 0; i < products.size(); i++) {
			readModel.addRow(products.get(i));
		}
	}

	public String toString() {
		return this.name;
	}

}
