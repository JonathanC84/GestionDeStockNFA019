package model;

public class CategoryModel {

	private int id;
	private String catName;
	private String catDesc;
	
	public CategoryModel() {
		this.id = 0;
		this.catName = "";
		this.catDesc = "";
	}

	public int getId() {
		return id;
	}

	public String getCatName() {
		return catName;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}	
	
}
