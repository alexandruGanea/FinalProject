package persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "travel_packages")

public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_id")
    private Airport departureAirport;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_id")
    private Airport destinationAirport;
    @Column(name = "departure_date")
    private String departureDate;
    @Column(name = "return_date")
    private String returnDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @Column(name = "adult_price")
    private double adultPrice;
    @Column(name = "child_price")
    private double childPrice;
    @Column(name = "is_promoted")
    private boolean isPromoted;
    @Column(name = "package_type")
    private String packageType;
    @Column(name = "available_packages")
    private int availablePackages;
    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL)
    private Set<Purchase> purchasesOfPackage;

    public TravelPackage() {
    }

    public TravelPackage(String departureDate, String returnDate, Airport departureAirport, Airport destinationAirport, Hotel hotel, String packageType, boolean isPromoted) {
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.hotel = hotel;
        this.packageType = packageType;
        this.isPromoted = isPromoted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public double getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(double childPrice) {
        this.childPrice = childPrice;
    }

    public boolean isPromoted() {
        return isPromoted;
    }

    public void setPromoted(boolean promoted) {
        isPromoted = promoted;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public int getAvailablePackages() {
        return availablePackages;
    }

    public void setAvailablePackages(int availablePackages) {
        this.availablePackages = availablePackages;
    }

    public Set<Purchase> getPurchasesOfPackage() {
        return purchasesOfPackage;
    }

    public void setPurchasesOfPackage(Set<Purchase> purchasesOfPackage) {
        this.purchasesOfPackage = purchasesOfPackage;
    }
}
