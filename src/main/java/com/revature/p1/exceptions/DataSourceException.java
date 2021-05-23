package com.revature.p1.exceptions;

public class DataSourceException extends RuntimeException {
    public DataSourceException() {
        super("There was a problem when communicating with the database. Check the logs for more details.");
    }

    public DataSourceException(String message) {
        super(message);
    }
}
