package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.CreateBookDto;
import mk.ukim.finki.emt.lab.dto.UpdateBookDto;
import mk.ukim.finki.emt.lab.model.exceptions.NoAvailableCopies;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    List<UpdateBookDto> findAll();
    Optional<UpdateBookDto> findById(Long id);
    Optional<UpdateBookDto> create(CreateBookDto createBookDto);
    Optional<UpdateBookDto> update(Long id, UpdateBookDto updateBookDto);
    void delete(Long id);
    Optional<UpdateBookDto> lowerAvailableCopies(Long id) throws NoAvailableCopies;
}