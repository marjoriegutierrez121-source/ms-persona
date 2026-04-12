package pe.inpe.ms_persona.repository;

import pe.inpe.ms_persona.entity.PersonaDireccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonaDireccionRepository extends JpaRepository<PersonaDireccion, Long> {
}
