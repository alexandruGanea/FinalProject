package business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AirportDTO {
    @NotNull
    @NotBlank(message = "Campul este gol")
    @Pattern(regexp = "([a-z A-Z])*")
    @NotEmpty
    private String name;
    private CityDTO cityDTO;

    public AirportDTO() {
    }

    public AirportDTO(@NotNull @NotBlank(message = "Campul este gol") @NotEmpty @Pattern(regexp = "([a-z A-Z])*") String name) {
        this.name = name;
    }

    public AirportDTO(@NotNull @NotBlank(message = "Campul este gol") @NotEmpty @Pattern(regexp = "([a-z A-Z])*") String name, CityDTO cityDTO) {
        this.name = name;
        this.cityDTO = cityDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }

    @Override
    public String toString() {
        return "Airport: " + this.name;
    }
}
