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
			if (this.defrow.get(i).equals("�ڵ��Է�") || this.defrow.get(i).equals("��¥")) {
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
		rs = stmt.executeQuery(query);
	}

}
