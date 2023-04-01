package service;

import entity.User;

import java.util.List;

public interface UserService {
    User findById(int id);
    User findByNickname(String nickname);
    List<User> findAll();
    void save(User user);
    User login(String email, String password) throws Exception;
    User register(String email, String password) throws Exception;
}
