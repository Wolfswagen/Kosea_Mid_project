import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InsertSalesPopUp extends SalesFrame {
//  패널(수정/행 추가/행 삭제)
	JPanel p;
	JButton cfm;
	DefaultTableModel insertModel;

	public InsertSalesPopUp(String name) throws SQLException {
		super(name);

		f.setSize(1000, 400);

		insertModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				if (defrow[c].equals("자동입력") || defrow[c].equals("검색") || defrow[c].equals("날짜")) {
					return false;
				} else {
					return true;
				}
			}
		};
		
		
		back = new JButton("뒤로");

//		초기화 블럭
		/* 패널부 초기화 */
		cfm = new JButton("입력");
		p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));
		/* 테이블 모델 초기화 */
		table.setModel(insertModel);
		insertModel.addRow(defrow);

//		이벤트 설정
		/* 입력 버튼 */
		cfm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				result = JOptionPane.showConfirmDialog(sp, "입력하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					try {
						insert();
						insertModel.setNumRows(0);
						insertModel.addRow(defrow);
						table.setModel(insertModel);
						JOptionPane.showMessageDialog(null, "입력이 완료되었습니다.", "확인", 1);
						f.dispose();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
						e1.printStackTrace();
					}

				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					try {
						ReadTableFrame rt = new ReadTableFrame("Customers");
						rt.noExit();
						rt.initFrame();
						rt.f.addWindowListener(new WindowAdapter() {
							public void windowClosed(WindowEvent e) {
								insertModel.setValueAt(rt.getCode(), table.getSelectedRow(), 4);
							}
						});

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
				f.dispose();
			}
		});
		f.removeWindowListener(f.getWindowListeners()[0]);

		/* 윈도우 종료 버튼 */
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});

	}

	public void initFrame() {
		/* 패널부 출력 */

		p.add(cfm);
		p.add(back);

		/* 프레임 출력 */
		f.add(sp, BorderLayout.CENTER);
		f.add(p, BorderLayout.NORTH);

		f.setVisible(true);
	}

//	insert 쿼리 작성
	public void insert() throws SQLException {
		insert(this.name);
	}

	public void insert(String name) throws SQLException {
		InsertDAO dao = new InsertDAO(name);
		while (table.getRowCount() > 0) {
			Vector<Object> data = new Vector<Object>();

			for (int i = 0; i < dao.column.size(); i++) {
				data.add(table.getValueAt(0, i));
			}
			dao.write(data);
			insertModel.removeRow(0);
		}
	}

	public String toString() {
		return "Insert " + this.name;
	}

}
