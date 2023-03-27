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
            for (Enemy dementor: dementors) {
                enemy_names[i++] = dementor.getName();
            }

            System.out.print(GREEN_BOLD_BRIGHT + newLine + "Wizard HP: " + wizard.getCurrentHP() + "/" + wizard.getBaseHP() + " ❤"
                    + WHITE_BOLD_BRIGHT  + "  |   " +  BLUE_BOLD_BRIGHT + "Mana: " + wizard.getCurrentmanaPool() + "/" + wizard.getManaPool() + " \uD83D\uDCA7"
                    + WHITE_BOLD_BRIGHT + "  |  " + YELLOW_BOLD_BRIGHT + "Wizard attack: " + wizard.getAttack_strength() + " \uD83D\uDCA5"
                    + WHITE_BOLD_BRIGHT  + "  |   "  +  "Level: " + wizard.getLevel() + " ⭐" + newLine + newLine);


            for (Enemy dementor: dementors) {
                System.out.print(RED_BOLD_BRIGHT + dementor.getName() + ": " + dementor.getCurrentHP() + "/" + dementor.getBaseHP() + " ❤" + newLine );
            }

            playerChoice = (new InputReader(RESET + newLine + "Choose an action:" + newLine, new String[]{"Attack", "Defend", "Potion", "Spell"})).readInputByNumber();
            int target_enemy = 0;

            if (playerChoice == 1) { // Attack

                target_enemy = (new InputReader(RESET + newLine + "Choose an enemy to attack" + newLine, enemy_names)).readInputByNumber();

                wizard.attack(dementors.get(target_enemy - 1));

            }
            else if (playerChoice == 2) { // Defend
                wizard.defend();
            } else if (playerChoice == 3) { // Potion

                playerChoice = (new InputReader(RESET + newLine + "Select a potion" + newLine, new String[]{"Health Potion", "Attack buff potion", "Mana potion"})).readInputByNumber();

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

                playerChoice = (new InputReader(RESET +newLine + "Choose which spell to cast !" + newLine, new String[]{"Wingardium leviosa", "Expecto Patronum"})).readInputByNumber();
                target_enemy = (new InputReader(RESET + newLine + "Choose an enemy to cast spell on" + newLine, enemy_names)).readInputByNumber();

                if (playerChoice == 1) { // "Wingardium leviosa"
                    if (!wizard.useWingardiumLeviosa(dementors.get(target_enemy - 1)))  {
                        System.out.println(YELLOW_BOLD_BRIGHT + "You can't cast wingardium anymore");
                        continue;
                    }
                }

                if (playerChoice == 2) {
                    if (!wizard.useExpecto(dementors.get(target_enemy - 1))){
                        System.out.println(YELLOW_BOLD_BRIGHT + "You can't cast expecto patronum anymore");
                        continue;
                    }
                }
            }


            boolean allFoesDead = true;
            for (Enemy dementor: dementors) {
                allFoesDead = allFoesDead && dementor.isDead();
            }

            if (allFoesDead) {
                System.out.println(GREEN_BOLD_BRIGHT + newLine + "Foes defeated !");
                break;
            }

            // now the protagonist is attacked
            for (Enemy dementor: dementors) {
                if (dementor.isAlive())
                    dementor.attack(wizard);

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
        rewards.Rewards();

        Shop shop = new Shop();
        shop.openShop();

    }
}
