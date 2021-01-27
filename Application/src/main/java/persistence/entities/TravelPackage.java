package persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "travel_packages")

@NamedQueries({
        @NamedQuery(
                name = "selectTravelPackageIdByName",
                query = "SELECT travelPackage.id FROM TravelPackage travelPackage " +
                        "WHERE travelPackage.name = :name"
        ),

        @NamedQuery(
                name = "selectTravelPackageByName",
                query = "SELECT travelPackage from TravelPackage travelPackage " +
                        "WHERE travelPackage.name = :name"
        ),

        @NamedQuery(
                name = "updateTravelPackageStockByName",
                query = "UPDATE TravelPackage travelPackage " +
                        "SET travelPackage.availablePackages = travelPackage.availablePackages - :soldItems " +
                        "WHERE travelPackage.name = :name"
        )
})

public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inbound_flight_id")
    private Flight inboundFlight;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "outbound_flight_id")
    private Flight outboundFlight;
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


    public TravelPackage(String name) {
        this.name = name;
    }

    public TravelPackage(String name, Flight inboundFlight, Flight outboundFlight, Hotel hotel, String packageType, boolean isPromoted) {
        this.name = name;
        this.inboundFlight = inboundFlight;
        this.outboundFlight = outboundFlight;
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

    public Flight getInboundFlight() {
        return inboundFlight;
    }

    public void setInboundFlight(Flight inboundFlight) {
        this.inboundFlight = inboundFlight;
    }

    public Flight getOutboundFlight() {
        return outboundFlight;
    }

    public void setOutboundFlight(Flight outboundFlight) {
        this.outboundFlight = outboundFlight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
