package mobilelele.app.services;

import mobilelele.app.models.service.UserRegisterServiceModel;
import mobilelele.app.models.service.UserServiceModel;
import org.springframework.stereotype.Service;

public interface UserService {

    boolean authenticate(String username, String password);

    void loginUser(String username);

    void logoutCurrentUser();

    void registerUser(UserRegisterServiceModel userRegisterServiceModel);
}
