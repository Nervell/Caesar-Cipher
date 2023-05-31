import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String word = "Computer Science Awesome"; int shift = 777;
        //Encrypted word
        ArrayList<String> encrypted = seperator(encrypt(shift, word));
        System.out.println(encrypt(shift, word));

        //Decrypted word
        String decrypted = decrypt(unitCorrector(encrypted), 0);
        System.out.println(unitCorrector(seperator(word)));
        System.out.println("Decrypted " + decrypted);
    }

    public static String encrypt(int shift, String word) {
        //Correct the range of the shift
        while (shift > 26) {
            shift -= 26;
        }

        char c; StringBuilder encrypted = new StringBuilder(); String alphabet = "abcdefghijklmnopqrstuvwxyz"; alphabet = alphabet.toUpperCase();
        //loop that go through every letter in the word that going to be encrypted
        for (int i = 0; i < word.length(); i++) {
            //should add spaces if necessary
            if (word.charAt(i) == ' ') {
                encrypted.append(" ");
                continue;
            }
            //Should change the upper and lower cases
            if (Character.isUpperCase(word.charAt(i))) {
                alphabet = alphabet.toUpperCase();
            } else {
                alphabet = alphabet.toLowerCase();
            }

            //loop that go through every letter in the alphabet
            for(int j = 0; j < alphabet.length(); j++) {
                c = alphabet.charAt(j);
                //check if letter in the alphabet match with the letter in the word
                if (c == word.charAt(i)) {
                    //correct the shift if it larger the length of the alphabet
                    if ((j + shift) > 25) {
                        j = ((j + shift) - 26);
                    } else {
                        j += shift;
                    }
                    c = alphabet.charAt(j);
                    encrypted.append(c);
                    break;
                }
            }
        }
        return encrypted.toString();
    }

    public static String decrypt(ArrayList<Unit> words, int shift) {
        Scanner reader = new Scanner(System.in);
        StringBuilder decrypted = new StringBuilder(); String alphabet = "abcdefghijklmnopqrstuvwxyz"; char c;
        //correct shift length
        while (shift > 26) {
            shift -= 26;
        }
        //should go through every word in the array
        for (Unit word : words) {
            //should go through every letter in the word of the array
            for (int i = 0; i < word.getWord().length(); i++) {
                //add spaces if necessary
                if (i == (word.getWord().length() - 1)) {
                    decrypted.append(" ");
                    continue;
                }

                //correct upper and lower cases
                if (Character.isUpperCase(word.getWord().charAt(i))) {
                    alphabet = alphabet.toUpperCase();
                } else {
                    alphabet = alphabet.toLowerCase();
                }

                //go through every letter in the alphabet
                for (int j = 0; j < alphabet.length(); j++) {
                    c = alphabet.charAt(j);
                    //correct shift range
                    if (c == word.getWord().charAt(i)) {
                        if ((j - shift) < 0) {
                            j = ((j - shift) + 26);
                            c = alphabet.charAt(j);
                        } else {
                            c -= (char) shift;
                        }
                        decrypted.append(c);
                        break;
                    }
                }
            }
        }
        //check if the word is correct
        if (check(unitCorrector(seperator(decrypted.toString())))) {
            System.out.print("Is this decryption correct? - " + decrypted + "\n" + "Yes or No: ");
            String answer = reader.nextLine();
            answer = answer.toLowerCase();
            //if yes return decrypted word, if no start method again with shift + 1
            if (answer.equals("yes")) {
                return decrypted.toString();
            } else {
                System.out.println(shift);
                return decrypt(words, shift + 1);
            }
        } else {
            return decrypt(words, shift + 1);
        }
    }

    public static ArrayList<String> seperator(String phrase) {
        ArrayList<String> words = new ArrayList<>();
        //separate phrase into a words
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') {
                words.add(phrase.substring(0,i));
                phrase = phrase.substring(i + 1);
                i = 0;
            }
        }
        words.add(phrase);
        return words;
    }

    public static ArrayList<Unit> unitCorrector(ArrayList<String> words) {
        ArrayList<Unit> wordsNumTwo = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            wordsNumTwo.add(i, new Unit(false,words.get(i)));
        }
        return wordsNumTwo;
    }

    public static boolean check(ArrayList<Unit> words) {
        boolean result = false; ArrayList<String> database = new ArrayList<>();
        //loop that go through every word in the array
        for (Unit word : words) {
            //scanner for the database
            try {
                File myObj = new File("C:\\Users\\98034\\IdeaProjects\\Ceaser Cipher\\src\\words.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine().toUpperCase();
                    data = data.substring(0, 1).toUpperCase() + data.substring(1).toLowerCase();
                    //if word from the array match with the word from the database return true and break the loop, return false otherwise.
                    database.add(data);

                    //System.out.println(words.get(i).isStatus());
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            //break the loop if any of word in the loop is incorrect
            if (database.contains(word.getWord())) {
                word.setStatus(true);
                result = true;
            } else {
                break;
            }
        }
        return result;
    }
}