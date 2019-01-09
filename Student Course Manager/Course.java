import java.util.*;
//import java.io.FileReader;
//import java.io.BufferedReader;

/**
 * A class with information on each specific course
 *
 * @author O'Neal Yako
 * @version 10/25/18
 */
public class Course
{
    private ArrayList<Course> prerequisites;
    private String courseCode;
    private String courseTitle;
    private double courseCredit;
    private String semesterOffered;

    /**
     * Constructor for Course class
     */
    public Course()
    {
        this("none", "none", 0.0, "B");
    }

    /**
     * Overloading constructor for Course
     *
     * @param courseCode
     * @param courseTitle
     * @param semesterOffered
     * @param courseCredit
     */
    public Course(String courseCode, String courseTitle, double courseCredit, String semesterOffered)
    {
        prerequisites = new ArrayList<>();
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseCredit = courseCredit;
        this.semesterOffered = semesterOffered;
    }

    /**
     * @return
     */
    String getCourseCode()
    {
        return courseCode;
    }
    /**
     * @param courseCode
     */
    void setCourseCode(String courseCode)
    {
        this.courseCode = courseCode;
    }

    /**
     * @return
     */
    String getCourseTitle()
    {
        return courseTitle;
    }
    /**
     * @param courseTitle
     */
    void setCourseTitle(String courseTitle)
    {
        this.courseTitle = courseTitle;
    }

    /**
     * @return
     */
    double getCourseCredit()
    {
        return courseCredit;
    }
    /**
     * @param credit
     */
    void setCourseCredit(double credit)
    {
        this.courseCredit = credit;
    }

    public String getSemesterOffered()
    {
        return semesterOffered;
    }
    public void setSemesterOffered(String semesterOffered)
    {
        this.semesterOffered = semesterOffered;
    }

    /**
     * @return
     */
    ArrayList<Course> getPrerequisites()
    {
        return prerequisites;
    }
    /**
     * @param preReqList
     */
    void setPrerequisites(ArrayList<Course> preReqList)
    {
        this.prerequisites = preReqList;
    }

    public String toString() {
        String rs = "Course Code: " + courseCode + " Course Title: " + courseTitle + " Course Credit " + courseCredit + " Semester Offered " + semesterOffered;
        return rs;
    }
}
