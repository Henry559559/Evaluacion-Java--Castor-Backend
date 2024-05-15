package arbh.rh.controlador;

import arbh.rh.excepcion.RecursoNoEncontradoExcepcion;
import arbh.rh.modelo.Cargo;
import arbh.rh.servicio.CargoSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rh-app")
@CrossOrigin(value = "http://localhost:3000")
public class CargoControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(CargoControlador.class);

    @Autowired
    private CargoSvc cargoSvc;

    @GetMapping("/cargo")
    public List<Cargo> obtenerCArgo(){
        var cargos = cargoSvc.listarCargo();
        //empleados.forEach((empleado -> logger.info(empleado.toString())));
        return cargos;
    }

    @GetMapping("/cargo/{id}")
    public ResponseEntity<Cargo>
    obtenerCargoPorId(@PathVariable Integer id){
        Cargo cargo = cargoSvc.buscarCargoPorId(id);
        if(cargo == null){
            throw new RecursoNoEncontradoExcepcion("No Se Encontro El id: " + id);
        }else {
            return ResponseEntity.ok(cargo);
        }
    }
}
