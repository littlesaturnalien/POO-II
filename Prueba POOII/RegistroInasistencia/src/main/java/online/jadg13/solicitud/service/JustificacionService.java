package online.jadg13.solicitud.service;

import online.jadg13.solicitud.entity.Justificacion;
import online.jadg13.solicitud.repository.JustificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class JustificacionService {
    @Autowired
    private JustificacionRepository repository;

    public Justificacion save(Justificacion justificacion) {
        return repository.save(justificacion);
    }

    public List<Justificacion> findAll() {
        return repository.findAll();
    }

    public Optional<Justificacion> findById(Long id) {
        return repository.findById(id);
    }

    public Justificacion update(Justificacion justificacion) {
        return repository.save(justificacion);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
