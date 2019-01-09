import java.util.*;
//import java.io.FileReader;
//import java.io.BufferedReader;

/**
 * A concrete class for the Software Engineering degree type
 *
 * @author O'Neal Yako
 * @version 10/25/18
 */
public class SEng extends HonoursDegree
{
    /**
     * Constructor for SEng class
     */
    public SEng()
    {
        super();
        degreeTitle = "SEng";
        initializeRequired("SENGrequired.csv");

    }

    public boolean meetsRequirements(Student student)
    {
        return false;
    }

   public double numberOfCreditsRemaining(Student student)
    {
        double totalCredits = creditsCompleted(student);

        if(requiredCredits >= totalCredits)
        {
            return requiredCredits - totalCredits;
        }
        else
        {
            return 0.0;
        }
    }

    public double creditsCompleted(Student student)
    {
        double totalCredits = 0.0;
        int gradeInt = 0;

        for(Attempt attempt : student.getCourseAttempts())
        {
            Course course = attempt.getCourse();
            if(attempt.getCourseStatus().toLowerCase().equals("inprogress"))
            {
                totalCredits += course.getCourseCredit();
            }
            else if(attempt.getCourseStatus().toLowerCase().equals("complete"))
            {
                try
                {
                    gradeInt = Integer.parseInt(attempt.getCourseGrade());
                    if(gradeInt >= 50)
                    {
                        totalCredits += course.getCourseCredit();
                    }
                }
                catch(Exception e)
                {
                  
                }
            }
        }
        for(Course course : student.getCoursesPlanned().values())
        {
            totalCredits += course.getCourseCredit();
        }
        return totalCredits;
    }

    public double overallGPA(Student student)
    {
        ArrayList<Attempt> courseAttempts = student.getCourseAttempts();
        double GPA = 0.0;
        int GPACourses = 0;
        int gradeInt = 0;

        for(Attempt attempt : courseAttempts)
        {
            try
            {
                gradeInt = Integer.parseInt(attempt.getCourseGrade());
                GPACourses++;
                GPA += gradeInt;
            }
            catch(Exception e)
            {

            }
        }

        GPA /= GPACourses;

        return GPA;
    }

    public double CISGPA(Student student)
    {
        ArrayList<Attempt> courseAttempts = student.getCourseAttempts();
        double GPA = 0.0;
        int GPACourses = 0;
        int gradeInt = 0;

        for(Attempt attempt : courseAttempts)
        {
            String[] courseCodeParts = attempt.getCourse().getCourseCode().split("\\*");
            if(courseCodeParts[0].equals("CIS"))
            {
                try
                {
                    gradeInt = Integer.parseInt(attempt.getCourseGrade());
                    GPACourses++;
                    GPA += gradeInt;
                }
                catch(Exception e)
                {

                }
            }
        }

        GPA /= GPACourses;

        return GPA;
    }

    public double lastTenGPA(Student student)
    {
        ArrayList<Attempt> courseAttempts = student.getCourseAttempts();
        double GPA = 0.0;
        int GPACourses = 0;
        int gradeInt = 0;

        for(int i = courseAttempts.size() - 1; i > 0; i--)
        {
            try
            {
                gradeInt = Integer.parseInt(courseAttempts.get(i).getCourseGrade());
                GPACourses++;
                GPA += gradeInt;
            }
            catch(Exception e)
            {

            }
            if(GPACourses == 10)
            {
                break;
            }
        }

        if(GPACourses == 10)
        {
            GPA /= GPACourses;

            return GPA;
        }
        return -1;
    }

}
