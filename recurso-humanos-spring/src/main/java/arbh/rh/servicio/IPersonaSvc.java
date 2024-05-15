package arbh.rh.servicio;

import arbh.rh.modelo.Persona;

import java.util.List;

public interface IPersonaSvc {
    public List<Persona> listarPersona();

    public Persona buscarPersonaPorId(Integer idPersona);

    public Persona guardarPersona(Persona persona);

    public void eliminarPersona(Persona persona);
}
