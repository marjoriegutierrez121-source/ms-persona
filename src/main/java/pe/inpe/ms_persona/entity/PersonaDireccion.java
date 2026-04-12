package pe.inpe.ms_persona.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pe.inpe.ms_persona.util.AuditModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "persona_direccion")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PersonaDireccion extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona_direccion")
    private Long idPersonaDireccion;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @Column(name = "id_location")
    private Long idLocation;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "referencia")
    private String referencia;

    @Column(name = "es_principal")
    private Boolean esPrincipal =false;
}