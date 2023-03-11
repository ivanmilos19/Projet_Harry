import lombok.Data;
import java.util.InputMismatchException;


import java.util.Scanner;
@Data
public class Wand {
    private int height;
    private String size = null;

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
    public  Wand(){
        Scanner scanner = new Scanner(System.in);
        String newLine = System.getProperty("line.separator");
        System.out.println(newLine + "Time for you to get your wand. How tall are you ? (Write your height in centimeters)");
        do {
            try {
                height = scanner.nextInt();
                break;
            } catch(InputMismatchException e) {
                scanner.nextLine();
                System.out.println(height + "This is not a valid choice");
                System.out.println(newLine + "Please write a valid height");
            }
        }while(true);

        determineWandSize();


        Core core = new Core();
        System.out.println("you have acquired " + size + " wand with a " + core.getCore() + " core."  );
    }


}
