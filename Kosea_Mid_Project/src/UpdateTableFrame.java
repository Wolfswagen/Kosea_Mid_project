import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;

import javax.swing.*;

public class UpdateTableFrame extends TableFrame {
//  상단 패널(카테고리/검색창/검색버튼/부분검색)
	JComboBox<String> cmb;
	JTextField inp;
	JButton src;
	JCheckBox chk;
	JPanel up;

//	하단 패널(선택/수정/취소)
	JPanel dp;
	JButton sel;
	JButton cfm;
	JButton can;

	public UpdateTableFrame(String name) throws SQLException {
		super(name);
//		초기화 블럭 시
		/* 상단 패널부 초기화 */
		cmb = new JComboBox<String>(column);
		inp = new JTextField("", 40);
		src = new JButton("검색");
		chk = new JCheckBox("부분 검색");
		up = new JPanel();

		/* 하단 패널부 초기화 */
		cfm = new JButton("수정");
		cfm.setEnabled(false);
		sel = new JButton("선택");
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
				try {
					select();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
					e1.printStackTrace();
				}
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
					cfm.setEnabled(true);
					can.setEnabled(true);
					sel.setEnabled(false);
					cmb.setEnabled(false);
					inp.setEditable(false);
					src.setEnabled(false);
				}
				JOptionPane.showMessageDialog(sp, insertModel.getRowCount() + "개 행이 선택되었습니다.");
			}
		});

		/* 수정 버튼 */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				result = JOptionPane.showConfirmDialog(sp, "수정하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					try {
						update();
						select();
						table.setModel(readModel);
						cfm.setEnabled(false);
						can.setEnabled(false);
						sel.setEnabled(true);
						cmb.setEnabled(true);
						inp.setEditable(true);
						src.setEnabled(true);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
						e1.printStackTrace();
					}
				}
			}
		});
		/* 취소 버튼 */
		can.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					insertModel.setNumRows(0);
					select();
					table.setModel(readModel);
					cfm.setEnabled(false);
					can.setEnabled(false);
					sel.setEnabled(true);
					cmb.setEnabled(true);
					inp.setEditable(true);
					src.setEnabled(true);
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

		dp.add(sel);
		dp.add(cfm);
		dp.add(can);

		/* 프레임 출력 */
		f.add(up, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);
		f.add(dp, BorderLayout.SOUTH);

		f.setVisible(true);
	}

//	update 쿼리 작성
	public void update() throws SQLException {
		UpdateDAO dao = new UpdateDAO(this.name);
		while (table.getRowCount() > 0) {
			Vector<Object> data = new Vector<Object>();
			for (int i = 0; i < column.size(); i++) {
				data.add(table.getValueAt(0, i));
			}
			dao.set(data);
			insertModel.removeRow(0);
		}
	}
	
	public void importProduct() throws SQLException {
		UpdateDAO dao = new UpdateDAO(this.name);
		while (table.getRowCount() > 0) {
			Vector<Object> data = new Vector<Object>();
			for (int i = 0; i < column.size(); i++) {
				data.add(table.getValueAt(0, i));
			}
			dao.set(data);
			insertModel.removeRow(0);
		}
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

	public void setCellComboBox() {
		addCellComboBox(table.getColumnModel().getColumn(1),
				new String[] { "OUTER", "TOP", "BOTTOM", "ONEPIECE", "SHOES", "ACC", "SUMMER" });
		addCellComboBox(table.getColumnModel().getColumn(3), new String[] { "판매중", "품절" });
		addCellComboBox(table.getColumnModel().getColumn(10), new String[] { "조건부 무료", "무료" });
	}
	
	public void noExit() {
		f.removeWindowListener(f.getWindowListeners()[0]);
		back.removeActionListener(back.getActionListeners()[0]);
		cfm.removeActionListener(cfm.getActionListeners()[0]);

		/* 윈도우 종료 버튼 */
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

			}
		});

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}

		});
		cfm.setText("입고");
		/* 수정 버튼 */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				result = JOptionPane.showConfirmDialog(sp, "수정하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					try {
						update();
						select();
						table.setModel(readModel);
						cfm.setEnabled(false);
						can.setEnabled(false);
						sel.setEnabled(true);
						cmb.setEnabled(true);
						inp.setEditable(true);
						src.setEnabled(true);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
						e1.printStackTrace();
					}
				}
			}
		});
		
	}

	public String toString() {
		return "Update " + this.name;
	}
}
