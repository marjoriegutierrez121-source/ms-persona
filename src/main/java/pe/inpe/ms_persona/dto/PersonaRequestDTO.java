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
public class PersonaRequestDTO {
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Long tipoDocumentoId;
    private String numeroDocumento;
    private LocalDate fechaNacimiento;
    private Long sexoId;
    private String telefono;
    private String email;
    private Boolean estado;
}
