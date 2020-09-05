
public class Banking {

	//Deposit, Withdraw, check previous transaction. 
	private int balance;
	private int prevTrans;
	private String customerName;
	private int customerID;
	
	Banking (String cname, int cid) {
		customerName = cname;
		customerID = cid;
	}
	
	public void showBalance() {
		System.out.println("Balance: $"+balance);
	}
	
	public void Deposit (int amount) {
		
		if (amount > 0) {
			balance = balance + amount;
			System.out.println("Balance: $"+ balance);
			
			prevTrans = amount;
		}
		else {
			System.out.println("You must deposit more than $0.");
		}

		
	}
	
	public void Withdraw (int amount) {
		
		if (amount != 0) {
			balance = balance - amount;
			System.out.println("Balance: $"+balance);
			
			prevTrans = -amount;
		}
		else {
			System.out.println("You cannot withdraw when you have $0 in your account.");
		}
		
		
	}
	
	public void prevTrans () {
		if (prevTrans < 0) {
			System.out.println("Withdrawn: $"+Math.abs(prevTrans));
		}
		else if (prevTrans > 0) {
			System.out.println("Deposited: $"+ prevTrans);
		}
		else {
			System.out.println("No previous transaction occured.");
		}
		
		//System.out.println("The previous transaction ");
	}
	
	
}
