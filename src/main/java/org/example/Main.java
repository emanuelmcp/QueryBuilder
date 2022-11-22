package org.example;

public class Main {
    public static void main(String[] args) {
        SelectBuilder builder = new SelectBuilder();
        String query =
                builder.select("campo1", "campo2", "campo3")
                        .from("campo1", "campo2")
                        .where()
                        .condition("campo1", Operator.LIKE, "%ex%")
                        .and()
                        .condition("campo1", Operator.EQUALS, "campo2")
                        .or()
                        .condition("campo1", Operator.MAYOR_OR_EQUALS, "campo1")
                        .join("tabla2", "campo1", "campo2")
                        .execute();
        System.out.println(query);
    }
}