import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        //user interface
        System.out.println("Do you want to" + "\n" + "1. Encrypt" + "\n" + "2. Decrypt");
        int userChoice = reader.nextInt();
        int shift = 0;
        reader.nextLine();

        if (userChoice == 1) {
            System.out.print("Enter a phrase you want to encrypt: ");
            String phrase = reader.nextLine().toLowerCase();
            System.out.print("Enter a shift: ");
            shift = reader.nextInt();
            reader.nextLine();

            System.out.println("Original word: " + phrase + "\nEncrypted word: " + encrypt(phrase, shift));
        } else if (userChoice == 2) {
            System.out.print("Enter a phrase you want to decrypt: ");
            String phrase = reader.nextLine().toLowerCase();
            System.out.println("Do you know the shift?" + "\n1. Yes\n2. No");
            int userInputShift = reader.nextInt();
            boolean isShiftPresent = false;
            reader.nextLine();

            if (userInputShift == 1) {
                System.out.print("Enter the shift: ");
                shift = reader.nextInt();
                isShiftPresent = true;
            }
            System.out.println("Encrypted phrase: " + phrase + "\nDecrypted phrase: " + decrypt(phrase, shift, isShiftPresent));
        }


    }

    public static StringBuilder encrypt(String phrase, int shift) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz"; StringBuilder encrypted = new StringBuilder();

        //correct shift
        while (shift > 26) {
            shift -= 26;
        }

        for (int i = 0; i < phrase.length(); i++) {

            //add spaces
            if (phrase.charAt(i) == ' ') {
                encrypted.append(" ");
                continue;
            }


            //index of letter in alphabet that equal to letter in the phrase with shift
            int index = alphabet.indexOf(phrase.charAt(i)) + shift;
            if (index > 25) {
                encrypted.append(alphabet.charAt(index - 26));
            } else {
                encrypted.append(alphabet.charAt(index));
            }
        }

        return encrypted;
    }

    public static StringBuilder decrypt(String phrase, int shift, boolean isShiftPresent) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz"; StringBuilder decrypted = new StringBuilder();

        //correct shift
        while (shift > 26) {
            shift -= 26;
        }

        for (int i = 0; i < phrase.length(); i++) {

            //add spaces
            if (phrase.charAt(i) == ' ') {
                decrypted.append(" ");
                continue;
            }

            //index of letter in alphabet that equal to letter in the phrase with shift
            int index = alphabet.indexOf(phrase.charAt(i)) - shift;
            if (index < 0) {
                decrypted.append(alphabet.charAt(index + 26));
            } else {
                decrypted.append(alphabet.charAt(index));
            }
        }

        if (isShiftPresent) {
            return decrypted;
        } else {
            if (check(decrypted)) {
                return decrypted;
            } else {
                return decrypt(phrase, shift + 1, false);
            }
        }
    }

    public static Boolean check(StringBuilder phrase) {
        boolean result = true; Database database = new Database();

        List<String> words = new ArrayList<>();
        //separate phrase into words
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') {
                words.add(phrase.substring(0,i));
                phrase = new StringBuilder(phrase.substring(i + 1));
                i = 0;
                if (i + 1 == phrase.length()) {
                    break;
                }
            }
        }
        words.add(String.valueOf(phrase));

        //
        List<Unit> wordsNumTwo = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            wordsNumTwo.add(i, new Unit(words.get(i), false));
        }

        for (Unit unit : wordsNumTwo) {
            if (!database.getDatabase().contains(unit.getName())) {
                return false;
            }
        }

        return result;
    }

}