import java.util.Scanner;

public class userMenu {
	
	private static int userChoice;
	private static int userAmount;
	private String customerName;
	private int customerID;

	public static void main(String[] args) {
		
		
		//Add EXIT option later.
		//System.out.println("**Welcome to Online Banking! Select an option to continue.**");
		
		System.out.println("Enter your name: ");
		Scanner scan1 = new Scanner(System.in);
		
		String customerName = scan1.nextLine();
		System.out.println(("Enter your bank ID number: "));
		Scanner scan2 = new Scanner(System.in);
		
		int customerID = scan2.nextInt();
		
		Banking customer = new Banking(customerName, customerID);
		
		System.out.println("****** Welcome to Online Banking "+customerName + ". Your customer ID is: "+customerID + " ******");
		System.out.println("Select an option below to continue.");
		
		do {
			System.out.print("\t1. Deposit\n \t2. Withdraw\n \t3. Show previous transaction\n \t4. Check Balance\n \t5. EXIT\n" );
			Scanner scan = new Scanner(System.in);
			
			int userChoice = scan.nextInt();
			
			//Call deposit method in Banking class
			if (userChoice == 1)
			{
				System.out.print("Enter amount to deposit: $");
				scan = new Scanner(System.in);
				
				int userAmount = scan.nextInt();
				
				
				customer.Deposit(userAmount);
			}
			//Call withdraw method in banking class
			else if (userChoice == 2)
			{
				System.out.print("Enter amount for withdraw: $");
				scan = new Scanner(System.in);
				
				int userAmount = scan.nextInt();
				
				customer.Withdraw(userAmount);
			}
			//Call previous transaction method in banking class to view previous transaction
			else if (userChoice == 3)
			{
				//System.out.println("**************");
				
				customer.prevTrans();
			}
			else if (userChoice ==4)
			{
				customer.showBalance();
				
			}
			else if (userChoice ==5)
			{
				System.out.println("Thank you for using our service!");
			}
			
			
		} while (userChoice != 5);
		
		
		
		

	}

}
