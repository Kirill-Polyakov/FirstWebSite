package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.users.User;
import entities.users.UsersList;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;

@AllArgsConstructor
public class UserDao {
    private final ObjectMapper objectMapper;
    private final File file;

    @SneakyThrows
    public void saveUsers(User user) {
        UsersList users = getAllUsers();
        users.getUsers().add(user);
        objectMapper.writeValue(file, users);
    }

    @SneakyThrows
    public UsersList getAllUsers() {
        if (file.exists() && file.length() != 0) {
            return objectMapper.readValue(file, UsersList.class);
        } else {
            return new UsersList(new ArrayList<>());
        }
    }
}
