package model;

public class SupplierModel {

	private int id;
	private String supplierName;
	private String supplierAddress;
	private String supplierPhoneNumber;
	
	public SupplierModel() {
		this.id = 0;
		this.supplierName = "";
		this.supplierAddress = "";
		this.supplierPhoneNumber = "";
	}

	public int getId() {
		return id;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public String getSupplierPhoneNumber() {
		return supplierPhoneNumber;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public void setSupplierPhoneNumber(String supplierPhoneNumber) {
		this.supplierPhoneNumber = supplierPhoneNumber;
	}


}

