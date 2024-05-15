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
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idEmpleado;
    Double  sueldo;
    String  nombre;
    String  cedula;
    String  departamento;
    String  foto;
    Date    fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "cargo_Id")
    private Cargo cargo;
}
