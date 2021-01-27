package business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CityDTO {
    @NotNull
    @NotBlank(message = "Campul este gol")
    @Pattern(regexp = "([a-z A-Z])*")
    @NotEmpty
    private String name;
    private CountryDTO countryDTO;


    public CityDTO() {
    }

    public CityDTO(@NotNull @NotBlank(message = "Campul este gol") @NotEmpty @Pattern(regexp = "([a-z A-Z])*") String name) {
        this.name = name;
    }

    public CityDTO(@NotNull @NotBlank(message = "Campul este gol") @NotEmpty @Pattern(regexp = "([a-z A-Z])*") String name, CountryDTO countryDTO) {
        this.name = name;
        this.countryDTO = countryDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryDTO getCountryDTO() {
        return countryDTO;
    }

    public void setCountryDTO(CountryDTO countryDTO) {
        this.countryDTO = countryDTO;
    }

    @Override
    public String toString() {
        return "City: " + this.name;
    }
}
