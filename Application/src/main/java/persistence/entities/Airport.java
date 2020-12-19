package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "airports")

@NamedQueries({
        @NamedQuery(
                name = "selectAirportByName",
                query = "SELECT airport " +
                        "FROM Airport airport " +
                        "WHERE airport.name = :name"
        ),

        @NamedQuery(
                name = "selectAirportByCityName",
                query = "SELECT airport " +
                        "FROM Airport airport " +
                        "JOIN airport.cityContainingAirport cityContainingAirport " +
                        "WHERE cityContainingAirport.name = :name"
        ),

        @NamedQuery(
                name = "selectAirportIdByName",
                query = "SELECT airport.id " +
                        "FROM Airport airport " +
                        "WHERE airport.name = :name"
        ),

        @NamedQuery(
                name = "updateAirportName",
                query = "UPDATE Airport airport " +
                        "SET airport.name = :newName " +
                        "WHERE airport.name = :oldName"
        ),

        @NamedQuery(
                name = "deleteAirportByName",
                query = "DELETE FROM Airport " +
                        "WHERE name = :name"
        )
})

public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_airport_id")
    private City cityContainingAirport;

    public Airport() {
    }

    public Airport(String name) {
        this.name = name;
    }

    public Airport(String name, City cityContainingAirport) {
        this.name = name;
        this.cityContainingAirport = cityContainingAirport;
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

    public City getCityContainingAirport() {
        return cityContainingAirport;
    }

    public void setCityContainingAirport(City city) {
        this.cityContainingAirport = city;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                '}';
    }
}
