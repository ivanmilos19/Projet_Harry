import lombok.Data;

import java.util.ArrayList;
import java.util.Scanner;

@Data
public class LevelDementor {
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


    public void battle(Wizard wizard, ArrayList<Enemy> dementors) {
        int playerChoice = 0;
        while (true) {

            String[] enemy_names = new String[dementors.size()];
            int i = 0;
            for (Enemy dementor : dementors) {
                enemy_names[i++] = dementor.getName();
            }

            System.out.print(GREEN_BOLD_BRIGHT + newLine + "Wizard HP: " + wizard.getCurrentHP() + "/" + wizard.getBaseHP() + " ❤"
                    + WHITE_BOLD_BRIGHT + "  |   " + BLUE_BOLD_BRIGHT + "Mana: " + wizard.getCurrentmanaPool() + "/" + wizard.getManaPool() + " \uD83D\uDCA7"
                    + WHITE_BOLD_BRIGHT + "  |  " + YELLOW_BOLD_BRIGHT + "Wizard attack: " + wizard.getAttack_strength() + " \uD83D\uDCA5"
                    + WHITE_BOLD_BRIGHT + "  |   " + "Level: " + wizard.getLevel() + " ⭐" + newLine + newLine);


            for (Enemy dementor : dementors) {
                System.out.print(RED_BOLD_BRIGHT + dementor.getName() + ": " + dementor.getCurrentHP() + "/" + dementor.getBaseHP() + " ❤" + newLine);
            }

            playerChoice = (new InputReader(RESET + newLine + "Choose an action:" + newLine, new String[]{"Basic spell", "Defend", "Inventory", "Spell"})).readInputByNumber();
            int target_enemy = 0;

            if (playerChoice == 1) { // Attack

                InputReaderWithNoop reader = new InputReaderWithNoop(RESET + newLine + "Choose an enemy to attack" + newLine, enemy_names);
                target_enemy = reader.readInputByNumber();
                if (reader.noopChosen())
                    continue;


                wizard.attack(dementors.get(target_enemy - 1));

            } else if (playerChoice == 2) { // Defend
                wizard.defend();
            } else if (playerChoice == 3) { // Potion

                InputReaderWithNoop reader = new InputReaderWithNoop(RESET + newLine + "Select a potion" + newLine, new String[]{"Health Potion | x"
                        + wizard.getHealthPotions().size() + " remaining", "Attack buff potion | x"
                        + wizard.getDamagePotions().size() + " remaining", "Mana potion | x"
                        + wizard.getManaPotions().size() + " remaining"});
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
                InputReaderWithNoop reader = new InputReaderWithNoop(RESET + newLine + "Choose which spell to cast !" + newLine, new String[]{"Wingardium leviosa | x"
                        + wizard.getWingardiumLeviosa().size() + " remaining", "Accio | x"
                        + wizard.getAccio().size() + " remaining", "Expecto Patronum | x"
                        + wizard.getExpectoPatronum().size() + " remaining"});
                playerChoice = reader.readInputByNumber();

                if (reader.noopChosen())
                    continue;

                reader = new InputReaderWithNoop(RESET + newLine + "Choose an enemy to cast spell on" + newLine, enemy_names);
                target_enemy = reader.readInputByNumber();

                if (reader.noopChosen())
                    continue;

                if (playerChoice == 1) { // "Wingardium leviosa"
                    boolean success = wizard.useWingardiumLeviosa(dementors.get(target_enemy - 1));
                    if (!success) {
                        System.out.println("can't cast wingardium leviosa no more");
                        continue;
                    }


                } else if (playerChoice == 2) {
                    boolean success = wizard.useAccio(dementors.get(target_enemy - 1));
                    if (!success) {
                        System.out.println("can't cast accio  no more");
                        continue;
                    }

                } else if (playerChoice == 3) {
                    boolean success = wizard.useExpecto(dementors.get(target_enemy - 1));
                    if (!success) {
                        System.out.println("can't cast expecto patronum no more");
                        continue;
                    }
                    System.out.println(BLUE_BOLD_BRIGHT + "Your patronus has repelled a dementor !");
                }
            }


            boolean allFoesDead = true;
            for (Enemy dementor : dementors) {
                allFoesDead = allFoesDead && dementor.isDead();
            }

            if (allFoesDead) {
                System.out.println(GREEN_BOLD_BRIGHT + newLine + "Foes defeated !");
                break;
            }

            // now the protagonist is attacked
            for (Enemy dementor : dementors) {
                if (dementor.isAlive())
                    dementor.attack(wizard);

            }

            wizard.stopDefending(); // the wizard's defense is back to normal

            System.out.println(RED_BOLD_BRIGHT + "You took " + wizard.getLastDamageTaken() + " damage !");

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
