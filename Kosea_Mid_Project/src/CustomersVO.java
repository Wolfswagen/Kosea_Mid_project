public class CustomersVO extends TableVO {

	final static String COLUMN[] = { "customer_code", "customer_name", "customer_ID", "address", "phone", "zip_code",
	"custom_clearance_code" };
	final static String DEFROW[] = { "자동입력", "", "", "", "", "", "" };
	
	public CustomersVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setColumn() {
		this.column.add("customer_code");
		this.column.add("customer_name");
		this.column.add("customer_ID");
		this.column.add("address");
		this.column.add("phone");
		this.column.add("zip_code");
		this.column.add("custom_clearance_code");
	}

	@Override
	public void setDefrow() {
		this.defrow.add("자동입력");
		this.defrow.add("");
		this.defrow.add("");
		this.defrow.add("");
		this.defrow.add("");
		this.defrow.add("");
		this.defrow.add("");
	}

	public String toString() {
		String result = "";
		for(int i =0; i <tuple.size(); i++) {
			result += tuple.get(i) +"\n"; 
		}
		return result;
	}

}