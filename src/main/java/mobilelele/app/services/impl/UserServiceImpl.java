package mobilelele.app.services.impl;

import mobilelele.app.models.entities.User;
import mobilelele.app.models.entities.UserRole;
import mobilelele.app.models.entities.enums.Role;
import mobilelele.app.models.service.UserRegisterServiceModel;
import mobilelele.app.repositories.UserRepository;
import mobilelele.app.repositories.UserRoleRepository;
import mobilelele.app.security.CurrentUser;
import mobilelele.app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public boolean authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            return false;
        } else {
            return passwordEncoder.matches(password, user.get().getPassword());
        }
    }

    @Override
    public void loginUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();

        List<Role> userRoles = user.
                getUserRoles().
                stream().
                map(UserRole::getRole)
                .collect(Collectors.toList());

        currentUser.setAnonymous(false);
        currentUser.setName(user.getUsername());
        currentUser.setUserRoles(userRoles);
    }

    @Override
    public void logoutCurrentUser() {
        currentUser.setAnonymous(true);
    }

    @Override
    @Transactional
    public void registerUser(UserRegisterServiceModel userRegisterServiceModel) {
        User user = this.modelMapper.map(userRegisterServiceModel, User.class);
        user.setFirstName(userRegisterServiceModel.getFirstName());

        user.setCreated(Instant.now());
        user.setUpdated(Instant.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        this.userRepository.save(user);
    }
}
