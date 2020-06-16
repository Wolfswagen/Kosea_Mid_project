import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DeleteTableFrame2 extends TableFrame2 {
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

	DefaultTableModel readModel2;

	public DeleteTableFrame2(String name) throws SQLException {
		super(name);
		readModel2 = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
//		초기화 블럭
		/* 상단 패널부 초기화 */
		cmb = new JComboBox<String>(column);
		inp = new JTextField("", 40);
		src = new JButton("검색");
		chk = new JCheckBox("부분 검색");
		up = new JPanel();

		/* 하단 패널부 초기화 */
		cfm = new JButton("삭제");
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
					// TODO Auto-generated catch block
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
						readModel2.addRow(readModel.getDataVector().get(table.getSelectedRow()));
						readModel.removeRow(table.getSelectedRow());
					}
					table.setModel(readModel2);
					cfm.setEnabled(true);
					can.setEnabled(true);
					sel.setEnabled(false);
					cmb.setEnabled(false);
					inp.setEditable(false);
					src.setEnabled(false);
				}
				JOptionPane.showMessageDialog(sp, readModel2.getRowCount() + "개 행이 선택되었습니다.");
			}
		});

		/* 삭제 버튼 */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(sp, "삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					int result2 = JOptionPane.showConfirmDialog(sp, "정말 삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
					if (result2 == 0) {
						try {
							delete();
							select();
							table.setModel(readModel);
							cfm.setEnabled(false);
							can.setEnabled(false);
							sel.setEnabled(true);
							cmb.setEnabled(true);
							inp.setEditable(true);
							src.setEnabled(true);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(table, e1.getMessage(), "오류", 0);
						}
					}
				}
			}
		});
		/* 취소 버튼 */
		can.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readModel2.setNumRows(0);
				try {
					select();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				table.setModel(readModel);

				cfm.setEnabled(false);
				can.setEnabled(false);
				sel.setEnabled(true);
				cmb.setEnabled(true);
				inp.setEditable(true);
				src.setEnabled(true);
			}
		});

	}

	public void initFrame() throws SQLException {
		/* 패널부 출력 */
		up.add(cmb);
		up.add(inp);
		up.add(src);
		up.add(chk);
		up.add(back);
		select();

		dp.add(sel);
		dp.add(cfm);
		dp.add(can);

		/* 프레임 출력 */
		f.add(up, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);
		f.add(dp, BorderLayout.SOUTH);

		f.setVisible(true);
	}

	public void delete() throws Exception {
		DeleteDAO2 dao = new DeleteDAO2(this.name);
		while (table.getRowCount() > 0) {
			int ccode = Integer.parseInt(table.getValueAt(0, 0).toString());
			dao.erase(this.name, ccode);
			readModel2.removeRow(0);
		}
	}

	public void select() throws SQLException {
		readModel.setNumRows(0);
		ReadDAO2 dao = new ReadDAO2(this.name);
		ArrayList<Vector<Object>> products;
		products = dao.list(this.name, cmb.getSelectedItem().toString(), inp.getText(), chk.isSelected());

		for (int i = 0; i < products.size(); i++) {
			readModel.addRow(products.get(i));
		}
	}

	public String toString() {
		return "Read " + this.name;
	}

}
