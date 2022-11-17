package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class SelectBuilder implements Executor {

    private final StringBuilder query= new StringBuilder("");

    private Connection connection;

    private PreparedStatement preparedStatement;

    ResultSet resultSet;

    public SelectBuilder() {
    }

    public SelectBuilder(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        this.connection = connection;
        this.preparedStatement = preparedStatement;
        this.resultSet = resultSet;
    }

    public SelectBuilder select(String ...fields){
        query.append("SELECT ");
        Utils.append(query, fields);
        query.deleteCharAt(query.length() - 2);
        return this;
    }

    public SelectBuilder select(){
        query.append("SELECT * ");
        return this;
    }

    public SelectBuilder from(String ...tables){
        query.append("FROM ");
        Utils.append(query, tables);
        query.deleteCharAt(query.length() - 2);
        return this;
    }

    public SelectBuilder where(){
        query.append("WHERE ");
        return this;
    }

    public SelectBuilder like(String expression){
        query.append("LIKE " + "'" + expression + "'" + " ");
        return this;
    }
    public SelectBuilder and(String field){
        query.append("AND " +field + " ");
        return this;
    }

    public SelectBuilder or(String field){
        query.append("OR " +field + " ");
        return this;
    }

    @Override
    public String execute() {
        return query.toString();
    }

    public Optional<ResultSet> fetch(){
        Optional<ResultSet> results;
        try{
            this.connection.setAutoCommit(false);
            this.preparedStatement = this.connection.prepareStatement(query.toString());
            results = Optional.ofNullable(preparedStatement.executeQuery());
            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return results;
    }
}
