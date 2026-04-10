package pe.inpe.ms_persona.repository;

import pe.inpe.ms_persona.entity.PersonaRol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPersonaRolRepository extends JpaRepository<PersonaRol, Long>{
    List<PersonaRol> findByPersonaIdPersonaAndEstadoTrue(Long idPersona);
}
