package com.joyce02;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/*
1.define a class, and inherent MyAdapter class
2. define a connection object and pool variable
3. create a constructor
4. rewrite all abstruct methods
 */
public class MyConnection3 extends MyAdapter{

    private Connection connection;
    private List<Connection> pool;

    public MyConnection3(Connection connection, List<Connection> pool){
        super(connection);
        this.connection = connection;
        this.pool = pool;
    }

    @Override
    public void close() throws SQLException {
        pool.add(connection);
    }
}
