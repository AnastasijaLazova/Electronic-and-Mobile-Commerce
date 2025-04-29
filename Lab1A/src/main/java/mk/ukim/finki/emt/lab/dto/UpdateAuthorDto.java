package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateAuthorDto(
        Long id,
        String name,
        String surname,
        Long countryId
) {
    public static UpdateAuthorDto from(Author author) {
        return new UpdateAuthorDto(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }

    public static List<UpdateAuthorDto> from(List<Author> authors) {
        return authors.stream().map(UpdateAuthorDto::from).collect(Collectors.toList());
    }

    public Author toAuthor(Country country) {
        return new Author(name,surname,country);
    }
}

