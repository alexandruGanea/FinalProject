package business.dto;

import javax.validation.constraints.NotNull;

public class TravelPackageDTO {

    private FlightDTO inboundFlightDTO;
    private FlightDTO outboundFlightDTO;
    private HotelDTO hotelDTO;
    private String packageType;
    private MealDTO mealDTO;
    private RoomDTO roomDTO;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private double adultPrice;
    @NotNull
    private double childPrice;
    private boolean isPromoted;
    @NotNull
    private int availablePackages;


    public TravelPackageDTO() {
    }

    public TravelPackageDTO(String name){
        this.name = name;
    }

    public TravelPackageDTO(String name, FlightDTO inboundFlightDTO, FlightDTO outboundFlightDTO, HotelDTO hotelDTO) {
        this.name = name;
        this.inboundFlightDTO = inboundFlightDTO;
        this.outboundFlightDTO = outboundFlightDTO;
        this.hotelDTO = hotelDTO;
    }

    public FlightDTO getInboundFlightDTO() {
        return inboundFlightDTO;
    }

    public void setInboundFlightDTO(FlightDTO inboundFlightDTO) {
        this.inboundFlightDTO = inboundFlightDTO;
    }

    public FlightDTO getOutboundFlightDTO() {
        return outboundFlightDTO;
    }

    public void setOutboundFlightDTO(FlightDTO outboundFlightDTO) {
        this.outboundFlightDTO = outboundFlightDTO;
    }

    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    public void setHotelDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
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

    public int getAvailablePackages() {
        return availablePackages;
    }

    public void setAvailablePackages(int availablePackages) {
        this.availablePackages = availablePackages;
    }

    public MealDTO getMealDTO() {
        return mealDTO;
    }

    public void setMealDTO(MealDTO mealDTO) {
        this.mealDTO = mealDTO;
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
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

    @Override
    public String toString() {
        return "TravelPackageDTO{" +
                "inboundFlightDTO=" + inboundFlightDTO +
                ", outboundFlightDTO=" + outboundFlightDTO +
                ", hotelDTO=" + hotelDTO +
                ", packageType=" + packageType +
                ", adultPrice=" + adultPrice +
                ", childPrice=" + childPrice +
                '}';
    }
}
