package pe.inpe.ms_persona.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pe.inpe.ms_persona.dto.PersonaRequestDTO;
import pe.inpe.ms_persona.dto.PersonaResponseDTO;
import pe.inpe.ms_persona.entity.Persona;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    // RequestDTO → Entity (para crear)
    @Mapping(target = "idPersona", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "registrationUser", ignore = true)
    @Mapping(target = "lastModificationDate", ignore = true)
    @Mapping(target = "lastModificationUser", ignore = true)
    @Mapping(target = "direcciones", ignore = true)
    @Mapping(target = "roles", ignore = true)
    Persona toEntity(PersonaRequestDTO requestDTO);

    // Entity → ResponseDTO
    @Mapping(source = "idPersona", target = "idPersona")
    @Mapping(target = "nombreCompleto", expression = "java(entity.getNombres() + \" \" + entity.getApellidoPaterno() + (entity.getApellidoMaterno() != null ? \" \" + entity.getApellidoMaterno() : \"\"))")
    PersonaResponseDTO toResponseDTO(Persona entity);

    // Actualizar entity existente (para PUT)
    @Mapping(target = "idPersona", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "registrationUser", ignore = true)
    @Mapping(target = "lastModificationDate", ignore = true)
    @Mapping(target = "lastModificationUser", ignore = true)
    @Mapping(target = "direcciones", ignore = true)
    @Mapping(target = "roles", ignore = true)
    void updateEntity(PersonaRequestDTO requestDTO, @MappingTarget Persona entity);
}
