package arbh.rh.controlador;

import arbh.rh.excepcion.RecursoNoEncontradoExcepcion;
import arbh.rh.modelo.Persona;
import arbh.rh.modelo.ServicioSolicitud;
import arbh.rh.servicio.ServicioSolicitudSvc;
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
public class ServicioSolicitudControlador {

    private static final Logger logger =
            LoggerFactory.getLogger(ServicioSolicitudControlador.class);

    @Autowired
    private ServicioSolicitudSvc servicioSolicitudSvc;

    @GetMapping("/servicio-solicitudes")
    public List<ServicioSolicitud> obtenerPersona(){
        var servicioSolicitud = servicioSolicitudSvc.listarServicioSolicitud();
        //empleados.forEach((empleado -> logger.info(empleado.toString())));
        return servicioSolicitud;
    }

    @PostMapping("/servicio-solicitudes")
    public ResponseEntity<ServicioSolicitud> agregarPersona(
            @RequestParam("descripcionServicio") String descripcionServicio) throws Exception {

        ServicioSolicitud nuevaServicioSolicitud = new ServicioSolicitud();
        nuevaServicioSolicitud.setDescripcionServicio(descripcionServicio);
        ServicioSolicitud servicioSolicitudGuardada = servicioSolicitudSvc.guardarServicioSolicitud(nuevaServicioSolicitud);
        return ResponseEntity.ok(servicioSolicitudGuardada);
    }

    @GetMapping("/servicio-solicitudes/{id}")
    public ResponseEntity<ServicioSolicitud>
    obtenerServicioSolicitudPorId(@PathVariable Integer id){
        ServicioSolicitud servicioSolicitud = servicioSolicitudSvc.buscarServicioSolicitud(id);
        if(servicioSolicitud == null){
            throw new RecursoNoEncontradoExcepcion("No Se Encontro El id: " + id);
        }else {
            return ResponseEntity.ok(servicioSolicitud);
        }
    }

    @PutMapping("/servicio-solicitudes/{id}")
    public ResponseEntity<ServicioSolicitud> actualizarServicioSolicitud(
            @PathVariable Integer id,
            @RequestParam("descripcionServicio") String descripcionServicio) throws Exception {

        ServicioSolicitud servicioSolicitud = servicioSolicitudSvc.buscarServicioSolicitud(id);
        if (servicioSolicitud == null) {
            throw new RecursoNoEncontradoExcepcion("El Id Recibido No Existe: " + id);
        } else {
            servicioSolicitud.setDescripcionServicio(descripcionServicio);
            servicioSolicitudSvc.guardarServicioSolicitud(servicioSolicitud);
            return ResponseEntity.ok(servicioSolicitud);
        }
    }

    @DeleteMapping("/servicio-solicitudes/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarServicioSolicitud(@PathVariable Integer id){
        ServicioSolicitud servicioSolicitud = servicioSolicitudSvc.buscarServicioSolicitud(id);
        if(servicioSolicitud == null){
            throw new RecursoNoEncontradoExcepcion("El Id Recibido No Existe: " + id);
        }else {
            servicioSolicitudSvc.eliminarServicoSolicitud(servicioSolicitud);
            // json {"eliminado": "true"}
            Map<String, Boolean> respuesta = new HashMap<>();
            respuesta.put("eliminado", Boolean.TRUE);
            return ResponseEntity.ok(respuesta);
        }
    }
}
