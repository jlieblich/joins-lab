package com.jonathanlieblich.myapplication;

/**
 * Created by jonlieblich on 10/28/16.
 */

public class Job {
    private String mSsn, mCompany;
    private int mSalary, mExperience;

    public Job(String ssn, String company, int salary, int experience) {
        mSsn = ssn;
        mCompany = company;
        mSalary = salary;
        mExperience = experience;
    }

    public String getSsn() {
        return mSsn;
    }

    public String getCompany() {
        return mCompany;
    }

    public int getSalary() {
        return mSalary;
    }

    public int getExperience() {
        return mExperience;
    }
}
