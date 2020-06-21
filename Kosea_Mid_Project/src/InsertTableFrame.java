import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;

public class InsertTableFrame extends TableFrame {
//  패널(수정/행 추가/행 삭제)
	JPanel p;
	JButton cfm;
	JButton add;
	JButton del;

	public InsertTableFrame(String name) throws SQLException {
		super(name);
//		초기화 블럭
		/* 패널부 초기화 */
		cfm = new JButton("입력");
		add = new JButton("행 추가");
		del = new JButton("행 삭제");
		p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));
		/* 테이블 모델 초기화 */
		table.setModel(insertModel);
		insertModel.addRow(defrow);

		if (name.equals("Products")) {
			setCellComboBox();
		}

//		이벤트 설정
		/* 행추가 버튼 */
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertModel.addRow(defrow);
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
						insert();
						insertModel.setNumRows(0);
						insertModel.addRow(defrow);

					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", 0);
						e1.printStackTrace();
					}
				}

			}
		});
	}

	public void initFrame() {
		/* 패널부 출력 */
		p.add(add);
		p.add(del);
		p.add(cfm);
		p.add(back);

		/* 프레임 출력 */
		f.add(sp, BorderLayout.CENTER);
		f.add(p, BorderLayout.NORTH);

		f.setVisible(true);
	}

//	insert 쿼리 작성
	public void insert() throws SQLException {

		InsertDAO dao = new InsertDAO(this.name);
		while (table.getRowCount() > 0) {
			Vector<Object> data = new Vector<Object>();
			for (int i = 0; i < dao.column.size(); i++) {
				data.add(table.getValueAt(0, i));
			}
			dao.write(data);
			insertModel.removeRow(0);
		}

	}

	public void setCellComboBox() {
		addCellComboBox(table.getColumnModel().getColumn(1),
				new String[] { "OUTER", "TOP", "BOTTOM", "ONEPIECE", "SHOES", "ACC", "SUMMER" });
		addCellComboBox(table.getColumnModel().getColumn(5), new String[] { "판매중", "품절" });
		addCellComboBox(table.getColumnModel().getColumn(12), new String[] { "조건부 무료", "무료" });
	}

	public String toString() {
		return "Insert " + this.name;
	}

}
