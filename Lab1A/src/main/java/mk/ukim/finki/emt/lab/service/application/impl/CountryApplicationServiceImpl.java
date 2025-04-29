package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.CreateCountryDto;
import mk.ukim.finki.emt.lab.dto.UpdateCountryDto;
import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.service.application.CountryApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryRepository countryRepository;

    public CountryApplicationServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<UpdateCountryDto> findAll() {
        return countryRepository.findAll()
                .stream()
                .map(country -> new UpdateCountryDto(
                        country.getId(),
                        country.getName(),
                        country.getContinent()
                ))
                .toList();
    }

    @Override
    public Optional<UpdateCountryDto> findById(Long id) {
        return countryRepository.findById(id)
                .map(country -> new UpdateCountryDto(
                        country.getId(),
                        country.getName(),
                        country.getContinent()
                ));
    }

    @Override
    public Optional<UpdateCountryDto> create(CreateCountryDto createCountryDto) {
        Country country = new Country(createCountryDto.name(), createCountryDto.continent());
        Country savedCountry = countryRepository.save(country);
        return Optional.of(new UpdateCountryDto(
                savedCountry.getId(),
                savedCountry.getName(),
                savedCountry.getContinent()
        ));
    }

    @Override
    public Optional<UpdateCountryDto> update(Long id, UpdateCountryDto updateCountryDto) {
        Country country = countryRepository.findById(id)
                .orElseThrow(InvalidCountryIdException::new);

        country.setName(updateCountryDto.name());
        country.setContinent(updateCountryDto.continent());

        Country updatedCountry = countryRepository.save(country);
        return Optional.of(new UpdateCountryDto(
                updatedCountry.getId(),
                updatedCountry.getName(),
                updatedCountry.getContinent()
        ));
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }


}

