package com.jonathanlieblich.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonlieblich on 10/28/16.
 */

public class Helper extends SQLiteOpenHelper {
    public static final String DB_NAME = "EMP_DATA";
    public static final int DB_VERSION = 1;

    public static final String TABLE_EMPLOYEE = "employee";
    public static final String TABLE_JOB = "job";

    public static final String COL_FIRST_NAME = "first_name";
    public static final String COL_LAST_NAME = "last_name";
    public static final String COL_CITY = "city";
    public static final String COL_SSN = "ssn";
    public static final String COL_BIRTH = "year_of_birth";
    public static final String COL_COMPANY = "company";
    public static final String COL_SALARY = "salary";
    public static final String COL_EXP = "experience";

    public static final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE "
            +TABLE_EMPLOYEE+" ("
            +COL_SSN+" TEXT PRIMARY KEY,"
            +COL_FIRST_NAME+" TEXT,"
            +COL_LAST_NAME+" TEXT,"
            +COL_BIRTH+" INT,"
            +COL_CITY+" INT)";

    public static final String CREATE_JOB_TABLE = "CREATE TABLE "
            +TABLE_JOB+" ("
            +COL_SSN+" TEXT PRIMARY KEY,"
            +COL_COMPANY+" TEXT,"
            +COL_SALARY+" INT,"
            +COL_EXP+" INT)";

    private static Helper sInstance;

    private Helper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static Helper getInstance(Context context) {
        if(sInstance == null) {
            sInstance = new Helper(context.getApplicationContext());
        }
        return sInstance;
    }

    public List<String> getAllNames() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_EMPLOYEE,
                new String[]{COL_FIRST_NAME,COL_LAST_NAME},
                null,
                null,
                null,
                null,
                null,
                null);

        List<String> all = new ArrayList<>();
        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                String first = cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME));
                String last = cursor.getString((cursor.getColumnIndex(COL_LAST_NAME)));
                all.add(first+" "+last);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return all;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_EMPLOYEE_TABLE);
        sqLiteDatabase.execSQL(CREATE_JOB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_EMPLOYEE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_JOB);
        onCreate(sqLiteDatabase);
    }

    public void addEmployee(Employee employee) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FIRST_NAME, employee.getFirstName());
        values.put(COL_LAST_NAME, employee.getLastName());
        values.put(COL_CITY, employee.getCity());
        values.put(COL_SSN, employee.getSsn());
        values.put(COL_BIRTH, employee.getBirthYear());
        db.insertOrThrow(TABLE_EMPLOYEE, null, values);
        db.close();
    }

    public void addJob(Job job) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_SSN, job.getSsn());
        values.put(COL_COMPANY, job.getCompany());
        values.put(COL_SALARY, job.getSalary());
        values.put(COL_EXP, job.getExperience());
        db.insertOrThrow(TABLE_JOB, null, values);
        db.close();
    }

    public List<String> nameByCompany() {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT "+COL_FIRST_NAME+","+COL_LAST_NAME+" FROM "+TABLE_EMPLOYEE
                +" JOIN "+TABLE_JOB+" ON "+TABLE_EMPLOYEE+"."+COL_SSN+" = "+TABLE_JOB+"."+COL_SSN
                +" WHERE "+COL_COMPANY+" = 'Macys'";
        Cursor cursor = db.rawQuery(query, null);

        List<String> names = new ArrayList<>();

        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                names.add(cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME))
                +" "+cursor.getString(cursor.getColumnIndex(COL_LAST_NAME)));
                cursor.moveToNext();
            }
            cursor.close();
            return names;
        }
        cursor.close();
        return null;
    }

    public List<String> companyByCity() {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT "+COL_COMPANY+" FROM "+TABLE_EMPLOYEE+" JOIN "+TABLE_JOB+" ON "
                +TABLE_EMPLOYEE+"."+COL_SSN+" = "+TABLE_JOB+"."+COL_SSN+" WHERE "+COL_CITY +" = 'Boston'";
        Cursor cursor = db.rawQuery(query, null);

        List<String> companies = new ArrayList<>();

        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                companies.add(cursor.getString(cursor.getColumnIndex(COL_COMPANY)));
                cursor.moveToNext();
            }
            cursor.close();
            return companies;
        }
        cursor.close();
        return null;
    }

    public List<String> highestPaidEmployee() {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT "+COL_FIRST_NAME+","+COL_LAST_NAME+" FROM "+TABLE_EMPLOYEE+" JOIN "+
                TABLE_JOB+" ON "+TABLE_EMPLOYEE+"."+COL_SSN+" = "+TABLE_JOB+"."+COL_SSN+" ORDER BY "+COL_SALARY;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            List<String> name = new ArrayList<>();
            name.add(cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME))+" "
            +cursor.getString(cursor.getColumnIndex(COL_LAST_NAME)));
            cursor.close();
            return name;
        }
        cursor.close();
        return null;
    }
}
