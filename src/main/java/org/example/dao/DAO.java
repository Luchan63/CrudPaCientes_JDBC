package org.example.dao;

import java.util.List;

public interface DAO<t,k>
{
    void insertar(t a) throws DAOException;
    void modificar(t a) throws DAOException;
    void elimanar(k id) throws DAOException;

     List<t> getALl() throws DAOException;

    t getById(k id) throws DAOException;
}
