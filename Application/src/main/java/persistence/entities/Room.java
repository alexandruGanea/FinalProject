package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "rooms")

@NamedQueries({
        @NamedQuery(
                name = "selectRoomByType",
                query = "SELECT room " +
                        "FROM Room room " +
                        "WHERE room.roomType = :roomType"
        ),

        @NamedQuery(
                name = "selectRoomByPrice",
                query = "SELECT room " +
                        "FROM Room room " +
                        "WHERE room.guestPrice <= :minPrice AND room.guestPrice >= :maxPrice"
        ),
        @NamedQuery(
                name = "selectRoomsByMaxGuests",
                query = "SELECT room " +
                        "FROM Room room " +
                        "WHERE room.maxGuests = :maxGuests"
        ),
        @NamedQuery(
                name = "selectRoomByTypeAndHotelId",
                query = "SELECT room FROM Room room " +
                        "JOIN room.hotelContainingRoom hotel " +
                        "WHERE room.roomType = :roomType " +
                        "AND hotel.id = :id"
        ),
        @NamedQuery(
                name = "selectRoomIdByTypeAndHotelId",
                query = "SELECT room.id FROM Room room " +
                        "JOIN room.hotelContainingRoom hotel " +
                        "where room.roomType = :roomType " +
                        "AND hotel.id = :id"
        ),
        @NamedQuery(
                name = "updateRoomAvailabilityById",
                query = "UPDATE Room room " +
                        "SET room.availableRooms = room.availableRooms-1 " +
                        "WHERE room.id = :roomId"
        )
})

public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "room_type")
    private String roomType;
    @Column(name = "guest_price")
    private double guestPrice;
    @Column(name = "available_rooms")
    private int availableRooms;
    @Column(name = "max_guests")
    private int maxGuests;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotelContainingRoom;


    public Room() {
    }

    public Room(String roomType) {
        this.roomType = roomType;
    }

    public Room(String roomType, int maxGuests) {
        this.roomType = roomType;
        this.maxGuests = maxGuests;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getGuestPrice() {
        return guestPrice;
    }

    public void setGuestPrice(double guestPrice) {
        this.guestPrice = guestPrice;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public Hotel getHotelContainingRoom() {
        return hotelContainingRoom;
    }

    public void setHotelContainingRoom(Hotel hotelContainingRoom) {
        this.hotelContainingRoom = hotelContainingRoom;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomType='" + roomType + '\'' +
                ", roomPrice=" + guestPrice +
                ", roomAvailability=" + availableRooms +
                '}';
    }
}
