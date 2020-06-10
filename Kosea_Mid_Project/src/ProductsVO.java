public class ProductsVO {
	private int pcode;
	private String category;
	private String pname;
	private String status;
	private int amount;
	private int org_price;
	private int discount;
	private int mul_dc;
	private double dc_rate;
	private String reg_date;
	private String shipping;

	final static String COLUMN[] = new String[] { "product_code", "category", "product_name", "status", "amount",
			"original_price", "discount", "multi_purchase_discount", "discount_rate", "register_date", "shipping" };
	final static String DEFROW[] = new String[] { "자동입력", "OUTER", "", "판매중", "0", "0", "0", "0", "0", "자동입력",
			"조건부 무료" };

	public ProductsVO(int pcode, String category, String pname, String status, int amount, int org_price, int discount,
			int mul_dc, double dc_rate, String reg_date, String shipping) {
		this.pcode = pcode;
		this.category = category;
		this.pname = pname;
		this.status = status;
		this.amount = amount;
		this.org_price = org_price;
		this.discount = discount;
		this.mul_dc = mul_dc;
		this.dc_rate = dc_rate;
		this.reg_date = reg_date;
		this.shipping = shipping;
	}

	public int getPcode() {
		return pcode;
	}

	public String getPname() {
		return pname;
	}

	public String getStatus() {
		return status;
	}

	public int getAmount() {
		return amount;
	}

	public int getOrg_price() {
		return org_price;
	}

	public int getDiscount() {
		return discount;
	}

	public int getMul_dc() {
		return mul_dc;
	}

	public double getDc_rate() {
		return dc_rate;
	}

	public String getReg_date() {
		return reg_date;
	}

	public String getShipping() {
		return shipping;
	}

	public String getCategory() {
		return category;
	}

	public Object[] toList() {
		Object[] result = { pcode, category, pname, status, amount, org_price, discount, mul_dc, dc_rate, reg_date,
				shipping };

		return result;

	}

	public String toString() {
		return String.valueOf(pcode) + " " + pname;

	}

}