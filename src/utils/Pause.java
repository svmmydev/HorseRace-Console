package utils;

import java.util.Scanner;

/**
 * Class created to make dramatic/pragmatic pauses when displaying information through the console.
 */
public class Pause {

    /**
     * Pause the execution of the current thread for the specified number of milliseconds.
     * Handles InterruptedException appropriately.
     *
     * @param value The number of milliseconds to pause the thread.
     */
    public static void milliseconds(long value) {
        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            // Leave catch block empty intentionally
        }
    }


    /**
     * Pause the execution of the current thread for the specified number of seconds.
     * Handles InterruptedException appropriately.
     *
     * @param value The number of seconds to pause the thread.
     */
    public static void seconds(long value) {
        milliseconds(value * 1000); // Convert seconds to milliseconds
    }

    /**
     * Pauses the program execution until the user presses Enter.
     * Displays a message indicating the user should press Enter to continue.
     */
    public static void untilEnter() {
        Scanner input = new Scanner(System.in);
        System.out.println("Press Enter to continue...");
        input.nextLine(); // Wait for the user to press Enter
    }
}