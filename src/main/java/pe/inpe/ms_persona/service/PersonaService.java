package pe.inpe.ms_persona.service;

import pe.inpe.ms_persona.dto.*;

import java.util.List;

public interface PersonaService {
    PersonaResponseDTO addPersona(PersonaRequestDTO request);

    PersonaResponseDTO getPersonaXID(Long id);

    PersonaResponseDTO updatePersona(Long id, PersonaRequestDTO request);

    List<PersonaResponseDTO> getPersonaXNumeroDocumento(String numeroDocumento);

    List<PersonaResponseDTO> getPersonaXNombre(String nombre);

    ValidacionPersonaDTO validarExistenciaPorDocumento(String numeroDocumento);

    ValidacionRolDTO validarSiTieneRol(Long idPersona, Long tipoRolId);

    List<PersonaDireccionResponseDTO> obtenerDireccionesPorPersona(Long idPersona);
}
