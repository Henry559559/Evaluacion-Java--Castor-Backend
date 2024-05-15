package arbh.rh.controlador;

import arbh.rh.excepcion.RecursoNoEncontradoExcepcion;
import arbh.rh.modelo.Cargo;
import arbh.rh.modelo.Empleado;
import arbh.rh.servicio.CargoSvc;
import arbh.rh.servicio.EmpleadoSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("rh-app")
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoControlador {

    private static final Logger logger =
            LoggerFactory.getLogger(EmpleadoControlador.class);


    @Autowired
    private FileService fileService;

    @Autowired
    private EmpleadoSvc empleadoSvc;

    @Autowired
    private CargoSvc cargoSvc;

    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados(){
        var empleados = empleadoSvc.listarEmpleado();
        //empleados.forEach((empleado -> logger.info(empleado.toString())));
        return empleados;
    }

    @PostMapping("/empleados")
    public ResponseEntity<Empleado> agregarEmpleado(
            @RequestParam("nombre") String nombre,
            @RequestParam("cedula") String cedula,
            @RequestParam("departamento") String departamento,
            @RequestParam("fechaIngreso") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaIngreso,
            @RequestParam("sueldo") Double sueldo,
            @RequestParam("idCargo") Integer idCargo,
            @RequestParam(value = "foto", required = false) MultipartFile fotoFile) throws Exception {
        Cargo cargo = cargoSvc.buscarCargoPorId(idCargo);
        if (cargo == null) {
            return ResponseEntity.badRequest().body(null); // Manejo de error si el cargo no se encuentra
        }
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNombre(nombre);
        nuevoEmpleado.setCedula(cedula);
        nuevoEmpleado.setDepartamento(departamento);
        nuevoEmpleado.setFechaIngreso(fechaIngreso);
        nuevoEmpleado.setSueldo(sueldo);
        nuevoEmpleado.setCargo(cargo);
        if (fotoFile != null && !fotoFile.isEmpty()) {
            String rutaArchivo = fileService.guardarArchivo(fotoFile);
            nuevoEmpleado.setFoto(rutaArchivo);
        }

        Empleado empleadoGuardado = empleadoSvc.guardarEmpleado(nuevoEmpleado);
        return ResponseEntity.ok(empleadoGuardado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado>
    obtenerEmpleadoPorId(@PathVariable Integer id){
        Empleado empleado = empleadoSvc.buscarEmpleadoPorId(id);
        if(empleado == null){
            throw new RecursoNoEncontradoExcepcion("No Se Encontro El id: " + id);
        }else {
            return ResponseEntity.ok(empleado);
        }
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(
            @PathVariable Integer id,
            @RequestParam("nombre") String nombre,
            @RequestParam("cedula") String cedula,
            @RequestParam("departamento") String departamento,
            @RequestParam("fechaIngreso") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaIngreso,
            @RequestParam("sueldo") Double sueldo,
            @RequestParam("idCargo") Integer idCargo,
            @RequestParam(value = "foto", required = false) MultipartFile fotoFile) throws Exception {
        Cargo cargo = cargoSvc.buscarCargoPorId(idCargo);
        if (cargo == null) {
            return ResponseEntity.badRequest().body(null); // Manejo de error si el cargo no se encuentra
        }
        Empleado empleado = empleadoSvc.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExcepcion("El Id Recibido No Existe: " + id);
        } else {
            empleado.setNombre(nombre);
            empleado.setCedula(cedula);
            empleado.setDepartamento(departamento);
            empleado.setFechaIngreso(fechaIngreso);
            empleado.setCargo(cargo);
            empleado.setSueldo(sueldo);
            if (fotoFile != null && !fotoFile.isEmpty()) {
                String rutaArchivo = fileService.guardarArchivo(fotoFile);
                empleado.setFoto(rutaArchivo);
            }
            empleadoSvc.guardarEmpleado(empleado);
            return ResponseEntity.ok(empleado);
        }
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Integer id){
        Empleado empleado = empleadoSvc.buscarEmpleadoPorId(id);
        if(empleado == null){
            throw new RecursoNoEncontradoExcepcion("El Id Recibido No Existe: " + id);
        }else {
            empleadoSvc.eliminarEmpleado(empleado);
            // json {"eliminado": "true"}
            Map<String, Boolean> respuesta = new HashMap<>();
            respuesta.put("eliminado", Boolean.TRUE);
            return ResponseEntity.ok(respuesta);
        }
    }
}
