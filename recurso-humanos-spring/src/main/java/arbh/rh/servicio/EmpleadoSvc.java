package arbh.rh.servicio;

import arbh.rh.modelo.Empleado;
import arbh.rh.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmpleadoSvc implements IEmpleadoServicio{
    @Autowired
    private EmpleadoRepositorio  empleadoRepositorio;

    @Override
    public List<Empleado> listarEmpleado() {
        return empleadoRepositorio.findAll();
    }
    @Override
    public Empleado buscarEmpleadoPorId(Integer idEmpleado) {
        Empleado empleado = empleadoRepositorio.findById(idEmpleado).orElse(null);
        return empleado;
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepositorio.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Empleado empleado) {
        empleadoRepositorio.delete(empleado);
    }
}
