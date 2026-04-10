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
                        .error(null)
                        .build()
        );
    }
    // GET /persona/{id}
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseDTO<PersonaResponseDTO>> getPersonaXId(
            @PathVariable Long id) {
        log.info("Recibida petición obtener persona/{}", id);
        PersonaResponseDTO data = personaService.getPersonaXID(id);
        return ResponseEntity.ok(
                GenericResponseDTO.<PersonaResponseDTO>builder()
                        .response(data)
                        .error(null)
                        .build()
        );
    }
    // PUT /persona/{id}
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDTO<PersonaResponseDTO>> updatePersona(
            @PathVariable Long id,
            @RequestBody PersonaRequestDTO request) {
        log.info("Recibida petición PUT /persona/{}", id);
        PersonaResponseDTO data = personaService.updatePersona(id, request);
        return ResponseEntity.ok(
                GenericResponseDTO.<PersonaResponseDTO>builder()
                        .response(data)
                        .error(null)
                        .build()
        );
    }
    // GET /persona?numeroDocumento=12345678
    // GET /persona?nombre=juan
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
                            .error(null)
                            .build()
            );
        }
        if (nombre != null && !nombre.isBlank()) {
            log.info("Recibida petición obtener persona?nombre={}", nombre);
            List<PersonaResponseDTO> data = personaService.getPersonaXNombre(nombre);
            return ResponseEntity.ok(
                    GenericResponseDTO.<List<PersonaResponseDTO>>builder()
                            .response(data)
                            .error(null)
                            .build()
            );
        }
        throw new BadRequestException(
                "Debe proporcionar al menos un parámetro de búsqueda: 'numeroDocumento' o 'nombre'");
    }

    // POST /persona/{id}/roles
    @PostMapping("/{id}/roles")
    public ResponseEntity<GenericResponseDTO<PersonaRolResponseDTO>> addRol(
            @PathVariable Long id,
            @RequestBody PersonaRolRequestDTO request) {
        log.info("Recibida petición agregar rol persona/{}/roles", id);
        PersonaRolResponseDTO data = personaRolService.addRolToPersona(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                GenericResponseDTO.<PersonaRolResponseDTO>builder()
                        .response(data)
                        .error(null)
                        .build()
        );
    }
    // GET /personas/{id}/roles
    @GetMapping("/{id}/roles")
    public ResponseEntity<GenericResponseDTO<List<PersonaRolResponseDTO>>> getRoles(
            @PathVariable Long id) {
        log.info("Recibida petición obtener rol persona/{}/roles", id);
        List<PersonaRolResponseDTO> data = personaRolService.getRolesByPersona(id);
        return ResponseEntity.ok(
                GenericResponseDTO.<List<PersonaRolResponseDTO>>builder()
                        .response(data)
                        .error(null)
                        .build()
        );
    }
}
