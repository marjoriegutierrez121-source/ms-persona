package pe.inpe.ms_persona.repository;

import org.springframework.stereotype.Repository;
import pe.inpe.ms_persona.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long> {

    List<Persona> findByNumeroDocumento(String numeroDocumento);

    List<Persona> findByNombresContainingIgnoreCase(String nombres);

    boolean existsByNumeroDocumento(String numeroDocumento);
}
