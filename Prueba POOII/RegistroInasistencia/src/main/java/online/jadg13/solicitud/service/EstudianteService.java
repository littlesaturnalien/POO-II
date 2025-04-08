package online.jadg13.solicitud.service;

import online.jadg13.solicitud.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import online.jadg13.solicitud.entity.Estudiante;
import java.util.List;
import java.util.Optional;


@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository repository;

    public Estudiante save(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    public List<Estudiante> findAll() {
        return repository.findAll();
    }

    public Estudiante findById(Long id) {
        return repository.findById(id).get();
    }

    public Estudiante update(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
