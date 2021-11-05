package com.company.exceptions;

/**
 * Exception class to warn users in case they are already enrolled in this course
 */
public class StudentAlreadyEnrolledException extends Exception{
    public StudentAlreadyEnrolledException(String errorMessage)
    {
        super(errorMessage);
    }
}
