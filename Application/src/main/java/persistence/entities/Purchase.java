package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "purchases")

public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "total_price")
    private double totalPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "package_id")
    private TravelPackage travelPackage;
    //M2M with Client Entity, Client initiates CRUD


}
