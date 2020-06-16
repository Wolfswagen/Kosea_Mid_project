import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

public class InsertDAO2 extends TableVO2 {
	
	public InsertDAO2(String tname) throws SQLException {
		super(tname);
	}

//	insert 쿼리 작성
	public void write(String tname, Vector<Object> tuple){
		String query = "CALL INSERT_" + tname + "('";
		for (int i = 0; i < tuple.size(); i++) {
			if (tuple.get(i).equals("자동입력")) {
			} else {
				if (i < tuple.size() - 1) {
					query += tuple.get(i) + "', '";
				} else {
					query += tuple.get(i) + "')";
				}
			}
		}
		System.out.println(query);
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "오류", 0);
			e.printStackTrace();
		}
	}

}
