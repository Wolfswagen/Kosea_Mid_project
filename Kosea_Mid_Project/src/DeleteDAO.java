import java.sql.*;

public class DeleteDAO extends TableVO {

	public DeleteDAO(String tname) throws SQLException {
		super(tname);
	}

	// delete 쿼리 작성
	public void erase(int ccode) throws SQLException {
		String query = "DELETE FROM " + name + " WHERE " + column.get(0) + " = " + ccode;
		stmt.executeQuery(query);
	}
	
	// delete 쿼리 작성(기본키 2개인 경우)
	public void erase(String scode, String pcode) throws SQLException {
		String query = "DELETE FROM " + name + " WHERE " + column.get(0) + " = " + scode + " and " + column.get(1) + " = "
				+ pcode;
		stmt.executeQuery(query);
	}
}
