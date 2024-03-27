package org.example.dao;

public class DAOException extends Exception {
    public DAOException(String mensaje) {
        super(mensaje);
    }

    public DAOException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public DAOException(Throwable causa) {
        super(causa);
    }
}