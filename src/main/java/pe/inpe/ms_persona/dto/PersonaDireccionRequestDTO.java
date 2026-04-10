package pe.inpe.ms_persona.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDireccionRequestDTO {

    private Long personaId;
    private Long idLocation;
    private String direccion;
    private String referencia;
}
