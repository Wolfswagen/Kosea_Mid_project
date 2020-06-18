import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DeletePopUp extends SalesFrame {
	JPanel up;

//	하단 패널(선택/수정/취소)
	JPanel dp;
	JButton sel;
	JButton cfm;
	JButton can;

	DefaultTableModel readModel2;
	String scode;

	public DeletePopUp(String name, String scode) throws SQLException {
		super(name);
		this.scode = scode;
		readModel2 = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
//		초기화 블럭
		/* 상단 패널부 초기화 */
		back = new JButton("뒤로");
		up = new JPanel();
		up.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));

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
							selectDetails();
							table.setModel(readModel);
							cfm.setEnabled(false);
							can.setEnabled(false);
							sel.setEnabled(true);
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
							e1.printStackTrace();
						}
					}
				}
			}
		});
		/* 취소 버튼 */
		can.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					readModel2.setNumRows(0);
					selectDetails();
					table.setModel(readModel);

					cfm.setEnabled(false);
					can.setEnabled(false);
					sel.setEnabled(true);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
					e1.printStackTrace();
				}
			}
		});

		/* 뒤로 가기 */
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		/* 윈도우 종료 버튼 */
		f.removeWindowListener(f.getWindowListeners()[0]);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});

		selectDetails();
	}

	public void initFrame() {
		/* 패널부 출력 */
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

	public void delete() throws SQLException {

		DeleteDAO dao = new DeleteDAO(this.name);
		while (table.getRowCount() > 0) {
			dao.erase(table.getValueAt(0, 0).toString(), table.getValueAt(0, 1).toString());
			readModel2.removeRow(0);
		}
	}

	public void selectDetails() throws SQLException {
		readModel.setNumRows(0);
		ReadDAO dao = new ReadDAO(this.name);
		ArrayList<Vector<Object>> products = dao.list(column.get(0), scode, false);
		for (int i = 0; i < products.size(); i++) {
			readModel.addRow(products.get(i));
		}
	}

	public String toString() {
		return "Delete " + this.name;
	}
}
