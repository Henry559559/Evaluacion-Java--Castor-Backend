package arbh.rh.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idSolicitud;
    String  estadoSolicitud;
    Date    fechaSolicitud;

    @ManyToOne
    @JoinColumn(name = "servicioSolicitud_Id")
    private ServicioSolicitud servicioSolicitud;

    @ManyToOne
    @JoinColumn(name = "personsa_Id")
    private Persona persona;
}
