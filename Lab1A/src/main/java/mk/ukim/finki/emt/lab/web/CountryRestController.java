package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.dto.CreateCountryDto;
import mk.ukim.finki.emt.lab.dto.UpdateCountryDto;
import mk.ukim.finki.emt.lab.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country API", description = "Endpoints for managing countries")
public class CountryRestController {

    private final CountryApplicationService countryApplicationService;

    public CountryRestController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @Operation(summary = "Get all countries", description = "Retrieves a list of all available countries.")
    @GetMapping("")
    public List<UpdateCountryDto> getAllCountries() {
        return countryApplicationService.findAll();
    }

    @Operation(summary = "Get country by ID", description = "Finds a country by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<UpdateCountryDto> getCountryById(@PathVariable Long id) {
        return countryApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new country", description = "Creates a new country based on the given data.")
    @PostMapping("/add")
    public ResponseEntity<UpdateCountryDto> addCountry(@RequestBody CreateCountryDto createCountryDto) {
        return countryApplicationService.create(createCountryDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an existing country", description = "Updates a country based on the provided ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<UpdateCountryDto> editCountry(@PathVariable Long id, @RequestBody UpdateCountryDto updateCountryDto) {
        return countryApplicationService.update(id, updateCountryDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Delete a country", description = "Deletes a country based on the provided ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryApplicationService.delete(id);
        return ResponseEntity.ok().build();
    }
}
