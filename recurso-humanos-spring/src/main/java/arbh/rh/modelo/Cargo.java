package arbh.rh.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCargo;
    String  cargo;
}
