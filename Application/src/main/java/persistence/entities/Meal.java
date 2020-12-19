package persistence.entities;


import javax.persistence.*;


@Entity
@Table(name = "meals")

@NamedQueries({
        @NamedQuery(
                name = "selectMealByHotelId",
                query = "SELECT meal " +
                        "FROM Meal meal " +
                        "JOIN meal.hotelWithMeals hotel " +
                        "WHERE hotel.id = :id"
        ),
        @NamedQuery(
                name = "selectMealIdByHotelId",
                query = "SELECT meal.id " +
                        "FROM Meal meal " +
                        "JOIN meal.hotelWithMeals hotelWithMeals " +
                        "WHERE hotelWithMeals.id = :id"
        )
})
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "BB_price")
    private double priceBB;
    @Column(name = "HB_price")
    private double priceHB;
    @Column(name = "FB_price")
    private double priceFB;
    @Column(name = "AI_price")
    private double priceAI;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotelWithMeals;

    public Meal() {
    }

    public Meal(double priceBB, double priceHB, double priceFB, double priceAI){
        this.priceBB = priceBB;
        this.priceHB = priceHB;
        this.priceFB = priceFB;
        this.priceAI = priceAI;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Hotel getHotelWithMeals() {
        return hotelWithMeals;
    }

    public void setHotelWithMeals(Hotel hotelWithMeals) {
        this.hotelWithMeals = hotelWithMeals;
    }
}
