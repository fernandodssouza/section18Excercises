package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class program2 {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner(System.in);
		List<Employee> employees = new ArrayList<>();
		String path = "";
		double salaryFilter;
		
		System.out.print("Enter full file path: ");
		path = sc.nextLine();
				
		try {
			employees = Employee.chargeOfCSVFile(path);
		}catch(Exception e){
			System.out.println("Não foi possível carregar os dados do arquivo. Erro: \n" + e.getMessage());
		}
		
		if(!employees.isEmpty()) {
			System.out.print("Enter the salary filter: ");
			salaryFilter = sc.nextDouble();
			
			//cria lista com emails apenas dos funcionários que contenham salários superioes ao filtro.
			List<String> email = employees.stream()
								.filter(e -> e.getSalary()>salaryFilter)
								.map(e -> e.getEmail())
								.collect(Collectors.toList());
			
			email.sort((a, b) -> a.toUpperCase().compareTo(b.toUpperCase()));
			
			email.forEach(System.out::println);
			
			char letter = 'M';
			
			double sum = Employee.sumOfSalary(employees, (e -> e.getName().toUpperCase().charAt(0) == letter));
			
			System.out.println("Sum of salary of people whose name starts with " + letter + ": R$ " + String.format("%.2f", sum));
			
		}
		sc.close();	
		
	}

}
