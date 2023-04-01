import controller.UserController;
import dao.UserDao;
import dao.UserDaoImpl;
import service.UserServiceImpl;

public class RegistrationSystem {
    public static void main(String[] args) throws Exception {
        try(UserDao dao = new UserDaoImpl("users.bin")) {
            System.out.println(dao.findAll());
            new UserController(
                new UserServiceImpl(dao)
            ).run();
        }
    }
}
