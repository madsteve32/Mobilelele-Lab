package mobilelele.app.security;

import mobilelele.app.models.entities.enums.Role;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Component
@SessionScope
public class CurrentUser {

    private static final String ANONYMOUS_NAME = "anonymous";

    private String name = ANONYMOUS_NAME;
    private boolean isAnonymous = true;
    private List<Role> userRoles = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        if (anonymous) {
            this.name = ANONYMOUS_NAME;
            this.userRoles.clear();
        }

        isAnonymous = anonymous;
    }

    public boolean isAdmin() {
        return userRoles.contains(Role.Admin);
    }

    public boolean isLoggedIn() {
        return !isAnonymous();
    }

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<Role> newUserRoles) {
        userRoles.clear();
        userRoles.addAll(newUserRoles);
    }
}
