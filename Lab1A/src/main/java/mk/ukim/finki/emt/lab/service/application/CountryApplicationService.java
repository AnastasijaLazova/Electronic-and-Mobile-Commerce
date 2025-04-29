package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.CreateCountryDto;
import mk.ukim.finki.emt.lab.dto.UpdateCountryDto;
import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<UpdateCountryDto> findAll();
    Optional<UpdateCountryDto> findById(Long id);
    Optional<UpdateCountryDto> create(CreateCountryDto createCountryDto);
    Optional<UpdateCountryDto> update(Long id, UpdateCountryDto updateCountryDto);
    void delete(Long id);
}
