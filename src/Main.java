import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter minimum length of password: ");
            int minLength = scanner.nextInt();

            System.out.print("Enter maximum length of password: ");
            int maxLength = scanner.nextInt();

            if (minLength > maxLength || minLength <= 0) {
                System.out.println("Error. Minimum length must be lower than max length and more than 0.");
                System.out.println("Starting new password generation due to error...\n");
                continue;
            }

            System.out.print("Include lowercase? (y/n): ");
            boolean includeLowercase = scanner.next().equalsIgnoreCase("y");

            System.out.print("Include uppercase? (y/n): ");
            boolean includeUppercase = scanner.next().equalsIgnoreCase("y");

            System.out.print("Include numbers? (y/n): ");
            boolean includeNumbers = scanner.next().equalsIgnoreCase("y");

            System.out.print("Include symbols? (y/n): ");
            boolean includeSymbols = scanner.next().equalsIgnoreCase("y");

            if (!includeLowercase && !includeUppercase && !includeNumbers && !includeSymbols) {
                System.out.println("Error. You have to choose at least one type of symbols to include.");
                System.out.println("Starting new password generation due to error...\n");
                continue;
            }

            String password = generatePassword(minLength, maxLength, includeLowercase, includeUppercase, includeNumbers, includeSymbols);
            System.out.println("Your password: " + password);

            String strength = evaluatePasswordStrength(password);
            System.out.println("Password difficulty: " + strength);
            break;
        }
    }

    private static String generatePassword(int minLength, int maxLength, boolean includeLowercase, boolean includeUppercase, boolean includeNumbers, boolean includeSymbols) {
        Random random = new Random();
        String allCharacters = "";

        if (includeLowercase) {
            allCharacters += "abcdefghijklmnopqrstuvwxyz";
        }

        if (includeUppercase) {
            allCharacters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }

        if (includeNumbers) {
            allCharacters += "0123456789";
        }

        if (includeSymbols) {
            allCharacters += "!@#$%^&*()-_=+[]{};:'\",.<>/?`~";
        }

        int length = random.nextInt(maxLength - minLength + 1) + minLength;
        String password = "";

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allCharacters.length());
            password += allCharacters.charAt(randomIndex);
        }

        return password;
    }

    private static String evaluatePasswordStrength(String password) {
        boolean hasLowercase = false;
        boolean hasUppercase = false;
        boolean hasNumber = false;
        boolean hasSymbol = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if ("abcdefghijklmnopqrstuvwxyz".contains(String.valueOf(c))) {
                hasLowercase = true;
            }

            if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(String.valueOf(c))) {
                hasUppercase = true;
            }

            if ("0123456789".contains(String.valueOf(c))) {
                hasNumber = true;
            }

            if ("!@#$%^&*()-_=+[]{};:'\",.<>/?`~".contains(String.valueOf(c))) {
                hasSymbol = true;
            }
        }

        int score = 0;
        if (hasLowercase) score++;
        if (hasUppercase) score++;
        if (hasNumber) score++;
        if (hasSymbol) score++;

        if (score == 4 && password.length() >= 12) {
            return "Very Strong";
        } else if (score >= 3 && password.length() >= 8) {
            return "Strong";
        } else if (score >= 2 && password.length() >= 6) {
            return "Medium";
        } else {
            return "Weak";
        }
    }
}