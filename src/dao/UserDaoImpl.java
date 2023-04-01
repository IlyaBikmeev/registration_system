package dao;

import entity.User;
import exceptions.UserNotFoundException;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao, Serializable {
    private int userCounter;
    private final String fileName;
    private final List<User> users;
    private int counter;

    public UserDaoImpl(String fileName) throws IOException, ClassNotFoundException{
        this.fileName = fileName;
        this.users = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Object user = null;
            while((user = in.readObject()) != null) {
                ++counter;
                users.add((User) user);
            }
        } catch(FileNotFoundException ex) {
            System.out.println("Not found file. Created new.");
        } catch(EOFException ex) {
            System.out.println("Data has been already read");
        }
    }

    @Override
    public Optional<User> findById(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findAny();
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return users.stream()
                .filter(u -> u.getNickname().equals(nickname))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public void save(User user) {

        user.setId(++counter);
        this.users.add(user);
    }

    @Override
    public void close() throws Exception {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for(User user : users) {
                out.writeObject(user);
            }
        }
    }
}
