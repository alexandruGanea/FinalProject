package persistence.entities;

import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clients")

@NamedQueries({
        @NamedQuery(
                name = "deleteClientByEmail",
                query = "DELETE from Client client WHERE client.email = :email"
        ),

        @NamedQuery(
                name = "selectClientByAccountId",
                query = "SELECT client FROM Client client JOIN client.clientAccount account " +
                        "WHERE account.id = :id"
        ),

        @NamedQuery(
                name = "selectClientByEmail",
                query = "SELECT client FROM Client client WHERE client.email = :email"
        ),
        @NamedQuery(
                name = "selectClientIdByAccountId",
                query = "SELECT client.id FROM Client client " +
                        "JOIN client.clientAccount account " +
                        "WHERE account.id = :id"
        )
})

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    //M2M with Purchase Entity, Client initiates CRUD
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "clients_purchases",
            joinColumns = {@JoinColumn(name = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "purchase_id")})
    private Set<Purchase> purchasesByClient;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account clientAccount;

    public Client() {
    }

    public Client(String lastName, String firstName, int yearOfBirth, String address, String phone, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.yearOfBirth = yearOfBirth;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Purchase> getPurchasesByClient() {
        return purchasesByClient;
    }

    public void setPurchasesByClient(Set<Purchase> purchasesByClient) {
        this.purchasesByClient = purchasesByClient;
    }

    public Account getClientAccount() {
        return clientAccount;
    }

    public void setClientAccount(Account clientAccount) {
        this.clientAccount = clientAccount;
    }

    @Override
    public String toString() {
        return "Client{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
