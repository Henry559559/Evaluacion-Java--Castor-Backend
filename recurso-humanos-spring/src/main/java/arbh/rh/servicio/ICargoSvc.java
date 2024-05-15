package arbh.rh.servicio;

import arbh.rh.modelo.Cargo;

import java.util.List;

public interface ICargoSvc {
    public List<Cargo> listarCargo();

    public Cargo buscarCargoPorId(Integer idCargo);
}
