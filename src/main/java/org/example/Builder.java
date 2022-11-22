package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class Builder {
    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    public Builder(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        this.connection = connection;
        this.preparedStatement = preparedStatement;
        this.resultSet = resultSet;
    }
}
