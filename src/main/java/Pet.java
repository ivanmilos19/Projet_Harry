import lombok.Data;
import java.util.Arrays;
@Data
public class Pet {
    private String pet;
    private static String pets[] = {"Owl", "Toad", "Rat", "Cat", "Badger", "Hypogriff"};

    public Pet() {
        String newLine = System.getProperty("line.separator");
        String instructions = (newLine + " Which pet should accompany you ?");
        InputReader petReader = new InputReader(instructions, pets);
        String pet = petReader.readInput();
        System.out.println("A " + pet + " will be by your side");
    }
}
