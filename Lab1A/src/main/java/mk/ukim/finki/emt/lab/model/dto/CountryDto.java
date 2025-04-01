package mk.ukim.finki.emt.lab.model.dto;

import lombok.Data;


public class CountryDto {
    String name;
    String continent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
