package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidAuthorId;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.service.AuthorService;
import mk.ukim.finki.emt.lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> listAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.of(this.authorRepository.findById(id).orElseThrow(InvalidAuthorId::new));
    }

    @Override
    public Optional<Author> create(String name, String surname, Long countryId) {
        return Optional.of(authorRepository.save(new Author(name, surname, countryService.findById(countryId).orElseThrow(InvalidCountryIdException::new))));

    }

    @Override
    public Optional<Author> update(Long id, String name, String surname, Long countryId) {
        Author author = findById(id).orElseThrow(InvalidAuthorId::new);
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(countryService.findById(countryId).orElseThrow(InvalidCountryIdException::new));
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> delete(Long id) {
        Author author = findById(id).orElseThrow(InvalidAuthorId::new);
        authorRepository.deleteById(id);
        return Optional.of(author);
    }
}
