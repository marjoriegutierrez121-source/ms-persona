package pe.inpe.ms_persona.service;

import pe.inpe.ms_persona.dto.PersonaRolRequestDTO;
import pe.inpe.ms_persona.dto.PersonaRolResponseDTO;
import pe.inpe.ms_persona.dto.ValidacionRolDTO;

import java.util.List;

    public interface PersonaRolService {

        PersonaRolResponseDTO addRolToPersona(Long idPersona, PersonaRolRequestDTO request);

        List<PersonaRolResponseDTO> getRolesByPersona(Long idPersona);

        ValidacionRolDTO validarSiTieneRol(Long idPersona, Long tipoRolId);
    }
