package DataHandler;

import java.util.ArrayList;

public class UserDataHandler {
    public static int checkDate(String userDate) {
        String[] dateTokens = userDate.split("\\.");
        ArrayList<Integer> numbers = new ArrayList<>();

        if (dateTokens.length != 3) {
            System.out.println("Wrong input date");
            throw new NumberFormatException();
        }

        for(String token: dateTokens) {
            try {
                numbers.add(Integer.parseInt(token));
            } catch (NumberFormatException e) {
                System.out.println("Wrong date");
                throw new NumberFormatException();
            }
        }

        if (numbers.get(0) < 1 ||
            numbers.get(0) > 29 ||
            (numbers.get(0) == 29 && numbers.get(2) % 4 != 0) ||
            (numbers.get(0) == 28 && numbers.get(2) % 4 == 0)) {
            System.out.println("Wrong day");
            throw new NumberFormatException();
        }

        if (numbers.get(1) < 1 || numbers.get(1) > 12) {
            System.out.println("Wrong month");
            throw new NumberFormatException();
        }

        if (numbers.get(2) < 1) {
            System.out.println("Wrong year");
            throw new NumberFormatException();
        }

        return numbers.get(2);
    }
}
