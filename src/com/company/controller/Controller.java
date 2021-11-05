package com.company.controller;

import com.company.exceptions.NoPlacesLeftForCourseException;
import com.company.exceptions.NumberOfCreditsExceededException;
import com.company.exceptions.StudentAlreadyEnrolledException;
import com.company.model.Course;
import com.company.model.RegistrationSystem;
import com.company.model.Student;
import com.company.model.Teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Controller class
 */
public class Controller {
    /*
     * We will need a list of all the possible
     * classes in order to iterate through
     * the needed arrays/parameters
     */
    private List<Course> courseList;
    private List<Student> studentList;
    private List<Teacher> teacherList;
    private RegistrationSystem registrationSystem;

    /**
     * Constructor
     * @param courseList list of courses
     * @param studentList list of students
     * @param teacherList list of teachers
     */
    public Controller(List<Course> courseList, List<Student> studentList, List<Teacher> teacherList){
        this.courseList = courseList;
        this.studentList = studentList;
        this.teacherList = teacherList;
        this.registrationSystem = new RegistrationSystem(courseList, studentList, teacherList);
    }

    /**
     * Getters and setters
     * @return the specific value
     */
    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public RegistrationSystem getRegistrationSystem() {
        return registrationSystem;
    }

    public void setRegistrationSystem(RegistrationSystem registrationSystem) {
        this.registrationSystem = registrationSystem;
    }

    /**
     * Register the student to the course
     * @param course course
     * @param student student
     * @return true if possible
     */
    public boolean register(Course course, Student student) throws NoPlacesLeftForCourseException,
            NumberOfCreditsExceededException, StudentAlreadyEnrolledException{
       return this.registrationSystem.register(course, student);
    }

    /**
     * @return Courses with free places
     */
    public List<Course> retrieveCoursesWithFreePlaces(){
        return this.registrationSystem.retrieveCoursesWithFreePlaces();
    }

    /**
     * @param course course
     * @return enrolled students to the specified course
     */
    public List<Student> retrieveStudentsEnrolledForACourse(Course course){
        return this.registrationSystem.retrieveStudentsEnrolledForACourse(course);
    }

    /**
     * @return all the courses
     */
    public List<Course> getAllCourses(){
        return this.registrationSystem.getAllCourses();
    }

    /**
     * If a setCredit setter is called from the
     * Course class, then modify all the students
     * credits respectively to modified value.
     * @param oldCredits old credit value for the course
     * @param newCredits new credit value
     * @param modifiedCourse the course that has been changed
     */
    public void updateCredits(int oldCredits, int newCredits, Course modifiedCourse){
        this.registrationSystem.updateCredits(oldCredits, newCredits, modifiedCourse);
    }

    /**
     * Remove the students from the course
     * which has been removed by the professor
     * @param removedCourse the course that has been removed by the professor
     */
    public void removeStudents(Course removedCourse){
        this.registrationSystem.removeStudents(removedCourse);
    }

    /**
     * Sort the students and print the list by a given criteria(id, numberOfCredits, Name)
     * @param criteria the used criteria of sorting
     */
    public void sortStudents(String criteria){

        /*
         * We are trying to not interfere with the original list,
         * therefore we are creating an auxiliaryList
         */
        List<Student> auxiliaryList = new ArrayList<>();
        auxiliaryList = studentList;

        //If criteria = "id"
        if(criteria=="id") {
            auxiliaryList.sort((Comparator.comparing(Student::getStudentId)));
        }
        //If criteria="credit"
        if(criteria=="credit"){
            auxiliaryList.sort((Comparator.comparing(Student::getTotalCredits)));
        }
        //If criteria="name"
        if(criteria=="name"){
            auxiliaryList.sort((Comparator.comparing(Student::getFirstName)));
        }

        //Print the sorted list
        for(Student stud:auxiliaryList){
            System.out.println(stud.toString());
        }
        System.out.println("==============================================");
    }

    /**
     * Sort the courses and print the list by a given criteria(id, numberOfCredits, Name)
     * @param criteria the used sorting criteria
     */
    public void sortCourses(String criteria){

        /*
         * We are trying to not interfere with the original list,
         * therefore we are creating an auxiliaryList
         */
        List<Course> auxiliaryList = new ArrayList<>();
        auxiliaryList = courseList;

        //If criteria = "name"
        if(criteria=="name") {
            auxiliaryList.sort((Comparator.comparing(Course::getName)));
        }
        //If criteria="enroll"
        if(criteria=="enroll"){
            auxiliaryList.sort((Comparator.comparing(Course::getMaxEnrollment)));
        }
        //If criteria="teacher"
        if(criteria=="credit"){
            auxiliaryList.sort((Comparator.comparing(Course::getCredits)));
        }

        //Print the sorted list
        for(Course course:auxiliaryList){
            System.out.println(course.toString());
        }
        System.out.println("==============================================");
    }


    /**
     * Filter the students and print the list by a given criteria(> or < than the given value)
     * @param biggerOrSmaller the given sign (< or >)
     * @param value the given value
     */
    public void filterStudents(String biggerOrSmaller, int value) {

        /*
         * We are trying to not interfere with the original list,
         * therefore we are creating an auxiliaryList
         */
        List<Student> auxiliaryList = new ArrayList<>();
        auxiliaryList = studentList;

        //If sign is >
        if (biggerOrSmaller == ">") {
            auxiliaryList.stream()
                    .filter(student -> student.getTotalCredits() > value)
                    .map(studentMap -> studentMap.toString())
                    .forEach(System.out::println);
            System.out.println("==============================================");
        }

        //If sign is <
        if (biggerOrSmaller == "<") {
            auxiliaryList.stream()
                    .filter(student -> student.getTotalCredits() < value)
                    .map(studentMap -> studentMap.toString())
                    .forEach(System.out::println);
            System.out.println("==============================================");
        }

    }


    /**
     * Filter the courses and print the list by a given criteria(> or < than the given value)
     * @param biggerOrSmaller the given sign (< or >)
     * @param value the given value
     */
    public void filterCourses(String biggerOrSmaller, int value) {

        /*
         * We are trying to not interfere with the original list,
         * therefore we are creating an auxiliaryList
         */
        List<Course> auxiliaryList = new ArrayList<>();
        auxiliaryList = courseList;

        //If sign is >
        if (biggerOrSmaller == ">") {
            auxiliaryList.stream()
                    .filter(student -> student.getCredits() > value)
                    .map(studentMap -> studentMap.toString())
                    .forEach(System.out::println);
            System.out.println("==============================================");
        }

        //If sign is <
        if (biggerOrSmaller == "<") {
            auxiliaryList.stream()
                    .filter(student -> student.getCredits() < value)
                    .map(studentMap -> studentMap.toString())
                    .forEach(System.out::println);
            System.out.println("==============================================");
        }

    }
}


