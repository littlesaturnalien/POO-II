package online.jadg13.solicitud.repository;

import online.jadg13.solicitud.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
