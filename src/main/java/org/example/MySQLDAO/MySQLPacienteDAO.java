package org.example.MySQLDAO;

import org.example.dao.DAOException;
import org.example.dao.PacienteDao;
import org.example.modelo.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLPacienteDAO implements PacienteDao
{
    // coneccion con la base de datos
    private final Connection conn;
    final String INSERT = "INSERT INTO paciente_jdbc.paciente(nombre,apellido,edad,telefono,correo,sistolica,diastolica,cardiopatia) VALUE(?,?,?,?,?,?,?,?)";
    final String UPDATE = "UPDATE paciente_jdbc.paciente SET nombre = ?,apellido = ?,edad = ?,telefono = ?,correo = ?,sistolica = ?,diastolica = ? where id = ? ";
    final String DELETE = "DELETE from paciente_jdbc.paciente where id = ?";
    final String GETALL = "SELECT id,nombre,apellido,edad,telefono,correo,sistolica,diastolica,cardiopatia from paciente_jdbc.paciente";
    final String GETONE = "SELECT id,nombre,apellido,edad,telefono,correo,sistolica,diastolica,cardiopatia from paciente_jdbc.paciente where id = ?";


    public MySQLPacienteDAO(Connection conn) {
        this.conn = conn;
    }



    @Override
    public void insertar(Paciente a) throws DAOException
    {
        PreparedStatement stat = null;

        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1,a.getNombre());
            stat.setString(2,a.getApellido());
            stat.setInt(3,a.getEdad());
            stat.setString(4,a.getTelefono());
            stat.setString(5,a.getCorreoElectronico());
            stat.setDouble(6,a.getTensionArterialSistolica());
            stat.setDouble(7,a.getTensionArterialDiastolica());
            a.calculoDeRiesgo();
            stat.setString(8,a.getCalculoDeRiesgo());
            if (stat.executeUpdate() == 0)
            {
                throw new DAOException("Puede que no se haya guardado el registro");
            } else {
                System.out.println("Registro exitoso");
            }
        } catch (SQLException e)
        {
            throw new DAOException("Error en sql", e);
        } finally {
            cerrarStat(stat);
        }

    }

    @Override
    public void modificar(Paciente a) throws DAOException
    {
        PreparedStatement stat = null;

        try {
        stat = conn.prepareStatement(UPDATE);
        stat.setString(1,a.getNombre());
        stat.setString(2,a.getApellido());
        stat.setInt(3,a.getEdad());
        stat.setString(4,a.getTelefono());
        stat.setString(5,a.getCorreoElectronico());
        stat.setDouble(6,a.getTensionArterialSistolica());
        stat.setDouble(7,a.getTensionArterialDiastolica());
        stat.setLong(8,a.getId());
        if (stat.executeUpdate() == 0)
        {
            throw new DAOException("Puede que no se haya actualizado el registro");
        } else {
            System.out.println("Actualizacion exitosa");
        }
        } catch (SQLException e)
        {
            throw new DAOException("Error en sql", e);
        } finally {
            cerrarStat(stat);
        }


    }

    @Override
    public void elimanar(Long id) throws DAOException
    {
        PreparedStatement stat = null;

        try {
            stat = conn.prepareStatement(DELETE);
            stat.setLong(1,id);
            if (stat.executeUpdate() == 0)
            {
                throw new DAOException("Puede que no se haya eliminado el registro");
            }
        } catch (SQLException e)
        {
            throw new DAOException("Error en sql", e);
        } finally {
            cerrarStat(stat);
        }

    }

    @Override
    public List<Paciente> getALl() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Paciente> pacientes = new ArrayList<>();

        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
        while (rs.next())
        {
            pacientes.add(convertir(rs));

        }
        } catch (SQLException e)
        {
            throw new DAOException("error en sql",e);
        } finally {
            cerrarStat(stat);
            cerrarRs(rs);
        }
        return pacientes;
    }

    @Override
    public Paciente getById(Long id) throws DAOException
    {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Paciente paciente = null;

        try {
            stat = conn.prepareStatement(GETONE);
            stat.setLong(1,id);
            rs = stat.executeQuery();
            if (rs.next())
            {
                paciente = convertir(rs);

            }
        } catch (SQLException e)
        {
            throw new DAOException("error en sql",e);
        } finally {
            cerrarStat(stat);
            cerrarRs(rs);
        }
        return paciente;

    }

    private void cerrarStat(PreparedStatement stat) throws DAOException {
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    private void cerrarRs(ResultSet rs)throws DAOException
    {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    private Paciente convertir(ResultSet rs)throws SQLException
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
