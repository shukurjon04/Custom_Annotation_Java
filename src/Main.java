import database.UserRepository;
import model.Check;
import model.User;
import service.AnnotationEngine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Scanner in = new Scanner(System.in);
        UserRepository userRepository = new UserRepository();
        User user = new User();
        System.out.print("Enter your name: ");
        user.setUsername(in.nextLine());
        System.out.print("Enter your email: ");
        user.setEmail(in.nextLine());
        System.out.print("Enter your password: ");
        user.setPassword(in.nextLine());
        System.out.print("Enter your phone number: ");
        user.setPhone(in.nextLine());

        AnnotationEngine engine = new AnnotationEngine();
        Check run = engine.run(user);
        if (run.isEmail()&& run.isPassword()&& run.isPhone()&& run.isUserName()){
            User insert = userRepository.insert(user);
            System.out.println(" Hush kelibsz ! "+user.toString());
        }
    }
}