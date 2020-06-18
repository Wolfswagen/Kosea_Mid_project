import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RefundSalesFrame extends SalesFrame {
//  상단 패널(카테고리/검색창/검색버튼/부분검색)
	JComboBox<String> cmb;
	JTextField inp;
	JButton src;
	JCheckBox chk;
	JPanel up;

//	하단 패널(추가/수정/취소)
	JPanel dp;
	JComboBox<String> cho;
	JButton cfm;
	JButton add;

	DefaultTableModel readModel2;
	Vector<String> column2;
	String[] defrow2;
	String name2;

	Vector<Object> list;

	public RefundSalesFrame(String name) throws SQLException {
		super(name);
		this.name = name + "_Customers_Details";
		this.column = setColumn(this.name);
		this.name2 = name + "_Products_Details";
		this.column2 = setColumn(this.name2);

//		초기화 블럭
		/* 상단 패널부 초기화 */
		cmb = new JComboBox<String>(column);
		inp = new JTextField("", 40);
		src = new JButton("검색");
		chk = new JCheckBox("부분 검색");
		up = new JPanel();
		back = new JButton("뒤로");

		/* 상단 패널부 초기화 */
		cho = new JComboBox<String>(new String[] { "환불", "교환" });
		cho.setEnabled(false);
		cfm = new JButton("확인");
		cfm.setEnabled(false);
		dp = new JPanel();
		dp.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));

		/* 테이블 모델 초기화 */
		readModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		table.setModel(readModel);

		readModel2 = new DefaultTableModel(column2, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
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
				if (table.getModel().equals(readModel)) {
					if (e.getClickCount() > 1) {
						try {
							readModel2.setNumRows(0);
							selectDetails(String.valueOf(readModel.getValueAt(table.getSelectedRow(), 0)), name2);
							table.setModel(readModel2);
							JOptionPane.showMessageDialog(sp, readModel2.getRowCount() + "개 행이 선택되었습니다.");
							cho.setEnabled(true);
							cfm.setEnabled(true);
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
							e1.printStackTrace();
						}
					}
				}
			}

		});
		/* 뒤로 가기 */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Vector<Object> param = new Vector<Object>();
				param.add(readModel2.getValueAt(table.getSelectedRow(), 2));
				param.add(readModel2.getValueAt(table.getSelectedRow(), 3));
				param.add(readModel2.getValueAt(table.getSelectedRow(), 4));
				param.add(readModel2.getValueAt(table.getSelectedRow(), 6));
				param.add(cho.getSelectedItem());
				RefundPopUp rp = new RefundPopUp(param);
				rp.initFrame();
				list = new Vector<Object>();
				rp.f.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						for (int i = 0; i < rp.data.size(); i++) {
							list.add(rp.data.get(i));
						}
						System.out.println(list);
					}
					
				});
			}

		});

		/* 뒤로 가기 */
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getModel().equals(readModel)) {
//					SalesMain.f.setVisible(true);
//					f.dispose();
				} else {
					try {
						readModel.setNumRows(0);
						select();
						table.setModel(readModel);
						cho.setEnabled(false);
						cfm.setEnabled(false);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
						e1.printStackTrace();
					}
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

		dp.add(cho);
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
		ArrayList<Vector<Object>> list = dao.list(column.get(0), code, false);
		for (int i = 0; i < list.size(); i++) {
			readModel2.addRow(list.get(i));
		}
	}

	public String toString() {
		return "Refunds";
	}

	public static void main(String[] args) throws SQLException {
		RefundSalesFrame r = new RefundSalesFrame("Refunds");
		r.initFrame();
	}
}
