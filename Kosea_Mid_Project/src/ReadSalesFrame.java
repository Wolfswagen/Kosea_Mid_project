import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

public class ReadSalesFrame extends SalesFrame {
//  상단 패널(카테고리/검색창/검색버튼/부분검색)
	JComboBox<String> cmb;
	JTextField inp;
	JButton src;
	JCheckBox chk;
	JPanel up;

	public ReadSalesFrame(String name) throws SQLException {
		super(name);
//		초기화 블럭
		/* 상단 패널부 초기화 */
		cmb = new JComboBox<String>(column);
		inp = new JTextField("", 40);
		src = new JButton("검색");
		chk = new JCheckBox("부분 검색");
		up = new JPanel();
		back = new JButton("뒤로");
		back.setEnabled(false);

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
					try {
						readModel2.setNumRows(0);
						selectDetails(String.valueOf(readModel.getValueAt(table.getSelectedRow(), 0)));
						table.setModel(readModel2);
						back.setEnabled(true);
						JOptionPane.showMessageDialog(sp, readModel2.getRowCount() + "개 행이 선택되었습니다.");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
						e1.printStackTrace();
					}
				}
			}

		});

		/* 뒤로 가기 */
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					readModel.setNumRows(0);
					select();
					table.setModel(readModel);
					back.setEnabled(false);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
					e1.printStackTrace();
				}
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

	public void select() throws SQLException {
		readModel.setNumRows(0);
		ReadDAO dao = new ReadDAO(this.name);
		ArrayList<Vector<Object>> products = dao.list(cmb.getSelectedItem().toString(), inp.getText(),
				chk.isSelected());

		for (int i = 0; i < products.size(); i++) {
			readModel.addRow(products.get(i));
		}
	}

	public void selectDetails(String code) throws SQLException {
		ReadDAO dao = new ReadDAO(this.name2);
		ArrayList<Vector<Object>> products = dao.list(column.get(0), code, false);

		for (int i = 0; i < products.size(); i++) {
			readModel2.addRow(products.get(i));
		}

	}

	public String toString() {
		return "Read " + this.name;
	}

	public static void main(String[] args) throws SQLException {
		ReadSalesFrame a = new ReadSalesFrame("sales");
		a.initFrame();
	}

}
