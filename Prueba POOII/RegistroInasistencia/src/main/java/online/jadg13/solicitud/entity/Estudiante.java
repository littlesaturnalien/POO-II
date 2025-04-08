package online.jadg13.solicitud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "estudiantes")
@Setter @Getter @ToString
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "cif", nullable = false, unique = true, length = 20)
    private String cif;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "El email debe ser válido")
    private String email;

    @Column(name = "telefono", nullable = true, length = 50)
    private String telefono;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "estudiante_carrera",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "carrera_id")
    )
    @Size(max = 2, message = "Un estudiante no puede llevar más de 2 carreras")
    @ToString.Exclude
    private Set<Carrera> carreras = new HashSet<>();
}
