package pe.inpe.ms_persona.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.inpe.ms_persona.dto.*;
import pe.inpe.ms_persona.entity.Persona;
import pe.inpe.ms_persona.entity.PersonaDireccion;
import pe.inpe.ms_persona.exception.ResourceNotFoundException;
import pe.inpe.ms_persona.mapper.PersonaDireccionMapper;
import pe.inpe.ms_persona.mapper.PersonaMapper;
import pe.inpe.ms_persona.repository.IPersonaDireccionRepository;
import pe.inpe.ms_persona.repository.IPersonaRepository;
import pe.inpe.ms_persona.service.PersonaService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    private final IPersonaRepository personaRepository;
    private final IPersonaDireccionRepository personaDireccionRepository;
    private final PersonaMapper personaMapper;
    private final PersonaDireccionMapper personaDireccionMapper;

    @Override
    @Transactional
    public PersonaResponseDTO addPersona(PersonaRequestDTO request) {
        log.info("Creando persona con documento: {}", request.getNumeroDocumento());

        Persona persona = personaMapper.toEntity(request);
        persona.setEstado(true);

        Persona saved = personaRepository.save(persona);
        log.info("Persona creada con ID: {}", saved.getIdPersona());

        return personaMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaResponseDTO getPersonaXID(Long id) {
        log.info("Buscando persona con ID: {}", id);
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con ID: " + id));
        return personaMapper.toResponseDTO(persona);
    }

    @Override
    @Transactional
    public PersonaResponseDTO updatePersona(Long id, PersonaRequestDTO request) {
        log.info("Actualizando persona con ID: {}", id);
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con ID: " + id));

        personaMapper.updateEntity(request, persona);

        Persona updated = personaRepository.save(persona);
        log.info("Persona actualizada con ID: {}", updated.getIdPersona());

        return personaMapper.toResponseDTO(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonaResponseDTO> getPersonaXNumeroDocumento(String numeroDocumento) {
        log.info("Buscando personas con numeroDocumento: {}", numeroDocumento);
        return personaRepository.findByNumeroDocumento(numeroDocumento)
                .stream()
                .map(personaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonaResponseDTO> getPersonaXNombre(String nombre) {
        log.info("Buscando personas con nombre que contenga: {}", nombre);
        return personaRepository.findByNombresContainingIgnoreCase(nombre)
                .stream()
                .map(personaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ValidacionPersonaDTO validarExistenciaPorDocumento(String numeroDocumento) {
        log.info("Validando existencia de persona con documento: {}", numeroDocumento);

        List<Persona> personas = personaRepository.findByNumeroDocumento(numeroDocumento);
        boolean existe = !personas.isEmpty();

        return ValidacionPersonaDTO.builder()
                .existe(existe)
                .idPersona(existe ? personas.get(0).getIdPersona() : null)
                .mensaje(existe ? "Persona encontrada" : "Persona no encontrada")
                .build();
    }

    @Override
    public ValidacionRolDTO validarSiTieneRol(Long idPersona, Long tipoRolId) {
        // Este método se delega a PersonaRolService
        return ValidacionRolDTO.builder()
                .tieneRol(false)
                .mensaje("Usar PersonaRolService para esta validación")
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonaDireccionResponseDTO> obtenerDireccionesPorPersona(Long idPersona) {
        log.info("Obteniendo direcciones de persona ID: {}", idPersona);

        if (!personaRepository.existsById(idPersona)) {
            throw new ResourceNotFoundException("Persona no encontrada con ID: " + idPersona);
        }

        List<PersonaDireccion> direcciones = personaDireccionRepository.findByIdPersona(idPersona);
        return direcciones.stream()
                .map(personaDireccionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}