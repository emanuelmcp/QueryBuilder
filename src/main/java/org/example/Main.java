package org.example;

public class Main {
    public static void main(String[] args) {
        SelectBuilder builder = new SelectBuilder();
        String query =
                builder.select()
                        .from("hola", "quetal").
                        where().and("campo3")
                        .or("campo1")
                        .execute();
        System.out.println(query);
    }
}