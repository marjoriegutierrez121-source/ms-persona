package pe.inpe.ms_persona.service;

import pe.inpe.ms_persona.dto.PersonaRolRequestDTO;
import pe.inpe.ms_persona.dto.PersonaRolResponseDTO;

import java.util.List;

public interface PersonaRolService {
    // POST /personas/{id}/roles
    PersonaRolResponseDTO addRolToPersona(Long idPersona, PersonaRolRequestDTO request);
    // GET /personas/{id}/roles
    List<PersonaRolResponseDTO> getRolesByPersona(Long idPersona);
}
