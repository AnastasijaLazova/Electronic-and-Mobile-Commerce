package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateBookDto(
        Long id,
        String title,
        Category category,
        Long authorId,
        Integer availableCopies
) {
    public static UpdateBookDto from(Book book) {
        return new UpdateBookDto(
                book.getId(),
                book.getTitle(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }

    public static List<UpdateBookDto> from(List<Book> books) {
        return books.stream().map(UpdateBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        return new Book(title, category, author, availableCopies);
    }
}
