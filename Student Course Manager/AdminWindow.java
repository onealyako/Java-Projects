import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.ItemListener;


 
public class AdminWindow extends JFrame{
    Student student;
    private JPanel contentPane;
    
   
    
    public AdminWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 163);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
        
                //Text Input
        

        
        JLabel lblStudentPlannerLogin = new JLabel("Welcome Admin");
		lblStudentPlannerLogin.setBounds(97, 24, 193, 16);
		contentPane.add(lblStudentPlannerLogin);
        
        JLabel transcriptAddLabel = new JLabel("Add A Course");
        transcriptAddLabel.setBounds(97, 50, 193, 16);
		contentPane.add(transcriptAddLabel);
        
        JLabel courseIDLabel = new JLabel("Course ID");
        courseIDLabel.setBounds(97, 70, 193, 16);
		contentPane.add(courseIDLabel);
        
          JTextField courseIDField = new JTextField(16);
        contentPane.add(courseIDField);
		courseIDField.setBounds(97, 90, 193, 16);
        
        JLabel transcriptNameLabel = new JLabel("Course Name");
        transcriptNameLabel.setBounds(97, 110, 193, 16);
		contentPane.add(transcriptNameLabel);
        
        JTextField courseNameField = new JTextField(16);
        contentPane.add(courseNameField);
		courseNameField.setBounds(97, 130, 193, 16);
        
        JLabel creditLabel = new JLabel("Credits");
        creditLabel.setBounds(97, 150, 193, 16);
		contentPane.add(creditLabel);
        
        JTextField creditField = new JTextField(16);
        contentPane.add(creditField);
		creditField.setBounds(97, 170, 193, 16);

        
        JLabel semesterTakenLabel = new JLabel("Semester Offered");
        semesterTakenLabel.setBounds(97, 190, 193, 16);
		contentPane.add(semesterTakenLabel);
        
        String[] semesterTakenOptions = new String[] {"F","B", "W"};
        JComboBox<String> semesterTakenDropdown = new JComboBox<>(semesterTakenOptions);
        semesterTakenDropdown.setBounds(97,210,193,16);
        contentPane.add(semesterTakenDropdown);
        
        JButton addcoursetranscript = new JButton("Add Course\n");
		addcoursetranscript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Click to see T!");
                String inputCourseID = courseIDField.getText();
                String inputCourseName = courseNameField.getText();
                String inputCredit = creditField.getText();
                String inputSemester = (String) semesterTakenDropdown.getSelectedItem();
                
                MyConnection mc = new MyConnection(DBDetails.username, DBDetails.password);
                mc.addCourse(inputCourseID, inputCredit, inputCourseName,inputSemester, "");
                                
                JOptionPane.showMessageDialog(null, "Course Added!");
                
			}
		});
        
        addcoursetranscript.setBounds(97, 230, 200, 29);
		contentPane.add(addcoursetranscript);
        
        setContentPane(contentPane);
        
        
    }
  
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public void createAndShowGUI() {
        //Create and set up the window. 
        //Display the window.
        JFrame frame = new StudentWindow();
        frame.pack();
        frame.setVisible(true);
    }
 
}