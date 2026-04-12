package pe.inpe.ms_persona.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pe.inpe.ms_persona.dto.PersonaDireccionRequestDTO;
import pe.inpe.ms_persona.dto.PersonaDireccionResponseDTO;
import pe.inpe.ms_persona.entity.PersonaDireccion;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaDireccionMapper {

    @Mapping(target = "idPersonaDireccion", ignore = true)
    @Mapping(target = "persona", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "registrationUser", ignore = true)
    @Mapping(target = "lastModificationDate", ignore = true)
    @Mapping(target = "lastModificationUser", ignore = true)
    @Mapping(target = "esPrincipal", constant = "false")
    PersonaDireccion toEntity(PersonaDireccionRequestDTO requestDTO);

    @Mapping(source = "idPersonaDireccion", target = "idPersonaDireccion")
    @Mapping(source = "persona.idPersona", target = "personaId")
    @Mapping(source = "idLocation", target = "idLocation")
    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "referencia", target = "referencia")
    @Mapping(source = "esPrincipal", target = "esPrincipal")
    PersonaDireccionResponseDTO toResponseDTO(PersonaDireccion entity);

    List<PersonaDireccionResponseDTO> toResponseDTOList(List<PersonaDireccion> entities);
}
