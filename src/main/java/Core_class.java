import lombok.Data;
import java.util.Arrays;

@Data
public class Core_class {

    private String core;
    private static String qualities[] = {"Power", "Consistency", "Broadness"};
    private static String cores[] = {"Dragon Heartstring", "Unicorn Hair", "Phoenix Feather"};

    public String determineCore(String playerChoice) {
        String core = null;
        int index = Arrays.asList(qualities).indexOf(playerChoice);
        core = cores[index];
        return core;
    }

    public Core_class() {
        String newLine = System.getProperty("line.separator");
        String instructions = newLine + "Choose the property of your core:";
        InputReader qualityReader = new InputReader(instructions, qualities);
        String quality = qualityReader.readInput();
        core = determineCore(quality);

    }


}

