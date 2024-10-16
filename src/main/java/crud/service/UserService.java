package crud.service;

import crud.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(int id);
    public User getUserById(int id);
}
