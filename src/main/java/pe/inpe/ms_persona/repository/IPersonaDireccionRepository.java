package pe.inpe.ms_persona.repository;

import org.springframework.stereotype.Repository;
import pe.inpe.ms_persona.entity.PersonaDireccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface IPersonaDireccionRepository extends JpaRepository<PersonaDireccion, Long> {

    List<PersonaDireccion> findByIdPersona(Long idPersona);

}
