package arbh.rh.servicio;

import arbh.rh.modelo.ServicioSolicitud;
import arbh.rh.repositorio.ServicoSolicitudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioSolicitudSvc implements IServicioSolicitudSvc{
    @Autowired
    private ServicoSolicitudRepositorio servicoSolicitudRepositorio;

    @Override
    public List<ServicioSolicitud> listarServicioSolicitud(){
        return servicoSolicitudRepositorio.findAll();
    }

    @Override
    public ServicioSolicitud buscarServicioSolicitud(Integer idServicioSolicitud){
        return servicoSolicitudRepositorio.findById(idServicioSolicitud).orElse(null);
    }

    @Override
    public ServicioSolicitud guardarServicioSolicitud(ServicioSolicitud servicioSolicitud){
        return  servicoSolicitudRepositorio.save(servicioSolicitud);
    }

    @Override
    public void eliminarServicoSolicitud(ServicioSolicitud servicioSolicitud){
        servicoSolicitudRepositorio.delete(servicioSolicitud);
    }
}
