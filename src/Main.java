import model.User;
import service.AnnotationEngine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
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
        engine.run(user);
    }
}