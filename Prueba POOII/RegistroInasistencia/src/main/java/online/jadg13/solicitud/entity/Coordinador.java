package online.jadg13.solicitud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "coordinadores")
@Getter @Setter @ToString
public class Coordinador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @Column(name = "cif", nullable = false, unique = true, length = 20)
    @NotBlank(message = "El CIF no puede estar vacío")
    private String cif;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "El email debe ser válido")
    private String email;

    @Column(name = "telefono", length = 50)
    @Size(max = 50, message = "El teléfono no puede exceder 50 caracteres")
    private String telefono;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "carrera_id", unique = true)
    private Carrera carrera;
}
