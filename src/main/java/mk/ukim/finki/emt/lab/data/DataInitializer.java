package mk.ukim.finki.emt.lab.data;

import jakarta.transaction.Transactional;
import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.Category;
import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (bookRepository.count() == 0) {
            // Countries
            Country uk = new Country();
            uk.setName("United Kingdom");
            uk.setContinent("Europe");
            countryRepository.save(uk);

            Country usa = new Country();
            usa.setName("United States");
            usa.setContinent("North America");
            countryRepository.save(usa);

            Country france = new Country();
            france.setName("France");
            france.setContinent("Europe");
            countryRepository.save(france);

            // Authors
            Author author1 = new Author();
            author1.setName("J.K.");
            author1.setSurname("Rowling");
            author1.setCountry(uk);
            authorRepository.save(author1);

            Author author2 = new Author();
            author2.setName("George");
            author2.setSurname("Orwell");
            author2.setCountry(uk);
            authorRepository.save(author2);

            Author author3 = new Author();
            author3.setName("Ernest");
            author3.setSurname("Hemingway");
            author3.setCountry(usa);
            authorRepository.save(author3);

            Author author4 = new Author();
            author4.setName("Victor");
            author4.setSurname("Hugo");
            author4.setCountry(france);
            authorRepository.save(author4);

            // Books
            List<Book> books = List.of(
                    new Book("Harry Potter and the Sorcerer's Stone", Category.FANTASY, author1, 10),
                    new Book("Harry Potter and the Chamber of Secrets", Category.FANTASY, author1, 8),
                    new Book("1984", Category.NOVEL, author2, 5),
                    new Book("Animal Farm", Category.NOVEL, author2, 7),
                    new Book("The Old Man and the Sea", Category.CLASSICS, author3, 6),
                    new Book("Les Misérables", Category.DRAMA, author4, 4),
                    new Book("The Hunchback of Notre-Dame", Category.CLASSICS, author4, 5)
            );

            bookRepository.saveAll(books);

            System.out.println("Database initialized with multiple books, authors, and countries!");
        }
    }
}
