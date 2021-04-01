package com.joyce05.handler;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

// 1. define a class and implement resultsethandler interface
public class BeanHandler<T> implements ResultSetHandler<T> {

    // 2. define a class object variable
    private Class<T> beanClass;

    // 3. create a constructor and give value to the variable
    public BeanHandler(Class<T> beanClass){
        this.beanClass = beanClass;
    }

    // 4. rewrite handler method. Enca
    @Override
    public T handler(ResultSet resultSet) {
        // state the class of self defined object
        T bean = null;
        try {
            bean = beanClass.newInstance();

            if (resultSet.next()){
                //
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }


}
