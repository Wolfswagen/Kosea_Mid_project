import java.sql.*;

public class DeleteDAO extends TableVO {

	public DeleteDAO(String tname) throws SQLException {
		super(tname);
	}

	// delete ���� �ۼ�
	public void erase(int ccode) throws SQLException {
		String query = "DELETE FROM " + name + " WHERE " + column.get(0) + " = " + ccode;
		stmt.executeQuery(query);
	}
}
