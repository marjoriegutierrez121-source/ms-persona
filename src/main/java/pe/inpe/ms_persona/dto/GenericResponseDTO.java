package pe.inpe.ms_persona.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponseDTO<T> {

    private T response;

    private ErrorMessageDTO error;

}
