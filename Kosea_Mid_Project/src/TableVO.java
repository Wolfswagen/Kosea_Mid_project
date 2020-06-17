import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

public class TableVO {
	final private String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final private String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	final private String USER = "kosea";
	final private String PASSWORD = "kosea2019a";

	private Connection con;
	protected Statement stmt;
	protected ResultSet rs;
	protected ResultSet md;

	String name;
	final protected Vector<String> column = new Vector<String>();
	final protected Vector<String> defrow = new Vector<String>();
	protected Vector<Object> tuple = new Vector<Object>();

	public TableVO(String tname) throws SQLException {
		this.name = tname;
		connDB();
		md = stmt.executeQuery("SELECT Column_name FROM cols WHERE TABLE_name = '" + name.toUpperCase() + "'");
		while (md.next()) {
			column.add(md.getString("column_name"));
		}

		defrow.add("�ڵ��Է�");
		if (name.equals("Products")) {
			defrow.add("OUTER");
			defrow.add("");
			defrow.add("�Ǹ���");
			defrow.add("0");
			defrow.add("0");
			defrow.add("0");
			defrow.add("0");
			defrow.add("0");
			defrow.add("�ڵ��Է�");
			defrow.add("���Ǻ� ����");
		}
		if (name.equals("Customers")) {
			defrow.add("");
			defrow.add("");
			defrow.add("");
			defrow.add("");
			defrow.add("");
			defrow.add("");
		}
		if (name.equals("Sales")) {
			defrow.add("�ڵ��Է�");
			defrow.add("�ڵ��Է�");
			defrow.add("�ڵ��Է�");
			defrow.add("�˻�");
			defrow.add("0");
			defrow.add("");
			defrow.add("����Ʈ�����");
			defrow.add("");
		}
//		[SALES_CODE, PRODUCT_CODE, PRODUCT_COLOR, PRODUCT_SIZE, PRODUCT_AMOUNT, REFUND_AMOUNT]
		if (name.equals("Sales_details")) {
			defrow.set(0, "�ŷ���ȣ");
			defrow.add("�˻�");
			defrow.add("");
			defrow.add("");
			defrow.add("0");
			defrow.add("0");
		}
	}

	public void connDB() {
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����", 0);
			e.printStackTrace();
		}
	}

	public Vector<String> getColumn() throws SQLException {
		con.close();
		return this.column;
	}

	public Vector<String> getDefrow() throws SQLException {
		con.close();
		return this.defrow;
	}
}