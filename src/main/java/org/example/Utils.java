package org.example;

public class Utils {
    public static void append(StringBuilder sb, String ...args){
        for (String table : args) sb.append(table + ", ");
    }
}
