package arbh.rh.servicio;

import arbh.rh.modelo.Persona;
import arbh.rh.repositorio.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaSvc implements IPersonaSvc{

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @Override
    public List<Persona> listarPersona(){
        return personaRepositorio.findAll();
    }

    @Override
    public Persona buscarPersonaPorId(Integer idPersona){
        return personaRepositorio.findById(idPersona).orElse(null);
    }
    @Override
    public Persona guardarPersona(Persona persona){
        return personaRepositorio.save(persona);
    }
    @Override
    public void eliminarPersona(Persona persona){
        personaRepositorio.delete(persona);
    }
}
