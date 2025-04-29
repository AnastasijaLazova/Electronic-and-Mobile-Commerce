package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.dto.CreateBookDto;
import mk.ukim.finki.emt.lab.dto.UpdateBookDto;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.enumerations.Category;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.service.application.BookApplicationService;
import mk.ukim.finki.emt.lab.service.domain.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/books")
@Tag(name = "Book API", description = "Endpoints for managing books")
public class BookRestController {

    private final BookApplicationService bookApplicationService;

    public BookRestController(BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }

    @Operation(summary = "Get all books", description = "Retrieves a list of all available books.")
    @GetMapping("")
    public List<UpdateBookDto> getAllBooks() {
        return bookApplicationService.findAll();
    }

    @Operation(summary = "Get book by ID", description = "Finds a book by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<UpdateBookDto> getBookById(@PathVariable Long id) {
        return bookApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new book", description = "Creates a new book based on the given data.")
    @PostMapping("/add")
    public ResponseEntity<UpdateBookDto> addBook(@RequestBody CreateBookDto createBookDto) {
        return bookApplicationService.create(createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an existing book", description = "Updates an existing book based on the provided ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<UpdateBookDto> editBook(@PathVariable Long id, @RequestBody UpdateBookDto updateBookDto) {
        return bookApplicationService.update(id, updateBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Delete a book", description = "Deletes a book based on the provided ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookApplicationService.delete(id);
        return ResponseEntity.ok().build();
    }
}
