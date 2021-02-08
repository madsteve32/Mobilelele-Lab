package mobilelele.app.models.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginServiceModel {

    private String username;
    private String password;

    @NotNull
    @Size(min = 2, message = "Username must be more than 2 characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Size(min = 3, message = "Password must be more than 3 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
