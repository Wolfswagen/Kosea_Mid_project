import java.sql.SQLException;

public class Sales_Customers_DetailsVO extends TableVO2 {
	public Sales_Customers_DetailsVO(String tname) throws SQLException {
		super(tname);
		// TODO Auto-generated constructor stub
	}

	final static String COLUMN[] = { "sales_code", "customer_code", "customer_name", "customer_id", "address", "phone",
			"custom_clearance_code", "sales_date", "order_date", "ship_date", "total", "payment", "message", "platform",
			"note" };
	final static String DEFROW[] = { "자동입력", "", "", "", "", "", "" };

}
