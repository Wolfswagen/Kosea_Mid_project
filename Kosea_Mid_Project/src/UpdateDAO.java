import java.sql.*;
import java.util.Vector;

public class UpdateDAO extends TableVO {

public UpdateDAO(String tname) throws SQLException {
		super(tname);
		// TODO Auto-generated constructor stub
	}

	//	update 쿼리 작성
	public void set(Vector<Object> data) throws SQLException {
		String query = "CALL UPDATE_" + name + "(" + data.get(0)+ ", '";
		for (int i = 0; i < data.size(); i++) {
			if (this.defrow.get(i).equals("자동입력")) {
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
