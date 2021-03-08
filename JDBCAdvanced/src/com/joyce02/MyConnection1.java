package com.joyce02;

import com.mysql.jdbc.JDBC4Connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/*
Self defined connection object
1. define a class, and inherent JDBC4Connection class


 */
public class MyConnection1 extends JDBC4Connection {
    //2. define Connection connect object and container object variables
    private Connection connection;
    private List<Connection> pool;

    //3. constructor
    public MyConnection1(String hostToConnectTo, int portToConnectTo, Properties info, String databaseToConnectTo, String url, Connection connection, List<Connection> pool) throws SQLException {
        super(hostToConnectTo, portToConnectTo, info, databaseToConnectTo, url);
        this.connection = connection;
        this.pool = pool;
    }

    // 4. rewrite close() to create reusable connection objects
    @Override
    public void close() throws SQLException {
        pool.add(connection);
    }
}

