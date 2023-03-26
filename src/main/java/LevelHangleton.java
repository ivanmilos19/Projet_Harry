import lombok.Data;

import java.util.InputMismatchException;
import java.util.Scanner;

@Data
public class LevelHangleton {
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

                if (playerChoice == 1 || playerChoice == 2 || playerChoice == 3 || playerChoice == 4 ) {
                    break;
                } else {
                    System.out.println(YELLOW_BOLD_BRIGHT + "⚠️ Please enter a valid choice.");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(YELLOW_BOLD_BRIGHT + "⚠️ Please enter a valid choice.");
            }
        } while (true);
    }


    public void battle(Wizard wizard, Enemy Pettigrow, Boss voldemort, Enemy portolion) {
        while (true) {
            System.out.print(GREEN_BOLD_BRIGHT + newLine + "Wizard HP: " + wizard.getCurrentHP() + "/" + wizard.getBaseHP() + " ❤");
            System.out.print(WHITE_BOLD_BRIGHT + "  |  " + RED_BOLD_BRIGHT + Pettigrow.getName() + ": " + Pettigrow.getCurrentHP() + "/" + Pettigrow.getBaseHP() + " ❤");
            System.out.print(WHITE_BOLD_BRIGHT + "  |  " + RED_BOLD_BRIGHT + voldemort.getName() + ": " + voldemort.getCurrentHP() + "/" + voldemort.getBaseHP() + " ❤");
            System.out.println(newLine + BLUE_BOLD_BRIGHT + "Mana: " + wizard.getCurrentmanaPool() + "/" + wizard.getManaPool() + " \uD83D\uDCA7" + WHITE_BOLD_BRIGHT + "       |");

            System.out.println(RESET + newLine + "Choose an action:" + newLine);
            System.out.println("1: Attack" + newLine + "2: Defend" + newLine + "3: Potion" + newLine + "4: Spell");


            inputChecker();


            if (playerChoice == 1) {
                System.out.println(RESET +newLine + "Choose an ennemy to attack" + newLine);
                System.out.println("1: " + Pettigrow.getName() + newLine + "2: " + voldemort.getName());

                inputChecker();

                if (playerChoice == 1) {
                    wizard.attack(Pettigrow);
                }

                else if (playerChoice == 2) {
                    wizard.attack(voldemort);
                }

            }
            else if (playerChoice == 2) {
                wizard.defend();
            }

            if (playerChoice == 3) {

                System.out.println(RESET + newLine + "Select a potion" + newLine);
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

                System.out.println(RESET +newLine + "Choose which spell to cast !" + newLine);
                System.out.println("1: Wingardium leviosa" + newLine + "2: Expecto Patronum" + newLine + "3: Accio");

                inputChecker();

                if (playerChoice == 1) {
                    System.out.println(RESET + newLine + "Choose an ennemy to attack" + newLine);
                    System.out.println("1: " + Pettigrow.getName() + newLine + "2: " + voldemort.getName());

                    inputChecker();

                    if (playerChoice == 1) {
                        wizard.useWingardiumLeviosa(Pettigrow);
                    }

                    if (playerChoice == 2) {
                        System.out.println(WHITE_BOLD_BRIGHT + "A rock has fallen on the the trolls head and dealt big damage !");
                        wizard.useWingardiumLeviosa(voldemort);
                    }
                }

                if (playerChoice == 2) {
                    System.out.println(RESET + newLine + "Choose an enemy to cast Expecto Patronum on:" + newLine);
                    System.out.println("1: " + Pettigrow.getName() + newLine + "2: " + voldemort.getName());

                    inputChecker();

                    if (playerChoice == 1) {
                        wizard.useExpecto(Pettigrow);
                    }

                    if (playerChoice == 2) {
                        wizard.useExpecto(voldemort);
                    }
                }

                if (playerChoice == 3) {
                    System.out.println(RESET + newLine + "Choose an object to cast accio on:" + newLine);
                    System.out.println("1: " + portolion.getName());
                    inputChecker();

                    if (playerChoice == 1) {
                        wizard.useAccio(portolion);
                        break;
                    }

                }

            }

            if (Pettigrow.isDead() && voldemort.isDead()) {
                System.out.println(GREEN_BOLD_BRIGHT + newLine + "Foe defeated !");
                break;
            }


            Pettigrow.attack(wizard); // now the protagonist is attacked
            voldemort.attack(wizard);

            System.out.println("You took " + (wizard.getCurrentHP() - wizard.getBaseHP()) + " damage !");

            if (wizard.isDead()) {
                System.out.println(RED_BOLD_BRIGHT + newLine + "Game Over");
                return;
            }


            System.out.println(WHITE_BOLD_BRIGHT + "--------------------------------------------------");


        }

        System.out.println(PURPLE_BOLD_BRIGHT + "You managed to escape! But voldemort doesn't give up so easily..." +newLine);

        wizard.setBaseHP(wizard.getBaseHP() + 100) ; ;
        wizard.setGold(wizard.getGold() + 20);
        System.out.println("You won 20 Gold! " +newLine);

        System.out.println(RESET +"Do you wish to buy something in Brewsings ?" + newLine);
        System.out.print("1.Yes" + newLine + "2.No" + newLine);
        inputChecker();

        if (playerChoice == 1) {
            System.out.println("Welcome to Brewsings! Which potion may warrant your attention ? " + newLine);
            System.out.println("You have: " + wizard.getGold() + " Gold\uD83D\uDCB0" + newLine);
            System.out.print("1. Health potion: 15 Gold" + newLine + "2. Damage boost potion: 30 Gold" + newLine + "3. Mana potion: 15 Gold" +newLine + "4. Nevermind" +newLine);
            inputChecker();

            if (playerChoice == 1) {
                wizard.addPotion(new Potion());
                wizard.setGold(wizard.getGold() - 15);
                System.out.println(newLine +"You have: " + wizard.getGold() + " Gold\uD83D\uDCB0");
            }
            if (playerChoice == 2) {
                wizard.addPotion(new Potion());
                wizard.setGold(wizard.getGold() - 30);
                System.out.println(newLine +"You have: " + wizard.getGold() + " Gold\uD83D\uDCB0");
            }
            if (playerChoice == 3) {
                wizard.addPotion(new Potion());
                wizard.setGold(wizard.getGold() - 15);
                System.out.println(newLine +"You have: " + wizard.getGold() + " Gold\uD83D\uDCB0");
            }
            if (playerChoice == 4) {
                return;
            }

        }
        if (playerChoice == 2) {
            return;
        }
    }
}
