package pe.inpe.ms_persona.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pe.inpe.ms_persona.dto.PersonaRequestDTO;
import pe.inpe.ms_persona.dto.PersonaResponseDTO;
import pe.inpe.ms_persona.entity.Persona;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    @Mapping(target = "idPersona", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "registrationUser", ignore = true)
    @Mapping(target = "lastModificationDate", ignore = true)
    @Mapping(target = "lastModificationUser", ignore = true)
    @Mapping(target = "estado", constant = "true")
    Persona toEntity(PersonaRequestDTO requestDTO);

    PersonaResponseDTO toResponseDTO(Persona entity);

    @Mapping(target = "idPersona", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "registrationUser", ignore = true)
    @Mapping(target = "lastModificationDate", ignore = true)
    @Mapping(target = "lastModificationUser", ignore = true)
    @Mapping(target = "estado", ignore = true)
    void updateEntity(PersonaRequestDTO requestDTO, @MappingTarget Persona entity);

    List<PersonaResponseDTO> toResponseDTOList(List<Persona> entities);
}