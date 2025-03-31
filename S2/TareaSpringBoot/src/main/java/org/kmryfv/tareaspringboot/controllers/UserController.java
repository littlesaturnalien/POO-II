package org.kmryfv.tareaspringboot.controllers;

import org.kmryfv.tareaspringboot.models.Role;
import org.kmryfv.tareaspringboot.models.User;
import org.kmryfv.tareaspringboot.models.UsersGlobal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/users")
@RestController()
public class UserController {
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody User user){
        for (User existingUser : UsersGlobal.users.values()){
            if (existingUser.name.equals(user.name) && existingUser.password.equals(user.password)
            && existingUser.email.equals(user.email) && existingUser.role.equals(user.role)){
                return ResponseEntity.badRequest().body("El usuario ya existe.");
            }
        }
        UsersGlobal.users.put(user.name, user);
        return ResponseEntity.ok("Usuario agregado exitosamente.");
    }

    @GetMapping("/getAll")
    public Map<String, User> getAll(){
        return UsersGlobal.users;
    }

    @GetMapping("/getAllTeachers")
    public Map<String, User> getAllTeachers(){
        Map<String, User> result = new HashMap<>();
        for (User user : UsersGlobal.users.values()){
            if(user.role == Role.PROFESOR){
                result.put(user.name, user);
            }
        }
        return result;
    }

    @GetMapping("/countTeachers&Students")
    public Map<String, Integer> countTeachersAndStudents(){
        int teacherCount = 0;
        int studentCount = 0;
        Map<String, Integer> result = new HashMap<>();
        for (User user : UsersGlobal.users.values()){
            if(user.role == Role.PROFESOR){
                teacherCount++;
            } else if (user.role == Role.ESTUDIANTE){
                studentCount++;
            }
        }
        result.put("profesores", teacherCount);
        result.put("estudiantes", studentCount);
        return result;
    }
}
