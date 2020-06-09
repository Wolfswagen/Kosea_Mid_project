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



	public Object[] toList() {
		Object[] result = { pcode, category, pname, status, amount, org_price, discount, mul_dc, dc_rate, reg_date,
				shipping };

		return result;

	}

	public String toString() {
		return String.valueOf(pcode) + " " + pname;

	}

}