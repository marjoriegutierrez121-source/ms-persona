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
    @Mapping(target = "direcciones", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "estado", constant = "true")
    Persona toEntity(PersonaRequestDTO requestDTO);

    @Mapping(source = "idPersona", target = "idPersona")
    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "apellidoPaterno", target = "apellidoPaterno")
    @Mapping(source = "apellidoMaterno", target = "apellidoMaterno")
    @Mapping(source = "tipoDocumentoId", target = "tipoDocumentoId")
    @Mapping(source = "numeroDocumento", target = "numeroDocumento")
    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento")
    @Mapping(source = "sexoId", target = "sexoId")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "estado", target = "estado")
    @Mapping(target = "nombreCompleto", expression = "java(entity.getNombres() + \" \" + entity.getApellidoPaterno() + (entity.getApellidoMaterno() != null ? \" \" + entity.getApellidoMaterno() : \"\"))")
    PersonaResponseDTO toResponseDTO(Persona entity);

    @Mapping(target = "idPersona", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "registrationUser", ignore = true)
    @Mapping(target = "lastModificationDate", ignore = true)
    @Mapping(target = "lastModificationUser", ignore = true)
    @Mapping(target = "direcciones", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "estado", ignore = true)
    void updateEntity(PersonaRequestDTO requestDTO, @MappingTarget Persona entity);

    List<PersonaResponseDTO> toResponseDTOList(List<Persona> entities);
}
