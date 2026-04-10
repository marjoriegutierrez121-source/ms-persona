package pe.inpe.ms_persona.service;

import pe.inpe.ms_persona.dto.PersonaRequestDTO;
import pe.inpe.ms_persona.dto.PersonaResponseDTO;

import java.util.List;

public interface PersonaService {
    PersonaResponseDTO addPersona(PersonaRequestDTO request);

    PersonaResponseDTO getPersonaXID(Long id);

    PersonaResponseDTO updatePersona(Long id, PersonaRequestDTO request);

    List<PersonaResponseDTO> getPersonaXNumeroDocumento(String numeroDocumento);
    // GET /personas?nombre=juan
    List<PersonaResponseDTO> getPersonaXNombre(String nombre);
}
