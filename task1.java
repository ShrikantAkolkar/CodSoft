
import java.util.Scanner;  
import java.lang.Math;  

public class task1 {  
    public static void main(String[] args) {  
        int secretNumber = (int) (Math.random() * 100) + 1;  
        int attemptsLeft = 5;  
        Scanner scanner = new Scanner(System.in);  
        boolean guessedCorrectly = false;  
        
        System.out.println("I'm thinking of a number between 1 and 100.");  
        System.out.println("You have " + attemptsLeft + " attempts to guess the number.");  
        
        while (attemptsLeft > 0) {  
            System.out.print("Enter your guess: ");  
            int guess = scanner.nextInt();  
            
            if (guess == secretNumber) {  
                System.out.println("Congratulations! You've guessed the number. You win!");  
                guessedCorrectly = true;  
                break;  
            } else if (guess > secretNumber) {  
                System.out.println("Your guess is too high. You have " + (attemptsLeft - 1) + " attempts left.");  
            } else {  
                System.out.println("Your guess is too low. You have " + (attemptsLeft - 1) + " attempts left.");  
            }  
            
            attemptsLeft--;  
        }  
        
        if (!guessedCorrectly) {  
            System.out.println("You've run out of attempts. The correct number was " + secretNumber + ". You lose!");  
        }  
    }  
}
