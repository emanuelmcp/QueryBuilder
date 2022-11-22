package org.example;

//TODO extender de builder

public class SelectBuilder implements Executor {

    private final StringBuilder query= new StringBuilder("");

   /* public SelectBuilder(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        super(connection, preparedStatement, resultSet);
    }*/


    public SelectBuilder select(String ...fields){
        query.append(" SELECT ");
        Utils.append(query, fields);
        query.deleteCharAt(query.length() - 2);
        return this;
    }

    public SelectBuilder select(){
        query.append(" SELECT * ");
        return this;
    }

    public SelectBuilder from(String ...tables){
        query.append(" FROM ");
        Utils.append(query, tables);
        query.deleteCharAt(query.length() - 2);
        return this;
    }

    public SelectBuilder where(){
        query.append(" WHERE ");
        return this;
    }

    public SelectBuilder condition(String field1, Operator op, String field2){
        if (op == Operator.EQUALS) query.append(field1 + " = " + field2);
        if (op == Operator.MAYOR) query.append(field1 + " > " + field2);
        if (op == Operator.MINOR) query.append(field1 + " < " + field2);
        if (op == Operator.MAYOR_OR_EQUALS) query.append(field1 + " >= " + field2);
        if (op == Operator.MINOR_OR_EQUALS) query.append(field1 + " <= " + field2);
        if (op == Operator.LIKE) query.append(field1 + " LIKE " + "'" + field2 + "'" + " ");
        return this;
    }

    public SelectBuilder join (String table, String field1, String field2){
        query.append(" INNER JOIN " + table + " ON " + field1 + " = " + field2);
        return this;
    }
    public SelectBuilder and (){
        query.append(" AND ");
        return this;
    }
    public SelectBuilder or (){
        query.append(" OR ");
        return this;
    }


    @Override
    public String execute() {
        query.append(";");
        return query.toString();
    }

    /*public Optional<ResultSet> fetch(){
        query.append(";");
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
    }*/
}
