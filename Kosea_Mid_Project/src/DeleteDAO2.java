import java.sql.*;

public class DeleteDAO2 extends TableVO2 {

	public DeleteDAO2(String tname) throws SQLException {
		super(tname);
	}

	// delete ���� �ۼ�
	public void erase(String tname, int ccode) throws SQLException {
		String query = "DELETE FROM " + tname + " WHERE " + column.get(0) + " = " + ccode;
		stmt.executeQuery(query);
	}
}
