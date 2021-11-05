package com.company.model;


import java.util.List;

/**
 * Teacher Class extends Person Class
 */
public class Teacher extends Person{
    private List<Course> courses;

    /**
     * Constructor with calling the super()
     * from Person class
     */
    public Teacher(String firstName, String lastName, List<Course> courses) {
        super(firstName, lastName);
        this.courses=courses;
    }

    /**
     * Getters and Setters
     */
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Remove a course
     * @param course the course that should be removed
     */
    public void deleteCourse(Course course)
    {
        courses.remove(course);
    }

    public void addCourse(Course course)
    {
        courses.add(course);
    }

    /**
     * To string version
     * @return toString version
     */
    @Override
    public String toString() {
        return "Teacher{" +
                ", Name="+ getFirstName()+
                ", Surname="+getLastName()+
                ", courses=" + printCourseNames() +
                '}';
    }


    /**
     * function to help the toString method to print the courses manually, preventing
     * stackoverflow error
     * @return
     */
    public String printCourseNames()
    {
        String names = "";
        for(Course c:courses)
        {
            names+=c.getName()+" ";
        }
        if(names=="")
            return "NONE";
        return names;

    }

}
