package business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

public class FlightDTO {

    @NotNull
    private String name;
    private AirportDTO departureAirportDTO;
    private AirportDTO destinationAirportDTO;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
    private String departureDate;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
    private String arrivalDate;
    @NotNull
    private int availableSeats;
    @NotNull
    private double seatPrice;

    public FlightDTO(String name, AirportDTO departureAirportDTO, String departureDate, AirportDTO destinationAirportDTO, String arrivalDate) {
        this.name = name;
        this.departureAirportDTO = departureAirportDTO;
        this.departureDate = departureDate;
        this.destinationAirportDTO = destinationAirportDTO;
        this.arrivalDate = arrivalDate;
    }

    public AirportDTO getDepartureAirportDTO() {
        return departureAirportDTO;
    }

    public void setDepartureAirportDTO(AirportDTO departureAirportDTO) {
        this.departureAirportDTO = departureAirportDTO;
    }

    public AirportDTO getDestinationAirportDTO() {
        return destinationAirportDTO;
    }

    public void setDestinationAirportDTO(AirportDTO destinationAirportDTO) {
        this.destinationAirportDTO = destinationAirportDTO;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
