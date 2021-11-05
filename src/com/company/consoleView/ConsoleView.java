package com.company.consoleView;

import com.company.controller.Controller;
import com.company.exceptions.NoPlacesLeftForCourseException;
import com.company.exceptions.NumberOfCreditsExceededException;
import com.company.exceptions.StudentAlreadyEnrolledException;
import com.company.model.Course;
import com.company.model.RegistrationSystem;
import com.company.model.Student;
import com.company.model.Teacher;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.List;
import java.util.Scanner;

/**
 * class for the console view
 */
public class ConsoleView {
    private List<Course> courseList;
    private List<Student> studentList;
    private List<Teacher> teacherList;
    private Controller controller;
    Scanner scanner = new Scanner(System.in);


    public ConsoleView(List<Course> courseList, List<Student> studentList, List<Teacher> teacherList) {
        this.courseList = courseList;
        this.studentList = studentList;
        this.teacherList = teacherList;
        this.controller = new Controller(courseList, studentList, teacherList);
    }

    /**
     * error message when the input is wrong
     */
    public void errorMessage() {
        spacing();
        System.out.println("ERROR! WRONG INPUT!");
        spacing();
    }

    public void spacing() {
        System.out.println();
        System.out.println("==============================================");
        System.out.println("==============================================");
        System.out.println();
    }

    public void success() throws NumberOfCreditsExceededException, StudentAlreadyEnrolledException, NoPlacesLeftForCourseException {
        System.out.println("SUCCESS!");
        System.out.println();
        System.out.println("RETURNING TO THE MAIN MENU");
        mainMenu();
    }


    /**
     * prints main menu
     */
    public void mainMenu() throws NumberOfCreditsExceededException, StudentAlreadyEnrolledException, NoPlacesLeftForCourseException {

        //main interface

        spacing();
        System.out.println("          WELCOME TO THE MAIN MENU");
        System.out.println("    PLEASE SELECT ONE OF THE OPTIONS BELOW");
        System.out.println();
        System.out.println("1. Register a student to a course");
        System.out.println("2. Retrieve the courses with a free place");
        System.out.println("3. Retrieve all available courses");
        System.out.println("4. Retrieve students enrolled for a course");
        System.out.println("5. Sort the list of the students by a given parameter");
        System.out.println("6. Sort the list of the courses by a given parameter");
        System.out.println("7. Filter the list of the students by a given parameters");
        System.out.println("8. Filter the list of the courses by a given parameters");
        System.out.println();
        System.out.println();
        System.out.println("YOUR INPUT: ");
        int answer = scanner.nextInt();
        spacing();

        //filter out the result with a case switch
        switch (answer) {
            case 1:
                registerAStudent();
                break;
            case 2:
                retrieveCoursesWithFreePlace();
                break;
            case 3:
                retrieveAllCourse();
                break;
            case 4:
                retrieveStudentsEnrolledForACourse();
                break;
            case 5:
                sortStudentList();
                break;
            case 6:
                sortCoursesList();
                break;
            case 7:
                filterStudentList();
                break;
            case 8:
                filterCourseList();
                break;
            default:
                errorMessage();
                break;
        }



    }

    /**
     * Registers a student to a given course
     */
    public void registerAStudent() throws NumberOfCreditsExceededException, StudentAlreadyEnrolledException, NoPlacesLeftForCourseException {
        spacing();
        System.out.println("    YOU HAVE SELECTED REGISTERING A STUDENT");
        System.out.println("        PLEASE COMPLETE THE DATA BELOW");
        System.out.println();

        //iterate through the student list to let the user choose one

        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(i + ". " + studentList.get(i).getFirstName() + " " + studentList.get(i).getLastName());
        }
        System.out.println();
        System.out.println("YOUR INPUT: ");
        int studentAnswer = scanner.nextInt();
        spacing();

        if (studentAnswer > studentList.size() || studentAnswer < 1) {
            errorMessage();
        }

        spacing();
        System.out.printf("SELECT A COURSE YOU WANT TO ENROLL THE STUDENT IN:");
        System.out.println();

        //iterate through the course list to enroll the student to it

        for (int i = 0; i < courseList.size(); i++) {
            System.out.println(i + ". " + courseList.get(i).getName());
        }
        System.out.println();
        System.out.println("YOUR INPUT: ");
        int courseAnswer = scanner.nextInt();
        spacing();

        if (courseAnswer > courseList.size() || courseAnswer < 1) {
            errorMessage();
        }

        if (controller.register(courseList.get(courseAnswer), studentList.get(studentAnswer))) {
            spacing();
            success();
        }

    }

    /**
     * prints out courses with free places
     */
    public void retrieveCoursesWithFreePlace() throws NumberOfCreditsExceededException, StudentAlreadyEnrolledException, NoPlacesLeftForCourseException {
        spacing();
        System.out.println("     YOU HAVE SELECTED TO SEE FREE COURSES");
        System.out.println();
        controller.retrieveCoursesWithFreePlaces();
        spacing();
        success();
    }

    /**
     * prints out all the courses
     */
    public void retrieveAllCourse() throws NumberOfCreditsExceededException, StudentAlreadyEnrolledException, NoPlacesLeftForCourseException {
        spacing();
        System.out.println("     YOU HAVE SELECTED TO SEE ALL COURSES");
        System.out.println();
        controller.getAllCourses();
        spacing();
        success();
    }

    /**
     * prints out all the students enrolled for a specific course
     */
    public void retrieveStudentsEnrolledForACourse() throws NumberOfCreditsExceededException, StudentAlreadyEnrolledException, NoPlacesLeftForCourseException {
        spacing();
        System.out.println("   YOU HAVE SELECTED TO SEE ENROLLED STUDENTS");
        System.out.println("        PLEASE COMPLETE THE DATA BELOW");
        System.out.println();

        System.out.printf("SELECT THE COURSE:");
        System.out.println();

        //iterate through the course list to enroll the student to it

        for (int i = 0; i < courseList.size(); i++) {
            System.out.println(i + ". " + courseList.get(i).getName());
        }
        System.out.println();
        System.out.println("YOUR INPUT: ");
        int courseAnswer = scanner.nextInt();
        spacing();

        if (courseAnswer > courseList.size() || courseAnswer < 1) {
            errorMessage();
        }

        controller.retrieveStudentsEnrolledForACourse(courseList.get(courseAnswer));
        spacing();
        success();

    }

    /**
     * Sort student list by a given criteria
     */
    public void sortStudentList() throws NumberOfCreditsExceededException, StudentAlreadyEnrolledException, NoPlacesLeftForCourseException {
        spacing();
        System.out.println("   YOU HAVE SELECTED TO SORT THE STUDENT LIST");
        System.out.println("          PLEASE SELECT THE CRITERIA");
        System.out.println();
        System.out.println("1. ID");
        System.out.println("2. CREDITS");
        System.out.println("3. NAME");
        System.out.println();
        System.out.println();
        System.out.println("YOUR INPUT: ");
        int answer = scanner.nextInt();
        spacing();

        switch (answer) {
            case 1:
                controller.sortStudents("id");
                spacing();
                success();
                break;
            case 2:
                controller.sortStudents("credit");
                spacing();
                success();
                break;
            case 3:
                controller.sortStudents("name");
                spacing();
                success();
                break;
            default:
                errorMessage();
                spacing();
                success();
                break;

        }
    }


    /**
     * Sort course list by a given criteria
     */
    public void sortCoursesList() throws NumberOfCreditsExceededException, StudentAlreadyEnrolledException, NoPlacesLeftForCourseException {
        spacing();
        System.out.println("   YOU HAVE SELECTED TO SORT THE COURSES LIST");
        System.out.println("          PLEASE SELECT THE CRITERIA");
        System.out.println();
        System.out.println("1. NAME");
        System.out.println("2. MAXENROLLMENT");
        System.out.println("3. CREDITS");
        System.out.println();
        System.out.println();
        System.out.println("YOUR INPUT: ");
        int answer = scanner.nextInt();
        spacing();

        switch (answer) {
            case 1:
                controller.sortCourses("name");
                spacing();
                success();
                break;
            case 2:
                controller.sortCourses("enroll");
                spacing();
                success();
                break;
            case 3:
                controller.sortCourses("credit");
                spacing();
                success();
                break;
            default:
                errorMessage();
                spacing();
                success();
                break;

        }
    }

    /**
     * Filter student list by a given criteria
     */
    public void filterStudentList() throws NumberOfCreditsExceededException, StudentAlreadyEnrolledException, NoPlacesLeftForCourseException {
        spacing();
        System.out.println("   YOU HAVE SELECTED TO FILTER THE STUDENT LIST");
        System.out.println("          PLEASE SELECT THE CRITERIA");
        System.out.println();
        System.out.println("1. MORE CREDITS THAN ");
        System.out.println("2. LESS CREDITS THAN ");
        System.out.println();
        System.out.println();
        System.out.println("YOUR INPUT: ");
        int answer = scanner.nextInt();
        System.out.println("VALUE: ");
        int value = scanner.nextInt();
        spacing();

        switch (answer) {
            case 1:
                controller.filterStudents(">", value);
                spacing();
                success();
                break;
            case 2:
                controller.filterStudents("<", value);
                spacing();
                success();
                break;
            default:
                errorMessage();
                spacing();
                success();
                break;

        }
    }


    /**
     * Filter course list by a given criteria
     */
    public void filterCourseList() throws NumberOfCreditsExceededException, StudentAlreadyEnrolledException, NoPlacesLeftForCourseException {
        spacing();
        System.out.println("   YOU HAVE SELECTED TO FILTER THE COURSE LIST");
        System.out.println("          PLEASE SELECT THE CRITERIA");
        System.out.println();
        System.out.println("1. MORE CREDITS THAN ");
        System.out.println("2. LESS CREDITS THAN ");
        System.out.println();
        System.out.println();
        System.out.println("YOUR INPUT: ");
        int answer = scanner.nextInt();
        System.out.println("VALUE: ");
        int value = scanner.nextInt();
        spacing();

        switch (answer) {
            case 1:
                controller.filterStudents(">", value);
                spacing();
                success();
                break;
            case 2:
                controller.filterStudents("<", value);
                spacing();
                success();
                break;
            default:
                errorMessage();
                spacing();
                success();
                break;

        }
    }



}