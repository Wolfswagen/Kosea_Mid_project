import java.sql.*;

public class FinishDAO extends TableVO {

	public FinishDAO(String tname) throws SQLException {
		super(tname);
	}

	// delete ���� �ۼ�
	public void end(int ccode) throws SQLException {
		String query = "call finish_sales(" + ccode + ")";
		stmt.executeQuery(query);
	}

}
