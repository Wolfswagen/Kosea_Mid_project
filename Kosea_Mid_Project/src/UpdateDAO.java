import java.sql.*;
import java.util.Vector;

public class UpdateDAO extends ConnectDAO {

//	insert 쿼리 작성
	public void set(String tname, Vector<Object> data) throws SQLException {
		connDB();
		String[] def;
		if (tname.equals("Products")) {
			def = ProductsVO.DEFROW;
		} else {
			def = CustomersVO.DEFROW;
		}
		String query = "CALL UPDATE_" + tname + "(" + data.get(0)+ ", '";
		for (int i = 0; i < data.size(); i++) {
			if (def[i].equals("자동입력")) {
			} else {
				if (i < data.size() - 1) {
					query += data.get(i) + "', '";
				} else {
					query += data.get(i) + "')";
				}
			}
		}
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			throw e;
		}
	}
}
