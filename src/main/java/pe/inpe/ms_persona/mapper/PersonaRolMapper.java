package pe.inpe.ms_persona.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pe.inpe.ms_persona.dto.PersonaRolRequestDTO;
import pe.inpe.ms_persona.dto.PersonaRolResponseDTO;
import pe.inpe.ms_persona.entity.PersonaRol;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaRolMapper {

    @Mapping(target = "idPersonaRol", ignore = true)
    @Mapping(target = "persona", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "registrationUser", ignore = true)
    @Mapping(target = "lastModificationDate", ignore = true)
    @Mapping(target = "lastModificationUser", ignore = true)
    @Mapping(target = "estado", constant = "true")
    PersonaRol toEntity(PersonaRolRequestDTO requestDTO);

    @Mapping(source = "idPersonaRol", target = "idPersonaRol")
    @Mapping(source = "persona.idPersona", target = "personaId")
    @Mapping(source = "tipoPersonaRolId", target = "tipoPersonaRolId")
    @Mapping(source = "fechaInicio", target = "fechaInicio")
    @Mapping(source = "fechaFin", target = "fechaFin")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "observaciones", target = "observaciones")
    PersonaRolResponseDTO toResponseDTO(PersonaRol entity);

    List<PersonaRolResponseDTO> toResponseDTOList(List<PersonaRol> entities);
}