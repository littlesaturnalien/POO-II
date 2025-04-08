package online.jadg13.solicitud.entity;

public enum EstadoJustificacion {
    PENDIENTE,
    ACEPTADA,
    RECHAZADA,
    EN_PROCESO;

    public static EstadoJustificacion fromString(String estado) {
        return EstadoJustificacion.valueOf(estado.toUpperCase());
    }
}
