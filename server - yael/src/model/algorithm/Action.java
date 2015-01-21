package model.algorithm;

import java.io.Serializable;

public class Action implements Serializable{
	String description;
	private double price;
	
	//
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Action() {
		
	}
	//
	
	//C'tor
	public Action(String description) {
		this.description=description;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public int hashCode(){
		return description.hashCode();
	}
	
	@Override
	public String toString(){
		return description;
	}
}
