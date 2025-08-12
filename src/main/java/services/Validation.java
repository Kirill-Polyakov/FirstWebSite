package services;

import dao.UserDao;
import entities.users.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Validation {
    private final UserDao userDao;

    public boolean validateRegister(String login, String password) {
        return validateLogin(login) && !isDuplicate(login) && validatePassword(password);
    }

    private boolean isDuplicate(String login) {
        return userDao.getAllUsers().getUsers().stream().map(User::getLogin).anyMatch(login::equals);
    }

    private boolean validateLogin(String login) {
        String regex = "^[a-zA-Z0-9_-]{3,16}$";
        return login.matches(regex);
    }

    private boolean validatePassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        return password.matches(regex);
    }
}
