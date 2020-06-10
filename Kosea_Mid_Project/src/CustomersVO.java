public class CustomersVO {
	private int ccode;
	private String cname;
	private String cid;
	private String address;
	private String phone;
	private String zip;
	private String ccc;

	final static String COLUMN[] = { "customer_code", "customer_name", "customer_ID", "address", "phone", "zip_code",
			"custom_clearance_code" };
	final static String DEFROW[] = { "자동입력", "", "", "", "", "", "" };

	public CustomersVO(int ccode, String cname, String cid, String address, String phone, String zip, String ccc) {
		this.ccode = ccode;
		this.cname = cname;
		this.cid = cid;
		this.address = address;
		this.phone = phone;
		this.zip = zip;
		this.ccc = ccc;
	}

	public Object[] toList() {
		Object[] result = { ccode, cname, cid, address, phone, zip, ccc };

		return result;

	}

	public String toString() {
		return String.valueOf(ccode) + " " + cid;

	}

}