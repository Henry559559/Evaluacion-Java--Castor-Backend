package arbh.rh.servicio;

import arbh.rh.modelo.Cargo;
import arbh.rh.modelo.Persona;
import arbh.rh.repositorio.CargoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoSvc implements ICargoSvc {

    @Autowired
    private CargoRepositorio cargoRepositorio;

    @Override
    public List<Cargo> listarCargo(){
        return cargoRepositorio.findAll();
    }
    @Override
    public Cargo buscarCargoPorId(Integer idCargo) {
        return cargoRepositorio.findById(idCargo).orElse(null);
    }
}
