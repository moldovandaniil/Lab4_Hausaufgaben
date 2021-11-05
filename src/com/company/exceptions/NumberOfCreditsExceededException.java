package com.company.exceptions;

/**
 * Exception class to warn users in case the number of credits is exceeded
 */
public class NumberOfCreditsExceededException extends Exception{
    public NumberOfCreditsExceededException(String errorMessage)
    {
        super(errorMessage);
    }
}
