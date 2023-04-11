import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name surname patronymic and date of birth");
        String[] inputTokens = scanner.nextLine().split(" ");

        if (inputTokens.length != 4) {
            System.out.println("Wrong input");
        } else {
            //TODO
        }
    }
}