package pe.inpe.ms_persona.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaResponseDTO {
    private Long idPersona;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreCompleto;  // Campo calculado: nombres + apellidos
    private Long tipoDocumentoId;
    private String numeroDocumento;
    private LocalDate fechaNacimiento;
    private Long sexoId;
    private String telefono;
    private String email;
    private Boolean estado;
}
