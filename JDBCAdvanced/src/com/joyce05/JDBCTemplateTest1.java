package com.joyce05;

import com.joyce.utils.DataSourceUtils;
import com.joyce05.handler.BeanHandler;
import com.joyce05.domain.Student;
import com.joyce05.handler.BeanListHandler;
import com.joyce05.handler.ScalarHandler;
import org.junit.Test;

import java.util.List;

// stimulate DAO
public class JDBCTemplateTest1 {

    private JDBCTemplate template = new JDBCTemplate(DataSourceUtils.getDataSource());

    @Test
    public void queryForScalar(){
        // check row number
        String str = "Select COUNT(*) from student";
        Long value=template.queryForScalar(str, new ScalarHandler<Long>());
        System.out.println(value);
    }

    @Test
    public void queryForList(){
        // check all students in student list
        String sql = "Select * from student";
        List<Student> list = template.queryForList(sql, new BeanListHandler<>(Student.class));
        for(Student s : list){
            System.out.println(s);
        }
    }

    @Test
    public void queryForObject(){
        String sql = "Select * from student where sid=?";
        Student student = template.queryForObject(sql, new BeanHandler<>(Student.class), 1);
        System.out.println(student.toString());
    }

    @Test
    public void delete(){
        String sql  = "Delete from student where name = ?";
        int result = template.update(sql, "Number five");
        if (result > 0){
            System.out.println("delete success");
        }else{
            System.out.println("delete failed");
        }
    }

    @Test
    public void update(){
        System.out.println("update function");

        String sql = "UPDATE student SET age=? WHERE name=?";
        Object[] params = {37, "Number Five"};


        int result = template.update(sql, params);
        if (result > 0){
            System.out.println("update success");
        }else{
            System.out.println("update failed");
        }
    }

    @Test
    public void insert(){
        // add new data
        String sql = "INSERT INTO student VALUES (?,?,?,?)";
        Object[] params = {5, "Number Five", 23, "1997-07-07"};
        int result = template.update(sql, params);
        if (result > 0){
            System.out.println("update success");
        }else{
            System.out.println("update failed");
        }

    }



}
