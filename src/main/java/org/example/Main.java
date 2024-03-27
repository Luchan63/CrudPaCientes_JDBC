package org.example;
import org.example.MySQLDAO.MySQLManager;
import org.example.dao.DAOException;
import org.example.modelo.Paciente;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerNum = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String[] args) {
        boolean salir = false;
        int opcion;
        do {
            menuPrincipal();
            opcion = scannerNum.nextInt();
            try {
                switch (opcion) {
                    case 1:
                        agregarPaciente();
                        break;
                    case 2:
                        mostrarAlumnos();
                        break;
                    case 3:
                        editarPaciente();
                        break;
                    case 4:
                        eliminarPaciente();
                        break;
                    case 5:
                        buscarPaciente();
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        } while (!salir);
        System.out.println("Fin del programa");
    }
    private static void menuPrincipal() {
        System.out.println("Menu de Opciones");
        System.out.println("================");
        System.out.println("1. Agregar paciente");
        System.out.println("2. Listar paciente");
        System.out.println("3. Modifier paciente");
        System.out.println("4. Eliminar paciente");
        System.out.println("5. Buscar paciente");
        System.out.println("0. Salir");
        System.out.println();
        System.out.print("Ingrese una opcion: ");
    }
    private static void agregarPaciente() throws SQLException, DAOException {
        System.out.println("Nombre: ");
        String nombre = scannerStr.next();
        System.out.println("Apellido: ");
        String apellido = scannerStr.next();
        System.out.println("Edad: ");
        int edad = scannerNum.nextInt();
        System.out.println("Telefono: ");
        String telefono = scannerStr.next();
        System.out.println("Correo: ");
        String correo = scannerStr.next();
        System.out.println("Presion sistolica");
        int sistolica = scannerNum.nextInt();
        System.out.println("Presion diastolica");
        int diastolica = scannerNum.nextInt();



        Paciente paciente = new Paciente(nombre,apellido,edad,telefono,correo,sistolica,diastolica);
        MySQLManager gestor = new MySQLManager();
        gestor.getPacienteDAO().insertar(paciente);// Llamada al método alta del gestor para agregar el alumno
        System.out.println("Alumno agregado exitosamente.");
    }
    private static void editarPaciente() throws SQLException,DAOException {
        cabeceraListarAlumnos();
        listarPaciente();
        pieDePagina();
        System.out.print("Ingrese el ID del alumno a modificar: ");
        Long id = scannerNum.nextLong();
        System.out.println("Nombre: ");
        String nombre = scannerStr.next();
        System.out.println("Apellido: ");
        String apellido = scannerStr.next();
        System.out.println("Edad: ");
        int edad = scannerNum.nextInt();
        System.out.println("Telefono: ");
        String telefono = scannerStr.next();
        System.out.println("Correo: ");
        String correo = scannerStr.next();
        System.out.println("Presion sistolica");
        int sistolica = scannerNum.nextInt();
        System.out.println("Presion diastolica");
        int diastolica = scannerNum.nextInt();

        Paciente paciente = new Paciente(nombre, apellido, edad,telefono,correo,sistolica,diastolica);
        paciente.setId(id);
        MySQLManager man = new MySQLManager();
        man.getPacienteDAO().modificar(paciente);
        System.out.println("Alumno actualizado exitosamente.");
    }
    private static void eliminarPaciente() throws SQLException, DAOException {
        cabeceraListarAlumnos();
        listarPaciente();
        pieDePagina();
        System.out.print("Ingrese el ID del alumno a eliminar: ");
        Long id = scannerNum.nextLong();
        MySQLManager man = new MySQLManager();

        man.getPacienteDAO().elimanar(id); // Llamada al método eliminar del gestor para eliminar el paciente
        System.out.println("Alumno eliminado exitosamente.");
    }
    private static void listarPaciente() throws SQLException, DAOException {
        MySQLManager gestor = new MySQLManager();
        List<Paciente> pacientes = gestor.getPacienteDAO().getALl(); // Llamada al método listar del gestor para obtener la lista de pacientes

        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }
    }

    private static void buscarPaciente() throws SQLException, DAOException {
        MySQLManager gestor = new MySQLManager();
        cabeceraListarAlumnos();
        listarPaciente();
        System.out.print("Ingrese el id del alumno a buscar: ");
        Long id = scannerNum.nextLong();
        Paciente paciente = gestor.getPacienteDAO().getById(id);
            cabeceraListarAlumnos();
        System.out.println(paciente);


    }

    private static void mostrarAlumnos() throws SQLException, DAOException {
        cabeceraListarAlumnos();
        listarPaciente();
        pieDePagina();
        pausa();
    }
    private static void cabeceraListarAlumnos() {
        // Salida de datos con variable local
        System.out.println("\n\n                                Listado de Alumnos");
        System.out.println("============================================================================================================================================");
        System.out.println("ID  Nombre                Apellido         Edad     Telefono       Correo               Sistolica        Diastolica        Calculo de riesgo");
        System.out.println("============================================================================================================================================");
    }
    private static void pieDePagina() {
        System.out.println("============================================================================================================================================");
        System.out.println("============================================================================================================================================");
    }
    private static void pausa() {
        System.out.println();
        System.out.println("Presione ENTER para continuar...");
        scannerStr.nextLine();
    }

}