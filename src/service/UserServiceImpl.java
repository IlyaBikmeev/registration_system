package service;

import dao.UserDao;
import entity.User;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("No user found with such id : %d\n", id))
                );
    }

    @Override
    public User findByNickname(String nickname) {
        return userDao.findByNickname(nickname)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("No user found with such nickname : %s\n", nickname))
                );
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(User user) {
        Optional<User> existingUser = userDao.findByNickname(user.getNickname());
        existingUser.ifPresent(u -> {
            throw new UserAlreadyExistsException(String.format(
                    "User with nickname %s already exists!", user.getNickname())
            );
        });
        userDao.save(user);
    }

    @Override
    public User login(String email, String password) throws Exception {
        Optional<User> user = userDao.findByNickname(email);
        if(user.isPresent() && user.get().getPasswordHash().equals(hashPassword(password))) {
            return user.get();
        }
        return null;
    }

    @Override
    public User register(String email, String password) throws Exception {
        Optional<User> existingUser = userDao.findByNickname(email);
        if(existingUser.isEmpty()) {
            User user = new User(email, hashPassword(password));
            userDao.save(user);
            return user;
        }
        else {
            return null;
        }
    }

    private String hashPassword(String password) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(password.getBytes());
        return new BigInteger(1, m.digest()).toString(16);
    }
}
