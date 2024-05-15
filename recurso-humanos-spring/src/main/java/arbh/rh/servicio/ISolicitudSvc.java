package arbh.rh.servicio;

import arbh.rh.modelo.Solicitud;

import java.util.List;

public interface ISolicitudSvc {
    public List<Solicitud> listarSolicitud();

    public Solicitud buscarSolicitud(Integer idSolicitud);

    public Solicitud guardarSolicitud(Solicitud solicitud);

    public void eliminarSolicitud(Solicitud solicitud);
}
