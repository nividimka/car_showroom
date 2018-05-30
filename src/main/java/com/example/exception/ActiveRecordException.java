package com.example.exception;

import java.sql.SQLException;


public class ActiveRecordException extends SQLException {

    private static final long serialVersionUID = 342820722361408621L;

    public ActiveRecordException(String message) {
        super(message);
    }

    public ActiveRecordException(Throwable cause) {
        super(cause);
    }

    public ActiveRecordException(String message, Throwable cause) {
        super(message, cause);
    }
}
