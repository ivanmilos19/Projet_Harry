import java.util.ArrayList;
import java.util.Scanner;

public class LevelTroll {
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


    public void battle(Wizard wizard, ArrayList<Boss> troll) {
        int playerChoice = 0;
        while (true) {

            String[] enemy_names = new String[troll.size()];
            int i = 0;
            for (Boss Troll: troll) {
                enemy_names[i++] = Troll.getName();
            }

            System.out.print(GREEN_BOLD_BRIGHT + newLine + "Wizard HP: " + wizard.getCurrentHP() + "/" + wizard.getBaseHP() + " ❤");

            for (Boss Troll: troll) {
                System.out.print(WHITE_BOLD_BRIGHT + "  |  " + RED_BOLD_BRIGHT + Troll.getName() + ": " + Troll.getCurrentHP() + "/" + Troll.getBaseHP() + " ❤");
            }
            System.out.println(newLine + BLUE_BOLD_BRIGHT + "Mana: " + wizard.getCurrentmanaPool() + "/" + wizard.getManaPool() + " \uD83D\uDCA7" + WHITE_BOLD_BRIGHT + "       |");

            playerChoice = (new InputReader(RESET + newLine + "Choose an action:" + newLine, new String[]{"Attack", "Defend", "Potion", "Spell"})).readInputByNumber();
            int target_boss = 0;

            if (playerChoice == 1) { // Attack

                target_boss = (new InputReader(RESET + newLine + "Choose an enemy to attack" + newLine, enemy_names)).readInputByNumber();

                wizard.attack(troll.get(target_boss - 1));

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

                playerChoice = (new InputReader(RESET +newLine + "Choose which spell to cast !" + newLine, new String[]{"Wingardium leviosa"})).readInputByNumber();
                target_boss = (new InputReader(RESET + newLine + "Choose an enemy to cast spell on" + newLine, enemy_names)).readInputByNumber();

                if (playerChoice == 1) { // "Wingardium leviosa"
                    wizard.useWingardiumLeviosa(troll.get(target_boss - 1));
                }


            }


            boolean allFoesDead = true;
            for (Boss Troll: troll) {
                allFoesDead = allFoesDead && Troll.isDead();
            }

            if (allFoesDead) {
                System.out.println(GREEN_BOLD_BRIGHT + newLine + "Foes defeated !");
                break;
            }

            // now the protagonist is attacked
            for (Boss Troll: troll) {
                if (Troll.isAlive())
                    Troll.attack(wizard);

            }
            System.out.println("You took " + (wizard.getCurrentHP() - wizard.getBaseHP()) + " damage !");

            if (wizard.isDead()) {
                System.out.println(RED_BOLD_BRIGHT + newLine + "Game Over");
                return;
            }


            System.out.println(WHITE_BOLD_BRIGHT + "--------------------------------------------------");


        }

        wizard.setBaseHP(wizard.getBaseHP() + 100) ; ;
        wizard.setGold(wizard.getGold() + 20);
        System.out.println("You won 20 Gold! " +newLine);

        playerChoice = (new InputReader(RESET + "Do you wish to buy something in Brewsings ?" + newLine, new String[]{"Yes", "No"})).readInputByNumber();

        if (playerChoice == 1) {
            System.out.println("Welcome to Brewsings! Which potion may warrant your attention ? " + newLine);
            System.out.println("You have: " + wizard.getGold() + " Gold\uD83D\uDCB0" + newLine);

            while (true) {

                int healthPrice = 15;
                int damagePrice = 30;
                int manaPrice = 15;


                playerChoice = (new InputReader(RESET + "Please enter your choice" + newLine, new String[]{"Health potion: 15 Gold", "Damage boost potion: 30 Gold", "Mana potion: 15 Gold", "Nevermind"})).readInputByNumber();


                if (playerChoice == 1 && wizard.getGold() - healthPrice > 0) {
                    wizard.addPotion(new Potion());
                    wizard.setGold(wizard.getGold() - healthPrice);
                    System.out.println(YELLOW_BOLD_BRIGHT + "Acquired a health potion");
                }

                else if (playerChoice == 2 && wizard.getGold() - damagePrice > 0) {
                    wizard.addPotion(new Potion());
                    wizard.setGold(wizard.getGold() - damagePrice);
                    System.out.println(YELLOW_BOLD_BRIGHT + "Acquired a damage boost potion");
                }

                else if (playerChoice == 3 && wizard.getGold() - manaPrice > 0) {
                    wizard.addPotion(new Potion());
                    wizard.setGold(wizard.getGold() - manaPrice);
                    System.out.println(YELLOW_BOLD_BRIGHT + "Acquired a mana potion");
                } else {
                    System.err.println("not enough gold" +newLine);
                }
                System.out.println(newLine + "You have: " + wizard.getGold() + " Gold\uD83D\uDCB0");

                if (playerChoice == 4) {
                    break;
                }
            }
        }
    }
}

