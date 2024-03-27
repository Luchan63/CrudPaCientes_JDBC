package org.example.MySQLDAO;

import org.example.dao.PacienteDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLManager
{
    // intancion la coneccion
    private final Connection conn;
    // intancio paciente dao
    private PacienteDao pacienteDao;

    // en el contructor hago la coneccion con la base de datos
    public MySQLManager() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/paciente_jdbc","root","");

    }


    public PacienteDao getPacienteDAO() // explico un poco el metodo
    {
        if (pacienteDao == null) // le digo que si pacienteDao no esta inicializado en ninguna parte del codigo
        {
            pacienteDao = new MySQLPacienteDAO(conn); // la inicializo pero con mysqlDao
        }
        return pacienteDao;
    }
}
