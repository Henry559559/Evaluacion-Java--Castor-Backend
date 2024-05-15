package arbh.rh.repositorio;

import arbh.rh.modelo.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepositorio extends JpaRepository<Solicitud, Integer> {
}
