import lombok.Data;
import java.util.Scanner;
import java.util.Arrays;
@Data
public class InputReader {
    String instructions;
    private String validChoices[];
    public InputReader(String instructions, String[] validChoices){
        this.instructions = instructions;
        this.validChoices = validChoices;
    }

    private boolean isPlayerChoiceValid(String playerChoice)
    {
        return Arrays.asList(validChoices).contains(playerChoice);
    }

    public void showValidOptions()
    {
        for (int i = 0; i < validChoices.length; i++) {
            System.out.println((i+1) + ": " + validChoices[i]);
        }
    }

    public String readInput() {
        String newLine = System.getProperty("line.separator");
        String playerChoice;
        Scanner scanner = new Scanner(System.in);
        System.out.println(instructions);
        showValidOptions();
        do {
            playerChoice = scanner.nextLine();
            if (isPlayerChoiceValid(playerChoice))
                break;
            System.out.println(playerChoice + " is not a valid choice");
            System.out.println(newLine + "⚠️ Please write only one of the available options");
            showValidOptions();
        } while (true);
        return  playerChoice;

    }
}
