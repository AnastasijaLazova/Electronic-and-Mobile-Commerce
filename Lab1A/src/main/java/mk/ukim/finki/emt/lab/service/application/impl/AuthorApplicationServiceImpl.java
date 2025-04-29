package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.CreateAuthorDto;
import mk.ukim.finki.emt.lab.dto.UpdateAuthorDto;
import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidAuthorId;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.service.application.AuthorApplicationService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorApplicationServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<UpdateAuthorDto> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(author -> new UpdateAuthorDto(author.getId(), author.getName(), author.getSurname(), author.getCountry().getId()))
                .toList();
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Optional<UpdateAuthorDto> findById(Long id) {
        return authorRepository.findById(id)
                .map(author -> new UpdateAuthorDto(author.getId(), author.getName(), author.getSurname(), author.getCountry().getId()));
    }

    @Override
    public Optional<UpdateAuthorDto> create(CreateAuthorDto authorDto) {
        Author author = new Author(
                authorDto.name(),
                authorDto.surname(),
                countryService.findById(authorDto.countryId())
                        .orElseThrow(InvalidCountryIdException::new)
        );
        Author savedAuthor = authorRepository.save(author);
        return Optional.of(new UpdateAuthorDto(savedAuthor.getId(), savedAuthor.getName(), savedAuthor.getSurname(), savedAuthor.getCountry().getId()));
    }

    @Override
    public Optional<UpdateAuthorDto> update(Long id, UpdateAuthorDto authorDto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(InvalidAuthorId::new);

        author.setName(authorDto.name());
        author.setSurname(authorDto.surname());
        author.setCountry(
                countryService.findById(authorDto.countryId())
                        .orElseThrow(InvalidCountryIdException::new)
        );

        Author updatedAuthor = authorRepository.save(author);
        return Optional.of(new UpdateAuthorDto(updatedAuthor.getId(), updatedAuthor.getName(), updatedAuthor.getSurname(), updatedAuthor.getCountry().getId()));
    }


}