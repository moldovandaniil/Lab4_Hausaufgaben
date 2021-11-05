package com.company.model;


/**
 * Creating a class Person that
 * will get extended by the Teacher
 * and Student class
 */
public class Person {
    private String firstName;
    private String lastName;

    /**
     * Default constructor for
     * @param firstName
     * @param lastName
     */
    public Person(String firstName, String lastName){
        this.firstName=firstName;
        this.lastName=lastName;
    }

    /**
     * Getters and setters
     */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns to string version
     * @return toString version
     */
    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
