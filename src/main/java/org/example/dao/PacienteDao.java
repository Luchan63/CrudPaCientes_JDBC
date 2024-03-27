package org.example.dao;

import org.example.modelo.Paciente;

// aqui extiendo Dao para tener ya los metodos predefinidos y utilice una ternica de jpa pero aplicada a jdbc que
// me resulto una idea brillante porque evito definir muchos metodos y solo debo sobreEscribirlo.
public interface PacienteDao extends DAO<Paciente, Long>{
}
