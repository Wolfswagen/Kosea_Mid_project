import java.sql.*;
import java.util.Objects;
import java.util.Vector;

public class UpdateDAO extends TableVO {

	public UpdateDAO(String tname) throws SQLException {
		super(tname);
		// TODO Auto-generated constructor stub
	}

	// update 쿼리 작성
	public void set(Vector<Object> data) throws SQLException {
		String query = "CALL UPDATE_" + name + "(" + data.get(0) + ", '";
		for (int i = 0; i < data.size(); i++) {
			if (this.defrow.get(i).equals("자동입력")) {
			} else {
				if (i < tuple.size() - 1) {
					if (Objects.isNull(tuple.get(i))) {
						query += "', '";
					} else {
						query += tuple.get(i) + "', '";
					}

				} else {
					if (Objects.isNull(tuple.get(i))) {
						query += "')";
					} else {
						query += tuple.get(i) + "')";
					}
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
