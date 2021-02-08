package mobilelele.app.models.entities;

import mobilelele.app.models.entities.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole extends BaseEntity {


    private Role role;

    public UserRole() {
    }


    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
