package mk.ukim.finki.emt.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    Long id;
    String name;
    @Enumerated(EnumType.STRING)
    Category category;

    Integer availableCopies;

    @ManyToOne
    Author author;

    public Book(Long id, Category category, String name, Integer availableCopies, Author author) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.availableCopies = availableCopies;
        this.author = author;
    }

    public Book(String name, Category category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    public Book(){

    }
}
