import java.util.*;

public class TaskOne {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random num = new Random();
        int score = 0;

        boolean playAgain = true;
        while (playAgain) {
            System.out.println("Select a range of numbers (e.g., 1 to 100)");
            System.out.print("Enter the range minimum: ");
            int a = input.nextInt();

            System.out.print("Enter the range maximum: ");
            int b = input.nextInt();

            if (a >= b) {
                System.out.println("Invalid range. Minimum should be less than maximum.");
                continue;
            }

            int randomNum = a + num.nextInt(b - a + 1);
            int guess = -1;
            int attempts = 0;
            int maxAttempts = 10;

            System.out.println("Guess the number between " + a + " and " + b);
            System.out.print("You have 10 attempts to guess the number ");
            
            while (attempts < maxAttempts && guess != randomNum) {
                System.out.print("Enter your guess: ");
                guess = input.nextInt();
                attempts++;

                if (guess < randomNum) {
                    System.out.println("Too low, try again!");
                } else if (guess > randomNum) {
                    System.out.println("Too high, try again!");
                } else {
                    System.out.println("Congratulations! You guessed the right number: " + randomNum);
                    score++;
                }
            }

            if (guess != randomNum) {
                System.out.println("You've used all your attempts. The correct number was: " + randomNum);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String response = input.next();
            if (!response.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Your total score: " + score);
        input.close();
    }
}
