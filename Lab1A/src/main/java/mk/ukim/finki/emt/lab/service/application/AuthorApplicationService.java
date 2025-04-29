package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.CreateAuthorDto;
import mk.ukim.finki.emt.lab.dto.UpdateAuthorDto;
import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidAuthorId;
import mk.ukim.finki.emt.lab.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    Optional<UpdateAuthorDto> update(Long id, UpdateAuthorDto authorDto);
    Optional<UpdateAuthorDto> create(CreateAuthorDto authorDto);
    Optional<UpdateAuthorDto> findById(Long id);
    List<UpdateAuthorDto> findAll();
    void delete(Long id);
}
