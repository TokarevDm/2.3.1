package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    public void updateUserById(long id, User user);

    public void saveUser(User user);

    public User getUserById(long id);

    public void removeUserById(long id);

    public List<User> getAllUsers();
}
