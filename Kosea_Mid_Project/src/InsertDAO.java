import java.sql.*;
import java.util.*;


public class InsertDAO extends TableVO {

	public InsertDAO(String tname) throws SQLException {
		super(tname);
	}

//	insert ���� �ۼ�
	public void write(String tname, Vector<Object> tuple) throws SQLException {
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
		rs = stmt.executeQuery(query);

	}

}
