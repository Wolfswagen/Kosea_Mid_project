
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InsertSalesFrame extends SalesFrame {
	// 상단 패널(카테고리/검색창/검색버튼/부분검색)
	JComboBox<String> cmb;
	JTextField inp;
	JButton src;
	JButton ins;
	JCheckBox chk;
	JPanel up;

//		하단 패널(선택/수정/취소)
	JPanel dp;
	JButton add;
	JButton del;
	JButton cfm;

	Vector<String> column2;
	String[] defrow2;
	String name2;

	DefaultTableModel insertModel;
	String scode;

	public InsertSalesFrame(String name) throws SQLException {
		super(name);
		this.name2 = name + "_details";
		this.column2 = setColumn(name2);
		this.defrow2 = setDefrow(name2);

//			초기화 블럭
		/* 상단 패널부 초기화 */
		cmb = new JComboBox<String>(column);
		inp = new JTextField("", 40);
		src = new JButton("검색");
		chk = new JCheckBox("부분 검색");
		ins = new JButton("신규 거래건");
		back = new JButton("뒤로");
		up = new JPanel();

		/* 하단 패널부 초기화 */
		add = new JButton("행추가");
		add.setEnabled(false);
		del = new JButton("행삭제");
		del.setEnabled(false);
		cfm = new JButton("입력");
		cfm.setEnabled(false);
		dp = new JPanel();
		dp.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));

		/* 테이블 모델 초기화 */
		table.setModel(readModel);

		insertModel = new DefaultTableModel(column2, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				if (defrow2[c].equals("자동입력") || defrow2[c].equals("검색") || defrow2[c].equals("거래번호")
						|| defrow2[c].equals("날짜")) {
					return false;
				} else {
					return true;
				}
			}
		};
//			초기화 블럭 끝

//			이벤트 설정
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
		/* 거래건 추가 버튼 */
		ins.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					InsertSalesPopUp ip = new InsertSalesPopUp("Sales");
					ip.f.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							try {
								select();
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
								e1.printStackTrace();
							}
						}
					});
					ip.initFrame();
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
				if (table.getModel() == readModel) {
					if (e.getClickCount() > 1) {
						try {
							insertModel.setNumRows(0);
							selectDetails(String.valueOf(readModel.getValueAt(table.getSelectedRow(), 0)), name2);
							scode = String.valueOf(readModel.getValueAt(table.getSelectedRow(), 0));
							table.setModel(insertModel);
							cmb.setEnabled(false);
							inp.setEditable(false);
							src.setEnabled(false);
							back.setEnabled(true);
							add.setEnabled(true);
							del.setEnabled(true);
							cfm.setEnabled(true);
							JOptionPane.showMessageDialog(sp, insertModel.getRowCount() + "개 행이 선택되었습니다.");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
							e1.printStackTrace();
						}
					}

				} else {
					if (e.getClickCount() > 1) {
						try {
							ReadTableFrame rt = new ReadTableFrame("Products");
							rt.noExit();
							rt.initFrame();
							rt.f.addWindowListener(new WindowAdapter() {
								public void windowClosed(WindowEvent e) {
									if (Objects.isNull(rt.getCode())) {

									} else {
										insertModel.setValueAt(rt.getCode(), table.getSelectedRow(), 1);
									}
								}
							});

						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
							e1.printStackTrace();
						}
					}
				}
			}

		});

		/* 행추가 버튼 */
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertModel.addRow(defrow2);
				insertModel.setValueAt(scode, insertModel.getRowCount() - 1, 0);
			}
		});

		/* 행삭제 버튼 */
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getRowCount() > 0) {
					if (table.getSelectedRowCount() > 0) {
						while (table.getSelectedRowCount() > 0) {
							insertModel.removeRow(table.getSelectedRow());
						}
					} else {
						insertModel.removeRow(insertModel.getRowCount() - 1);
					}
				}
			}
		});

		/* 입력 버튼 */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				result = JOptionPane.showConfirmDialog(sp, "입력하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					try {
						insertDetails(insertModel.getValueAt(0, 0).toString(), name2);
						insertModel.setNumRows(0);
						insertModel.addRow(defrow);
						cmb.setEnabled(true);
						inp.setEditable(true);
						src.setEnabled(true);
						back.setEnabled(false);
						add.setEnabled(false);
						del.setEnabled(false);
						cfm.setEnabled(false);
						readModel.setNumRows(0);
						select();
						table.setModel(readModel);

					} catch (Exception e1) {
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
				if (table.getModel().equals(insertModel)) {
					try {
						readModel.setNumRows(0);
						select();
						table.setModel(readModel);
						cmb.setEnabled(true);
						inp.setEditable(true);
						src.setEnabled(true);
						add.setEnabled(false);
						del.setEnabled(false);
						cfm.setEnabled(false);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
						e1.printStackTrace();
					}
				} else {
					SalesMain.f.setVisible(true);
					f.dispose();
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
		up.add(ins);
		up.add(back);

		dp.add(add);
		dp.add(del);
		dp.add(cfm);

		/* 프레임 출력 */
		f.add(up, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);
		f.add(dp, BorderLayout.SOUTH);

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

	public void selectDetails(String code, String name) throws SQLException {
		ReadDAO dao = new ReadDAO(name);
		ArrayList<Vector<Object>> products = dao.list(column.get(0), code, false);
		for (int i = 0; i < products.size(); i++) {
			insertModel.addRow(products.get(i));
		}
	}

	public void insertDetails(String code, String name) throws SQLException {
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