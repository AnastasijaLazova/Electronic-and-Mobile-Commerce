package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAllAuthors();
    Optional<Author> findById(Long id);
    Optional<Author> create(String name, String surname, Long countryId);
    Optional<Author> update(Long id, String name, String surname, Long countryId);
    Optional<Author> delete(Long id);
}
