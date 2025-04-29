package mk.ukim.finki.emt.lab.config;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.enumerations.Category;
import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.enumerations.Role;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CountryRepository countryRepository,
                           AuthorRepository authorRepository,
                           BookRepository bookRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initializeData() {
        // Countries
        Country uk = new Country("United Kingdom", "Europe");
        Country usa = new Country("United States", "North America");
        Country france = new Country("France", "Europe");

        countryRepository.save(uk);
        countryRepository.save(usa);
        countryRepository.save(france);

        // Authors
        Author author1 = new Author("J.K.", "Rowling", uk);
        Author author2 = new Author("George", "Orwell", uk);
        Author author3 = new Author("Ernest", "Hemingway", usa);
        Author author4 = new Author("Victor", "Hugo", france);

        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
        authorRepository.save(author4);

        // Books
        bookRepository.save(new Book("Harry Potter and the Sorcerer's Stone", Category.FANTASY, author1, 10));
        bookRepository.save(new Book("Harry Potter and the Chamber of Secrets", Category.FANTASY, author1, 8));
        bookRepository.save(new Book("1984", Category.NOVEL, author2, 5));
        bookRepository.save(new Book("Animal Farm", Category.NOVEL, author2, 7));
        bookRepository.save(new Book("The Old Man and the Sea", Category.CLASSICS, author3, 6));
        bookRepository.save(new Book("Les Misérables", Category.DRAMA, author4, 4));
        bookRepository.save(new Book("The Hunchback of Notre-Dame", Category.CLASSICS, author4, 5));

        // Users
        userRepository.save(new User(
                "librarian",
                passwordEncoder.encode("al"),
                "Anastasija",
                "Lazova",
                Role.ROLE_LIBRARIAN
        ));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));
    }
}

