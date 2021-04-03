package com.quiz;

public class StudentTest {
    public static void main(String[] args) {
        StudentRecord record = new StudentRecord();
        // create student table (id, name, gender)
        //record.createTable();

        // add one student record
        Object[] param = {"student two", "M"};
        record.addStudent(param);

        // update one student record
        Object[] updateParam = {"April Fool" ,2};
        record.updateStudentName(updateParam);
    }
}
