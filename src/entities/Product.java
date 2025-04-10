package entities;

import java.util.List;

public class Product {
	private String name;
	private double price;
	
	public Product(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public static double averagePrice(List<Product> p) {
		
		double resul = 0;
		int counter = 0;
		
		for(Product prod: p) {
			resul += prod.getPrice();
			counter += 1;
		}
		
		return resul/counter;
	}
	
	
	@Override
	public String toString() {
		return "Product [name=" + name + ", price= R$ " + String.format("%.2f", price)+ "]";
	}
	
	
}
