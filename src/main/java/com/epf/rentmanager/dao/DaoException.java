package com.epf.rentmanager.dao;

public class DaoException extends Exception {
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }

}
