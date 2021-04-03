package com.joyce05.handler;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

// 1. define a class and implement resultsethandler interface
public class BeanListHandler<T> implements ResultSetHandler<T> {

    // 2. define a class object variable
    private Class<T> beanClass;

    // 3. create a constructor and give value to the variable
    public BeanListHandler(Class<T> beanClass){
        this.beanClass = beanClass;
    }

    // 4. rewrite handler method. Encapsulate many records to objects
    @Override
    public List<T> handler(ResultSet resultSet) {
        // state list object
        List<T> list = new ArrayList<>();
        try {
            while (resultSet.next()){
                T bean = beanClass.newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int count = metaData.getColumnCount();

                // iterate over the columns
                for (int i=1; i<=count; i++){
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(columnName);
                    PropertyDescriptor pd = new PropertyDescriptor(columnName.toLowerCase(), beanClass);
                    // get set method
                    Method writeMethod = pd.getWriteMethod();
                    // use set method
                    writeMethod.invoke(bean, value);
                }
                list.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
