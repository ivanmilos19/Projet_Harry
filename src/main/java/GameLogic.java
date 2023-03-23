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

    public void inputChecker() {
        do {
            try {
                playerChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(YELLOW_BOLD_BRIGHT + "⚠️ Please write a valid choice.");
            }
        } while (true);
    }

    public void battle(Wizard wizard, AbstractEnemy enemy) {
        while (true) {
            System.out.print(GREEN_BOLD_BRIGHT + newLine + "Wizard HP: " + wizard.currentHP + "/" + wizard.baseHP + " ❤");
            System.out.print(WHITE_BOLD_BRIGHT + "  |  " + RED_BOLD_BRIGHT + "Enemy HP: " + enemy.currentHP + "/" + enemy.baseHP + " ❤");
            System.out.println(newLine + BLUE_BOLD_BRIGHT + "Mana: " + wizard.currentmanaPool + "/" + wizard.manaPool + " \uD83D\uDCA7");

            System.out.println(RESET + newLine + newLine + "Choose an action:" + newLine);
            System.out.println("1: Attack" + newLine + "2: Defend" + newLine + "3: Potion" + newLine + "4: Spell");


            inputChecker();


            if (playerChoice == 1) {
                wizard.attack(enemy); // the protagonist is attacking its enemy
            }
            if (playerChoice == 2) {
                wizard.defend(wizard);
            }

            if (playerChoice == 3) {
                System.out.println(RESET + newLine + newLine + "Select a potion" + newLine);
                System.out.println("1: Health Potion" + newLine + "2: Attack buff potion" + newLine + "3: Mana potion");

                inputChecker();

                if (playerChoice == 1) {
                    wizard.useHealthPotion();
                    continue;
                }
                if (playerChoice == 2) {
                    wizard.equipDamagePotion();
                    continue;
                }
                if (playerChoice == 3) {
                    wizard.useManaPotion();
                    continue;
                }

            }

            if (playerChoice == 4) {

                System.out.println(RESET + newLine + newLine + "Choose which spell to cast !" + newLine);
                System.out.println("1: Wingardium leviosa" + newLine + "2: X");

                inputChecker();

                if (playerChoice == 1) {
                    wizard.useWingardiumLeviosa(enemy);
                }

            }

            if (enemy.isDead()) {
                System.out.println(GREEN_BOLD_BRIGHT + newLine + "Foe defeated !");
                break;
            }

            enemy.attack(wizard); // now the protagonist is attacked

            if (wizard.isDead()) {
                System.out.println(RED_BOLD_BRIGHT + newLine + "Game Over");
                break;
            }


            System.out.println(WHITE_BOLD_BRIGHT + "--------------------------------------------------");


        }

        wizard.Gold += 20;
        System.out.println("You won " + wizard.Gold + "Gold" +newLine);

        System.out.println(RESET +"Do you wish to buy something in the shop ?" + newLine);
        System.out.print("1.Yes" + newLine + "2.No" + newLine);
        inputChecker();

        if (playerChoice == 1) {
            System.out.println("Welcome to Brewsings! Which potion may warrant your attention ? " + newLine);
            System.out.println("You have: " + wizard.Gold + " Gold\uD83D\uDCB0" + newLine);
            System.out.print("1. Health potion: 15 Gold" + newLine + "2. Damage boost potion: 30 Gold" + newLine + "3. Mana potion: 15 Gold" +newLine);
            inputChecker();

            if (playerChoice == 1) {
                wizard.addPotion(new Potion());
                wizard.Gold -= 15;
                System.out.println(newLine +"You have: " + wizard.Gold + "Gold\uD83D\uDCB0");
            }
            if (playerChoice == 2) {
                wizard.addPotion(new Potion());
                wizard.Gold -= 30;
                System.out.println(newLine +"You have: " + wizard.Gold + "Gold\uD83D\uDCB0");
            }
            if (playerChoice == 3) {
                wizard.addPotion(new Potion());
                wizard.Gold -= 15;
                System.out.println(newLine +"You have: " + wizard.Gold + "Gold\uD83D\uDCB0");
            }

        }
        if (playerChoice == 2) {
            return;
        }
    }
}

