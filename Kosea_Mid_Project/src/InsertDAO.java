import java.sql.*;
import java.util.*;

public class InsertDAO extends TableVO {

	public InsertDAO(String tname) throws SQLException {
		super(tname);
	}

//	insert ���� �ۼ�
	public void write(Vector<Object> tuple) throws SQLException {
		String query = "CALL INSERT_" + name + "('";
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
		rs = stmt.executeQuery(query);
	}

}