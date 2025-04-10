package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Employee {
	
	private String name;
	private String email;
	private double salary;
	
	public Employee(String name, String email, double salary) {
		super();
		this.name = name;
		this.email = email;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Emplyee [name=" + name + ", email=" + email + ", salary=" + salary + "]";
	}
	
	//método para carregar uma lista de employees a partir de um arquivo csv:
	
	public static List<Employee> chargeOfCSVFile(String path){
		List<Employee> list = new ArrayList<>();
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			double salary;
			while(line != null) {
				String[] data = line.split(",");
				salary = 0.0;
				
				try {
					salary = Double.parseDouble(data[2]);
					list.add(new Employee(data[0], data[1], salary));
				}catch(Exception e) {
					System.out.println("Verifique os dados do arquivo CVS! Impossível converter as informações para objeto do tipo Employee!\n" + e.getMessage());
				}
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				br.close();
				fr.close();
			} catch (Exception e2) {
				System.out.println("Erro ao tentar fechar br e fr: \n" + e2.getMessage());
			}
		}
		
		return list;
	}
	
	//função que retorna a soma do salário que funcionarios que cumpram uma determinada condição em uma lista de funcionários:
	
	public static double sumOfSalary(List<Employee> emp, Predicate<Employee> p) {
		double sum;
		
		List<Double> salarys = emp.stream()
								.filter(p)
								.map(e -> e.getSalary())
								.collect(Collectors.toList());
		
		sum = salarys.stream().reduce(0.0, (s1, s2) -> s1 + s2);
		
		return sum;
	}
}
