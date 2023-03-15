import lombok.Data;

@Data
public class Pet_class {
    private String pet;
    private static String pets[] = {"Snowy Owl", "Toad", "Rat", "Cat"};

    public Pet_class() {
        String newLine = System.getProperty("line.separator");
        String instructions = (newLine + "Which pet should accompany you ?");
        InputReader petReader = new InputReader(instructions, pets);
        String pet = petReader.readInput();
        System.out.println("You now have a " + pet );
    }
}
