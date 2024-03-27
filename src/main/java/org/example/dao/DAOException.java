package org.example.dao;


// creo excepciones predefinidas
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