package mk.ukim.finki.emt.lab.service.application.impl;


import mk.ukim.finki.emt.lab.dto.CreateBookDto;
import mk.ukim.finki.emt.lab.dto.UpdateBookDto;
import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidAuthorId;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidBookIdException;
import mk.ukim.finki.emt.lab.model.exceptions.NoAvailableCopies;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.service.application.BookApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookApplicationServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<UpdateBookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(book -> new UpdateBookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getCategory(),
                        book.getAuthor().getId(),
                        book.getAvailableCopies()
                ))
                .toList();
    }

    @Override
    public Optional<UpdateBookDto> findById(Long id) {
        return bookRepository.findById(id)
                .map(book -> new UpdateBookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getCategory(),
                        book.getAuthor().getId(),
                        book.getAvailableCopies()
                ));
    }

    @Override
    public Optional<UpdateBookDto> create(CreateBookDto createBookDto) {
        Author author = authorService.findById(createBookDto.authorId())
                .orElseThrow(InvalidAuthorId::new);

        Book book = new Book(
                createBookDto.title(),
                createBookDto.category(),
                author,
                createBookDto.availableCopies()
        );

        Book savedBook = bookRepository.save(book);
        return Optional.of(new UpdateBookDto(
                savedBook.getId(),
                savedBook.getTitle(),
                savedBook.getCategory(),
                savedBook.getAuthor().getId(),
                savedBook.getAvailableCopies()
        ));
    }

    @Override
    public Optional<UpdateBookDto> update(Long id, UpdateBookDto updateBookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(InvalidBookIdException::new);

        book.setTitle(updateBookDto.title());
        book.setCategory(updateBookDto.category());
        book.setAuthor(authorService.findById(updateBookDto.authorId())
                .orElseThrow(InvalidAuthorId::new));
        book.setAvailableCopies(updateBookDto.availableCopies());

        Book updatedBook = bookRepository.save(book);
        return Optional.of(new UpdateBookDto(
                updatedBook.getId(),
                updatedBook.getTitle(),
                updatedBook.getCategory(),
                updatedBook.getAuthor().getId(),
                updatedBook.getAvailableCopies()
        ));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }


    @Override
    public Optional<UpdateBookDto> lowerAvailableCopies(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(InvalidBookIdException::new);

        if (book.getAvailableCopies() <= 0) {
            throw new NoAvailableCopies();
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        Book updatedBook = bookRepository.save(book);
        return Optional.of(new UpdateBookDto(
                updatedBook.getId(),
                updatedBook.getTitle(),
                updatedBook.getCategory(),
                updatedBook.getAuthor().getId(),
                updatedBook.getAvailableCopies()
        ));
    }
}
