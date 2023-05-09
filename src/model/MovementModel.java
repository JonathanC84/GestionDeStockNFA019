package model;

import java.time.ZonedDateTime;

public class MovementModel {

	private int id;
	private String movementType;
	private int movementQuantity;
	private ZonedDateTime movementTime;
	private int productId;
	
	public MovementModel() {
		this.id = 0;
		this.movementType = "";
		this.movementQuantity = 0;
		this.movementTime = null;
		this.productId = 0;
	}

	public int getId() {
		return id;
	}

	public String getMovementType() {
		return movementType;
	}

	public int getMovementQuantity() {
		return movementQuantity;
	}

	public ZonedDateTime getMovementTime() {
		return movementTime;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	public void setMovementQuantity(int movementQuantity) {
		this.movementQuantity = movementQuantity;
	}

	public void setMovementTime(ZonedDateTime movementTime) {
		this.movementTime = movementTime;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "MovementModel [id=" + id + ", movementType=" + movementType + ", movementQuantity=" + movementQuantity
				+ ", movementDate=" + movementTime + ", productId=" + productId + "]";
	}
	
	
}
