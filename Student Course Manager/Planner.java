//import java.util.*;
//import java.io.FileReader;
//import java.io.BufferedReader;
import java.awt.*;
import javax.swing.*;

public class Planner {

	public static void main(String[] args) {

		CourseCatalog catalog = new CourseCatalog();
		catalog.initializeCatalog("courseList.csv");
        System.out.println(catalog);


		//GUI
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainGUI frame = new MainGUI();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

	}

}
