package arbh.rh.controlador;

import arbh.rh.excepcion.RecursoNoEncontradoExcepcion;
import arbh.rh.modelo.Persona;
import arbh.rh.servicio.PersonaSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rh-app")
@CrossOrigin(value = "http://localhost:3000")
public class PersonaControlador {

    private static final Logger logger =
            LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private PersonaSvc personaSvc;

    @GetMapping("/personas")
    public List<Persona> obtenerPersona(){
        var persona = personaSvc.listarPersona();
        //empleados.forEach((empleado -> logger.info(empleado.toString())));
        return persona;
    }

    @PostMapping("/personas")
    public ResponseEntity<Persona> agregarPersona(
            @RequestParam("nombre") String nombre,
            @RequestParam("correo") String correo,
            @RequestParam("identificacion") String identificacion) throws Exception {

        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre(nombre);
        nuevaPersona.setCorreo(correo);
        nuevaPersona.setIdentificacion(identificacion);
        Persona PersonaGuardada = personaSvc.guardarPersona(nuevaPersona);
        return ResponseEntity.ok(PersonaGuardada);
    }

    @GetMapping("/personas/{id}")
    public ResponseEntity<Persona>
    obtenerPersonaPorId(@PathVariable Integer id){
        Persona persona = personaSvc.buscarPersonaPorId(id);
        if(persona == null){
            throw new RecursoNoEncontradoExcepcion("No Se Encontro El id: " + id);
        }else {
            return ResponseEntity.ok(persona);
        }
    }

    @PutMapping("/personas/{id}")
    public ResponseEntity<Persona> actualizarPersona(
            @PathVariable Integer id,
            @RequestParam("nombre") String nombre,
            @RequestParam("correo") String correo,
            @RequestParam("identificacion") String identificacion) throws Exception {

        Persona persona = personaSvc.buscarPersonaPorId(id);
        if (persona == null) {
            throw new RecursoNoEncontradoExcepcion("El Id Recibido No Existe: " + id);
        } else {
            persona.setNombre(nombre);
            persona.setCorreo(correo);
            persona.setIdentificacion(identificacion);
            personaSvc.guardarPersona(persona);
            return ResponseEntity.ok(persona);
        }
    }

    @DeleteMapping("/personas/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarPersona(@PathVariable Integer id){
        Persona persona = personaSvc.buscarPersonaPorId(id);
        if(persona == null){
            throw new RecursoNoEncontradoExcepcion("El Id Recibido No Existe: " + id);
        }else {
            personaSvc.eliminarPersona(persona);
            // json {"eliminado": "true"}
            Map<String, Boolean> respuesta = new HashMap<>();
            respuesta.put("eliminado", Boolean.TRUE);
            return ResponseEntity.ok(respuesta);
        }
    }
}
