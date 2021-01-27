package persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hotels")

@NamedQueries({
        @NamedQuery(
                name = "selectHotelByName",
                query = "SELECT hotel " +
                        "FROM Hotel hotel " +
                        "WHERE hotel.hotelName = :name"
        ),
        @NamedQuery(
                name = "selectHotelByCityName",
                query = "SELECT hotel FROM Hotel hotel " +
                        "JOIN hotel.cityContainingHotel cityContainingHotel " +
                        "WHERE cityContainingHotel.name = :name"
        ),

        @NamedQuery(
                name = "updateHotelName",
                query = " UPDATE Hotel hotel " +
                        "SET hotel.hotelName = :newName " +
                        "WHERE hotel.hotelName = :oldName"
        ),

        @NamedQuery(
                name = "updateHotelDescription",
                query = " UPDATE Hotel hotel " +
                        "SET hotel.description = :newDescription " +
                        "WHERE hotel.description = :oldDescription"
        ),

        @NamedQuery(
                name = "updateHotelRating",
                query = "UPDATE Hotel hotel " +
                        "SET hotel.hotelRating = :newRating " +
                        "WHERE hotel.hotelRating = :oldRating"
        ),

        @NamedQuery(
                name = "deleteHotelByNameAndCity",
                query = "DELETE FROM Hotel " +
                        "WHERE hotelName = :name " +
                        "AND cityContainingHotel = :city"
        ),

        @NamedQuery(
                name = "selectHotelByRoomTypeAndAvailability",
                query = "SELECT hotel " +
                        "FROM Hotel hotel " +
                        "JOIN hotel.roomsInHotel roomsInHotel " +
                        "WHERE roomsInHotel.roomType = :roomType " +
                        "AND roomsInHotel.availableRooms = :roomAvailability"
        ),
        @NamedQuery(
                name = "selectHotelByNameAndCity",
                query = "SELECT hotel " +
                        "FROM Hotel hotel " +
                        "JOIN hotel.cityContainingHotel city " +
                        "WHERE hotel.hotelName = :hotelName " +
                        "AND city.name = :cityName"
        ),
        @NamedQuery(
                name = "selectHotelIdByNameAndCity",
                query = "SELECT hotel.id " +
                        "FROM Hotel hotel " +
                        "JOIN hotel.cityContainingHotel city " +
                        "WHERE hotel.hotelName = :hotelName " +
                        "AND city.name = :cityName"
        ),
        @NamedQuery(
                name = "selectHotelById",
                query = "SELECT hotel " +
                        "FROM Hotel hotel " +
                        "WHERE hotel.id = :hotelId"
        )

})

public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "hotel_name")
    private String hotelName;
    @Column(name = "hotel_rating")
    private int hotelRating;
    @Column(name = "description")
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_hotel_id")
    private City cityContainingHotel;
    @OneToMany(mappedBy = "hotelContainingRoom", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Room> roomsInHotel;
    @OneToOne(mappedBy = "hotelWithMeals", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Meal mealsByHotel;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<TravelPackage> travelPackagesInHotel;


    public Hotel() {
    }

    public Hotel(String hotelName){
        this.hotelName = hotelName;
    }

    public Hotel(String hotelName, int hotelRating, String description) {
        this.hotelName = hotelName;
        this.hotelRating = hotelRating;
        this.description = description;
    }

    public Hotel(String hotelName, int hotelRating, String description, City cityContainingHotel) {
        this.hotelName = hotelName;
        this.hotelRating = hotelRating;
        this.description = description;
        this.cityContainingHotel = cityContainingHotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(int hotelRating) {
        this.hotelRating = hotelRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public City getCityContainingHotel() {
        return cityContainingHotel;
    }

    public void setCityContainingHotel(City cityContainingHotel) {
        this.cityContainingHotel = cityContainingHotel;
    }

    public Set<TravelPackage> getTravelPackagesInHotel() {
        return travelPackagesInHotel;
    }

    public void setTravelPackagesInHotel(Set<TravelPackage> travelPackagesInHotel) {
        this.travelPackagesInHotel = travelPackagesInHotel;
    }

    public Set<Room> getRoomsInHotel() {
        return roomsInHotel;
    }

    public void setRoomsInHotel(Set<Room> roomsInHotel) {
        this.roomsInHotel = roomsInHotel;
    }

    public Meal getMealsByHotel() {
        return mealsByHotel;
    }

    public void setMealsByHotel(Meal mealsByHotel) {
        this.mealsByHotel = mealsByHotel;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelName='" + hotelName + '\'' +
                ", hotelRating=" + hotelRating +
                '}';
    }
}
