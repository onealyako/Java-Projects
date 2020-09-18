/**
 * This class deals with user input and creates student
 * properties. 
 * 
 * Author: O'Neal Yako
 */

import java.util.Scanner;

public class StudentDatabase {

	private String firstName;
	private String lastName;
	private int gradeYear;
	private String studentID;
	private String courses;
	private int tuitionBalance;
	private static int costOfCourse =600;
	private static int id= 1000;
	
	//Constructor
	public StudentDatabase() {
	
		System.out.print("\nEnter first name:");
		Scanner fN = new Scanner(System.in);
		this.firstName = fN.nextLine();
		
		System.out.print("Enter last name:");
		Scanner lName = new Scanner(System.in);
		this.lastName = lName.nextLine();
		
		System.out.println("\t*** SELECT GRADE LEVEL ***");
		System.out.println("YEAR 1\nYEAR 2\nYEAR 3\nYEAR 4\n");
		System.out.println("Enter Grade Year:");
		Scanner gYear = new Scanner(System.in);
		this.gradeYear = gYear.nextInt();
		
		id = 1000;	
		//id++;

	}
	
	//Generate an ID (first number is grade level)
	public void setStudentID() {
		id++;
		this.studentID = gradeYear + "" + id;
		//System.out.println("Student ID: "+studentID);
	}
	
	//Enroll in courses
	public void enrolCourse() {
		
		do {
			System.out.print("Enter course to enroll (Q to quit): ");
			Scanner input = new Scanner(System.in);
			String course = input.nextLine();
			
			if (!course.equals("Q")) {
				courses = courses + "\n " + course;
				tuitionBalance = tuitionBalance + costOfCourse;
			}
			else { break; }
		} while (1 != 0);
			
		//viewBalance();
	}
	
	//View balance
	public void viewBalance() {
		System.out.println("Tuition Balance: $"+tuitionBalance);
	}
	
	//pay tuition
	public void payTuition() {
		System.out.println("\t* * * * * *");
		viewBalance();
		System.out.print("Enter amount to pay: $");
		Scanner input = new Scanner(System.in);
		int feesPaid = input.nextInt();
		
		if (feesPaid > tuitionBalance) {
			System.out.println("You are paying more than you owe.");
		}
		else {
			tuitionBalance = tuitionBalance - feesPaid;
			//System.out.println("REMAINING BALANCE: $"+tuitionBalance);
			viewBalance();
		}
		
	}
	
	//Show student status
	public String toString() {
		return "\nName:"+firstName +" "+ lastName +
		"\nGrade Level:"+ gradeYear +
		"\nStudent ID: "+studentID +
		"\nCourses Enrolled:"+courses +
		"\nTuition Balance: $"+tuitionBalance;
	}

}