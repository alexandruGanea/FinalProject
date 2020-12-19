package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "flights")

@NamedQueries({
        @NamedQuery(
                name = "selectFlightByDepartureAirport",
                query = "SELECT flight " +
                        "FROM Flight flight " +
                        "WHERE departureAirport = :departureAirport"
        ),
        @NamedQuery(
                name = "selectFlightByName",
                query = "SELECT flight " +
                        "FROM Flight flight " +
                        "WHERE flight.name = :name"
        ),
        @NamedQuery(
                name = "selectFlightIdByName",
                query = "SELECT flight.id " +
                        "FROM Flight flight " +
                        "WHERE flight.name = :name"
        )
})

public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "flight_name")
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_departure")
    private Airport departureAirport;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_destination")
    private Airport destinationAirport;
    @Column(name = "departure_date")
    private String departure;
    @Column(name = "arrival_date")
    private String arrival;
    @Column(name = "available_seats")
    private int availableSeats;
    @Column(name = "seat_price")
    private double seatPrice;


    public Flight() {
    }

    public Flight(String name) {
        this.name = name;
    }

    public Flight(String name, Airport departureAirport, String departure, Airport destinationAirport, String arrival, double seatPrice, int availableSeats) {
        this.name = name;
        this.departureAirport = departureAirport;
        this.departure = departure;
        this.destinationAirport = destinationAirport;
        this.arrival = arrival;
        this.seatPrice = seatPrice;
        this.availableSeats = availableSeats;
    }

    public Flight(String departure, String arrival, double seatPrice) {
        this.departure = departure;
        this.arrival = arrival;
        this.seatPrice = seatPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

