import java.util.Scanner;
import java.util.ArrayList;

public class Example {

	public static void main(String[] args) {
		
	ArrayList<String> cities = new ArrayList<String>();
	ArrayList<String> countries = new ArrayList<String>();
	
	Scanner reader = new Scanner(System.in);
	
	while(true)
	{
		System.out.println("Enter city: ");
		String input1 = reader.nextLine();
		
		System.out.println("ENter country: ");
		String input2 = reader.nextLine();
		
		cities.add(input1);
		countries.add(input2);
		
		if(input1.isEmpty())
		{
			
				System.out.println("Number of cities: " + (cities.size()-1));
				System.out.println("Number of countries: " + (countries.size()-1));
				
			return; 
		}
	}
	
	}
	
		
}
	

	

