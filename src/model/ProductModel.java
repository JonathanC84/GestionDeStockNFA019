package model;

public class ProductModel {

	private int id;
	private String prodRef;
	private String prodName;
	private String prodDesc;
	private int prodQuantity;
	private int prodExpTime;
	private double prodUnitPrice;
	private int prodCategory;
	private int prodSupplier;
	private boolean isVisible;
	
	public ProductModel() {
		this.id = 0;
		this.prodRef = "";
		this.prodName = "";
		this.prodDesc = "";
		this.prodQuantity = 0;
		this.prodExpTime = 0;
		this.prodUnitPrice = 0;
		this.prodCategory = 0;
		this.prodSupplier = 0;		
		this.isVisible = true;
	}

	public int getId() {
		return id;
	}

	public String getProdRef() {
		return prodRef;
	}

	public String getProdName() {
		return prodName;
	}

	public String getProdDesc() {
		return prodDesc;
	}

	public int getProdQuantity() {
		return prodQuantity;
	}

	public int getProdExpTime() {
		return prodExpTime;
	}

	public double getProdUnitPrice() {
		return prodUnitPrice;
	}

	public int getProdCategory() {
		return prodCategory;
	}

	public int getProdSupplier() {
		return prodSupplier;
	}
	
	public boolean getIsVisible() {
		return isVisible;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProdRef(String prodRef) {
		this.prodRef = prodRef;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	public void setProdQuantity(int prodQuantity) {
		this.prodQuantity = prodQuantity;
	}

	public void setProdExpTime(int prodExpTime) {
		this.prodExpTime = prodExpTime;
	}

	public void setProdUnitPrice(double prodUnitPrice) {
		this.prodUnitPrice = prodUnitPrice;
	}

	public void setProdCategory(int prodCategory) {
		this.prodCategory = prodCategory;
	}

	public void setProdSupplier(int prodSupplier) {
		this.prodSupplier = prodSupplier;
	}
	
	public void setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public String toString() {
		return "ProductModel [id=" + id + ", prodRef=" + prodRef + ", prodName=" + prodName + ", prodDesc=" + prodDesc
				+ ", prodQuantity=" + prodQuantity + ", prodExpTime=" + prodExpTime + ", prodUnitPrice=" + prodUnitPrice
				+ ", prodCategory=" + prodCategory + ", prodSupplier=" + prodSupplier + "]";
	}
	
	
}
