package arbh.rh.controlador;

import arbh.rh.excepcion.RecursoNoEncontradoExcepcion;
import arbh.rh.modelo.Cargo;
import arbh.rh.modelo.Persona;
import arbh.rh.modelo.ServicioSolicitud;
import arbh.rh.modelo.Solicitud;
import arbh.rh.servicio.PersonaSvc;
import arbh.rh.servicio.ServicioSolicitudSvc;
import arbh.rh.servicio.SolicitudSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rh-app")
@CrossOrigin(value = "http://localhost:3000")
public class SolicitudControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(SolicitudControlador.class);

    @Autowired
    private SolicitudSvc solicitudSvc;

    @Autowired
    private PersonaSvc personaSvc;

    @Autowired
    private ServicioSolicitudSvc servicioSolicitudSvc;


    @GetMapping("/solicitudes")
    public List<Solicitud> obtenerSolicitud(){
        var solicitud = solicitudSvc.listarSolicitud();
        //empleados.forEach((empleado -> logger.info(empleado.toString())));
        return solicitud;
    }

    @PostMapping("/solicitudes")
    public ResponseEntity<Solicitud> agregarSolicitud(
            @RequestParam("estadoSolicitud") String nombre,
            @RequestParam("fechaSolicitud")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaSolicitud,
            @RequestParam("idServicioSolicitud") Integer idServicioSolicitud,
            @RequestParam("idPersona") Integer idPersona) throws Exception {
        ServicioSolicitud servicioSolicitud = servicioSolicitudSvc.buscarServicioSolicitud(idServicioSolicitud);
        if (servicioSolicitud == null) {
            return ResponseEntity.badRequest().body(null); // Manejo de error si el cargo no se encuentra
        }
        Persona persona = personaSvc.buscarPersonaPorId(idPersona);
        if (persona == null) {
            return ResponseEntity.badRequest().body(null); // Manejo de error si el cargo no se encuentra
        }
        Solicitud nuevaSolicitud = new Solicitud();
        nuevaSolicitud.setEstadoSolicitud(nombre);
        nuevaSolicitud.setFechaSolicitud(fechaSolicitud);
        nuevaSolicitud.setServicioSolicitud(servicioSolicitud);
        nuevaSolicitud.setPersona(persona);
        Solicitud PersonaSolicitud = solicitudSvc.guardarSolicitud(nuevaSolicitud);
        return ResponseEntity.ok(PersonaSolicitud);
    }

    @GetMapping("/solicitudes/{id}")
    public ResponseEntity<Solicitud>
    obtenerSolicitudPorId(@PathVariable Integer id){
        Solicitud solicitud = solicitudSvc.buscarSolicitud(id);
        if(solicitud == null){
            throw new RecursoNoEncontradoExcepcion("No Se Encontro El id: " + id);
        }else {
            return ResponseEntity.ok(solicitud);
        }
    }

    @PutMapping("/solicitudes/{id}")
    public ResponseEntity<Solicitud> actualizarSolicitud(
            @PathVariable Integer id,
            @RequestParam("estadoSolicitud") String nombre,
            @RequestParam("fechaSolicitud")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaSolicitud,
            @RequestParam("idServicioSolicitud") Integer idServicioSolicitud,
            @RequestParam("idPersona") Integer idPersona) throws Exception {
        ServicioSolicitud servicioSolicitud = servicioSolicitudSvc.buscarServicioSolicitud(idServicioSolicitud);
        if (servicioSolicitud == null) {
            return ResponseEntity.badRequest().body(null); // Manejo de error si el cargo no se encuentra
        }
        Persona persona = personaSvc.buscarPersonaPorId(idPersona);
        if (persona == null) {
            return ResponseEntity.badRequest().body(null); // Manejo de error si el cargo no se encuentra
        }
        Solicitud solicitud = solicitudSvc.buscarSolicitud(id);
        if (solicitud == null) {
            throw new RecursoNoEncontradoExcepcion("El Id Recibido No Existe: " + id);
        } else {
            solicitud.setEstadoSolicitud(nombre);
            solicitud.setFechaSolicitud(fechaSolicitud);
            solicitud.setServicioSolicitud(servicioSolicitud);
            solicitud.setPersona(persona);
            solicitudSvc.guardarSolicitud(solicitud);
            return ResponseEntity.ok(solicitud);
        }
    }

    @DeleteMapping("/solicitudes/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarSolicitud(@PathVariable Integer id){
        Solicitud solicitud = solicitudSvc.buscarSolicitud(id);
        if(solicitud == null){
            throw new RecursoNoEncontradoExcepcion("El Id Recibido No Existe: " + id);
        }else {
            solicitudSvc.eliminarSolicitud(solicitud);
            // json {"eliminado": "true"}
            Map<String, Boolean> respuesta = new HashMap<>();
            respuesta.put("eliminado", Boolean.TRUE);
            return ResponseEntity.ok(respuesta);
        }
    }
}
