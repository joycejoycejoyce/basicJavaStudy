package com.joyce05.handler;

import java.sql.ResultSet;
// an interface to process result set
public interface ResultSetHandler<T> {
    <T> T handler(ResultSet resultSet);
}
