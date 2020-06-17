import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;

import javax.swing.*;

public class RudTableFrame extends TableFrame {
//  상단 패널(카테고리/검색창/검색버튼/부분검색)
	JComboBox<String> cmb;
	JTextField inp;
	JButton src;
	JCheckBox chk;
	JPanel up;

//	하단 패널(선택/수정/취소)
	JPanel dp;
	JButton sel;
	JButton del;
	JButton upd;
	JButton can;

	public RudTableFrame(String name) throws SQLException {
		super(name);
//		초기화 블럭
		/* 상단 패널부 초기화 */
		cmb = new JComboBox<String>(column);
		inp = new JTextField("", 40);
		src = new JButton("검색");
		chk = new JCheckBox("부분 검색");
		up = new JPanel();

		/* 하단 패널부 초기화 */
		sel = new JButton("선택");
		upd = new JButton("수정");
		upd.setEnabled(false);
		del = new JButton("삭제");
		del.setEnabled(false);
		can = new JButton("취소");
		can.setEnabled(false);
		dp = new JPanel();
		dp.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));

		/* 테이블 모델 초기화 */
		table.setModel(readModel);
//		초기화 블럭 끝

//		이벤트 설정
		/* 검색 버튼 이벤트 */
		src.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readModel.setNumRows(0);
				select();
			}
		});
		/* 선택 버튼 */
		sel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() > 0) {
					while (table.getSelectedRowCount() > 0) {
						insertModel.addRow(readModel.getDataVector().get(table.getSelectedRow()));
						readModel.removeRow(table.getSelectedRow());
					}
					table.setModel(insertModel);
					if (name.equals("Products")) {
						setCellComboBox();
					}
					cmb.setEnabled(false);
					inp.setEditable(false);
					src.setEnabled(false);
					sel.setEnabled(false);

					upd.setEnabled(true);
					del.setEnabled(true);
					can.setEnabled(true);
				}
				JOptionPane.showMessageDialog(sp, insertModel.getRowCount() + "개 행이 선택되었습니다.");
			}
		});

		/* 수정 버튼 */
		upd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				result = JOptionPane.showConfirmDialog(sp, "수정하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					update();
					select();
					table.setModel(readModel);
					cmb.setEnabled(true);
					inp.setEditable(true);
					src.setEnabled(true);
					sel.setEnabled(true);

					upd.setEnabled(false);
					del.setEnabled(false);
					can.setEnabled(false);
				}
			}
		});

		/* 삭제 버튼 */
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(sp, "삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					int result2 = JOptionPane.showConfirmDialog(sp, "정말 삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
					if (result2 == 0) {
						delete();
						select();
						table.setModel(readModel);
						cmb.setEnabled(true);
						inp.setEditable(true);
						src.setEnabled(true);
						sel.setEnabled(true);

						upd.setEnabled(false);
						del.setEnabled(false);
						can.setEnabled(false);
					}
				}
			}
		});
		/* 취소 버튼 */
		can.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertModel.setNumRows(0);
				select();
				table.setModel(readModel);
				cmb.setEnabled(true);
				inp.setEditable(true);
				src.setEnabled(true);
				sel.setEnabled(true);

				upd.setEnabled(false);
				del.setEnabled(false);
				can.setEnabled(false);
			}
		});

	}

	public void initFrame() {
		/* 패널부 출력 */
		up.add(cmb);
		up.add(inp);
		up.add(src);
		up.add(chk);
		up.add(back);
		select();

		dp.add(sel);
		dp.add(upd);
		dp.add(del);
		dp.add(can);

		/* 프레임 출력 */
		f.add(up, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);
		f.add(dp, BorderLayout.SOUTH);

		f.setVisible(true);
	}

	public void delete() {
		try {
			DeleteDAO dao = new DeleteDAO(this.name);
			while (table.getRowCount() > 0) {
				int ccode = Integer.parseInt(table.getValueAt(0, 0).toString());
				dao.erase(ccode);
				insertModel.removeRow(0);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "오류", 0);
			e.printStackTrace();
		}
	}

	public void select() {
		readModel.setNumRows(0);
		try {
			ReadDAO dao = new ReadDAO(this.name);
			ArrayList<Vector<Object>> products = dao.list(cmb.getSelectedItem().toString(), inp.getText(),
					chk.isSelected());

			for (int i = 0; i < products.size(); i++) {
				readModel.addRow(products.get(i));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "오류", 0);
			e.printStackTrace();
		}
	}

//	update 쿼리 작성
	public void update() {
		try {
			UpdateDAO dao = new UpdateDAO(this.name);
			while (table.getRowCount() > 0) {
				Vector<Object> data = new Vector<Object>();
				for (int i = 0; i < column.size(); i++) {
					data.add(table.getValueAt(0, i));
				}
				dao.set(data);
				insertModel.removeRow(0);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "오류", 0);
			e.printStackTrace();
		}
	}

	public void setCellComboBox() {
		addCellComboBox(table.getColumnModel().getColumn(1),
				new String[] { "OUTER", "TOP", "BOTTOM", "ONEPIECE", "SHOES", "ACC", "SUMMER" });
		addCellComboBox(table.getColumnModel().getColumn(3), new String[] { "판매중", "품절" });
		addCellComboBox(table.getColumnModel().getColumn(10), new String[] { "조건부 무료", "무료" });
	}

	public String toString() {
		return "Read " + this.name;
	}

	public static void main(String[] args) throws SQLException {
		RudTableFrame t = new RudTableFrame("Customers");
		t.initFrame();
	}

}
