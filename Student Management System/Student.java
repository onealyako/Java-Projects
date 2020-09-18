/**
 * This class runs the main method and stores 
 * an array of objects depending on user input
 * 
 * Author: O'Neal Yako
 */
import java.util.Scanner;

public class Student {
	

public static void main(String[] args) {
	//Ask user how many new students we  want to add
	System.out.print("Enter number of new students to enroll:");
	Scanner userInput = new Scanner(System.in);
	int input = userInput.nextInt();
	
	//Create array of object depending on user input.
	StudentDatabase[] students = new StudentDatabase[input];
	
	//Create n number of new students
	for (int n = 0; n<input; n++) {
		students[n] = new StudentDatabase();
		students[n].setStudentID();
		students[n].enrolCourse();
		students[n].payTuition();
	
	}
	
	for (int n = 0; n<input; n++) {
		//View Status of student
				System.out.print(students[n].toString());
	}
}}
