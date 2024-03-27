package org.example.modelo;

public class Paciente
{
    private Long id = null;
    private String nombre;
    private String apellido;
    private int edad;
    private String telefono;
    private String correoElectronico;
    private int TensionArterialSistolica;
    private int TensionArterialDiastolica;
    private String calculoDeRiesgo;

    public Paciente() {
    }

    public Paciente(String nombre, String apellido, int edad, String telefono, String correoElectronico, int tensionArterialSimbolica, int tensionArterialDiastolica) {
        this.id = getId();
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.TensionArterialSistolica = tensionArterialSimbolica;
        this.TensionArterialDiastolica = tensionArterialDiastolica;
        calculoDeRiesgo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getTensionArterialSistolica() {
        return TensionArterialSistolica;
    }

    public void setTensionArterialSistolica(int tensionArterialSimbolica) {
        TensionArterialSistolica = tensionArterialSimbolica;
        calculoDeRiesgo();
    }

    public int getTensionArterialDiastolica() {
        return TensionArterialDiastolica;
    }

    public void setTensionArterialDiastolica(int tensionArterialDiastolica) {
        TensionArterialDiastolica = tensionArterialDiastolica;
        calculoDeRiesgo();
    }

    public void setCalculoDeRiesgo(String calculoDeRiesgo) {
        this.calculoDeRiesgo = calculoDeRiesgo;
    }

    public String getCalculoDeRiesgo() {
        return calculoDeRiesgo;
    }

    public void calculoDeRiesgo()
    {
        if(this.getEdad() < 45 && this.getTensionArterialSistolica() < 130 && this.getTensionArterialDiastolica() < 85){
            this.setCalculoDeRiesgo("Bajo");
        } else if (this.getEdad() < 45 && this.getTensionArterialSistolica() >= 130 && this.getTensionArterialSistolica() <= 139 && this.getTensionArterialDiastolica() > 85) {
            this.setCalculoDeRiesgo("Medio");
        } else if (this.getEdad() < 45 && this.getTensionArterialSistolica() >= 140 && this.getTensionArterialDiastolica() >= 90) {
            this.setCalculoDeRiesgo("Alto");
        } else if (this.getEdad() >= 45 && this.getEdad() <= 64 && this.getTensionArterialSistolica() < 130 && this.getTensionArterialDiastolica() <85){
            this.setCalculoDeRiesgo("Bajo");
        } else if (this.getEdad() >= 45 && this.getEdad() <= 64 && this.getTensionArterialSistolica() >= 130 && this.getTensionArterialSistolica() <= 159 && this.getTensionArterialDiastolica() >= 85 && this.getTensionArterialDiastolica() <= 99) {
            this.setCalculoDeRiesgo("Medio");
        } else if (this.getEdad() >= 45 && this.getEdad() <= 64 && this.getTensionArterialSistolica() >= 160 && this.getTensionArterialDiastolica() >= 100) {
            this.setCalculoDeRiesgo("Alto");
        } else if (this.getEdad() >= 65 && this.getTensionArterialSistolica() < 130 && this.getTensionArterialDiastolica() < 85) {
            this.setCalculoDeRiesgo("Medio");
        } else if (this.getEdad() >= 65 && this.getTensionArterialSistolica() >= 130 && this.getTensionArterialSistolica() <= 139 && this.getTensionArterialDiastolica() >= 85 && this.getTensionArterialDiastolica() <= 89) {
            this.setCalculoDeRiesgo("Medio");
        } else if (this.getEdad() >= 65 && this.getTensionArterialSistolica() >= 140 && this.getTensionArterialDiastolica() >= 90) {
            this.setCalculoDeRiesgo("Alto");
        } else {
            this.setCalculoDeRiesgo("Indeterminado");
        }
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", numeroTelefono=" + telefono +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", TensionArterialSistolica=" + TensionArterialSistolica +
                ", TensionArterialDiastolica=" + TensionArterialDiastolica +
                ", calculoDeRiesgo='" + calculoDeRiesgo + '\'' +
                '}';
    }

}
