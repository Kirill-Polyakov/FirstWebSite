package services;

import dao.UserDao;
import entities.users.User;
import entities.users.UsersList;
import enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder;

    public void saveUsers(String login, String password) {
        String encodePassword = passwordEncoder.encode(password);
        User user = User.builder()
                .id(UUID.randomUUID())
                .login(login)
                .password(encodePassword)
                .role(Role.USER)
                .build();
        userDao.saveUsers(user);
    }

    public Optional<User> getUserByCredentials(String login, String password) {
        UsersList users = userDao.getAllUsers();
        return users.getUsers().stream()
                .filter(user -> user.getLogin().equals(login) && passwordEncoder.matches(password, user.getPassword()))
                .findFirst();
    }
}
