package business.dto;

import javax.validation.constraints.*;

public class HotelDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    private String name;
    @NotNull
    @Min(1)
    @Max(5)
    private int rating;
    @NotNull
    private String description;
    private CityDTO cityDTO;

    public HotelDTO(String name) {
        this.name = name;

    }

    public HotelDTO(String name, CityDTO cityDTO) {
        this.name = name;
        this.cityDTO = cityDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }

    @Override
    public String toString() {
        return "HotelDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
