import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    private final List<String> database;

    public Database() {
        database = new ArrayList<>();
        //This code is going through txt file which contains more than 400,000 words and then store them in the list
        try {
            File myObj = new File("C:\\Users\\98034\\IdeaProjects\\CaesarCipher\\src\\words.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().toLowerCase();
                database.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public List<String> getDatabase() {
        return this.database;
    }
}
