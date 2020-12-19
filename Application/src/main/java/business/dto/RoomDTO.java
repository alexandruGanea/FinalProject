package business.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RoomDTO {


    @NotNull
    @NotEmpty
    @NotBlank
    private String roomType;
    @NotNull
    @NotNull
    private int maxGuests;
    private double guestPrice;
    @NotNull
    private int availableRooms;
    private HotelDTO hotelDTO;

    public RoomDTO() {
    }

    public RoomDTO(String roomType) {
        this.roomType = roomType;
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

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    public void setHotelDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
    }


    @Override
    public String toString() {
        return "RoomDTO{" +
                "roomType='" + roomType + '\'' +
                '}';
    }
}
