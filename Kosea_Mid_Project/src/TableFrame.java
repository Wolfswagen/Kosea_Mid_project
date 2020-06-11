
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public abstract class TableFrame {
	JFrame f;
	JButton back;

//  테이블 
	DefaultTableModel readModel;
	DefaultTableModel insertModel;
	JScrollPane sp;
	JTable table;
	String[] column;

	public TableFrame() {
//		초기화 블럭
		setColumn();

		f = new JFrame(this.toString());
		f.setSize(1000, 500);

		/* 테이블 초기화 */
		readModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		insertModel = new DefaultTableModel(column, 0) {
			private static final long serialVersionUID = -4113365722825486170L;

			/* 테이블 수정 불가 설정 */
			public boolean isCellEditable(int i, int c) {
				String name = insertModel.getColumnName(c);
				if (name.equals("product_code") || name.equals("register_date") || name.equals("customer_code")) {
					return false;
				} else {
					return true;
				}

			}
		};

		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		sp = new JScrollPane(table);

//		이벤트 설정
		/* 뒤로 버튼 */
		back = new JButton("뒤로");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame sf = new MainFrame();
				sf.initFrame();
				f.dispose();
			}
		});
		/* 윈도우 종료 버튼 */
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

//	frame 시작
	public abstract void initFrame();

//  테이블 칼럼 설정
	public abstract void setColumn();

//	table combobox 추가
	public void addCellComboBox(TableColumn column, String[] tpl) {
		JComboBox<String> comboBox = new JComboBox<String>(tpl);
		column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		column.setCellRenderer(renderer);
	}
}
