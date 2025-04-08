package online.jadg13.solicitud.service;

import online.jadg13.solicitud.entity.Carrera;
import online.jadg13.solicitud.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository repository;

    public Carrera save(Carrera carrera) {
        return repository.save(carrera);
    }

    public List<Carrera> findAll() {
        return repository.findAll();
    }

    public Carrera findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada con id: " + id));
    }

    public Carrera update(Carrera carrera) {
        return repository.save(carrera);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
