import java.sql.*;
import java.util.Objects;
import java.util.Vector;

public class UpdateDAO extends TableVO {

	public UpdateDAO(String tname) throws SQLException {
		super(tname);
	}

	// update ���� �ۼ�
	public void set(Vector<Object> data) throws SQLException {
		String query = "CALL UPDATE_" + name + "(" + data.get(0) + ", '";

		for (int i = 0; i < data.size(); i++) {
			if (this.defrow.get(i).equals("�ڵ��Է�") || this.defrow.get(i).equals("��¥")
					|| this.defrow.get(i).equals("�ŷ���ȣ")) {
			} else {
				if (i < data.size() - 1) {
					if (Objects.isNull(data.get(i))) {
						query += "', '";
					} else {
						query += data.get(i) + "', '";
					}

				} else {
					if (Objects.isNull(data.get(i))) {
						query += "')";
					} else {
						query += data.get(i) + "')";
					}
				}
			}
		}
		stmt.executeQuery(query);
	}
}
