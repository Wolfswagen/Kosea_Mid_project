import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class ReadTableFrame extends TableFrame {

//  패널(검색 카테고리 선택/검색어 입력/검색 버튼/부분 검색 여부)
	JPanel p;
	JComboBox<String> cmb;
	JTextField inp;
	JButton cfm;
	JCheckBox chk;

	public ReadTableFrame() {
		super();
//		초기화 블럭 시작
		/* 패널부 초기화 */
		inp = new JTextField("", 40);
		cfm = new JButton("검색");
		chk = new JCheckBox("부분 검색");
		p = new JPanel();
		cmb = new JComboBox<String>(column);
		/* 테이블 모델설정 */
		table.setModel(readModel);

//		초기화 블럭 끝

//		이벤트 설정
		/* 검색 버튼 이벤트 */
		cfm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readModel.setNumRows(0);
				select();
			}
		});
	}

	public void initFrame() {
		/* 패널부 출력 */
		p.add(cmb);
		p.add(inp);
		p.add(cfm);
		p.add(chk);
		p.add(back);
		select();

		/* 프레임 출력 */
		f.add(p, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);

		f.setVisible(true);
	}

//	SELECT 결과 조회
	public abstract void select();

}
