import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Information on the user. Name, student number, etc.
 *
 * @author
 * @version 10/25/18
 */
public class Student
{
    private String fullName;
    private String firstName;
    private String lastName;
    private Integer studentNum;
    private ArrayList<Attempt> courseAttempts;
    private TreeMap<String, Course> coursesPlanned;
    private Degree degreeProgram;
    private CourseCatalog catalog;


    /**
     * Constructor for Student class
     */
    public Student()
    {
        this("none", "none", "none", 0);
    }

    /**
     * Overloading constructor for Student
     *
     * @param firstName
     * @param lastName
     * @param fullName
     * @param studentNum
     */
    public Student(String firstName, String lastName, String fullName, int studentNum)
    {
        this.courseAttempts = new ArrayList<>();
        this.coursesPlanned = new TreeMap<>();
        this.degreeProgram = null;
        this.catalog = new CourseCatalog();
        catalog.initializeCatalog("courseList.csv");
        this.fullName = fullName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNum = studentNum;
    }

    public Student(String name, String id, Degree degree, ArrayList<Attempt> courses)
    {

        this.courseAttempts = courses;
        this.coursesPlanned = new TreeMap<>();
        this.degreeProgram = degree;
        this.catalog = new CourseCatalog();
        catalog.initializeCatalog("courseList.csv");
        this.fullName = name;
        this.firstName = name;
        this.lastName = name;
        this.studentNum = Integer.parseInt(id);
    }



    //Accessors and Mutators

    /**
     * @param firstName
     * @param lastName
     */
    public void setFullName(String firstName, String lastName)
    {
        fullName = firstName + " " + lastName;
    }
    /**
     * @return
     */
    public String getFullName()
    {
        return fullName;
    }

    /**
     * @return
     */
    public String getFirstName()
    {
        return firstName;
    }
    /**
     * @param first
     */
    public void setFirstName(String first)
    {
        this.firstName = first;
    }

    /**
     * @return
     */
    public String getLastName()
    {
        return lastName;
    }
    /**
     * @param last
     */
    public void setLastName(String last)
    {
        this.lastName = last;
    }

    /**
     * @return
     */
    public Integer getStudentNum()
    {
        return studentNum;
    }
    /**
     * @param studentNum
     */
    public void setStudentNum(Integer studentNum)
    {
        this.studentNum = studentNum;
    }

    public Degree getDegreeProgram()
    {
        return degreeProgram;
    }
    public void setDegreeProgram(Degree degreeProgram)
    {
        this.degreeProgram = degreeProgram;
    }

    public CourseCatalog getCatalog()
    {
        return catalog;
    }
    public void setCatalog(CourseCatalog catalog)
    {
        this.catalog = catalog;
    }

    public ArrayList<Attempt> getCourseAttempts()
    {
        return courseAttempts;
    }

    public TreeMap<String, Course> getCoursesPlanned()
    {
        return coursesPlanned;
    }

    public void setCoursesPlanned(TreeMap<String, Course> coursesPlanned)
    {
        this.coursesPlanned = coursesPlanned;
    }


    public void addCourse(String courseCode, String semester)
    {
        Course toAdd = catalog.findCourse(courseCode);

        if(toAdd != null)
        {
            if(!toAdd.getSemesterOffered().equals("B") && !toAdd.getSemesterOffered().equals(semester.substring(0, 1)))
            {
                return;
            }
            if(semester.charAt(0) == 'F' || semester.charAt(0) == 'W')
            {
                try
                {
                    if(Integer.parseInt(semester.substring(1)) >= 17)
                    {
                        coursesPlanned.put(semester, toAdd);
                    }
                    else
                    {
                        System.out.println("Invalid semester");
                    }
                }
                catch(Exception e)
                {
                }
            }
        }
    }

    public void removeCourse(String courseCode, String semester)
    {
        for(String semesterKey : coursesPlanned.keySet())
        {
            Course course = coursesPlanned.get(semesterKey);

            if(course.getCourseCode().equals(courseCode) && semesterKey.equals(semester))
            {
                coursesPlanned.remove(semester);
                return;
            }
        }
        for(Attempt attempt : courseAttempts)
        {
            Course course = attempt.getCourse();
            if(course.getCourseCode().equals(courseCode) && attempt.getSemesterTaken().equals(semester))
            {
                courseAttempts.remove(attempt);
                return;
            }
        }
    }

    public void changeStatus(String courseCode, String semester, String status)
    {
        if(status.toLowerCase().equals("inprogress") || status.toLowerCase().equals("complete"))
        {
            for(String semesterKey : coursesPlanned.keySet())
            {
                Course course = coursesPlanned.get(semesterKey);
                if(course.getCourseCode().equals(courseCode) && semesterKey.equals(semester))
                {
                    Attempt attempt = new Attempt();
                    attempt.setCourseGrade("");
                    attempt.setCourseStatus(status);
                    attempt.setSemesterTaken(semester);
                    attempt.setCourse(course);
                    courseAttempts.add(attempt);
                    return;
                }
            }
            for(Attempt attempt : courseAttempts)
            {
                Course course = attempt.getCourse();
                if(course.getCourseCode().equals(courseCode) && attempt.getSemesterTaken().equals(semester))
                {
                    attempt.setCourseStatus(status);
                    return;
                }
            }
        }
        else if(status.toLowerCase().equals("planned"))
        {
            for(Attempt attempt : courseAttempts)
            {
                Course course = attempt.getCourse();
                if(course.getCourseCode().equals(courseCode) && attempt.getSemesterTaken().equals(semester))
                {
                    coursesPlanned.put(semester, course);
                    courseAttempts.remove(attempt);
                    return;
                }
            }
        }
    }

    public void changeGrade(String courseCode, String semester, String grade)
    {
        for(Attempt attempt : courseAttempts)
        {
            Course course = attempt.getCourse();
            if(course.getCourseCode().equals(courseCode) && attempt.getSemesterTaken().equals(semester))
            {
                attempt.setCourseGrade(grade);
                return;
            }
        }
    }

    public ArrayList<Course> findPrerequisites(String courseCode)
    {
        Course course = catalog.findCourse(courseCode);

        return course.getPrerequisites();
    }

    /**
     * Imports the student transcript ans sets the variables of the courses in
     * the plan's coursesTaken ArrayList to the ones read from the file
     *
     * @param filename
     */
    public void importData(String filename)
    {
        courseAttempts.clear();
        coursesPlanned.clear();
        try
        {
            BufferedReader myReader = new BufferedReader(new FileReader(filename));
            String[] splitString;
            String line = myReader.readLine();

            while(line != null)
            {
              
                String oneLine = line;
                Course readCourse = new Course();
                Attempt attempt = new Attempt();

                splitString = oneLine.split(",");
                try {
                if(catalog.findCourse(splitString[0]) != null)
                {

                    readCourse = catalog.findCourse(splitString[0]);
                    if(splitString[4].toLowerCase().equals("complete") || splitString[4].toLowerCase().equals("inprogress"))
                    {

                        attempt.setCourse(readCourse);
                        attempt.setCourseStatus(splitString[1]);
                        attempt.setCourseGrade(splitString[2]);
                        attempt.setSemesterTaken(splitString[3]);

                        courseAttempts.add(attempt);

                    }

                }
                    line = myReader.readLine();
                }

                catch (Exception e) {
                    System.out.println("Error, going on to next line");
                    line = myReader.readLine();

                }

            }

            myReader.close();
        }
        catch(Exception e)
        {
        }
    }

    public void sortTranscript()
    {
        for(int i = 0; i < courseAttempts.size() - 1; i++)
        {
            for(int j = 0; j < courseAttempts.size() - 1 - i; j++)
            {
                if(Integer.parseInt(courseAttempts.get(j).getSemesterTaken().substring(1)) > Integer.parseInt(courseAttempts.get(j + 1).getSemesterTaken().substring(1)))
                {
                    Attempt temp = courseAttempts.get(j);
                    courseAttempts.set(j, courseAttempts.get(j + 1));
                    courseAttempts.set(j + 1, temp);
                }
                if((courseAttempts.get(j).getSemesterTaken().charAt(0) == 'F' && courseAttempts.get(j + 1).getSemesterTaken().charAt(0) == 'W') && (Integer.parseInt(courseAttempts.get(j).getSemesterTaken().substring(1)) == Integer.parseInt(courseAttempts.get(j + 1).getSemesterTaken().substring(1))))
                {
                    Attempt temp = courseAttempts.get(j);
                    courseAttempts.set(j, courseAttempts.get(j + 1));
                    courseAttempts.set(j + 1, temp);
                }
            }
        }

        TreeMap<String, Course> sort = new TreeMap<>(new ValueComparator(coursesPlanned));

        //coursesPlanned = sort;
    }

    public String toString()
    {
        System.out.println("---, --,test 9 --,");
        System.out.println(coursesPlanned);
        sortTranscript();
        String printThis = "";
        System.out.println("---, --,test 10 --,");
        System.out.println(coursesPlanned);


        printThis += firstName + " " + lastName + " - " + studentNum + "\n";

        printThis += "Attempts:\n";
        if(!courseAttempts.isEmpty())
        {
            for(Attempt attempt : courseAttempts)
            {
                Course course = attempt.getCourse();
                printThis += course.getCourseCode() + " - " + attempt.getCourseStatus() + ", " + attempt.getCourseGrade() + ", " + attempt.getSemesterTaken() + "\n";
            }
        }
        else
        {
            printThis += "None.\n";
        }

        printThis += "\nPlanned:\n";
        System.out.println(getCoursesPlanned());
        if(!coursesPlanned.isEmpty())
        {
            for(String semester : coursesPlanned.keySet())
            {
                Course course = coursesPlanned.get(semester);
                printThis += course.getCourseCode() + " - Planned for: " + semester + "\n";
            }
        }
        else
        {
            printThis += "None.\n";
        }

        return printThis;
    }
}

class ValueComparator implements Comparator<String>
{
    Map<String, Course> map;
    public ValueComparator(Map<String, Course> map)
    {
        this.map = map;
    }

    public int compare(String a, String b)
    {
        if((a.charAt(0) == 'F' && b.charAt(0) == 'W') && (Integer.parseInt(a.substring(1)) == Integer.parseInt(b.substring(1))))
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}
