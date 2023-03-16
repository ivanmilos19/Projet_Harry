import lombok.Data;

import java.util.InputMismatchException;
import java.util.Scanner;

@Data
public class GameLogic {
    public static final String WHITE = "\033[0;37m";   // WHITE
    public static final String ORANGE = "\033[38;5;208m";   // ORANGE
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String RED = "\033[0;31m";     // RED
    String newLine = System.getProperty("line.separator");
    Scanner scanner = new Scanner(System.in);
    int playerChoice;

    public  void battle(Wizard wizard, AbstractEnemy enemy) {
        while (true)
        {
            System.out.print(GREEN + newLine + "      Wizard HP: " + wizard.currentHP + "/" + wizard.baseHP);
            System.out.print(WHITE +  "  |  " + RED + "Enemy HP: " + enemy.currentHP + "/" + enemy.baseHP) ;

            System.out.println(ORANGE + newLine + newLine + "             Choose an action:");
            System.out.println("       1: Attack          2: Defend" );

            do {
                try {
                    playerChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    break;
                } catch(InputMismatchException e) {
                    scanner.nextLine();
                    System.out.println("⚠️ Please write a valid choice.");
                }
            }while(true);


            if (playerChoice == 1) {
                wizard.attack(enemy); // the protagonist is attacking its enemy
            }
            if (playerChoice == 2 ) {
                wizard.defend(wizard);
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

            System.out.println( WHITE + "--------------------------------------------------");

        }
        System.out.println(newLine + WHITE +"Final state");
        System.out.print(WHITE + newLine + "HP: " + wizard.currentHP + "/" + wizard.baseHP);

    }
}
