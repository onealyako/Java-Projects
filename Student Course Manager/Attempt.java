//import java.util.*;
//import java.io.FileReader;
//import java.io.BufferedReader;

public class Attempt
{
    private String courseStatus;
    private String courseGrade;
    private String semesterTaken;
    private Course course;

    public Attempt()
    {
        this("none", "none", "none");
    }

    public Attempt(String courseGrade, String courseStatus, String semesterTaken)
    {
        course = new Course();
        this.courseGrade = courseGrade;
        this.courseStatus = courseStatus;
        this.semesterTaken = semesterTaken;
    }
    
    public Attempt(String courseGrade, String courseStatus, String semesterTaken, Course c)
    {
        course = c;
        this.courseGrade = courseGrade;
        this.courseStatus = courseStatus;
        this.semesterTaken = semesterTaken;
    }

    public String getCourseGrade()
    {
        return courseGrade;
    }
    
    public void setCourseGrade(String courseGrade)
    {
        this.courseGrade = courseGrade;
    }

    public String getCourseStatus()
    {
        return courseStatus;
    }
    
    public void setCourseStatus(String courseStatus)
    {
        this.courseStatus = courseStatus;
    }

    public String getSemesterTaken()
    {
        return semesterTaken;
    }
    
    public void setSemesterTaken(String semesterTaken)
    {
        this.semesterTaken = semesterTaken;
    }

    public Course getCourse()
    {
        return course;
    }
    
    public void setCourse(Course course)
    {
        this.course = course;
    }
    
    public String toString() {
        String rs = "Course: [" + course.toString() + "] Status: " + courseStatus + "Grade: " + courseGrade;
        return rs;
    }
}