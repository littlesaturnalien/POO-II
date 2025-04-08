package org.kmryfv.api_postgresdb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Entity
@Getter
@Setter
public class Book extends Identifiable {
    private String name;
    private String author;
    private String description;
    private String isbn;
    private String editorial;
    private Year publicationYear;
}
