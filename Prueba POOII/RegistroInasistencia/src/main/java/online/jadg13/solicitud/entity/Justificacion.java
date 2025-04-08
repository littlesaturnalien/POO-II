package online.jadg13.solicitud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "justificaciones")
@Getter @Setter @ToString
public class Justificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_ini", nullable = false)
    @NotNull(message = "La fecha de inicio no puede ser nula")
    private LocalDate fecha_ini;

    @Column(name = "fecha_fin", nullable = false)
    @NotNull(message = "La fecha de fin no puede ser nula")
    private LocalDate fecha_fin;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fecha_registro = LocalDate.now();

    @Column(name = "motivo", nullable = false, length = 350)
    private String motivo;

    @Column(name = "evidencia", nullable = false, length = 350)
    private String evidencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    @NotNull(message = "El estado no puede ser nulo")
    private EstadoJustificacion estado = EstadoJustificacion.PENDIENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    @ToString.Exclude
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrera_id", nullable = false)
    @ToString.Exclude
    private Carrera carrera;

}
