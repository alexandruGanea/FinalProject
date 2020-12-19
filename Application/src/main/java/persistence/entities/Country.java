package persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "countries")

@NamedQueries({
        @NamedQuery(
                name = "selectCountryByName",
                query = "SELECT country " +
                        "FROM Country country " +
                        "WHERE country.name = :name"
        ),
        @NamedQuery(
                name = "selectCountriesByContinentName",
                query = "SELECT country " +
                        "FROM Country country " +
                        "JOIN country.continentContainingCountry continentContainingCountry " +
                        "WHERE continentContainingCountry.name = :name"
        ),
        @NamedQuery(
                name = "selectCountryIdByName",
                query = "SELECT country.id " +
                        "FROM Country country " +
                        "WHERE country.name = :name"
        ),

        @NamedQuery(
                name = "deleteCountryByName",
                query = "DELETE FROM Country " +
                        "WHERE name = :name"
        ),

        @NamedQuery(
                name = "updateCountryName",
                query = "UPDATE Country country " +
                        "SET country.name = :newName " +
                        "WHERE country.name = :oldName"
        )
})

public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "continent_id")
    private Continent continentContainingCountry;
    @OneToMany(mappedBy = "countryContainingCity", cascade = CascadeType.ALL)
    private Set<City> citiesInCountry;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, Continent continent) {
        this.name = name;
        this.continentContainingCountry = continent;
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

    public Continent getContinent() {
        return continentContainingCountry;
    }

    public void setContinent(Continent continentContainingCountry) {
        this.continentContainingCountry = continentContainingCountry;
    }

    public Set<City> getCitiesInCountry() {
        return citiesInCountry;
    }

    public void setCitiesInCountry(Set<City> citiesInCountry) {
        this.citiesInCountry = citiesInCountry;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                '}';
    }
}
