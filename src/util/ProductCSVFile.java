package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Product;

public class ProductCSVFile {
	public static List<Product> products(String path){
		List<Product> products = new ArrayList<>();		
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			String[] data;
			while(line != null) {
				
				data = line.split(",");
				double p = 0;
				
				p = Double.parseDouble(data[1]);
				
				products.add(new Product(data[0], p));
				
				line = br.readLine();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return products;
	}
}
