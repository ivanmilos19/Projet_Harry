import com.sun.nio.sctp.SendFailedNotification;
import lombok.Data;

import java.util.InputMismatchException;
import java.util.Scanner;

@Data
public class GameLogic {
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE
    String newLine = System.getProperty("line.separator");
    Scanner scanner = new Scanner(System.in);
    int playerChoice;

    public  void battle(Wizard wizard, AbstractEnemy enemy) {
        while (true)
        {
            System.out.print(GREEN_BOLD_BRIGHT + newLine + "Wizard HP: " + wizard.currentHP + "/" + wizard.baseHP + " ❤");
            System.out.print(WHITE_BOLD_BRIGHT +  "  |  " + RED_BOLD_BRIGHT + "Enemy HP: " + enemy.currentHP + "/" + enemy.baseHP + " ❤") ;

            System.out.println(RESET + newLine + newLine + "Choose an action:"+ newLine);
            System.out.println( "1: Attack" +newLine+ "2: Defend" +newLine+ "3: Potion" );



            do {
                try {
                    playerChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    break;
                } catch(InputMismatchException e) {
                    scanner.nextLine();
                    System.out.println(YELLOW_BOLD_BRIGHT+"⚠️ Please write a valid choice.");
                }
            }while(true);


            if (playerChoice == 1) {
                wizard.attack(enemy); // the protagonist is attacking its enemy
            }
            if (playerChoice == 2 ) {
                wizard.defend(wizard);
            }

            if (playerChoice == 3) {
                wizard.equipDamagePotion();
                continue;
            }

            if (enemy.isDead())
            {
                System.out.println(newLine +"Foe defeated !");
                break;
            }

            enemy.attack(wizard); // now the protagonist is attacked

            if (wizard.isDead())
            {
                System.out.println(newLine + "Game Over");
                break;
            }


            System.out.println( WHITE_BOLD_BRIGHT + "--------------------------------------------------");

        }
        System.out.println(newLine + WHITE_BOLD_BRIGHT +"Final state");
        System.out.print(WHITE_BOLD_BRIGHT + newLine + "HP: " + wizard.currentHP + "/" + wizard.baseHP);

    }
}
