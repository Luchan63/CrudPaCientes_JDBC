package org.example.MySQLDAO;

import org.example.dao.DAOException;
import org.example.dao.PacienteDao;
import org.example.modelo.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// implementamos la interfas Paciente dao
public class MySQLPacienteDAO implements PacienteDao
{
    // coneccion con la base de datos
    private final Connection conn;

    // consultas ya definidas para evitar inyeccion de sql
   private static final String INSERT = "INSERT INTO paciente_jdbc.paciente(nombre,apellido,edad,telefono,correo,sistolica,diastolica,cardiopatia) VALUE(?,?,?,?,?,?,?,?)";
   private static final String UPDATE = "UPDATE paciente_jdbc.paciente SET nombre = ?,apellido = ?,edad = ?,telefono = ?,correo = ?,sistolica = ?,diastolica = ? where id = ? ";
   private static final String DELETE = "DELETE from paciente_jdbc.paciente where id = ?";
   private static final String GETALL = "SELECT id,nombre,apellido,edad,telefono,correo,sistolica,diastolica,cardiopatia from paciente_jdbc.paciente";
   private static final String GETONE = "SELECT id,nombre,apellido,edad,telefono,correo,sistolica,diastolica,cardiopatia from paciente_jdbc.paciente where id = ?";


    // contructor con la coneccion incluida luego explico por que
    public MySQLPacienteDAO(Connection conn) {
        this.conn = conn;
    }



    @Override
    public void insertar(Paciente a) throws DAOException , SQLException
    {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(INSERT);
            statement.setString(1,a.getNombre());
            statement.setString(2,a.getApellido());
            statement.setInt(3,a.getEdad());
            statement.setString(4,a.getTelefono());
            statement.setString(5,a.getCorreoElectronico());
            statement.setDouble(6,a.getTensionArterialSistolica());
            statement.setDouble(7,a.getTensionArterialDiastolica());
            a.calculoDeRiesgo();
            statement.setString(8,a.getCalculoDeRiesgo());
            if (statement.executeUpdate() == 0)
            {
                throw new DAOException("Puede que no se haya guardado el registro");
            } else {
                System.out.println("Registro exitoso");
            }
        } catch (SQLException e)
        {
            throw new DAOException("Error en sql", e);
        } finally {
            cerrarStat(statement);
        }

    }

    @Override
    public void modificar(Paciente a) throws DAOException, SQLException
    {
        PreparedStatement statement = null;

        try {
        statement = conn.prepareStatement(UPDATE);
            statement.setString(1,a.getNombre());
            statement.setString(2,a.getApellido());
            statement.setInt(3,a.getEdad());
            statement.setString(4,a.getTelefono());
            statement.setString(5,a.getCorreoElectronico());
            statement.setDouble(6,a.getTensionArterialSistolica());
            statement.setDouble(7,a.getTensionArterialDiastolica());
        statement.setLong(8,a.getId());
        if (statement.executeUpdate() == 0)
        {
            throw new DAOException("Puede que no se haya actualizado el registro");
        } else {
            System.out.println("Actualizacion exitosa");
        }
        } catch (SQLException e)
        {
            throw new DAOException("Error en sql", e);
        } finally {
            cerrarStat(statement);
        }


    }

    @Override
    public void elimanar(Long id) throws DAOException, SQLException
    {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(DELETE);
            statement.setLong(1,id);
            if (statement.executeUpdate() == 0)
            {
                throw new DAOException("Puede que no se haya eliminado el registro");
            }
        } catch (SQLException e)
        {
            throw new DAOException("Error en sql", e);
        } finally {
            cerrarStat(statement);
        }

    }

    @Override
    public List<Paciente> getALl() throws DAOException, SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Paciente> pacientes = new ArrayList<>();

        try {
            statement = conn.prepareStatement(GETALL);
            resultSet = statement.executeQuery();
        while(resultSet.next())
        {
            pacientes.add(convertir(resultSet));

        }
        } catch (SQLException e)
        {
            throw new DAOException("error en sql",e);
        } finally {
            cerrarStat(statement);
            cerrarRs(resultSet);
        }
        return pacientes;
    }

    @Override
    public Paciente getById(Long id) throws DAOException, SQLException
    {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Paciente paciente = null;

        try {
            statement = conn.prepareStatement(GETONE);
            statement.setLong(1,id);
            resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                paciente = convertir(resultSet);

            }
        } catch (SQLException e)
        {
            throw new DAOException("error en sql",e);
        } finally {
            cerrarStat(statement);
            cerrarRs(resultSet);
        }
        return paciente;

    }

    private void cerrarStat(PreparedStatement stat) throws DAOException, SQLException {
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    private void cerrarRs(ResultSet rs)throws DAOException, SQLException
    {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    private Paciente convertir(ResultSet rs)throws SQLException, DAOException
    {
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        int edad = rs.getInt("edad");
        String telefono = rs.getString("telefono");
        String correro = rs.getString("correo");
        int sitolica = rs.getInt("sistolica");
        int diastolica = rs.getInt("diastolica");
        String cardiopatia = rs.getString("cardiopatia");

        Paciente paciente = new Paciente(nombre,apellido,edad,telefono,correro,sitolica,diastolica);
        paciente.setId(rs.getLong("id"));
        paciente.setCalculoDeRiesgo(cardiopatia);


        return paciente;
    }
}
