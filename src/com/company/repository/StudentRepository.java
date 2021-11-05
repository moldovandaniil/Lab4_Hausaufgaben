package com.company.repository;

import com.company.model.RegistrationSystem;
import com.company.model.Student;


/**
 * Repository to control the Student class
 */
public class StudentRepository extends InMemoryRepository<Student>{
    RegistrationSystem regSys;

    public StudentRepository()
    {
        super();
    }

    /**
     * Overriding the update function
     * @param obj object
     * @return the modified object
     */
    @Override
    public Student update(Student obj) {
        /* Find the course by ID*/
        Student studentToUpdate = this.repoList.stream()
                .filter(course -> course.getStudentId() == obj.getStudentId())
                .findFirst()
                .orElseThrow();

        /*Update the new values*/
        studentToUpdate.setFirstName(obj.getFirstName());
        studentToUpdate.setLastName(obj.getLastName());
        studentToUpdate.setTotalCredits(obj.getTotalCredits());
        studentToUpdate.setEnrolledCourses(obj.getEnrolledCourses());


        return studentToUpdate;
    }


}