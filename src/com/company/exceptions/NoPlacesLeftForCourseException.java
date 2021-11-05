package com.company.exceptions;

/**
 * Exception class to warn users in case there are no places left for the selected course
 */
public class NoPlacesLeftForCourseException extends Exception{
    public NoPlacesLeftForCourseException(String errorMessage)
    {
        super(errorMessage);
    }
}
