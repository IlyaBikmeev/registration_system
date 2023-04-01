package dao;

import entity.User;

import java.util.List;
import java.util.Optional;

//CRUD - create read update delete
public interface UserDao extends AutoCloseable {
    Optional<User> findById(int id);
    Optional<User> findByNickname(String nickname);
    List<User> findAll();
    void save(User user);
}
