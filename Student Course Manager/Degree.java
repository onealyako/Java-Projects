import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Abstract class Degree - An abstract parent class for each type of
 * degree a user can select from
 * <p>
 *
 *
 * @author
 * @version 10/25/18
 */
public abstract class Degree
{
    String degreeTitle;
    ArrayList<String> requiredCourses;
    ArrayList<Course> requirements;


    /**
     * Constructor for the Degree class
     */
    public Degree()
    {
        requiredCourses = new ArrayList<String>();
        requirements = new ArrayList<Course>();
        this.degreeTitle = null;
    }

    /**
     * Sets the degreeTitle
     *
     * @param title Degree title to set
     */
    public void setDegreeTitle(String title)
    {
        if(title != null && !title.isEmpty())
        {
            this.degreeTitle = title;
        }
    }
    /**
     * Gets and returns the degreeTitle
     *
     * @return degreeTitle
     */
    public String getDegreeTitle()
    {
        return degreeTitle;
    }

    /**
     * Gets passed an ArrayList of Strings of courseCodes that are read from CSV file
     *
     * @param listOfRequiredCourseCodes ArrayList of courseCode strings
     */
    public void setRequiredCourses(ArrayList<String> listOfRequiredCourseCodes)
    {
        if(listOfRequiredCourseCodes != null && !listOfRequiredCourseCodes.isEmpty())
        {
            this.requiredCourses = listOfRequiredCourseCodes;
        }
    }
    /**
     * @return requiredCourses List of required courses
     */
    public ArrayList<Course> getRequiredCourses() {
        return requirements;
    }


    //initializie

        public void initializeRequired(String filename) {
        try
        {
            BufferedReader myReader = new BufferedReader(new FileReader(filename));
            String line = myReader.readLine();
            String[] splitString, splitPrereq;


            while(line != null)
            {

                String oneLine = line;
                Course readCourse = new Course();
                ArrayList<Course> prereq = new ArrayList<Course>();
                splitString = oneLine.split(",");

                try {
                readCourse.setCourseCode(splitString[0].trim());
                readCourse.setCourseTitle(splitString[2].trim());
                readCourse.setCourseCredit(Double.parseDouble(splitString[1].trim()));
                readCourse.setSemesterOffered(splitString[3].trim());


                if(splitString.length == 5)
                {
                    splitPrereq = splitString[4].trim().split(":");
                    for(int i = 0; i < splitPrereq.length; i++)
                    {

                            Course prereqCourse = new Course();
                            String [] splitFuther = splitPrereq[i].split(",");

                        try {
                        prereqCourse.setCourseCode(splitString[0].trim());
                        prereqCourse.setCourseTitle(splitString[2].trim());
                        prereqCourse.setCourseCredit(Double.parseDouble(splitString[1].trim()));
                        prereqCourse.setSemesterOffered(splitString[3].trim());

                        }
                        catch (Exception e) {
                            System.out.println("Invalid course\ngoing on to next line");
                        }
                            splitString = oneLine.split(",");

                                prereq.add(prereqCourse);

                    }
                }
                readCourse.setPrerequisites(prereq);
                requirements.add(readCourse);

                line = myReader.readLine();
                }
                catch (Exception e) {

                    line = myReader.readLine();
                }
            }
            myReader.close();
        }
        catch(Exception e)
        {
        }

    }


    /**
     * This function will go through both the courses that are required for a certain degree
     * and the courses that the user has in their plan of study.
     *
     * @param thePlan The user's PlanOfStudy
     * @return meetsReq Boolean of whether or not the student meets the degree requirements
     */
    public abstract boolean meetsRequirements(Student student);

    /**
     * This function will check how many credits the user has completed so far in their university
     * career.
     *
     * @param thePlan the user's PlanOfStudy
     * @return creditsRemaining The number of credits left to complete in the degree
     */


    /**
     * This function will compare the required courses in the program .
     *
     * @param thePlan
     * @return
     */
        public ArrayList<Course> remainingRequiredCourses(Student student)
    {
            System.out.println("test5");
        MyConnection c = new MyConnection(DBDetails.username, DBDetails.password);
        c.repopulateCourses();
            System.out.println("test4");
        boolean add = true;
        ArrayList<Course> remainingReq = new ArrayList<>();
            System.out.println("test9");
        for(Course required : student.getDegreeProgram().getRequiredCourses())
        {
            System.out.println("test8");
            for(Attempt taken : student.getCourseAttempts())
            {
                System.out.println("test7");
                Course course = taken.getCourse();
                if(course.getCourseCode().equals(required.getCourseCode()))
                {
                    System.out.println("test6");
                    add = false;
                    break;
                }
            }
            if(add)
            {
                System.out.println("test10");
                if(c.findCourse(required.getCourseCode()) != null)
                {
                    System.out.println("test11");
                    System.out.println(required.getCourseCode());
                    String [] splitresult = c.findCourse(required.getCourseCode()).split(",");
                    Course tempC = new Course(splitresult[0], splitresult[2],  Double.parseDouble(splitresult[1]), splitresult[3]);


                    remainingReq.add(tempC);
                }
                else
                {
                    System.out.println(required + " is not in catalog.");
                }
            }
            add = true;
        }
        return remainingReq;
    }

        abstract public double numberOfCreditsRemaining(Student student);



        abstract public double creditsCompleted(Student student);



    public abstract double overallGPA(Student student);
    public abstract double CISGPA(Student student);
    public abstract double lastTenGPA(Student student);

    /**
     * This function overrides the toString function and returns a string.
     *
     * @return printThis the string to be printed
     */
    public String toString()
    {
        String printThis = "";

        printThis += degreeTitle + ": ";

        for(String courseCode : requiredCourses)
        {
            printThis += courseCode;
            if(courseCode != requiredCourses.get(requiredCourses.size() - 1))
            {
                printThis += ", ";
            }
        }
        return printThis;
    }
}
