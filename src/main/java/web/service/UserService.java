package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    void updateUserById(long id, User user);

    void saveUser(User user);

    User getUserById(long id);

    void removeUserById(long id);

    List<User> getAllUsers();


}
