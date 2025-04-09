package online.jadg13.solicitud.controller;

import online.jadg13.solicitud.entity.EstadoJustificacion;
import online.jadg13.solicitud.entity.Justificacion;
import online.jadg13.solicitud.service.CarreraService;
import online.jadg13.solicitud.service.EstudianteService;
import online.jadg13.solicitud.service.JustificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/justificacion")
public class JustificacionController {
    @Autowired
    private JustificacionService justificacionService;
    @Autowired
    private CarreraService carreraService;
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public Map<String, String> getAll() {
        Map<String, String> json = new HashMap<>();
        try {
            var justificaciones = justificacionService.findAll();
            if (justificaciones.isEmpty()) {
                json.put("status", "success");
                json.put("message", "No hay justificaciones registradass");
            } else {
                json.put("status", "success");
                json.put("data", justificaciones.toString());
            }
        } catch (Exception e) {
            json.put("status", "error");
            json.put("message", e.toString());
        }
        return json;
    }

    @PostMapping
    public Map<String, String> store(@RequestBody Map<String, Object> request) {
        Map<String, String> json = new HashMap<>();
        try {
            var justificacion = new Justificacion();
            justificacion.setFecha_ini((LocalDate) request.get("fecha_inicio"));
            justificacion.setFecha_fin((LocalDate) request.get("fecha_final"));
            justificacion.setMotivo((String) request.get("motivo"));
            justificacion.setEvidencia((String) request.get("evidencia"));
            justificacion.setEstudiante(estudianteService.findById((Long) request.get("id_estudiante")));
            justificacion.setCarrera(carreraService.findById((Long) request.get("id_carrera")));
            var savedJustificacion = justificacionService.save(justificacion);
            json.put("status", "success");
            json.put("message", "Justificación enviada con éxito");
            json.put("data", savedJustificacion.toString());
        } catch (Exception e) {
            json.put("status", "error");
            json.put("message", e.toString());
        }
        return json;
    }

    @GetMapping("/{id}")
    public Map<String, String> show(@PathVariable("id") Long id) {
        Map<String, String> json = new HashMap<>();
        try {
            var justificacion = justificacionService.findById(id);
            if (justificacion != null) {
                json.put("status", "success");
                json.put("data", justificacion.toString());
            } else {
                json.put("status", "error");
                json.put("message", "Justificación no encontrada");
            }
        } catch (Exception e) {
            json.put("status", "error");
            json.put("message", e.toString());
        }
        return json;
    }

    @PutMapping("/{id}")
    public Map<String, String> updateStatus(@PathVariable("id") Long id, @RequestBody Map<String, String> status) {
        Map<String, String> json = new HashMap<>();
        try {
            var existingJustficacion = justificacionService.findById(id);
            if (existingJustficacion != null) {
                existingJustficacion.setEstado(EstadoJustificacion.valueOf(status.get("estado")));
                var updatedJustificacion = justificacionService.update(existingJustficacion);
                json.put("status", "success");
                json.put("message", "Estado de la justificación actualizado con éxito");
                json.put("data", updatedJustificacion.toString());
            } else {
                json.put("status", "error");
                json.put("message", "Justificacion no encontrada");
            }
        } catch (Exception e) {
            json.put("status", "error");
            json.put("message", e.toString());
        }
        return json;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable("id") Long id) {
        Map<String, String> json = new HashMap<>();
        try {
            var justificacion = justificacionService.findById(id);
            if (justificacion != null) {
                justificacionService.delete(id);
                json.put("status", "success");
                json.put("message", "Justificación eliminada con éxito");
            } else {
                json.put("status", "error");
                json.put("message", "Justificación no encontrada");
            }
        } catch (Exception e) {
            json.put("status", "error");
            json.put("message", e.toString());
        }
        return json;
    }
}
