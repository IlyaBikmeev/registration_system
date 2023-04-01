package entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private int id;
    private String nickname;
    private String passwordHash;
    private final Date registerDate;

    public User(String nickname, String passwordHash) {
        this.nickname = nickname;
        this.passwordHash = passwordHash;
        this.registerDate = new Date();
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", registerDate=" + registerDate +
                '}';
    }
}
