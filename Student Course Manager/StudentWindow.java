import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.ItemListener;


 
public class StudentWindow extends JFrame{
    Student student;
    private JPanel contentPane;
    
    public StudentWindow() {
            
    }
    
    public StudentWindow(Student s) {
        student = s;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 163);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
        
                //Text Input
        JTextField inputField = new JTextField(16);
        contentPane.add(inputField);
		inputField.setBounds(97, 50, 193, 16);

        
        JLabel lblStudentPlannerLogin = new JLabel("Welcome");
		lblStudentPlannerLogin.setBounds(97, 24, 193, 16);
		contentPane.add(lblStudentPlannerLogin);
        
        //convert ArrayList of Course Attempts to String Array
        
        
        //Transcript Label
        JLabel transcriptLabel = new JLabel("Lookup a student");
        transcriptLabel.setBounds(97, 70, 193, 16);
		contentPane.add(transcriptLabel);
        
        setContentPane(contentPane);

        
        //contentPane.setBounds(97,70,193,16);
        
        
        
        JButton btnNewButton = new JButton("Search\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Click to see T!");
                String x = inputField.getText();
                if (x.length() > 0) {
                    System.out.println("searching...");
                    System.out.println(x);
                    searchStudent(x);
                }
			}
		});
        
        btnNewButton.setBounds(97, 90, 117, 29);
		contentPane.add(btnNewButton);      
        
        
        JButton newStudentBttn = new JButton("New Student");
		newStudentBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                System.out.println("button clicked");
                createNewStudent();
				//JOptionPane.showMessageDialog(null, "Click to see T!");
              
			}
		});
        
        newStudentBttn.setBounds(97, 110, 117, 29);
		contentPane.add(newStudentBttn);      
        
    }
    
    public void searchStudent(String x) {
        System.out.println("Searching for " + x);
        System.out.println("-"+x+"-");
        MyConnection c = new MyConnection(DBDetails.username, DBDetails.password);
        
        try {
            DBStudent result = c.findStudent(x);
            ArrayList<Attempt> attempts = new ArrayList<Attempt>();
            TreeMap<String, Course> coursesPlanned = new TreeMap<>();
            for (String s: result.getCourses()) {
                if (s.length() > 0) {

                String [] line = s.split(",");
                System.out.println(s);
                System.out.println("test1");
                if (line[3].equals("Completed")) {
                    System.out.println("test2");
                    Course tempCourse = new Course(line[0], "", -1, line[1]);
                    System.out.println("test3");
                    Attempt a = new Attempt(line[2], line[3], line[1], tempCourse);
                    System.out.println("test4");
                    attempts.add(a);
                    System.out.println("test5");
                }
                    
                else {
                    Course tempCourse = new Course(line[0], "", -1, line[1]);
                    System.out.println("Adding to future courses");
                    System.out.println(tempCourse);
                    coursesPlanned.put(line[0], tempCourse);

                }
                           
                
                
                }
                
                 else {
                    System.out.println("No Courses Record Yet");
                 }
            }
            System.out.println(attempts);
            
           
            
            Degree degTemp;
            
            if (result.getDegree().equals("BCG")) {
                degTemp = new BCG();
            }
            
            else if (result.getDegree().equals("SEng")) {
                degTemp = new SEng();
            }
            
            else if (result.getDegree().equals("CS")) {
                degTemp = new CS();
            }
            
            else {
                System.out.println("Couldn't read Degree type");
                degTemp = new BCG();
            }
            
            
            Student found = new Student(result.getName(), result.getId(), degTemp, attempts);
            found.setCoursesPlanned(coursesPlanned);
            System.out.println("---displaying planned courses----");
            System.out.println(coursesPlanned);
            System.out.println("---------");
            System.out.println("---displaying planned courses for Student----");
            System.out.println(found.getCoursesPlanned());
            System.out.println("---------");
            System.out.println(found);
            System.out.println("---displaying planned courses for Student----");
            System.out.println(found.getCoursesPlanned());
            System.out.println("---------");
            
            if (found != null) {
                System.out.println("Opening new window for student");
                foundStudent(found);
            }
            
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("student not found");
        }
        
    }
    
    public void foundStudent(Student stud) {
        
        
        
        
        
        getContentPane().removeAll(); 
        System.out.println("Firstname is: " + stud.getFirstName());
        JLabel welcomeLabel = new JLabel("Welcome " + stud.getFirstName());
		welcomeLabel.setBounds(97, 24, 193, 16);
		contentPane.add(welcomeLabel);
        
        JLabel creditsCompletedLabel = new JLabel("Total Completed Credits: " + stud.getDegreeProgram().creditsCompleted(stud));
		creditsCompletedLabel.setBounds(400, 300, 193, 16);
		contentPane.add(creditsCompletedLabel);
        
        JLabel creditsLabel = new JLabel("Total Remaining Credits: " + stud.getDegreeProgram().numberOfCreditsRemaining(stud));
		creditsLabel.setBounds(400, 350, 193, 16);
		contentPane.add(creditsLabel);
        
        JLabel gpaLabel = new JLabel("GPA Overall: " + stud.getDegreeProgram().overallGPA(stud));
		gpaLabel.setBounds(400, 370, 193, 16);
		contentPane.add(gpaLabel);
        
         JLabel gpaLabelCis = new JLabel("CIS GPA: " + stud.getDegreeProgram().CISGPA(stud));
		gpaLabelCis.setBounds(400, 390, 193, 16);
		contentPane.add(gpaLabelCis);
        
        JLabel last10GPALabel = new JLabel("Last 10 GPA: " + stud.getDegreeProgram().lastTenGPA(stud));
		last10GPALabel.setBounds(400, 410, 193, 16);
		contentPane.add(last10GPALabel);
        
        if (stud.getDegreeProgram().meetsRequirements(stud)) {
            JLabel completedLabelTrue = new JLabel("You Completed all required Courses!!");
		completedLabelTrue.setBounds(400, 550, 193, 16);
		contentPane.add(completedLabelTrue);
            
        }
        else {
        JLabel completedLabel = new JLabel("You have not completed all required Courses");
		completedLabel.setBounds(400, 550, 600, 16);
		contentPane.add(completedLabel);
        }
        
        


        
        JLabel yourMajorLabel = new JLabel("Your Major is " + stud.getDegreeProgram().getDegreeTitle());
		yourMajorLabel.setBounds(400, 24, 193, 16);
		contentPane.add(yourMajorLabel);
        System.out.println("Your major is: " + stud.getDegreeProgram().getDegreeTitle());
                JButton reqiredCoursesBttn = new JButton("Show Requirements\n");
		reqiredCoursesBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Click to see T!");
                System.out.println("Showing Requirements");
                showRequirements(stud);
                
			}
		});
        
        reqiredCoursesBttn.setBounds(400, 50, 150, 29);
		contentPane.add(reqiredCoursesBttn);  
        
                JButton reqiredCoursesLeftBttn = new JButton("Required Courses Left\n");
		reqiredCoursesLeftBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Click to see T!");
                System.out.println("Showing Required Courses Left");
                requiredLeft(stud);
                
			}
		});
        
        reqiredCoursesLeftBttn.setBounds(400, 80, 300, 29);
		contentPane.add(reqiredCoursesLeftBttn);  
        
        ///
            JButton reqiredCoursesLeftFutureBttn = new JButton("Required Courses Left With Planned Accounted\n");
		reqiredCoursesLeftFutureBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Click to see T!");
                System.out.println("Showing Required Courses Left");
                requiredLeftWithFuture(stud);
                
			}
		});
        
        reqiredCoursesLeftFutureBttn.setBounds(400, 500, 400, 29);
		contentPane.add(reqiredCoursesLeftFutureBttn);  

        
        ///

        
        System.out.println("Your required courses are: ---- ");
        System.out.println(stud.getDegreeProgram().getRequiredCourses());

        
        JButton showtranscriptbttn = new JButton("Show Transcript\n");
		showtranscriptbttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Click to see T!");
                System.out.println("Showing Transcript");
                showTranscript(stud);
                
			}
		});
        
        showtranscriptbttn.setBounds(97, 50, 150, 29);
		contentPane.add(showtranscriptbttn);  
        
                JButton showcoursesbttn = new JButton("Show Planned Courses\n");
		showcoursesbttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Click to see T!");
                System.out.println("Showing Courses");
                showCoursePlan(stud);
                
			}
		});
        
        showcoursesbttn.setBounds(97, 70, 200, 29);
		contentPane.add(showcoursesbttn);
        
        
                //Label for Transcript Edit
        JLabel transcriptEditLabel = new JLabel("Edit your Transcript");
        transcriptEditLabel.setBounds(97, 120, 193, 16);
		contentPane.add(transcriptEditLabel);
        
        JLabel transcriptAddLabel = new JLabel("Add A Course");
        transcriptAddLabel.setBounds(97, 140, 193, 16);
		contentPane.add(transcriptAddLabel);
        
        JLabel courseIDLabel = new JLabel("Course ID");
        courseIDLabel.setBounds(97, 160, 193, 16);
		contentPane.add(courseIDLabel);
        
          JTextField courseIDField = new JTextField(16);
        contentPane.add(courseIDField);
		courseIDField.setBounds(97, 180, 193, 16);
        
        JLabel transcriptNameLabel = new JLabel("Course Name");
        transcriptNameLabel.setBounds(97, 200, 193, 16);
		contentPane.add(transcriptNameLabel);
        
        JTextField courseNameField = new JTextField(16);
        contentPane.add(courseNameField);
		courseNameField.setBounds(97, 220, 193, 16);
        
        JLabel courseGradeLabel = new JLabel("Course Grade");
        courseGradeLabel.setBounds(97, 240, 193, 16);
		contentPane.add(courseGradeLabel);
        
        JTextField gradeField = new JTextField(16);
        contentPane.add(gradeField);
		gradeField.setBounds(97, 260, 193, 16);

        JLabel semesterTakenLabel = new JLabel("Semester Taken");
        semesterTakenLabel.setBounds(97, 280, 193, 16);
		contentPane.add(semesterTakenLabel);
        
        String[] semesterTakenOptions = new String[] {"F17","S17", "F18","S18", "F19","S19", "F20","S20", "F21","S21"};
        JComboBox<String> semesterTakenDropdown = new JComboBox<>(semesterTakenOptions);
        semesterTakenDropdown.setBounds(97,300,193,16);
        contentPane.add(semesterTakenDropdown);
        
        JLabel statusLabel = new JLabel("Course Status");
        statusLabel.setBounds(97, 320, 193, 16);
		contentPane.add(statusLabel);
        
        String[] statusOptions = new String[] {"Completed","Inprogress"};
        JComboBox<String> statusDropdown = new JComboBox<>(statusOptions);
        statusDropdown.setBounds(97,340,193,16);
        contentPane.add(statusDropdown);
        
        JButton addcoursetranscript = new JButton("Add Course\n");
		addcoursetranscript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Click to see T!");
                String inputCourseID = courseIDField.getText();
                String inputCourseName = courseNameField.getText();
                String inputGrade = gradeField.getText();
                String inputSemester = (String) semesterTakenDropdown.getSelectedItem();
                String inputStatus = (String) statusDropdown.getSelectedItem();
                
                Course c = new Course(inputCourseID, inputCourseName, 0.0, inputSemester);
                Attempt a = new Attempt(inputGrade, inputStatus, inputSemester, c);
                System.out.println("Forming Attempt:");
                System.out.println("Course ID" + inputCourseID);
                System.out.println("Course Name" + inputCourseName);
                System.out.println("Course Grade" + inputGrade);
                System.out.println("Course Semester" + inputSemester);
                System.out.println("Course Status" + inputStatus);
                System.out.println("----Course-----");
                System.out.println(c);
                System.out.println("----Attempt-----");
                System.out.println(a);
                
                MyConnection myConn = new MyConnection(DBDetails.username, DBDetails.password);
                System.out.println("adding Course Attempt to DB...");
                myConn.addCourseAttempt(stud,a);
                
                stud.getCourseAttempts().add(a);
                
                JOptionPane.showMessageDialog(null, "Course Added to Transcript!");
                
			}
		});
        
        addcoursetranscript.setBounds(97, 360, 200, 29);
		contentPane.add(addcoursetranscript);
        
        //END ADD COURSE. 
        
        JLabel changeGradeLabel = new JLabel("Change A Grade");
        changeGradeLabel.setBounds(97, 420, 193, 16);
		contentPane.add(changeGradeLabel);
        
        JLabel courseIDEditLabel = new JLabel("Course ID");
        courseIDEditLabel.setBounds(97, 440, 193, 16);
		contentPane.add(courseIDEditLabel);
        
        JTextField courseIDEditField = new JTextField(16);
        contentPane.add(courseIDEditField);
		courseIDEditField.setBounds(97, 460, 193, 16);
            
        JLabel gradeEditLabel = new JLabel("New Grade");
        gradeEditLabel.setBounds(97, 520, 193, 16);
		contentPane.add(gradeEditLabel);
        
        JTextField gradeEditField = new JTextField(16);
        contentPane.add(gradeEditField);
		gradeEditField.setBounds(97, 540, 193, 16);
        
        JLabel semesterLabelEdit = new JLabel("Semester Taken");
        semesterLabelEdit.setBounds(97, 480, 193, 16);
		contentPane.add(semesterLabelEdit);

        
        
        String[] editSemesterOptions = new String[] {"F17","S17", "F18","S18", "F19","S19", "F20","S20", "F21","S21"};
        JComboBox<String> editSemesterDropdown = new JComboBox<>(editSemesterOptions);
        editSemesterDropdown.setBounds(97,500,193,16);
        contentPane.add(editSemesterDropdown);

        
        JButton editgradetranscript = new JButton("Edit Grade\n");
		editgradetranscript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                
                String inputCourseIDEdit = courseIDEditField.getText();
                String inputGradeEditField = gradeEditField.getText();
                String inputSemesterEdit = (String) editSemesterDropdown.getSelectedItem();
                
                System.out.println("Course Name" + inputCourseIDEdit);
                System.out.println("Course Grade" + inputGradeEditField);
                System.out.println("Course Semester" + inputSemesterEdit);
                
                MyConnection myConn = new MyConnection(DBDetails.username, DBDetails.password);
                System.out.println("changing Course grade in DB...");
                myConn.changeCourseGrade(stud, inputCourseIDEdit, inputSemesterEdit, inputGradeEditField);
                
                
                System.out.println("Changing Grade");
                stud.changeGrade(inputCourseIDEdit, inputSemesterEdit, inputGradeEditField);
                JOptionPane.showMessageDialog(null, "Grade Changed!");
                
            
			}
		});
        
        editgradetranscript.setBounds(97, 580, 200, 29);
		contentPane.add(editgradetranscript);

        
        //DROP COURSE SECTION
        
        
        JLabel dropCourseLabel = new JLabel("Drop A Course");
        dropCourseLabel.setBounds(500, 140, 193, 16);
		contentPane.add(dropCourseLabel);
        
        JLabel dropIDLabel = new JLabel("Course ID");
        dropIDLabel.setBounds(500, 160, 193, 16);
		contentPane.add(dropIDLabel);
        
        JTextField dropIDField = new JTextField(16);
        contentPane.add(dropIDField);
		dropIDField.setBounds(500, 180, 193, 16);
            
        
        JLabel semesterIDLabel = new JLabel("Semester Taken");
        semesterIDLabel.setBounds(500, 200, 193, 16);
		contentPane.add(semesterIDLabel);

        
        String[] dropSemesterOptions = new String[] {"F17","S17", "F18","S18", "F19","S19", "F20","S20", "F21","S21"};
        JComboBox<String> dropSemesterDropdown = new JComboBox<>(dropSemesterOptions);
        dropSemesterDropdown.setBounds(500,220,193,16);
        contentPane.add(dropSemesterDropdown);

        
        JButton dropcourse = new JButton("Delete Course\n");
		dropcourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                
                String inputCourseDrop = dropIDField.getText();
                String inputSemesterDrop = (String) dropSemesterDropdown.getSelectedItem();
                
                System.out.println("Course Name" + inputCourseDrop);
                System.out.println("Course Semester" + inputSemesterDrop);
                
                System.out.println("Removing course");
                stud.removeCourse(inputCourseDrop, inputSemesterDrop);
                
                MyConnection myConn = new MyConnection(DBDetails.username, DBDetails.password);
                System.out.println("deleting Course from DB...");
                myConn.deleteStudentCourse(stud, inputCourseDrop, inputSemesterDrop);
                
                JOptionPane.showMessageDialog(null, "Deleted Course!");

                
            
			}
		});
        
        dropcourse.setBounds(500, 240, 200, 29);
		contentPane.add(dropcourse);
        
        

        //END DROP COURSE SECTION
        JLabel futureEditLabel = new JLabel("Edit your Future Courses");
        futureEditLabel.setBounds(800, 120, 193, 16);
		contentPane.add(futureEditLabel);
        
        JLabel futureAddLabel = new JLabel("Add A Course");
        futureAddLabel.setBounds(800, 140, 193, 16);
		contentPane.add(futureAddLabel);
        
        JLabel futureIDLabel = new JLabel("Course ID");
        futureIDLabel.setBounds(800, 160, 193, 16);
		contentPane.add(futureIDLabel);
        
        JTextField futureIDField = new JTextField(16);
        contentPane.add(futureIDField);
		futureIDField.setBounds(800, 180, 193, 16);
        
        JLabel futureNameLabel = new JLabel("Course Name");
        futureNameLabel.setBounds(800, 200, 193, 16);
		contentPane.add(futureNameLabel);
        
        JTextField futureNameField = new JTextField(16);
        contentPane.add(futureNameField);
		futureNameField.setBounds(800, 220, 193, 16);
        
        JLabel futureWeightLabel = new JLabel("Weight");
        futureWeightLabel.setBounds(800, 240, 193, 16);
		contentPane.add(futureWeightLabel);
        
        JTextField futureWeightField = new JTextField(16);
        contentPane.add(futureWeightField);
		futureWeightField.setBounds(800, 260, 193, 16);

        JLabel futureSemesterTakenLabel = new JLabel("Semester Offerred");
        futureSemesterTakenLabel.setBounds(800, 280, 193, 16);
		contentPane.add(futureSemesterTakenLabel);
        
        String[] semesterTakenOptionsFuture = new String[] {"F17","S17", "F18","S18", "F19","S19", "F20","S20", "F21","S21"};
        JComboBox<String> semesterTakenDropdownFuture = new JComboBox<>(semesterTakenOptionsFuture);
        semesterTakenDropdownFuture.setBounds(800,300,193,16);
        contentPane.add(semesterTakenDropdownFuture);
        
        
        JButton futureaddcourse = new JButton("Add Course\n");
		futureaddcourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Click to see T!");
                String inputCourseID = futureIDField.getText();
                String inputCourseName = futureNameField.getText();
                String inputWeight = futureWeightField.getText();
                String inputSemester = (String) semesterTakenDropdownFuture.getSelectedItem();
                
                Course c = new Course(inputCourseID, inputCourseName, Double.parseDouble(inputWeight), inputSemester);
                System.out.println("Forming Attempt:");
                System.out.println("Course ID" + inputCourseID);
                System.out.println("Course Name" + inputCourseName);
                System.out.println("Course Weight" + inputWeight);
                System.out.println("Course Semester" + inputSemester);
                System.out.println("----Course-----");
                System.out.println(c);
                stud.getCoursesPlanned().put(inputCourseID, c);
                
                
                MyConnection myConn = new MyConnection(DBDetails.username, DBDetails.password);
                System.out.println("adding Course Plan to DB...");
                myConn.addCourseFuture(stud,c);
                
                //stud.getCourseAttempts().add(a);
                
                JOptionPane.showMessageDialog(null, "Course Added to Transcript!");
                
			}
		});
        
        futureaddcourse.setBounds(800, 360, 200, 29);
		contentPane.add(futureaddcourse);
        

        
        
        //END ADD SAVED COURSE
        
        repaint();
    }
    
    public void createNewStudent() {
        
        getContentPane().removeAll(); 
        System.out.println("New Student Created");
        
        //Title Label
        JLabel lblStudentPlannerLogin = new JLabel("Enter New Student Info");
		lblStudentPlannerLogin.setBounds(97, 24, 193, 16);
		contentPane.add(lblStudentPlannerLogin);
        
        //drop down for Degree
        
                //Title Label
        JLabel degreeLabel = new JLabel("Select your Degree");
		degreeLabel.setBounds(97, 50, 193, 16);
		contentPane.add(degreeLabel);

        
        String[] degreeOptions = new String[] {"BCG","BCH"};
        JComboBox<String> degreeDropdown = new JComboBox<>(degreeOptions);
        degreeDropdown.setBounds(97,70,193,16);
        contentPane.add(degreeDropdown);
        
        JLabel majorLabel = new JLabel("Select your Major");
		majorLabel.setBounds(97, 100, 193, 16);
		contentPane.add(majorLabel);
        
        System.out.println("adding majors");
        String[] majorOptions = new String[] {"CS","SE-Eng"};
        JComboBox<String> majorDropdown = new JComboBox<>(majorOptions);
        majorDropdown.setBounds(97,120,193,16);
        majorDropdown.setEnabled(false);
        contentPane.add(majorDropdown);
        
        //change listener to add major if relavent.
        degreeDropdown.addItemListener(new ItemListener() {
            
            public void itemStateChanged(ItemEvent event) {
                System.out.println("state change test 1");
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    //Object item = event.getItem();
                // do something with object
                    System.out.println("state change test 2");
                    String selectedDegree = (String) degreeDropdown.getSelectedItem();
                    System.out.println("You seleted the degree: " + selectedDegree);
                    if (selectedDegree.equals("BCH")) {
                        System.out.println("enabling majors");
                        majorDropdown.setEnabled(true);
                        //repaint();
                    }
                    
                
                } }

            
        });
        
               //First Name Label
        JLabel firstNameLabel = new JLabel("Enter your First Name");
		firstNameLabel.setBounds(97, 150, 193, 16);
		contentPane.add(firstNameLabel);
        
        // get the selected item:
        //String selectedDegree = (String) degreeDropdown.getSelectedItem();
        //System.out.println("You seleted the degree: " + selectedDegree);
        
        //First Name Field
        JTextField firstNameField = new JTextField(16);
        contentPane.add(firstNameField);
		firstNameField.setBounds(97, 170, 193, 16);
        
        
         //Last Name Label
        JLabel lastNameLabel = new JLabel("Enter your Last Name");
		lastNameLabel.setBounds(97, 190, 193, 16);
		contentPane.add(lastNameLabel);
        
        // get the selected item:
        //String selectedDegree = (String) degreeDropdown.getSelectedItem();
        //System.out.println("You seleted the degree: " + selectedDegree);
        
        //Last Name Field
        JTextField lastNameField = new JTextField(16);
        contentPane.add(lastNameField);
		lastNameField.setBounds(97, 210, 193, 16);
        
          //ID Label
        JLabel idLabel = new JLabel("Enter your Student ID");
		idLabel.setBounds(97, 230, 193, 16);
		contentPane.add(idLabel);
        
        // get the selected item:
        //String selectedDegree = (String) degreeDropdown.getSelectedItem();
        //System.out.println("You seleted the degree: " + selectedDegree);
        
        //ID Field
        JTextField idField = new JTextField(16);
        contentPane.add(idField);
		idField.setBounds(97, 250, 193, 16);
        
        JButton createNewStudent = new JButton("Create");
		createNewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                
                String inputFirstName = firstNameField.getText();
                String inputLastName = lastNameField.getText();
                String inputID = idField.getText();
                String inputDegree = (String) degreeDropdown.getSelectedItem();
                String inputMajor = (String) majorDropdown.getSelectedItem();
        
                Degree degTemp;
            
                if (inputDegree.equals("BCG")) {
                    degTemp = new BCG();
                }
            
                else {
                    System.out.println("Major is: " + inputMajor);
                    if (inputMajor.equals("SE-Eng")) {
                        System.out.println("fefefe");
                        degTemp = new SEng();
                        System.out.println("degree title is:" + degTemp.getDegreeTitle());
                    }
                    else if (inputMajor.equals("CS")) {
                        degTemp = new CS();
                    }
                    else {
                        degTemp = new BCG();
                    }
                }
        
                ArrayList<Attempt> inputAttempts = new ArrayList<Attempt>();
                Student newStudentFromInput = new Student(inputFirstName, inputID, degTemp, inputAttempts);
                
                MyConnection c = new MyConnection(DBDetails.username, DBDetails.password);
                ArrayList<String> cl = new ArrayList<String>();
                cl.add("");
		        DBStudent s = new DBStudent(inputID,inputFirstName,degTemp.getDegreeTitle(),cl);
                c.saveStudent(s);

        

                System.out.println("New Student Created");
                
                foundStudent(newStudentFromInput);
              
			}
		});
        
        createNewStudent.setBounds(97, 270, 117, 29);
		contentPane.add(createNewStudent);      

              
        repaint();
    }
    
    public void showTranscript(Student s) {
        
        String[] transcript = new String[s.getCourseAttempts().size()];
        for (int i = 0; i < s.getCourseAttempts().size(); i++) {
            System.out.println(s.getCourseAttempts().get(i));
            transcript[i] = s.getCourseAttempts().get(i).toString();
        }
        
                //Transcript List
        JList<String> transcriptList = new JList<String>(transcript);
        //transcriptList.setBounds(97, 70, 400, 900);
        contentPane.add(transcriptList);
        
        //scroll pane
        
            JScrollPane scrollPane = new JScrollPane(transcriptList);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            JViewport viewport = scrollPane.getViewport();
            int w = 400;
            int h = viewport.getPreferredSize().height;
            Dimension preferredSize = new Dimension(w, h);
            viewport.setPreferredSize(preferredSize);
            JOptionPane.showMessageDialog(null, scrollPane);


        
    }
    
        public void showRequirements(Student s) {
        ArrayList<Course> requiredCourses = s.getDegreeProgram().getRequiredCourses();
        String[] requirements = new String[requiredCourses.size()];
        for (int i = 0; i < requiredCourses.size(); i++) {
            System.out.println(requiredCourses.get(i));
            requirements[i] = requiredCourses.get(i).toString();
        }
        
                //Transcript List
        JList<String> reqList = new JList<String>(requirements);
        //transcriptList.setBounds(97, 70, 400, 900);
        contentPane.add(reqList);
        
        //scroll pane
        
            JScrollPane scrollPane = new JScrollPane(reqList);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            JViewport viewport = scrollPane.getViewport();
            int w = 400;
            int h = viewport.getPreferredSize().height;
            Dimension preferredSize = new Dimension(w, h);
            viewport.setPreferredSize(preferredSize);
            JOptionPane.showMessageDialog(null, scrollPane);


        
    }
    
    public void requiredLeft(Student s) {
        ArrayList<Course> requiredCourses = s.getDegreeProgram().remainingRequiredCourses(s);
        System.out.println(requiredCourses);
        String[] requirements = new String[requiredCourses.size()];
        for (int i = 0; i < requiredCourses.size(); i++) {
            System.out.println(requiredCourses.get(i));
            requirements[i] = requiredCourses.get(i).toString();
        }
        
                //Transcript List
        JList<String> reqList = new JList<String>(requirements);
        //transcriptList.setBounds(97, 70, 400, 900);
        contentPane.add(reqList);
        
        //scroll pane
        
            JScrollPane scrollPane = new JScrollPane(reqList);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            JViewport viewport = scrollPane.getViewport();
            int w = 400;
            int h = viewport.getPreferredSize().height;
            Dimension preferredSize = new Dimension(w, h);
            viewport.setPreferredSize(preferredSize);
            JOptionPane.showMessageDialog(null, scrollPane);


        
    }
    
    public void requiredLeftWithFuture(Student s) {
        ArrayList<Course> requiredCourses = s.getDegreeProgram().remainingRequiredCourses(s);
        System.out.println(requiredCourses);
        String[] requirements = new String[requiredCourses.size()];
        for (int i = 0; i < requiredCourses.size(); i++) {
            if (s.getCoursesPlanned().containsKey(requiredCourses.get(i).getCourseCode())) {
                System.out.println("Overlap, wont add");
            }
            else {
            System.out.println(requiredCourses.get(i));
            requirements[i] = requiredCourses.get(i).toString();
            }
        }
        
                //Transcript List
        JList<String> reqList = new JList<String>(requirements);
        //transcriptList.setBounds(97, 70, 400, 900);
        contentPane.add(reqList);
        
        //scroll pane
        
            JScrollPane scrollPane = new JScrollPane(reqList);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            JViewport viewport = scrollPane.getViewport();
            int w = 400;
            int h = viewport.getPreferredSize().height;
            Dimension preferredSize = new Dimension(w, h);
            viewport.setPreferredSize(preferredSize);
            JOptionPane.showMessageDialog(null, scrollPane);


        
    }
    
        public void showCoursePlan(Student s) {
        
        String[] courses = new String[s.getCoursesPlanned().size()];
        int i = 0;
        for(String semester : s.getCoursesPlanned().keySet())
            {
                Course course = s.getCoursesPlanned().get(semester);
                courses[i] = course.getCourseCode() + " - Planned for: " + semester;
                i++;
            }
        
                //Courses List
        JList<String> coursesList = new JList<String>(courses);
        //transcriptList.setBounds(97, 70, 400, 900);
        contentPane.add(coursesList);
        
        //scroll pane
        
            JScrollPane scrollPane = new JScrollPane(coursesList);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            JViewport viewport = scrollPane.getViewport();
            int w = 400;
            int h = viewport.getPreferredSize().height;
            Dimension preferredSize = new Dimension(w, h);
            viewport.setPreferredSize(preferredSize);
            JOptionPane.showMessageDialog(null, scrollPane);


        
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