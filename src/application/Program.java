package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entities.Product;
import util.ProductCSVFile;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		List<Product> products = new ArrayList<>();
		String path = "";
		
		System.out.print("Enter full file path: ");
		path = sc.nextLine();
		
		try {
			products = ProductCSVFile.products(path);
		}catch (Exception e) {
			System.out.println("Processamento do arquivo não concluido!");
		}
		
		if(!products.isEmpty()) {
			double averagePrice = Product.averagePrice(products);
			
			System.out.println("Average price: R$ " + String.format("%.2f", averagePrice));
			
			Stream<Product> prod2 = products.stream().filter(p -> p.getPrice()<averagePrice);
			List<Product> list = prod2.collect(Collectors.toList());
			
			list.sort((p1, p2) -> p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase()));
			
			Collections.reverse(list);
			
			list.forEach(System.out::println);
		}
		
		sc.close();
	}

}
