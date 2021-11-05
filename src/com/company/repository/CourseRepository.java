package com.company.repository;

import com.company.model.Course;
import com.company.model.RegistrationSystem;

/**
 * Repository to control the Course class
 */
public class CourseRepository extends InMemoryRepository<Course>{

    /*Instance of regSys*/
    RegistrationSystem regSys = new RegistrationSystem();

    public CourseRepository()
    {
        super();
    }

    /**
     * Overriding the update function
     * @param obj object
     * @return the modified object
     */
    @Override
    public Course update(Course obj) {

        /* Find the course by ID*/
        Course courseToUpdate = this.repoList.stream()
                .filter(course -> course.getName() == obj.getName())
                .findFirst()
                .orElseThrow();

        /*Update the new values*/
        courseToUpdate.setMaxEnrollment(obj.getMaxEnrollment());
        courseToUpdate.setTeacher(obj.getTeacher());
        courseToUpdate.setName(obj.getName());
        courseToUpdate.setStudentsEnrolled(obj.getStudentsEnrolled());

        /*When the credits are updated, the students credits
        also should be updated. Therefore, we are using regSys
         */
        regSys.updateCredits(courseToUpdate.getCredits(), obj.getCredits(), courseToUpdate);

        courseToUpdate.setCredits(obj.getCredits());

        return courseToUpdate;
    }
}
