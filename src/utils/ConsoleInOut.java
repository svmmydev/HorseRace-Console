package utils;

import java.util.Scanner;

public class ConsoleInOut {
    /**
     * Returns an integer introduced by the user through the console within the inclusive range of min-max.
     * Before requesting data, it prints a message; if the input is not as expected, it repeats the message.
     * Uses Scanner to obtain the data.
     * @param message String: Message to display on the screen
     * @param min int: Minimum value inclusive
     * @param max int: Maximum value inclusive
     * @return int: Returns the integer entered by the user that meets the conditions
     */
    public static int getIntegerInRange(String message, int min, int max) {
        Scanner input = new Scanner(System.in);
        int entrada = 0;
        boolean check = false;
        do {
            System.out.println(message);
            if (input.hasNextInt()) {
                entrada = input.nextInt();
                if (entrada>=min && entrada<=max) {
                    check = true;
                } else {
                    System.out.println("\u001B[31m"+"The value must be between "+min+" and "+max+"\u001B[0m");
                }
            } else {
                System.out.println("\u001B[31m"+"The data must be an integer"+"\u001B[0m");
            }
            input.nextLine();
        } while (!check);
        return entrada;
    }
    /**
     * After displaying a message on console, accepts user input.
     * If it matches the regular expression pattern, returns the entered data.
     * Otherwise, indicates incorrect format and displays the message again.
     * Uses Scanner to obtain the data.
     * @param message String: Message displayed on the console
     * @param pattern String: Regular expression to match
     * @return String: the correctly formatted value entered by the user
     */
    public static String getStringWithRegex(String message, String pattern) {
        Scanner input = new Scanner(System.in);
        String entrada;
        do {
            System.out.println(message);
            entrada = input.nextLine().trim(); // remove any potential spaces at the beginning/end
            if (entrada.matches(pattern)) { // pattern validation check
                return entrada;
            } else {
                System.out.println("\u001B[31m"+"The data format is incorrect"+"\u001B[0m");
            }
        } while (true);
    }
}
