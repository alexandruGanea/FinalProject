package business.dto;

import javax.validation.constraints.NotNull;

public class MealDTO {

    @NotNull
    private double priceBB;
    @NotNull
    private double priceHB;
    @NotNull
    private double priceFB;
    @NotNull
    private double priceAI;
    private HotelDTO hotelDTO;

    public MealDTO() {
    }

    public MealDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
    }

    public MealDTO(double priceBB, double priceHB, double priceFB, double priceAI) {
        this.priceBB = priceBB;
        this.priceHB = priceHB;
        this.priceFB = priceFB;
        this.priceAI = priceAI;
    }

    public double getPriceBB() {
        return priceBB;
    }

    public void setPriceBB(double priceBB) {
        this.priceBB = priceBB;
    }

    public double getPriceHB() {
        return priceHB;
    }

    public void setPriceHB(double priceHB) {
        this.priceHB = priceHB;
    }

    public double getPriceFB() {
        return priceFB;
    }

    public void setPriceFB(double priceFB) {
        this.priceFB = priceFB;
    }

    public double getPriceAI() {
        return priceAI;
    }

    public void setPriceAI(double priceAI) {
        this.priceAI = priceAI;
    }

    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    public void setHotelDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
    }


}
