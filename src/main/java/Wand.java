import lombok.Data;
import java.util.InputMismatchException;
import java.util.Scanner;

@Data
public class Wand {
    private int height;
    private String size = null;
    private String playerChoice = null;
    private static String qualities[] = {"Power", "Consistency", "Broadness"};
    private void determineWandSize() {
        if (height <= 165) {
            this.size = "an 8 inch";
        }
        else if (height > 165 && height < 185) {
            this.size = "an 11 inch";
        }
        else if (height >= 185) {
            this.size = "a 14 inch";
        }

    }

    public Wand(){
        Scanner scanner = new Scanner(System.in);
        String newLine = System.getProperty("line.separator");
        System.out.println(newLine + "Time for you to get your wand. How tall are you ? (Write your height in centimeters)");
        do {
            try {
                height = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                break;
            } catch(InputMismatchException e) {
                scanner.nextLine();
                System.out.println("⚠️ Please write a valid height.");
            }
        }while(true);

        determineWandSize();


        String instructions = newLine + "Choose the property of your core:";
        InputReader qualityReader = new InputReader(instructions, qualities);
        qualityReader.showValidOptions();



        playerChoice = scanner.nextLine();
        Core core = Core.valueOf(playerChoice);

        switch(core) {
            case PHOENIX_FEATHER -> System.out.println("");
            case DRAGON_HEARTSTRING -> System.out.println("");
            case UNICORN_HAIR -> System.out.println("");
        }

        System.out.println("You have acquired " + size + " wand with a " + core.name().toLowerCase().replace("_", " ") + " core");

    }
}

