import lombok.Data;

import java.util.ArrayList;
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


    public void battle(Wizard wizard, ArrayList<Enemy> hangleton) {
        int playerChoice = 0;
        while (true) {

            String[] enemy_names = new String[hangleton.size()];
            int i = 0;
            for (Enemy Hangelton: hangleton) {
                enemy_names[i++] = Hangelton.getName();
            }

            System.out.print(GREEN_BOLD_BRIGHT + newLine + "Wizard HP: " + wizard.getCurrentHP() + "/" + wizard.getBaseHP() + " ❤"
                    + WHITE_BOLD_BRIGHT  + "  |   " +  BLUE_BOLD_BRIGHT + "Mana: " + wizard.getCurrentmanaPool() + "/" + wizard.getManaPool() + " \uD83D\uDCA7"
                    + WHITE_BOLD_BRIGHT + "  |  " + YELLOW_BOLD_BRIGHT + "Wizard attack: " + wizard.getAttack_strength() + " \uD83D\uDCA5"
                    + WHITE_BOLD_BRIGHT  + "  |   " + PURPLE_BOLD_BRIGHT +  "Accuracy: " + wizard.getAccuracy() + " \uD83C\uDFAF"
                    + WHITE_BOLD_BRIGHT  + "  |   "  +  "Level: " + wizard.getLevel() + " ⭐" +newLine + newLine);


            for (Enemy Hangelton: hangleton) {
                System.out.print(RED_BOLD_BRIGHT + Hangelton.getName() + ": " + Hangelton.getCurrentHP() + "/" + Hangelton.getBaseHP() + " ❤" + newLine );
            }

            playerChoice = (new InputReader(RESET + newLine + "Choose an action:" + newLine, new String[]{"Attack", "Defend", "Potion", "Spell"})).readInputByNumber();
            int target_enemy = 0;


            if (playerChoice == 1) { // Attack

                InputReaderWithNoop reader = new InputReaderWithNoop(RESET + newLine + "Choose an enemy to attack" + newLine, enemy_names);
                playerChoice = reader.readInputByNumber();
                if (reader.noopChosen())
                    continue;


                wizard.attack(hangleton.get(target_enemy - 1));

            }
            else if (playerChoice == 2) { // Defend
                wizard.defend();
            } else if (playerChoice == 3) { // Potion

                InputReaderWithNoop reader = new InputReaderWithNoop(RESET + newLine + "Select a potion" + newLine, new String[]{"Health Potion | x"
                        + wizard.getNumberHealthPotion(wizard.getHealthPotions()) + " remaining", "Attack buff potion | x"
                        + wizard.getNumberAttackPotion(wizard.getDamagePotions()) + " remaining", "Mana potion | x"
                        + wizard.getNumberManaPotion(wizard.getManaPotions()) + " remaining"});
                playerChoice = reader.readInputByNumber();
                if (reader.noopChosen())
                    continue;

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

            } else if (playerChoice == 4) { // Spell
                InputReaderWithNoop reader = new InputReaderWithNoop(RESET +newLine + "Choose which spell to cast !" + newLine, new String[]
                        {"Wingardium leviosa | x"
                        + wizard.getNumberWingardiumSpells(wizard.getWingardiumLeviosa()) + " remaining", "Wingardium leviosa | x"
                        + wizard.getNumberAccioSpells(wizard.getAccio()) + " remaining"} );

                playerChoice = reader.readInputByNumber();

                if (reader.noopChosen())
                    continue;

                reader = new InputReaderWithNoop(RESET + newLine + "Choose an enemy to cast spell on" + newLine, enemy_names);
                target_enemy = reader.readInputByNumber();

                if (reader.noopChosen())
                    continue;

                if (playerChoice == 1) { // "Wingardium leviosa"
                    wizard.useWingardiumLeviosa(hangleton.get(target_enemy - 1));
                }
                else if (playerChoice == 2 ) { // "Wingardium leviosa"
                    wizard.useAccio(hangleton.get(target_enemy - 1));
                } else if (playerChoice == 3 ) { // "Wingardium leviosa"
                    wizard.useExpecto(hangleton.get(target_enemy - 1));
                }

            }

            boolean allFoesDead = true;
            for (Enemy Hangelton: hangleton) {
                allFoesDead = allFoesDead && Hangelton.isDead();
            }

            if (allFoesDead) {
                System.out.println(GREEN_BOLD_BRIGHT + newLine + "Foes defeated !");
                break;
            }

            // now the protagonist is attacked
            for (Enemy Hangelton: hangleton) {
                if (Hangelton.isAlive())
                    Hangelton.attack(wizard);

            }

            wizard.stopDefending(); // the wizard's defense is back to normal

            System.out.println(RED_BOLD_BRIGHT + "You took " + wizard.damageInflicted() + " damage !");

            if (wizard.isDead()) {
                System.out.println(RED_BOLD_BRIGHT + newLine + "Game Over");
                return;
            }


            System.out.println(WHITE_BOLD_BRIGHT + "--------------------------------------------------");


        }

        Rewards rewards = new Rewards();
        rewards.getRewards(wizard);

        Shop shop = new Shop();
        shop.enterShop(wizard);

    }
}