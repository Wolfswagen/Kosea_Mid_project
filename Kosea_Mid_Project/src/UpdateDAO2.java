import java.sql.*;
import java.util.Vector;

public class UpdateDAO2 extends TableVO2 {

public UpdateDAO2(String tname) throws SQLException {
		super(tname);
		// TODO Auto-generated constructor stub
	}

	//	insert ���� �ۼ�
	public void set(String tname, Vector<Object> data) throws SQLException {
		String query = "CALL UPDATE_" + tname + "(" + data.get(0)+ ", '";
		for (int i = 0; i < data.size(); i++) {
			if (this.defrow.get(i).equals("�ڵ��Է�")) {
			} else {
				if (i < data.size() - 1) {
					query += data.get(i) + "', '";
				} else {
					query += data.get(i) + "')";
				}
			}
		}
		try {
			stmt.executeQuery(query);
		} catch (SQLException e) {
			throw e;
		}
	}
}
