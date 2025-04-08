package online.jadg13.solicitud.controller;

import online.jadg13.solicitud.entity.Carrera;
import online.jadg13.solicitud.entity.Coordinador;

import online.jadg13.solicitud.service.CarreraService;
import online.jadg13.solicitud.service.CoordinadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/coordinador")
public class CoordinadorController {

    @Autowired
    private CoordinadorService service;
    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public Map<String, String> getAll(){
        Map<String, String> json = new HashMap<>();
        try{
            var coordinadores = service.findAll();
            if (coordinadores.isEmpty()){
                json.put("status", "success");
                json.put("message", "No hay coordinadores registrados");
            }else{
                json.put("status", "success");
                json.put("data", coordinadores.toString());
            }
        }catch (Exception e){
            json.put("status", "error");
            json.put("message", e.toString());
        }
        return json;
    }



    @PostMapping
    public Map<String, String> store(@RequestBody Map<String, String> request){
        Map<String, String> json = new HashMap<>();
        try{
            var coordinador = new Coordinador();
            coordinador.setNombre(request.get("nombre"));
            coordinador.setApellido(request.get("apellido"));
            coordinador.setEmail(request.get("email"));
            coordinador.setCif(request.get("cif"));
            coordinador.setTelefono(request.get("telefono"));

            Long carreraId = Long.parseLong(request.get("carrera_id"));
            var carrera = carreraService.findById(carreraId);

            coordinador.setCarrera(carrera);

            var savedCoordinador = service.save(coordinador);
            json.put("status", "success");
            json.put("message", "Coordinador creado con éxito");
            json.put("data", savedCoordinador.toString());
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
            var coordinador = service.findById(id);
            if (coordinador != null){
                json.put("status", "success");
                json.put("data", coordinador.toString());
            }else{
                json.put("status", "error");
                json.put("message", "Coordinador no encontrado");
            }
        }catch (Exception e){
            json.put("status", "error");
            json.put("message", e.toString());
        }
        return json;
    }

    @PutMapping("/{id}")
    public Map<String, String> update(@PathVariable("id") Long id, @RequestBody Coordinador coordinador){
        Map<String, String> json = new HashMap<>();
        try{
            var existingCoordinador = service.findById(id);


            if (existingCoordinador != null){
                existingCoordinador.setNombre(coordinador.getNombre());
                existingCoordinador.setApellido(coordinador.getApellido());
                existingCoordinador.setEmail(coordinador.getEmail());
                existingCoordinador.setCif(coordinador.getCif());
                existingCoordinador.setTelefono(coordinador.getTelefono());

                Long carreraId = coordinador.getCarrera().getId();
                Carrera carrera = carreraService.findById(carreraId);

                existingCoordinador.setCarrera(carrera);

                var updatedCoordinador = service.update(existingCoordinador);
                json.put("status", "success");
                json.put("message", "Coordinador actualizado con éxito");
                json.put("data", updatedCoordinador.toString());
            }else{
                json.put("status", "error");
                json.put("message", "Coordinador no encontrado");
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
            var coordinador = service.findById(id);
            if (coordinador != null){
                service.delete(id);
                json.put("status", "success");
                json.put("message", "Coordinador eliminado con éxito");
            }else{
                json.put("status", "error");
                json.put("message", "Coordinador no encontrado");
            }
        }catch (Exception e){
            json.put("status", "error");
            json.put("message", e.toString());
        }
        return json;
    }


}
