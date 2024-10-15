package crud.dao;

import crud.model.User;

public interface UserDao {
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
    public User getUserById(int id);
}
