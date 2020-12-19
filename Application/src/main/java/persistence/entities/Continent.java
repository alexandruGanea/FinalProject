package persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "continents")

@NamedQueries({
        @NamedQuery(
                name = "updateContinentName",
                query = "UPDATE Continent continent " +
                        "SET continent.name = :newName " +
                        "WHERE continent.name = :oldName"
        ),
        @NamedQuery(
                name = "selectContinentByName",
                query = "SELECT continent " +
                        "FROM Continent continent " +
                        "WHERE continent.name = :name"
        ),
        @NamedQuery(
                name = "deleteContinentByName",
                query = "DELETE FROM Continent " +
                        "WHERE name = :name"
        ),

        @NamedQuery(
                name ="selectContinentIdByName",
                query = "SELECT continent.id " +
                        "FROM Continent continent " +
                        "WHERE continent.name = :name"
        )
})

public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "continentContainingCountry", cascade = CascadeType.ALL)
    private Set<Country> countriesOnContinent;

    public Continent() {
    }

    public Continent(String name) {
        this.name = name;
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

    public Set<Country> getCountriesOnContinent() {
        return countriesOnContinent;
    }

    public void setCountriesOnContinent(Set<Country> countriesOnContinent) {
        this.countriesOnContinent = countriesOnContinent;
    }

    @Override
    public String toString() {
        return "Continent: " + name;
    }
}
