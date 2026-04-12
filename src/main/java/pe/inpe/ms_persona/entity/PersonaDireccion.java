package pe.inpe.ms_persona.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.*;
import pe.inpe.ms_persona.util.auditoria.AuditModel;

@Entity
@Table(name = "persona_direccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDireccion extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona_direccion")
    private Long idPersonaDireccion;

    @Column(name = "id_persona")
    private Long idPersona;

    @Column(name = "id_location")
    private Long idLocation;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "referencia")
    private String referencia;

    @Column(name = "es_principal")
    private Boolean esPrincipal = false;

}