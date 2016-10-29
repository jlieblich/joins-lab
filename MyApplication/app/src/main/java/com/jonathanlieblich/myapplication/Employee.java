package com.jonathanlieblich.myapplication;

/**
 * Created by jonlieblich on 10/28/16.
 */

public class Employee {
    private String mFirstName, mLastName, mCity, mSsn;
    private int mBirthYear;

    public Employee(String firstName, String lastName, String city,
                    String ssn, int birthYear) {
        mFirstName = firstName;
        mLastName = lastName;
        mCity = city;
        mSsn = ssn;
        mBirthYear = birthYear;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getSsn() {
        return mSsn;
    }

    public String getCity() {
        return mCity;
    }

    public int getBirthYear() {
        return mBirthYear;
    }
}
