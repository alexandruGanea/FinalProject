package persistence.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "deleteAccountByName", query = "DELETE FROM Account " +
                "WHERE accountName = :accountName"),
        @NamedQuery(name = "changeAccountPassword", query = "UPDATE Account account SET account.accountPassword = :newPassword " +
                "WHERE account.accountName = accountName"),
        @NamedQuery(name = "updateUserLogin", query = "UPDATE Account account SET account.isUserLogin = :isUserLogin " +
                "WHERE account.accountName = :accountName AND account.accountPassword = :accountPassword"),
        @NamedQuery(name = "updateAdminLogin", query = "UPDATE Account account SET account.isAdminLogin = :isAdminLogin " +
                "WHERE accountName = :accountName AND account.accountPassword = :accountPassword"),
        @NamedQuery(name = "selectAccountByNameAndPassword", query = "SELECT account from Account account " +
                "WHERE account.accountName = :accountName AND account.accountPassword = :accountPassword"),
        @NamedQuery(name = "selectAccountIdByNameAndPassword", query = "SELECT account.id FROM Account account " +
                "WHERE account.accountName = :accountName AND account.accountPassword = :accountPassword"),
        @NamedQuery(name = "selectLoginByNameAndPassword", query = "SELECT account.isUserLogin FROM Account account " +
                "WHERE account.accountName = :accountName AND account.accountPassword = :accountPassword")
})

@Entity
@Table(name = "accounts")

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "account_password")
    private String accountPassword;
    @Column(name = "is_admin_login")
    private boolean isAdminLogin;
    @Column(name = "is_user_login")
    private boolean isUserLogin;
    @OneToOne(mappedBy = "clientAccount", cascade = CascadeType.ALL)
    private Client client;

    public Account() {
    }

    public Account(String accountName, String accountPassword) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public boolean isAdminLogin() {
        return isAdminLogin;
    }

    public void setAdminLogin(boolean adminLogin) {
        isAdminLogin = adminLogin;
    }

    public boolean isUserLogin() {
        return isUserLogin;
    }

    public void setUserLogin(boolean userLogin) {
        isUserLogin = userLogin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountName='" + accountName + '\'' +
                ", isAdminLogin=" + isAdminLogin +
                ", isUserLogin=" + isUserLogin +
                '}';
    }
}