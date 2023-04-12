import DataHandler.UserDataHandler;
import DataHandler.WrongInputException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name surname patronymic and date of birth");
        String inputBuffer = scanner.nextLine();

        try {
            String result = UserDataHandler.start(inputBuffer);
            System.out.println(result);
        } catch (WrongInputException ignored) {
        }
    }
}