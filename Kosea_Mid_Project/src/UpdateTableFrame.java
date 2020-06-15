import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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

	public UpdateTableFrame(String name) {
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
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(table, e1.getMessage(), "오류", 0);
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
				cfm.setEnabled(false);
				can.setEnabled(false);
				sel.setEnabled(true);
				cmb.setEnabled(true);
				inp.setEditable(true);
				src.setEnabled(true);
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
		dp.add(cfm);
		dp.add(can);

		/* 프레임 출력 */
		f.add(up, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);
		f.add(dp, BorderLayout.SOUTH);

		f.setVisible(true);
	}

//	update 쿼리 작성
	public void update() throws Exception {
		UpdateDAO dao = new UpdateDAO();
		while (table.getRowCount() > 0) {
			TableVO data;
			if (this.name.equals("Products")) {
				data = new ProductsVO();
			} else {
				data = new CustomersVO();
			}
			for (int i = 0; i < data.column.size(); i++) {
				data.tuple.add(table.getValueAt(0, i));
			}
			try {
				dao.set(this.name, data.tuple);
				insertModel.removeRow(0);
			} catch (Exception e) {
				throw e;
			}
		}
	}

	public void select() {
		readModel.setNumRows(0);
		ReadDAO dao = new ReadDAO();
		ArrayList<TableVO> products;
		products = dao.list(this.name, cmb.getSelectedItem().toString(), inp.getText(), chk.isSelected());

		for (int i = 0; i < products.size(); i++) {
			readModel.addRow(products.get(i).tuple);
		}
	}

	public void setCellComboBox() {
		addCellComboBox(table.getColumnModel().getColumn(1),
				new String[] { "OUTER", "TOP", "BOTTOM", "ONEPIECE", "SHOES", "ACC", "SUMMER" });
		addCellComboBox(table.getColumnModel().getColumn(3), new String[] { "판매중", "품절" });
		addCellComboBox(table.getColumnModel().getColumn(10), new String[] { "조건부 무료", "무료" });
	}

	public void setColumn() {
		if (this.name.equals("Products")) {
			this.column = ProductsVO.COLUMN;
		} else {
			this.column = CustomersVO.COLUMN;
		}

	}

	public String toString() {
		return "Update " + this.name;
	}
}
