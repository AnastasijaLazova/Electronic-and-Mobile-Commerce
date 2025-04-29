package mk.ukim.finki.emt.lab.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.dto.CreateAuthorDto;
import mk.ukim.finki.emt.lab.dto.UpdateAuthorDto;
import mk.ukim.finki.emt.lab.service.application.AuthorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author API", description = "Endpoints for managing authors")
public class AuthorRestController {
    private final AuthorApplicationService authorApplicationService;

    public AuthorRestController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }

    @Operation(summary = "Get all authors", description = "Retrieves a list of all available authors.")
    @GetMapping("")
    public List<UpdateAuthorDto> getAllAuthors() {
        return authorApplicationService.findAll();
    }

    @Operation(summary = "Get author by ID", description = "Finds an author by their ID.")
    @GetMapping("/{id}")
    public ResponseEntity<UpdateAuthorDto> getAuthorById(@PathVariable Long id) {
        return authorApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new author", description = "Creates a new author based on the given data.")
    @PostMapping("/add")
    public ResponseEntity<UpdateAuthorDto> addAuthor(@RequestBody CreateAuthorDto createAuthorDto) {
        return authorApplicationService.create(createAuthorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an existing author", description = "Updates an author by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<UpdateAuthorDto> editAuthor(@PathVariable Long id, @RequestBody UpdateAuthorDto updateAuthorDto) {
        return authorApplicationService.update(id, updateAuthorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Delete an author", description = "Deletes an author by their ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorApplicationService.delete(id);
        return ResponseEntity.ok().build();
    }
}
