package dao;

import entity.User;

import java.util.List;

public interface UserDao {
    void create(User user);
    void delete(Long id);
    void update(User user);
    User findById(Long id);
    List<User> findByLastName(String name);
    List<User> findAll();
}
