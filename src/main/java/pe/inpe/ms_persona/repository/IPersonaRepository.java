package pe.inpe.ms_persona.repository;

import pe.inpe.ms_persona.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPersonaRepository extends JpaRepository<Persona, Long> {
    // GET /personas?numeroDocumento=12345678
    List<Persona> findByNumeroDocumento(String numeroDocumento);

    // GET /personas?nombre=juan
    List<Persona> findByNombresContainingIgnoreCase(String nombres);
}
