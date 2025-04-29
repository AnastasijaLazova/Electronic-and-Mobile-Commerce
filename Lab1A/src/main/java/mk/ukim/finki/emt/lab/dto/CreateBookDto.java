package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateBookDto(
        String title,
        Category category,
        Long authorId,
        Integer availableCopies
) {
    public static CreateBookDto from(Book book) {
        return new CreateBookDto(
                book.getTitle(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }

    public static List<CreateBookDto> from(List<Book> books) {
        return books.stream().map(CreateBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        return new Book(title, category, author, availableCopies);
    }
}

