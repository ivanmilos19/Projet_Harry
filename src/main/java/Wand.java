import lombok.Data;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

@Data
public class Wand {
    private int height;
    private int size;

    Core core;


    private static String qualities[] = {"Power", "Consistency", "Broadness" };
    private static  Core cores[] = { Core.DRAGON_HEARTSTRING, Core.UNICORN_HAIR, Core.PHOENIX_FEATHER };
    private void determineWandSize() {
        if (height <= 165) {
            this.size = 8 ;
        }
        else if (height > 165 && height < 185) {
            this.size = 11;
        }
        else if (height >= 185) {
            this.size = 14;
        }

    }



    public void Wand(){
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

        System.out.println("You have a: " + size + " inch wand with a " + core.name().toLowerCase().replace("_", " ") + " core");


    }
}


