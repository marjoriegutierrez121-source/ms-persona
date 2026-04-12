package pe.inpe.ms_persona.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.inpe.ms_persona.dto.*;
import pe.inpe.ms_persona.exception.BadRequestException;
import pe.inpe.ms_persona.service.PersonaRolService;
import pe.inpe.ms_persona.service.PersonaService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/persona")
public class PersonaController {

    private final PersonaService personaService;
    private final PersonaRolService personaRolService;

    @PostMapping
    public ResponseEntity<GenericResponseDTO<PersonaResponseDTO>> addPersona(
            @RequestBody PersonaRequestDTO request) {
        log.info("Recibida petición para agregar persona");
        PersonaResponseDTO data = personaService.addPersona(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                GenericResponseDTO.<PersonaResponseDTO>builder()
                        .response(data)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseDTO<PersonaResponseDTO>> getPersonaXId(
            @PathVariable Long id) {
        log.info("Recibida petición obtener persona/{}", id);
        PersonaResponseDTO data = personaService.getPersonaXID(id);
        return ResponseEntity.ok(
                GenericResponseDTO.<PersonaResponseDTO>builder()
                        .response(data)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDTO<PersonaResponseDTO>> updatePersona(
            @PathVariable Long id,
            @RequestBody PersonaRequestDTO request) {
        log.info("Recibida petición PUT /persona/{}", id);
        PersonaResponseDTO data = personaService.updatePersona(id, request);
        return ResponseEntity.ok(
                GenericResponseDTO.<PersonaResponseDTO>builder()
                        .response(data)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<GenericResponseDTO<List<PersonaResponseDTO>>> buscar(
            @RequestParam(required = false) String numeroDocumento,
            @RequestParam(required = false) String nombre) {
        if (numeroDocumento != null && !numeroDocumento.isBlank()) {
            log.info("Recibida petición obtener persona?numeroDocumento={}", numeroDocumento);
            List<PersonaResponseDTO> data = personaService.getPersonaXNumeroDocumento(numeroDocumento);
            return ResponseEntity.ok(
                    GenericResponseDTO.<List<PersonaResponseDTO>>builder()
                            .response(data)
                            .build()
            );
        }
        if (nombre != null && !nombre.isBlank()) {
            log.info("Recibida petición obtener persona?nombre={}", nombre);
            List<PersonaResponseDTO> data = personaService.getPersonaXNombre(nombre);
            return ResponseEntity.ok(
                    GenericResponseDTO.<List<PersonaResponseDTO>>builder()
                            .response(data)
                            .build()
            );
        }
        throw new BadRequestException(
                "Debe proporcionar al menos un parámetro de búsqueda: 'numeroDocumento' o 'nombre'");
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<GenericResponseDTO<PersonaRolResponseDTO>> addRol(
            @PathVariable Long id,
            @RequestBody PersonaRolRequestDTO request) {
        log.info("Recibida petición agregar rol persona/{}/roles", id);
        PersonaRolResponseDTO data = personaRolService.addRolToPersona(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                GenericResponseDTO.<PersonaRolResponseDTO>builder()
                        .response(data)
                        .build()
        );
    }

    @GetMapping("/{id}/roles")
    public ResponseEntity<GenericResponseDTO<List<PersonaRolResponseDTO>>> getRoles(
            @PathVariable Long id) {
        log.info("Recibida petición obtener rol persona/{}/roles", id);
        List<PersonaRolResponseDTO> data = personaRolService.getRolesByPersona(id);
        return ResponseEntity.ok(
                GenericResponseDTO.<List<PersonaRolResponseDTO>>builder()
                        .response(data)
                        .build()
        );
    }

    @GetMapping("/validar/documento/{numeroDocumento}")
    public ResponseEntity<GenericResponseDTO<ValidacionPersonaDTO>> validarExistencia(
            @PathVariable String numeroDocumento) {
        log.info("Validando existencia de persona con documento: {}", numeroDocumento);
        ValidacionPersonaDTO data = personaService.validarExistenciaPorDocumento(numeroDocumento);
        return ResponseEntity.ok(
                GenericResponseDTO.<ValidacionPersonaDTO>builder()
                        .response(data)
                        .build()
        );
    }

    @GetMapping("/{id}/tiene-rol/{tipoRolId}")
    public ResponseEntity<GenericResponseDTO<ValidacionRolDTO>> tieneRolActivo(
            @PathVariable Long id,
            @PathVariable Long tipoRolId) {
        log.info("Verificando si persona {} tiene rol activo {}", id, tipoRolId);
        ValidacionRolDTO data = personaRolService.validarSiTieneRol(id, tipoRolId);
        return ResponseEntity.ok(
                GenericResponseDTO.<ValidacionRolDTO>builder()
                        .response(data)
                        .build()
        );
    }

    @GetMapping("/{id}/direcciones")
    public ResponseEntity<GenericResponseDTO<List<PersonaDireccionResponseDTO>>> getDirecciones(
            @PathVariable Long id) {
        log.info("Obteniendo direcciones de persona/{}", id);
        List<PersonaDireccionResponseDTO> data = personaService.obtenerDireccionesPorPersona(id);
        return ResponseEntity.ok(
                GenericResponseDTO.<List<PersonaDireccionResponseDTO>>builder()
                        .response(data)
                        .build()
        );
    }
}