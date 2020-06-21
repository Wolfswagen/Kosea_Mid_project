import java.sql.*;
import java.util.Vector;

public class RefundDAO extends TableVO {

	public RefundDAO(String tname) throws SQLException {
		super(tname);
	}

	public void insertRefund(Vector<Object> tuple) throws SQLException {
		String query = "CALL REFUND ('";
		for (int i = 0; i < tuple.size(); i++) {
			if (i < tuple.size() - 1) {
				query += tuple.get(i) + "', '";

			} else {
				query += tuple.get(i) + "')";
			}
		}
		rs = stmt.executeQuery(query);
	}

	public void cancelRefund(String scode, String pcode) throws SQLException {
		String query = "CALL CANCEL_REFUND ('" + scode + "', '" + pcode + "')";
		rs = stmt.executeQuery(query);
	}
}
