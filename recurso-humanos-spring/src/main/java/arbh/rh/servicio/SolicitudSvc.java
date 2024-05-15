package arbh.rh.servicio;

import arbh.rh.modelo.Solicitud;
import arbh.rh.repositorio.SolicitudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudSvc implements ISolicitudSvc{

    @Autowired
    private SolicitudRepositorio solicitudRepositorio;

    @Override
    public List<Solicitud> listarSolicitud(){
        return solicitudRepositorio.findAll();
    }

    @Override
    public Solicitud buscarSolicitud(Integer idSolicitud){
        return solicitudRepositorio.findById(idSolicitud).orElse(null);
    }

    @Override
    public Solicitud guardarSolicitud(Solicitud solicitud){
        return solicitudRepositorio.save(solicitud);
    }

    @Override
    public void eliminarSolicitud(Solicitud solicitud){
        solicitudRepositorio.delete(solicitud);
    }
}
