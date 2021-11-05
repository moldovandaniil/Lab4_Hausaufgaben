package com.company.model;

import java.util.List;

/**
 * Class Students inherits the class Person
 */
public class Student extends Person{
    private long studentId;
    private int totalCredits;
    private List<Course> enrolledCourses;


    /**
     * Calling super for the Person
     * constructor parameters+additional
     * params for Student class
     */
    public Student(String firstName, String lastName, long studentId,
                   int totalCredits, List<Course> enrolledCourses) {

        super(firstName, lastName);
        this.studentId=studentId;
        this.totalCredits=totalCredits;
        this.enrolledCourses=enrolledCourses;
    }

    /**
     * Getters and Setters
     */
    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    /**
     * Add a course to the courses list
     */
    public void addCourse(Course course)
    {
        this.enrolledCourses.add(course);
    }
    public void removeCourse(Course course)
    {
        this.enrolledCourses.remove(course);
    }

    /**
     * Add credits (Or discharge credits).
     * @param credits number of added/discharged credits
     */
    public void addCredits(int credits)
    {
        this.setTotalCredits(this.totalCredits+credits);
    }


    /**
     * Returns to string
     * @return toString version
     */
    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", Name="+ getFirstName()+
                ", Surname="+getLastName()+
                ", totalCredits=" + totalCredits +
                ", enrolledCourses="+printCourseNames()+"}";

    }

    /**
     * function to help the toString method to print the courses manually, preventing
     * stackoverflow error
     * @return
     */
    public String printCourseNames()
    {
        String names = "";
        for(Course c:enrolledCourses)
        {
            names+=c.getName()+" ";
        }
        if(names=="")
            return "NONE";
        return names;

    }
}
