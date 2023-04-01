package controller;

import entity.User;
import service.UserService;

import java.util.Scanner;

public class UserController {
    private final UserService service;
    private final Scanner scanner;

    public UserController(UserService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void run() throws Exception {
        boolean running = true;
        while(running) {
            System.out.println("Введите команду!\n1 - авторизоваться\n2 - создать нового пользователя\n3 - выход");
            int command = scanner.nextInt();
            scanner.nextLine();     //Чтобы считался переход на новую строку
            if (command == 1) {
                System.out.println("Введите логин и пароль для вашего аккаунта: ");
                String login = scanner.nextLine();
                String password = scanner.nextLine();
                login(login, password);

            } else if (command == 2) {
                System.out.println("Введите логин и пароль для вашего аккаунта: ");
                String login = scanner.nextLine();
                String password = scanner.nextLine();
                register(login, password);
            } else if(command == 3) {
                running = false;
            } else {
                System.out.println("Неизвестная команда");
            }
        }
    }

    private void login(String nickname, String password) throws Exception {
        User user = service.login(nickname, password);
        if(user != null) {
            System.out.printf(
                    "Добрый день, %s! Вы успешно авторизованы!\n", user.getNickname()
            );
        }
        else {
            System.out.println("Неверный логин или пароль! Попробуйте ещё раз...");
        }
    }

    private void register(String nickname, String password) throws Exception {
        User user = service.register(nickname, password);
        if(user == null) {
            System.out.println("Пользователь с таким именем уже существует!");
        }
        else {
            System.out.printf("Добрый день, %s! Вы успешно зарегистрировались!\n", user.getNickname());
        }
    }

}
