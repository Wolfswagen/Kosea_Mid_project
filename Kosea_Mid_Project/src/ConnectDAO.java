import java.sql.*;

public class ConnectDAO {
	final private String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final private String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	final private String USER = "kosea";
	final private String PASSWORD = "kosea2019a";

	private Connection con;
	protected Statement stmt;
	protected ResultSet rs;

	public void connDB() {
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
