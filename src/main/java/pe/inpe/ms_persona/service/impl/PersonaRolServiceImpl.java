package pe.inpe.ms_persona.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.inpe.ms_persona.dto.PersonaRolRequestDTO;
import pe.inpe.ms_persona.dto.PersonaRolResponseDTO;
import pe.inpe.ms_persona.dto.ValidacionRolDTO;
import pe.inpe.ms_persona.entity.Persona;
import pe.inpe.ms_persona.entity.PersonaRol;
import pe.inpe.ms_persona.exception.ResourceNotFoundException;
import pe.inpe.ms_persona.mapper.PersonaRolMapper;
import pe.inpe.ms_persona.repository.IPersonaRepository;
import pe.inpe.ms_persona.repository.IPersonaRolRepository;
import pe.inpe.ms_persona.service.PersonaRolService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonaRolServiceImpl implements PersonaRolService {

    private final IPersonaRepository personaRepository;
    private final IPersonaRolRepository personaRolRepository;
    private final PersonaRolMapper personaRolMapper;

    @Override
    @Transactional
    public PersonaRolResponseDTO addRolToPersona(Long idPersona, PersonaRolRequestDTO request) {
        log.info("Asignando rol {} a persona con ID: {}", request.getTipoPersonaRolId(), idPersona);

        Persona persona = personaRepository.findById(idPersona)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con ID: " + idPersona));

        PersonaRol personaRol = personaRolMapper.toEntity(request);
        personaRol.setIdPersona(persona.getIdPersona());

        if (personaRol.getEstado() == null) {
            personaRol.setEstado(true);
        }

        personaRol.setRegistrationDate(LocalDateTime.now());
        personaRol.setRegistrationUser("SYSTEM");
        personaRol.setLastModificationDate(LocalDateTime.now());
        personaRol.setLastModificationUser("SYSTEM");

        PersonaRol saved = personaRolRepository.save(personaRol);
        log.info("Rol asignado con ID: {}", saved.getIdPersonaRol());

        return personaRolMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonaRolResponseDTO> getRolesByPersona(Long idPersona) {
        log.info("Obteniendo roles de persona con ID: {}", idPersona);

        if (!personaRepository.existsById(idPersona)) {
            throw new ResourceNotFoundException("Persona no encontrada con ID: " + idPersona);
        }

        return personaRolRepository.findByIdPersona(idPersona)
                .stream()
                .map(personaRolMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ValidacionRolDTO validarSiTieneRol(Long idPersona, Long tipoRolId) {
        log.info("Verificando si persona {} tiene rol activo {}", idPersona, tipoRolId);

        if (!personaRepository.existsById(idPersona)) {
            return ValidacionRolDTO.builder()
                    .tieneRol(false)
                    .mensaje("Persona no encontrada")
                    .build();
        }

        boolean tieneRol = personaRolRepository
                .existsByIdPersonaAndTipoPersonaRolIdAndEstadoTrue(idPersona, tipoRolId);

        return ValidacionRolDTO.builder()
                .tieneRol(tieneRol)
                .idPersonaRol(tieneRol ?
                        personaRolRepository.findByIdPersonaAndTipoPersonaRolIdAndEstadoTrue(idPersona, tipoRolId)
                        .map(PersonaRol::getIdPersonaRol).orElse(null) : null)
                .mensaje(tieneRol ? "Rol activo encontrado" : "No tiene el rol activo")
                .build();
    }
}