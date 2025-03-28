package org.kmryfv.models;

public class Book {
    String title;
    String author;
    int year;
    int price;
    String isbn;

    public Book(String title, String author, int year, int price, String isbn) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
        this.isbn = isbn;
    }
}
