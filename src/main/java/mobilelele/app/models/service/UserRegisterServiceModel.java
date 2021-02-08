package mobilelele.app.models.service;

import mobilelele.app.models.entities.UserRole;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UserRegisterServiceModel {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String roles;

    @NotNull
    @Length(min = 2, message = "Firstname must be more than 2 characters.")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Length(min = 2, message = "Lastname must be more than 2 characters.")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Size(min = 2, message = "Username must be more than 2 characters.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Size(min = 3, message = "Password must be more than 3 characters.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "Role is required.")
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
