package online.jadg13.solicitud.service;

import online.jadg13.solicitud.entity.Coordinador;
import online.jadg13.solicitud.repository.CoordinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CoordinadorService {

    @Autowired
    private CoordinadorRepository repository;

    public Coordinador save(Coordinador coordinador) {
        return repository.save(coordinador);
    }

    public List<Coordinador> findAll() {
        return repository.findAll();
    }

    public Coordinador findById(Long id) {
        return repository.findById(id).get();
    }

    public Coordinador update(Coordinador coordinador) {
        return repository.save(coordinador);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }




}
