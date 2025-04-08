package org.kmryfv.api_postgresdb.controllers;

import org.kmryfv.api_postgresdb.models.Book;
import org.kmryfv.api_postgresdb.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/libro")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/imprimirTodos")
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @PostMapping("/obtener/{id}")
    public Book getByID(@PathVariable UUID id){
        return bookRepository.findById(id).orElse(null);
    }

    @PostMapping("/crear")
    public void create(@RequestBody Book book){
        bookRepository.save(book);
    }

    @PostMapping("/eliminar")
    public void delete(@RequestBody Book book){
        bookRepository.delete(book);
    }

    @PutMapping("/actualizar")
    public Book update(@PathVariable UUID id, @RequestBody Book book){
        if (bookRepository.existsById(id)){
            return bookRepository.save(book);
        }
    }
}
