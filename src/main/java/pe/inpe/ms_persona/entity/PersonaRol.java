package pe.inpe.ms_persona.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.*;
import pe.inpe.ms_persona.util.auditoria.AuditModel;

import java.time.LocalDate;

@Entity
@Table(name = "persona_rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaRol extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona_rol")
    private Long idPersonaRol;

    @Column(name = "id_persona")
    private Long idPersona;

    @Column(name = "tipo_persona_rol_id")
    private Long tipoPersonaRolId;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "estado")
    private Boolean estado = true;

    @Column(name = "observaciones")
    private String observaciones;
}
