package arbh.rh.repositorio;

import arbh.rh.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {
}
