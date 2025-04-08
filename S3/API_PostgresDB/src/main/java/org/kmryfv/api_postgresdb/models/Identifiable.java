package org.kmryfv.api_postgresdb.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class Identifiable {
    @Id
    @UuidGenerator
    public UUID id;
}
