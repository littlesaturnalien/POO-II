package online.jadg13.solicitud.controller;

import online.jadg13.solicitud.entity.Carrera;
import online.jadg13.solicitud.entity.Estudiante;
import online.jadg13.solicitud.service.CarreraService;
import online.jadg13.solicitud.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;
    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public Map<String, String> getAll(){
        Map<String, String> json = new HashMap<>();
        try{
            var estudiantes = estudianteService.findAll();
            if (estudiantes.isEmpty()){
                json.put("status", "success");
                json.put("message", "No hay estudiantes registrados");
            }else{
                json.put("status", "success");
                json.put("data", estudiantes.toString());
            }
        }catch (Exception e){
            json.put("status", "error");
            json.put("message", e.toString());
        }
        return json;
    }



    @PostMapping
    public Map<String, String> store(@RequestBody Map<String, Object> request){
        Map<String, String> json = new HashMap<>();
        try{
            Set<Carrera> carreras = new HashSet<>();

            var estudiante = new Estudiante();
            estudiante.setNombre((String) request.get("nombre"));
            estudiante.setApellido((String) request.get("apellido"));
            estudiante.setEmail((String) request.get("email"));
            estudiante.setCif((String) request.get("cif"));
            estudiante.setTelefono((String) request.get("telefono"));

            var carreraIds = (List<Integer>) request.get("carrera_ids");
            for (Integer carreraId : carreraIds) {
                Carrera carrera = carreraService.findById(Long.valueOf(carreraId));
                if (carrera != null) {
                    carreras.add(carrera);
                }
            }
            estudiante.setCarreras(carreras);

            var savedEstudiante = estudianteService.save(estudiante);
            json.put("status", "success");
            json.put("message", "Estudiante creado con éxito");
            json.put("data", savedEstudiante.toString());
        } catch (Exception e){
            json.put("status", "error");
            json.put("message", e.toString());
        }
        return json;
    }

    @GetMapping("/{id}")
    public Map<String, String> show(@PathVariable("id") Long id){
        Map<String, String> json = new HashMap<>();
        try{
            var estudiante = estudianteService.findById(id);
            if (estudiante != null){
                json.put("status", "success");
                json.put("data", estudiante.toString());
            }else{
                json.put("status", "error");
                json.put("message", "Estudiante no encontrado");
            }
        }catch (Exception e){
            json.put("status", "error");
            json.put("message", e.toString());
        }
        return json;
    }

    @PutMapping("/{id}")
    public Map<String, String> update(@PathVariable("id") Long id, @RequestBody Map<String, Object> estudiante){
        Map<String, String> json = new HashMap<>();
        try{
            Set<Carrera> carreras = new HashSet<>();
            var existingEstudiante = estudianteService.findById(id);

            if (existingEstudiante != null){
                existingEstudiante.setNombre((String) estudiante.get("nombre"));
                existingEstudiante.setApellido((String) estudiante.get("apellido"));
                existingEstudiante.setEmail((String) estudiante.get("email"));
                existingEstudiante.setCif((String) estudiante.get("cif"));
                existingEstudiante.setTelefono((String) estudiante.get("telefono"));

                var carreraIds = (List<Integer>) estudiante.get("carrera_ids");

                for (Integer carreraId : carreraIds) {
                    Carrera carrera = carreraService.findById(Long.valueOf(carreraId));
                    if (carrera != null) {
                        carreras.add(carrera);
                    }
                }
                existingEstudiante.setCarreras(carreras);

                var updatedEstudiante = estudianteService.update(existingEstudiante);
                json.put("status", "success");
                json.put("message", "Estudiante actualizado con éxito");
                json.put("data", updatedEstudiante.toString());
            }else{
                json.put("status", "error");
                json.put("message", "Estudiante no encontrado");
            }

        }catch (Exception e){
            json.put("status", "error");
            json.put("message", e.toString());
        }
        return json;



    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable("id") Long id){
        Map<String, String> json = new HashMap<>();
        try{
            var estudiante = estudianteService.findById(id);
            if (estudiante != null){
                estudianteService.delete(id);
                json.put("status", "success");
                json.put("message", "Estudiante eliminado con éxito");
            }else{
                json.put("status", "error");
                json.put("message", "Estudiante no encontrado");
            }
        }catch (Exception e){
            json.put("status", "error");
            json.put("message", e.toString());
        }
        return json;
    }
}
