import lombok.Data;

import java.util.Arrays;

@Data
public class SortingHat {
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String RESET = "\033[0m";  // Text Reset


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
        String instructions = newLine + CYAN_BOLD_BRIGHT + "Welcome to Hogwarts ! The sorting hat will choose which house you will belong to. If you could describe yourself in one word which of the following qualities would you choose ?" + RESET;
        InputReader qualityReader = new InputReader(instructions, qualities);
        int choice = qualityReader.readInputByNumber();
        String quality = qualities[choice - 1];
        String houseName = determineHouse(quality);
        System.out.println("You are in " + houseName + " !");

        if (houseName == "Gryffindor") {
            house = new Gryffindor();
            System.out.println("Your defense is higher.");
        } else if (houseName == "Ravenclaw") {
            house = new Ravenclaw();
            System.out.println("Your basic spell accuracy is higher.");
        } else if (houseName == "Hufflepuff") {
            house = new Hufflepuff();
            System.out.println("Your potions effects are stronger.");
        } else if (houseName == "Slytherin") {
            house = new Slytherin();
            System.out.println("Your basic spell damage is higher.");
        }
    }
}
