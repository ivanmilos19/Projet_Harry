import lombok.Data;
import java.util.Arrays;
@Data
public class SortingHat {

    private House house;

    private static String qualities[] = {"Daring", "Curious", "Loyal", "Ambitious"};
    private static String houses[] = {"Gryffindor", "Ravenclaw", "Hufflepuff", "Slytherin"};


    public String determineHouse(String playerChoice) {
        String house = null;
        int index = Arrays.asList(qualities).indexOf(playerChoice);
        house = houses[index];
        return house;
    }

    public SortingHat() {
        String newLine = System.getProperty("line.separator");
        String instructions = newLine + "Welcome to Hogwarts ! The sorting hat will choose which house you will belong to. If you could describe yourself in one word which of the following qualities would you choose ?";
        InputReader qualityReader = new InputReader(instructions, qualities);
        int choice = qualityReader.readInputByNumber();
        String quality = qualities[choice - 1];
        String houseName = determineHouse(quality);
        System.out.println("You are in " + houseName + " !");

        if (houseName == "Gryffindor")
            house = new Gryffindor();
        else if (houseName == "Ravenclaw") {
            house = new Ravenclaw();
        } else if (houseName == "Hufflepuff") {
            house = new Hufflepuff();
        } else if (houseName == "Slytherin") {
            house = new Slytherin();
        }
    }
}
