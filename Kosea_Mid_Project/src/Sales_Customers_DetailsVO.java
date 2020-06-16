
public class Sales_Customers_DetailsVO extends TableVO {
	final static String COLUMN[] = { "sales_code", "customer_code", "customer_name", "customer_id", "address", "phone",
			"custom_clearance_code", "sales_date", "order_date", "ship_date", "total", "payment", "message", "platform",
			"note" };
	final static String DEFROW[] = { "자동입력", "", "", "", "", "", "" };

	@Override
	public void setColumn() {
		for (int i = 0; i < COLUMN.length; i++) {
			this.column.add(COLUMN[i]);
		}
	}

	@Override
	public void setDefrow() {
		this.defrow.add("자동입력");
	}

	public static void main(String[] args) {
		ReadTableFrame rt = new ReadTableFrame("SALES_CUSTOMERS_DETAILS");
		rt.initFrame();
	}

}
