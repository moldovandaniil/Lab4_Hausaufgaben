package com.company.test;

/**
 * Importing junit for assert tests
 */

import com.company.consoleView.ConsoleView;
import com.company.controller.Controller;
import com.company.exceptions.NoPlacesLeftForCourseException;
import com.company.exceptions.NumberOfCreditsExceededException;
import com.company.exceptions.StudentAlreadyEnrolledException;
import com.company.repository.CourseRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;
import com.company.model.Course;
import com.company.model.RegistrationSystem;
import com.company.model.Student;
import com.company.model.Teacher;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AssertTest {
    @org.junit.Test
    public void testMethod() throws NumberOfCreditsExceededException, StudentAlreadyEnrolledException, NoPlacesLeftForCourseException {
        /*Creating three repositories for each class*/
        CourseRepository courseRepository = new CourseRepository();
        StudentRepository studentRepository = new StudentRepository();
        TeacherRepository teacherRepository = new TeacherRepository();

        /*Creating three teacher examples*/
        Teacher teacher1 = new Teacher("Alex", "Unu", new ArrayList<>());
        Teacher teacher2 = new Teacher("Andrei", "Doi", new ArrayList<>());
        Teacher teacher3 = new Teacher("Mihai", "Trei", new ArrayList<>());

        /*Adding the teachers to the repositories*/
        teacherRepository.create(teacher1);
        teacherRepository.create(teacher2);
        teacherRepository.create(teacher3);

        /*Creating five students*/
        Student student1 = new Student("Alfa", "Alfaru",
                                1234567, 0, new ArrayList<>()) ;
        Student student2 = new Student("Beta", "Betaru",
                                1234568, 24,new ArrayList<>());
        Student student3 = new Student("Teta", "Tetaru",
                                1234569, 0, new ArrayList<>());
        Student student4 = new Student("Omega", "Megaru",
                                1234563, 0, new ArrayList<>());
        Student student5 = new Student("Gamma", "Gammaru",
                                1234564, 0, new ArrayList<>());

        /*Adding the students to the repositories*/
        studentRepository.create(student1);
        studentRepository.create(student2);
        studentRepository.create(student3);
        studentRepository.create(student4);
        studentRepository.create(student5);

        /*Creating three courses*/
        Course course1 = new Course("Mate", teacher1, 2, new ArrayList<>(), 8);
        Course course2 = new Course("Info", teacher2, 5, new ArrayList<>(), 5);
        Course course3 = new Course("Geo", teacher3, 5, new ArrayList<>(), 5);

        /*Adding the courses to the repositories
        and attributing courses to teachers*/
        courseRepository.create(course1);
        teacher1.addCourse(course1);
        courseRepository.create(course2);
        teacher2.addCourse(course2);
        courseRepository.create(course3);
        teacher3.addCourse(course3);



        /*Creating an instance of Registration System*/
        Controller controller = new Controller(courseRepository.getAll(),
                                                            studentRepository.getAll(), teacherRepository.getAll());

        /*FIRST ASSERT EQUAL TEST*/


        /*Enrolling student1 to course1*/
        try{
            assertEquals(controller.register(courseRepository.getAll().get(0),
                    studentRepository.getAll().get(0)), true);
        }
        catch(NoPlacesLeftForCourseException |NumberOfCreditsExceededException
                |StudentAlreadyEnrolledException error)
        {
            System.out.println("An error occured!");
            System.out.println(error);
        }

        /*Checking whether 8 credits were attributed to the student*/
        assertEquals(studentRepository.getAll().get(0).getTotalCredits(), 8);

        /*Testing whether the highly mentioned course is listed in
        the student's enrolled courses list
         */
        assertEquals(studentRepository.getAll().get(0).getEnrolledCourses().get(0).getName(),
                    courseRepository.getAll().get(0).getName());

        /*Vice-versa test from the above*/
        assertEquals(courseRepository.getAll().get(0).getStudentsEnrolled().get(0),
                        studentRepository.getAll().get(0));


        /*SECOND ASSERT EQUAL TEST*/

        /*Student 2 has 24 credits already, so enrolling him to
        the first course (8 credits) should not be possible due
        to the 30 credit limit. Assert should be towards false
         */
        try {
            assertEquals(controller.register(courseRepository.getAll().get(0),
                    studentRepository.getAll().get(1)), false);
        }
        catch(NoPlacesLeftForCourseException |NumberOfCreditsExceededException
                |StudentAlreadyEnrolledException error)
        {
            System.out.println("An error occured!");
            System.out.println(error);
        }


        /*Checking whether the number of credits remained unchanged*/
        assertEquals(studentRepository.getAll().get(1).getTotalCredits(), 24);


        /*THIRD ASSERT EQUAL TEST*/

        /*Student is already enrolled! Should give error*/

        try{
            assertEquals(controller.register(courseRepository.getAll().get(0),
                    studentRepository.getAll().get(0)), false);
        }
        catch(NoPlacesLeftForCourseException |NumberOfCreditsExceededException
                |StudentAlreadyEnrolledException error)
        {
            System.out.println("An error occured!");
            System.out.println(error);
        }

        /*FOURTH ASSERT EQUAL TEST*/

        /*Testing the limit of the possible registered students to the course*/
        try{
            assertEquals(controller.register(courseRepository.getAll().get(0),
                    studentRepository.getAll().get(2)), true);
        }
        catch(NoPlacesLeftForCourseException |NumberOfCreditsExceededException
                |StudentAlreadyEnrolledException error)
        {
            System.out.println("An error occured!");
            System.out.println(error);
        }

        /*Due to the fact that the max number of possible students is 2
        this statement should equal false, warn the student and show the
        available courses
         */
        try {
            assertEquals(controller.register(courseRepository.getAll().get(0),
                    studentRepository.getAll().get(3)), false);
        }
        catch(NoPlacesLeftForCourseException |NumberOfCreditsExceededException
                |StudentAlreadyEnrolledException error)
        {
            System.out.println("An error occured!");
            System.out.println(error);
        }

        /*FIFTH ASSERT EQUAL TEST*/

        /*Modifying the first course (credits 8 -> 4)*/
        Course modifiedCourse1 = new Course("Mate", teacher1, 2, course1.getStudentsEnrolled(), 4);
        /*Updating it via repository*/
        courseRepository.update(modifiedCourse1);
        /*Now each enrolled student should have 4 instead of 8 credits*/
        assertEquals(studentRepository.getAll().get(0).getTotalCredits(), 4);


        /*FINAL ASSERT EQUAL TEST*/

        /*Modifying teacher 4 (No Courses)*/
        Teacher modifiedTeacher1 = new Teacher("Alex", "Unu", new ArrayList<>());
        /*Updating it via repository*/
        teacherRepository.update(modifiedTeacher1);
        /*Now the course that the teacher has been previously assigned to
        and removed recently should have NO students enrolled to it
         */
        assertEquals(courseRepository.getAll().get(0).getStudentsEnrolled().size(), 0);


        controller.sortStudents("name");

        controller.sortCourses("credit");

        controller.filterStudents(">", 1);

        controller.filterCourses(">", 1);

        ConsoleView consoleView = new ConsoleView(courseRepository.getAll(), studentRepository.getAll(), teacherRepository.getAll());
        consoleView.mainMenu();

    }
}
