import java.sql.*;
import java.util.*;

public class InsertDAO extends ConnectDAO {

//	insert ���� �ۼ�
	public void write(String tname, Vector<Object> tuple) throws SQLException {
		connDB();
		String query = "CALL INSERT_" + tname + "('";
		for (int i = 0; i < tuple.size(); i++) {
			if (tuple.get(i).equals("�ڵ��Է�")) {
			} else {
				if (i < tuple.size() - 1) {
					query += tuple.get(i) + "', '";
				} else {
					query += tuple.get(i) + "')";
				}
			}
		}
		System.out.println(query);
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			throw e;
		}
	}

}
