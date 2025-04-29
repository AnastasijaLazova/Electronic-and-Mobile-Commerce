package mk.ukim.finki.emt.lab.dto;
import mk.ukim.finki.emt.lab.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record CreateCountryDto(
        String name,
        String continent
){
    public Country toCountry() {
        return new Country(name, continent);
    }
}
