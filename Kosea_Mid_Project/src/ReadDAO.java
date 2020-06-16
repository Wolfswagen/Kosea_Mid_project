import java.sql.SQLException;
import java.util.*;

public class ReadDAO extends TableVO {

	public ReadDAO(String tname) throws SQLException {
		super(tname);
	}

	public ArrayList<Vector<Object>> list(String tname, String atr, String inp, boolean partial) throws SQLException {
		ArrayList<Vector<Object>> list = new ArrayList<Vector<Object>>();
		String query = "";

		if (inp.isEmpty()) {
			query = "SELECT * FROM " + tname + " ORDER BY " + this.column.get(0);
		} else {
			if (partial) {
				query = "SELECT * FROM " + tname + " WHERE (" + atr + " LIKE '%" + inp + "' OR " + atr + " LIKE '" + inp
						+ "%' OR " + atr + " LIKE '%" + inp + "%') ORDER BY " + this.column.get(0);
			} else {
				query = "SELECT * FROM " + tname + " WHERE " + atr + " = '" + inp + "' ORDER BY " + this.column.get(0);
			}
		}

		rs = stmt.executeQuery(query);
		while (rs.next()) {
			Vector<Object> data = new Vector<Object>();
			for (int i = 0; i < this.column.size(); i++) {
				data.add(rs.getObject(this.column.get(i)));
			}
			list.add(data);
		}

		return list;
	}

}
