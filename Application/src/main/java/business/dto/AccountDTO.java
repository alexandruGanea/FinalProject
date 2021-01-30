package business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    private String accountName;
    @NotNull
    @NotEmpty
    @NotBlank
    private String accountPassword;
    private boolean isAdminLogin;
    private boolean isUserLogin;

    public AccountDTO() {
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

    public AccountDTO(String accountName, String accountPassword) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;

    }
}