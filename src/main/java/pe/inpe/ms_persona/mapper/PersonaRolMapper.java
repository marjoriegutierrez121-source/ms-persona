package pe.inpe.ms_persona.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pe.inpe.ms_persona.dto.PersonaRolRequestDTO;
import pe.inpe.ms_persona.dto.PersonaRolResponseDTO;
import pe.inpe.ms_persona.entity.PersonaRol;

@Mapper(componentModel = "spring")
public interface PersonaRolMapper {

    // RequestDTO → Entity
    @Mapping(target = "idPersonaRol", ignore = true)
    @Mapping(target = "persona", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "registrationUser", ignore = true)
    @Mapping(target = "lastModificationDate", ignore = true)
    @Mapping(target = "lastModificationUser", ignore = true)
    PersonaRol toEntity(PersonaRolRequestDTO requestDTO);

    // Entity → ResponseDTO
    @Mapping(source = "idPersonaRol", target = "idPersonaRol")
    @Mapping(source = "persona.idPersona", target = "personaId")
    @Mapping(target = "personaNombre", expression = "java(entity.getPersona().getNombres() + \" \" + entity.getPersona().getApellidoPaterno())")
    @Mapping(source = "rolId", target = "rolId")
    @Mapping(source = "estado", target = "estado")
    PersonaRolResponseDTO toResponseDTO(PersonaRol entity);

    // Actualizar entity
    @Mapping(target = "idPersonaRol", ignore = true)
    @Mapping(target = "persona", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "registrationUser", ignore = true)
    @Mapping(target = "lastModificationDate", ignore = true)
    @Mapping(target = "lastModificationUser", ignore = true)
    void updateEntity(PersonaRolRequestDTO requestDTO, @MappingTarget PersonaRol entity);
}