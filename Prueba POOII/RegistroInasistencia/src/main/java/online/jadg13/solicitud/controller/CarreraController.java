package online.jadg13.solicitud.controller;

import jakarta.validation.Valid;
import online.jadg13.solicitud.entity.Carrera;
import online.jadg13.solicitud.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carrera")
public class CarreraController {

    @Autowired
    private CarreraService service;

    @GetMapping
    public Map<String, String> listar() {
        Map<String, String> json = new HashMap<>();
        try{
            List<Carrera> carreras = service.findAll();
            if (carreras.isEmpty()) {
                json.put("status", "success");
                json.put("message", "No hay carreras registradas");
            } else {
                json.put("status", "success");
                json.put("data", carreras.toString());
            }
        }catch (Exception e){
            json.put("status", "error" + e.toString());
        }
        return json;
    }

    @PostMapping
    public Map<String, String> store(@RequestBody Map<String, String> request) {
        Map<String, String> json = new HashMap<>();
        try {
            Carrera carrera = new Carrera();
            carrera.setNombre(request.get("nombre"));
            carrera.setDescripcion(request.get("descripcion"));

            Carrera savedCarrera = service.save(carrera);
            json.put("status", "success");
            json.put("message", "Carrera creada con éxito");
            json.put("data", savedCarrera.toString());
        } catch (Exception e) {
            json.put("status", "error" + e.toString());
        }
        return json;

    }

    @GetMapping("/{id}")
    public Map<String, String> show(@PathVariable("id") Long id) {
        Map<String, String> json = new HashMap<>();
        try {
            Carrera carrera = service.findById(id);
            if (carrera != null) {
                json.put("status", "success");
                json.put("data", carrera.toString());
            } else {
                json.put("status", "error");
                json.put("message", "Carrera no encontrada");
            }
        } catch (Exception e) {
            json.put("status", "error" + e.toString());
        }
        return json;
    }

    @PutMapping("/{id}")
    public Map<String, String> update(@PathVariable("id") Long id, @Valid @RequestBody Carrera carrera) {
        Map<String, String> json = new HashMap<>();
        try {
            Carrera existingCarrera = service.findById(id);
            if (existingCarrera != null) {
                existingCarrera.setNombre(carrera.getNombre());
                existingCarrera.setDescripcion(carrera.getDescripcion());
                Carrera updatedCarrera = service.update(existingCarrera);
                json.put("status", "success");
                json.put("message", "Carrera actualizada con éxito");
                json.put("data", updatedCarrera.toString());
            } else {
                json.put("status", "error");
                json.put("message", "Carrera no encontrada");
            }
        } catch (Exception e) {
            json.put("status", "error" + e.toString());
        }
        return json;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable("id") Long id) {
        Map<String, String> json = new HashMap<>();
        try {
            Carrera carrera = service.findById(id);
            if (carrera != null) {
                service.delete(id);
                json.put("status", "success");
                json.put("message", "Carrera eliminada con éxito");
            } else {
                json.put("status", "error");
                json.put("message", "Carrera no encontrada");
            }
        } catch (Exception e) {
            json.put("status", "error" + e.toString());
        }
        return json;
    }




}
