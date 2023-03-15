import lombok.Data;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

@Data
public class Wand {
    private int height;
    private String size = null;

    Core core;
    private static String qualities[] = {"Power", "Consistency", "Broadness" };
    private static  Core cores[] = { Core.DRAGON_HEARTSTRING, Core.UNICORN_HAIR, Core.PHOENIX_FEATHER };
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
        String playerChoice = qualityReader.readInput();
        int index = Arrays.asList(qualities).indexOf(playerChoice);
        Core core = cores[index];

        System.out.println("You have acquired " + size + " wand with a " + core.name().toLowerCase().replace("_", " ") + " core");
    }
}

