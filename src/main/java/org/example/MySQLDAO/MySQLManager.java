package org.example.MySQLDAO;

import org.example.dao.DAOManager;
import org.example.dao.PacienteDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLManager implements DAOManager
{
    private Connection conn;

    private PacienteDao pacienteDao;

    public MySQLManager() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/paciente_jdbc","root","");

    }
    @Override
    public PacienteDao getPacienteDAO()
    {
        if (pacienteDao == null)
        {
            pacienteDao = new MySQLPacienteDAO(conn);
        }
        return pacienteDao;
    }
}
