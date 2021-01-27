package persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "purchases")

@NamedQueries({
        @NamedQuery(name = "selectPurchasesByPrice",
                query = "SELECT purchase FROM Purchase purchase " +
                        "WHERE purchase.totalPrice = :price"
        ),

        @NamedQuery(name = "selectPurchasesByDate",
                query = "SELECT purchase FROM Purchase purchase " +
                        "WHERE purchase.purchaseDate = :date")
})

public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "purchase_datetime")
    private String purchaseDate;
    @Column(name = "total_price")
    private double totalPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "package_id")
    private TravelPackage travelPackage;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client clientWithPurchases;


    public Purchase() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    public void setTravelPackage(TravelPackage travelPackage) {
        this.travelPackage = travelPackage;
    }

    public Client getClient() {
        return clientWithPurchases;
    }

    public void setClient(Client clientWithPurchases) {
        this.clientWithPurchases = clientWithPurchases;
    }
}
