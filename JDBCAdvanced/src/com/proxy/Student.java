package com.proxy;

public class Student implements StudentInterface{
    public void eat(String food){
        System.out.println("student eat "+food);
    }

    public void study(){
        System.out.println("Student is studying");
    }
}
