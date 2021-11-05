package com.company;

import com.company.consoleView.ConsoleView;
import com.company.exceptions.NoPlacesLeftForCourseException;
import com.company.exceptions.NumberOfCreditsExceededException;
import com.company.exceptions.StudentAlreadyEnrolledException;
import com.company.test.AssertTest;

public class Main {

    public static void main(String[] args) throws NumberOfCreditsExceededException, StudentAlreadyEnrolledException, NoPlacesLeftForCourseException {
        AssertTest assertTest = new AssertTest();
        assertTest.testMethod();


    }
}
