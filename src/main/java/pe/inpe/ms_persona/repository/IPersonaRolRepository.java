package pe.inpe.ms_persona.repository;

import org.springframework.stereotype.Repository;
import pe.inpe.ms_persona.entity.PersonaRol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPersonaRolRepository extends JpaRepository<PersonaRol, Long> {

    List<PersonaRol> findByIdPersona(Long idPersona);

    boolean existsByIdPersonaAndTipoPersonaRolIdAndEstadoTrue(Long idPersona, Long tipoRolId);

    Optional<PersonaRol> findByIdPersonaAndTipoPersonaRolIdAndEstadoTrue(Long idPersona, Long tipoRolId);
}