import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * A concrete class for the Bachelor of General Computing degree type
 *
 *
 *
 * @author O'Neal Yako
 * @version 10/25/18
 */
public class BCG extends GeneralDegree
{
    private static final double oneSubjectCredits = 11.00;
    private static final double max1000LvlCredits = 6.00;
    private static final double req3000orHigherCredits = 4.00;
    private static final double reqCisStat2000Credits = 0.50;
    private static final double reqScienceCredits = 2.00;
    private static final double reqArtsSocSciCredits = 2.00;
    ArrayList<Course> requirements;

    public BCG()
    {
        super();
        degreeTitle = "BCG";
        initializeRequired("BCGrequired.csv");
    }
    
    public boolean meetsRequirements(Student student)
    {
        double creditsSubject = 0.0;
        double credits1000 = 0.0;
        double credits3000 = 0.0;
        double creditsCisStat2000 = 0.0;
        double reqSci = 0.0;
        double reqArtSocSci = 0.0;
        double totalCredits = 0.0;

        String[] courseCodeParts;

        int gradeInt = 0;

        ArrayList<Attempt> courseAttempts = student.getCourseAttempts();
        TreeMap<String, Course> plannedCourses = student.getCoursesPlanned();

        for(Attempt attempt : courseAttempts)
        {
            Course course = attempt.getCourse();

            try
            {
                gradeInt = Integer.parseInt(attempt.getCourseGrade());
            }
            catch(Exception e)
            {

            }

            if(attempt.getCourseStatus().toLowerCase().equals("inprogress") || gradeInt >= 50)//|| attempt.getCourseGrade().toUpperCase().equals("P"))
            {
                courseCodeParts = course.getCourseCode().split("\\*", 2);
                if(courseCodeParts[0].equals("CIS"))
                {
                    creditsSubject += course.getCourseCredit();
                    reqSci += course.getCourseCredit();
                }
                if(Double.parseDouble(courseCodeParts[1]) < 2000)
                {
                    credits1000 += course.getCourseCredit();
                }
                if(Double.parseDouble(courseCodeParts[1]) >= 3000)
                {
                    credits3000 += course.getCourseCredit();
                }
                if((courseCodeParts[0].equals("CIS") || courseCodeParts[0].equals("STAT")) && Double.parseDouble(courseCodeParts[1]) >= 2000)
                {
                    creditsCisStat2000 += course.getCourseCredit();
                }
                try
                {
                    ARTS isArtCredit = ARTS.valueOf(courseCodeParts[0]);
                    reqArtSocSci += course.getCourseCredit();
                }
                catch(Exception e)
                {
                }
                if(creditsSubject < oneSubjectCredits && credits1000 < max1000LvlCredits)
                {
                    totalCredits += course.getCourseCredit();
                }
            }
        }
        for(Course course : plannedCourses.values())
        {
            courseCodeParts = course.getCourseCode().split("\\*", 2);
            if(courseCodeParts[0].equals("CIS"))
            {
                creditsSubject += course.getCourseCredit();
                reqSci += course.getCourseCredit();
            }
            if(Double.parseDouble(courseCodeParts[1]) < 2000)
            {
                credits1000 += course.getCourseCredit();
            }
            if(Double.parseDouble(courseCodeParts[1]) >= 3000)
            {
                credits3000 += course.getCourseCredit();
            }
            if((courseCodeParts[0].equals("CIS") || courseCodeParts[0].equals("STAT")) && Double.parseDouble(courseCodeParts[1]) >= 2000)
            {
                creditsCisStat2000 += course.getCourseCredit();
            }
            try
            {
                ARTS isArtCredit = ARTS.valueOf(courseCodeParts[0]);
                reqArtSocSci += course.getCourseCredit();
            }
            catch(Exception e)
            {
            }
            if(creditsSubject < oneSubjectCredits && credits1000 < max1000LvlCredits)
            {
                totalCredits += course.getCourseCredit();
            }
        }
        if(totalCredits >= requiredCredits && credits3000 >= req3000orHigherCredits && creditsCisStat2000 >= reqCisStat2000Credits && reqArtSocSci >= reqArtsSocSciCredits && reqSci >= reqScienceCredits && credits1000 <= max1000LvlCredits && creditsSubject <= oneSubjectCredits)
        {
            return true;
        }
        else
        {
            return false;
        }
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
                    /*if(attempt.getCourseGrade().equals("P"))
                    {
                        totalCredits += course.getCourseCredit();
                    }*/
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
        double lastTenCredits = 0.0;
        int gradeInt = 0;

        for(int i = courseAttempts.size() - 1; i > 0; i--)
        {
            try
            {
                gradeInt = Integer.parseInt(courseAttempts.get(i).getCourseGrade());
                GPACourses++;
                lastTenCredits += courseAttempts.get(i).getCourse().getCourseCredit();
                GPA += gradeInt;
            }
            catch(Exception e)
            {

            }
            if(GPACourses >= 10)
            {
                break;
            }
        }

        if(lastTenCredits >= 10)
        {
            GPA /= GPACourses;

            return GPA;
        }
        return -1;
    }
}

enum ARTS
{
    MUSC, AVC, ASCI, ARTH, CLAS
}
