package org.example.dao;

import java.sql.SQLException;
import java.util.List;
// creo una interface con datos generico para poder manipularlo ya.
public interface DAO<t,k>
{
    void insertar(t a) throws DAOException, SQLException;
    void modificar(t a) throws DAOException , SQLException;
    void elimanar(k id) throws DAOException,   SQLException;

     List<t> getALl() throws DAOException, SQLException;

    t getById(k id) throws DAOException, SQLException;
}
