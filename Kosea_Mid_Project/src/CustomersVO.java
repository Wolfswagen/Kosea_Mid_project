public class CustomersVO {
	final static String COLUMN[] = { "customer_code", "customer_name", "customer_ID", "address", "phone", "zip_code",
			"custom_clearance_code" };
	final static String DEFROW[] = { "자동입력", "", "", "", "", "", "" };

	private int ccode;
	private String cname;
	private String cid;
	private String address;
	private String phone;
	private String zip;
	private String ccc;

//	getter 시작
	public int getCcode() {
		return ccode;
	}

	public String getCname() {
		return cname;
	}

	public String getCid() {
		return cid;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getZip() {
		return zip;
	}

	public String getCcc() {
		return ccc;
	}
//	getter 끝

	public CustomersVO(int ccode, String cname, String cid, String address, String phone, String zip, String ccc) {
		this.ccode = ccode;
		this.cname = cname;
		this.cid = cid;
		this.address = address;
		this.phone = phone;
		this.zip = zip;
		this.ccc = ccc;
	}

	public Object[] toArray() {
		Object[] result = { ccode, cname, cid, address, phone, zip, ccc };

		return result;

	}

	public String toString() {
		return String.valueOf(ccode) + " " + cid;

	}

}