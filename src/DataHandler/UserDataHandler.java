package DataHandler;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataHandler {
    /**
     * Checks the entered information for correctness and brings it to the right view
     *
     * @param userData information entered by the user
     * @return information in the correct form
     * @throws WrongInputException if the name or date is entered incorrectly
     */
    public static String start(String userData) throws WrongInputException {
        String[] inputTokens = userData.split(" ");

        if (inputTokens.length != 4) {
            System.out.println("Wrong input");
            throw new WrongInputException();
        }

        try {
            checkName(inputTokens[0], inputTokens[1], inputTokens[2]);
            checkDate(inputTokens[3]);
        } catch (NumberFormatException | InputMismatchException e) {
            throw new WrongInputException();
        }

        return getName(inputTokens[0], inputTokens[1], inputTokens[2]) + " " +
               getGender(inputTokens[2]) + " " +
               getAge(inputTokens[3]);
    }

    /**
     * Checks the date for correctness
     *
     * @param userDate Date entered by the user
     */
    private static void checkDate(String userDate) {
        String[] dateTokens = userDate.split("\\.");
        ArrayList<Integer> numbers = new ArrayList<>();

        if (dateTokens.length != 3) {
            System.out.println("Wrong input date");
            throw new NumberFormatException();
        }

        for (String token: dateTokens) {
            try {
                numbers.add(Integer.parseInt(token));
            } catch (NumberFormatException e) {
                System.out.println("Wrong date");
                throw new NumberFormatException();
            }
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate;

        try {
            birthDate = LocalDate.of(numbers.get(2), numbers.get(1), numbers.get(0));
        } catch (DateTimeException ex) {
            System.out.println("Wrong date");
            throw new NumberFormatException();
        }

        if (currentDate.isBefore(birthDate)) {
            System.out.println("Wrong date");
            throw new NumberFormatException();
        }
    }

    /**
     * Checks the name for correctness
     *
     * @param surname surname entered by the user
     * @param name name entered by the user
     * @param patronymic patronymic entered by the user
     */
    private static void checkName(String surname, String name, String patronymic) {
        Pattern pattern = Pattern.compile("[а-яёА-ЯЁ]+");
        Matcher matcher = pattern.matcher(name + surname + patronymic);

        char patronymicLastLetter = patronymic.charAt(patronymic.length() - 1);

        if (!matcher.matches() || (patronymicLastLetter != 'ч' && patronymicLastLetter != 'а')) {
            System.out.println("Wrong name");
            throw new InputMismatchException();
        }
    }

    /**
     * Gives the age with the correct ending
     *
     * @param userDate Date entered by the user
     * @return age with the correct ending
     */
    private static String getAge(String userDate) {
        String[] dateTokens = userDate.split("\\.");

        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = LocalDate.of(Integer.parseInt(dateTokens[2]),
                                           Integer.parseInt(dateTokens[1]),
                                           Integer.parseInt(dateTokens[0]));

        String age = Integer.toString(Period.between(birthDate, currentDate).getYears());
        int ageValue = Integer.parseInt(age);

        int lastNumber = Integer.parseInt(String.valueOf(age.charAt(age.length() - 1)));

        if ((ageValue > 10 && ageValue < 15) || (lastNumber > 4 && lastNumber <= 9) || (lastNumber == 0)) {
            age += " лет";
        } else if (lastNumber > 1 && lastNumber < 5) {
            age += " года";
        } else {
            age += " год";
        }

        return  age;
    }

    /**
     * Gives the gender
     *
     * @param patronymic patronymic entered by the user
     * @return the gender
     */
    private static String getGender(String patronymic) {
        char patronymicLastLetter = patronymic.charAt(patronymic.length() - 1);

        if (patronymicLastLetter == 'ч') {
            return "М";
        } else {
            return "Ж";
        }
    }

    /**
     * Gives surname with initials
     *
     * @param surname surname entered by the user
     * @param name name entered by the user
     * @param patronymic patronymic entered by the user
     * @return  surname with initials
     */
    private static String getName(String surname, String name, String patronymic) {
        return surname + " " + name.charAt(0) + "." + patronymic.charAt(0) + ".";
    }
}
