package com.joyce01;

import com.joyce.utils.JDBCUtils;
import com.joyce02.MyConnection2;
import com.joyce02.MyConnection3;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

// self defined db connector
public class MyDataSource implements DataSource {

    // 1. prepare a container. The container is for store many connection objects
    private static List<Connection> pool = Collections.synchronizedList(new ArrayList<>());

    //2. define static block, get 10 connection objects
    static {
        for(int i = 1; i <=10; i++){
            Connection connection = JDBCUtils.getConnection();
            pool.add(connection);
        }
    }
    // 3. rewrite getConnection() method, for get a connection object
    @Override
    public Connection getConnection() throws SQLException {
        if (pool.size() > 0){
            Connection connection = pool.remove(0);
            Connection proxyConnection = (Connection) Proxy.newProxyInstance(connection.getClass().getClassLoader(), new Class[]{Connection.class}, new InvocationHandler() {
                /*
                execute Connection class, all will go through invoke
                    if close(){
                        return the connection
                      } else{
                        execute the original methods
                      }
                 */
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (method.getName().equals("close")) {
                        pool.add(connection);
                        return null;
                    } else {
                        return method.invoke(connection, args);
                    }
                }
            });

            return proxyConnection;
        }else {
            throw new RuntimeException("Run out of connection object");
        }
    }

    // 4. define getSize method, get Connection container size
    public int getSize(){
        return pool.size();
    }


    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
