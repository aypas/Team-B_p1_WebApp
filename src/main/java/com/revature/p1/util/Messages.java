package com.revature.p1.util;

public class Messages {
    private static String unauthorized;
//    private String unauthorized = "You are not authorized";

    public Messages(){super();}

    public Messages(String unauthorized){
        this.unauthorized = unauthorized;
    }

    public static String getUnauthorized() {
        return "You are not authorized.";
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Messages{");
        sb.append("unauthorized='").append(unauthorized).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
