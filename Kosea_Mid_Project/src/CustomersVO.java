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
		for(int i = 0; i < COLUMN.length; i++) {
			this.column.add(COLUMN[i]);
		}
	}

	@Override
	public void setDefrow() {
		this.defrow.add("자동입력");
	}

	public String toString() {
		String result = "";
		for(int i =0; i <tuple.size(); i++) {
			result += tuple.get(i) +"\n"; 
		}
		return result;
	}

}