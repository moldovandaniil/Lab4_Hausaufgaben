package com.company.model;

import java.util.List;

/**
 * Course class
 */
public class Course {
    private String name;
    private Person teacher;
    private int maxEnrollment;
    private List<Student> studentsEnrolled;
    private int credits;

    /**
     * Constructor for the Course class
     */
    public Course(String name, Person teacher, int maxEnrollment,
                    List<Student> studentsEnrolled, int credits) {
        this.name = name;
        this.teacher = teacher;

        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled = studentsEnrolled;
        this.credits = credits;
    }

    /**
     * Getters and setters
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(List<Student> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * @return toString
     */
    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", teacher=" + teacher.getFirstName() + " "+teacher.getLastName()+
                ", maxEnrollment=" + maxEnrollment +
                ", studentsEnrolled=" + printCourseNames() +
                ", credits=" + credits +
                '}';
    }

    /**
     * Add a student to the student list
     */
    public void addStudent(Student student)
    {
        this.studentsEnrolled.add(student);

        /*
         * Add to the specified student the
         * course he/she has been enrolled to
         */
        student.addCourse(this);
    }

    /**
     * function to help the toString method to print the enrolled students manually, preventing
     * stackoverflow error
     * @return
     */
    public String printCourseNames()
    {
        String names = "";
        for(Student c:studentsEnrolled)
        {
            names+=c.getFirstName()+" "+c.getLastName()+" : ";
        }
        if(names=="")
            return "NONE";
        return names;

    }
}
