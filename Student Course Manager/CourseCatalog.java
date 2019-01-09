import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;


public class CourseCatalog
{
    private ArrayList<Course> availableCourses;

    /**
     * Constructor for CourseCatalog class
     */
    public CourseCatalog()
    {
        availableCourses = new ArrayList<>();
    }

    /**
     *
     * @return availableCourses
     */
    public ArrayList<Course> getAvailableCourses()
    {
        return availableCourses;
    }

    /**
     * Reads from a CSV file and initializes the CourseCatalog of all
     * courses available for the user
     * @param filename
     */
    public void initializeCatalog(String filename)
    {
        availableCourses.clear();
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
                        for(Course course : availableCourses)
                        {
                            if(course.getCourseCode().equals(splitPrereq[i].trim()))
                            {
                                prereq.add(course);
                            }
                        }
                    }
                }
                readCourse.setPrerequisites(prereq);
                addCourse(readCourse);

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
     * Adds a course to the courseCatalog
     * @param toAdd
     */
    public void addCourse(Course toAdd)
    {
        if(!availableCourses.contains(toAdd))
        {
            availableCourses.add(toAdd);
        }
    }

    /**
     * Removes a course from the courseCatalog
     *
     *
     * @param toRemove
     */
    public void removeCourse(Course toRemove)
    {
        for (Course course : availableCourses)
        {
            if (course.equals(toRemove))
            {
                availableCourses.remove(course);
                return;
            }
        }
    }

    public void saveCatalog()
    {

    }

    /**
     * Finds and returns a course in the course catalog
     *
     * 
     * @param courseCode
     * @return course
     */
    public Course findCourse(String courseCode)
    {
        for(Course course : availableCourses)
        {
            if(course.getCourseCode().equals(courseCode))
            {
                return course;
            }
        }
        return null;
    }

    /**
     * Overrides the toString() function and converts the course catalog to a
     * string of all available courses and their prerequisite course Codes
     * @return printThis
     */
    public String toString()
    {
        String printThis = "";

        for(Course course : availableCourses)
        {
            printThis += course.getCourseCode() + " - " + course.getCourseTitle() + " - " + course.getCourseCredit() + " - " + course.getSemesterOffered();
            if(!course.getPrerequisites().isEmpty())
            {
                printThis += " - ";
            }
            for(Course prereq : course.getPrerequisites())
            {
                printThis += prereq.getCourseCode();
                if(course != availableCourses.get(availableCourses.size() - 1))
                {
                    printThis += ", ";
                }
            }
            printThis += "\n";
        }

        return printThis;
    }
}
