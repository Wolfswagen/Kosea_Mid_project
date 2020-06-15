public class ProductsVO extends TableVO {
	final static String COLUMN[] = new String[] { "product_code", "category", "product_name", "status", "amount",
			"original_price", "discount", "multi_purchase_discount", "discount_rate", "register_date", "shipping" };
	final static String DEFROW[] = new String[] { "�ڵ��Է�", "OUTER", "", "�Ǹ���", "0", "0", "0", "0", "0", "�ڵ��Է�",
			"���Ǻ� ����" };

	public ProductsVO() {
		super();

	}

	@Override
	public void setColumn() {
		this.column.add("product_code");
		this.column.add("category");
		this.column.add("product_name");
		this.column.add("status");
		this.column.add("amount");
		this.column.add("original_price");
		this.column.add("discount");
		this.column.add("multi_purchase_discount");
		this.column.add("discount_rate");
		this.column.add("register_date");
		this.column.add("shipping");
	}

	@Override
	public void setDefrow() {
		this.defrow.add("�ڵ��Է�");
		this.defrow.add("OUTER");
		this.defrow.add("");
		this.defrow.add("�Ǹ���");
		this.defrow.add("0");
		this.defrow.add("0");
		this.defrow.add("0");
		this.defrow.add("0");
		this.defrow.add("0");
		this.defrow.add("�ڵ��Է�");
		this.defrow.add("���Ǻ� ����");

	}

}