package mk.ukim.finki.emt.lab.model.domain;

import jakarta.persistence.Id;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany
    private List<Book> books;


    public Wishlist() {

    }

    public Wishlist(User user){
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.books = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public User getUser() {
        return user;
    }

    public List<Book> getBooks() {
        return books;
    }

}
