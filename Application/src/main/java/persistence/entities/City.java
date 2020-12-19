package persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cities")

@NamedQueries({
        @NamedQuery(
                name = "selectCityByName",
                query = "SELECT city " +
                        "FROM City city " +
                        "WHERE city.name = :name"
        ),

        @NamedQuery(
                name = "selectCitiesByCountryName",
                query = "SELECT foundCity " +
                        "FROM City foundCity " +
                        "JOIN foundCity.countryContainingCity countryContainingCity " +
                        "WHERE countryContainingCity.name = :name "
        ),

        @NamedQuery(
                name = "selectCityIdByName",
                query = "SELECT city.id " +
                        "FROM City city " +
                        "WHERE city.name = :name"
        ),

        @NamedQuery(
                name = "updateCityName",
                query = "UPDATE City city " +
                        "SET city.name = :newName " +
                        "WHERE city.name = :oldName"
        ),

        @NamedQuery(
                name = "deleteCityByName",
                query = "DELETE FROM City " +
                        "WHERE name = :name"
        )
})

public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country countryContainingCity;
    @OneToMany(mappedBy = "cityContainingAirport", cascade = CascadeType.ALL)
    private Set<Airport> airportsInCity;
    @OneToMany(mappedBy = "cityContainingHotel", cascade = CascadeType.ALL)
    private Set<Hotel> hotelsInCity;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public City(String name, Country countryContainingCity) {
        this.name = name;
        this.countryContainingCity = countryContainingCity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return countryContainingCity;
    }

    public void setCountry(Country country) {
        this.countryContainingCity = country;
    }

    public Set<Airport> getAirportsInCity() {
        return airportsInCity;
    }

    public void setAirportsInCity(Set<Airport> airportsInCity) {
        this.airportsInCity = airportsInCity;
    }

    public Set<Hotel> getHotelsInCity() {
        return hotelsInCity;
    }

    public void setHotelsInCity(Set<Hotel> hotelsInCity) {
        this.hotelsInCity = hotelsInCity;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                '}';
    }
}
