package mk.ukim.finki.emt.lab.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.lab.model.enumerations.Category;

@Entity
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    Long id;
    String title;
    @Enumerated(EnumType.STRING)
    Category category;

    Integer availableCopies;

    @ManyToOne
    Author author;

    public Book(Long id, Category category, String title, Integer availableCopies, Author author) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.availableCopies = availableCopies;
        this.author = author;
    }

    public Book(String title, Category category, Author author, Integer availableCopies) {
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = title;
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
