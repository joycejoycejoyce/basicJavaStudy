package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        Student student = new Student();
        //student.eat("burger");
        //student.study();

        /*
        3 parameters needed:
            class loader: same interface as the student
            interface type class array: same interface as the student
            proxy rule: new function
         */
        StudentInterface proxyStudent = (StudentInterface) Proxy.newProxyInstance(student.getClass().getClassLoader(), new Class[]{StudentInterface.class}, new InvocationHandler() {
            /*
                everything in class method will go through invoke
                method: methods in student class
                args: arguments in methods
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /*
                if it is study() method, add new function
                if not, dont change
                 */
                if (method.getName().equals("study")){
                    System.out.println("study with Joyce!");
                    return null;
                }else {
                    return method.invoke(student, args);
                }
            }
        });

        proxyStudent.eat("hotdog");
        proxyStudent.study();
    }
}
