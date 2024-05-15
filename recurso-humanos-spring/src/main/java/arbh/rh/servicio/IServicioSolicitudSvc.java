package arbh.rh.servicio;

import arbh.rh.modelo.ServicioSolicitud;

import java.util.List;

public interface IServicioSolicitudSvc {
    public List<ServicioSolicitud> listarServicioSolicitud();

    public ServicioSolicitud buscarServicioSolicitud(Integer idServicioSolicitud);

    public ServicioSolicitud guardarServicioSolicitud(ServicioSolicitud servicioSolicitud);

    public void eliminarServicoSolicitud(ServicioSolicitud servicioSolicitud);
}
