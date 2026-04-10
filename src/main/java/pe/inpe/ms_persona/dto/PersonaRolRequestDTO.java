package pe.inpe.ms_persona.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaRolRequestDTO {
    private Long rolId;
    private Boolean estado;
}
