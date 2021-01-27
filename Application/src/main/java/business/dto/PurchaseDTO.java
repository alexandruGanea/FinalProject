package business.dto;

public class PurchaseDTO {

    private int noOfAdults;
    private int noOfChildren;
    private TravelPackageDTO travelPackageDTO;
    private double price;
    private ClientDTO clientDTO;
    private String date;

    public PurchaseDTO() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TravelPackageDTO getTravelPackageDTO() {
        return travelPackageDTO;
    }

    public void setTravelPackageDTO(TravelPackageDTO travelPackageDTO) {
        this.travelPackageDTO = travelPackageDTO;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public int getNoOfAdults() {
        return noOfAdults;
    }

    public void setNoOfAdults(int noOfAdults) {
        this.noOfAdults = noOfAdults;
    }

    public int getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
    }
}
