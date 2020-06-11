import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class InsertTableFrame extends TableFrame {
//  패널(수정/행 추가/행 삭제)
	JPanel p;
	JButton cfm;
	JButton add;
	JButton del;

//  입력 테이블
	String[] defrow;

	public InsertTableFrame() {
		super();

//		초기화 블럭 시
		/* 패널부 초기화 */
		cfm = new JButton("입력");
		add = new JButton("행 추가");
		del = new JButton("행 삭제");
		p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));
		/* 테이블 모델 초기화 */
		table.setModel(insertModel);
		insertModel.addRow(defrow);
//		초기화 블럭 끝

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
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(table, e1.getMessage(), "오류", 0);
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
	public abstract void insert() throws Exception;

}
