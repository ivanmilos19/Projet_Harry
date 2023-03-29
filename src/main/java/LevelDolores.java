import java.util.ArrayList;
import java.util.Scanner;

public class LevelDolores {
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

    Spell spell;
    Potion potion;

    public void battle(Wizard wizard, Enemy dolores) {
        int playerChoice = 0;
        int turn_number = 1;
        while (true) {

            System.out.print(GREEN_BOLD_BRIGHT + newLine + "Wizard HP: " + wizard.getCurrentHP() + "/" + wizard.getBaseHP() + " ❤"
                    + WHITE_BOLD_BRIGHT  + "  |   " +  BLUE_BOLD_BRIGHT + "Mana: " + wizard.getCurrentmanaPool() + "/" + wizard.getManaPool() + " \uD83D\uDCA7"
                    + WHITE_BOLD_BRIGHT + "  |  " + YELLOW_BOLD_BRIGHT + "Wizard attack: " + wizard.getAttack_strength() + " \uD83D\uDCA5"
                    + WHITE_BOLD_BRIGHT  + "  |   " + PURPLE_BOLD_BRIGHT +  "Accuracy: " + wizard.getAccuracy() + " \uD83C\uDFAF"
                    + WHITE_BOLD_BRIGHT  + "  |   "  +  "Level: " + wizard.getLevel() + " ⭐" +newLine + newLine);


            System.out.print(RED_BOLD_BRIGHT + dolores.getName() + ": " + dolores.getCurrentHP() + "/" + dolores.getBaseHP() + " ❤" + newLine);



            playerChoice = (new InputReader(RESET + newLine + "Choose an action:" + newLine, new String[]{"Attack", "Defend", "Inventory", "Spell"})).readInputByNumber();


            if (playerChoice == 1) { // Attack

                wizard.attack(dolores);

            }
            else if (playerChoice == 2) { // Defend
                wizard.defend();

            } else if (playerChoice == 3) { // Potion

                String [] inventory_choices = {"Health Potion | x"
                        + wizard.getNumberHealthPotion(wizard.getHealthPotions()) + " remaining", "Attack buff potion | x"
                        + wizard.getNumberAttackPotion(wizard.getDamagePotions()) + " remaining", "Mana potion | x"
                        + wizard.getNumberManaPotion(wizard.getManaPotions()) + " remaining"};

                if (turn_number >= 3)
                {
                    String [] inventory_choices_ext = new String[inventory_choices.length + 1];
                    for (int i = 0; i < inventory_choices.length; i++) {
                        inventory_choices_ext[i] = inventory_choices[i];
                    }
                    inventory_choices_ext[inventory_choices_ext.length - 1] = "Fireworks";
                    inventory_choices = inventory_choices_ext;
                }

                InputReaderWithNoop reader = new InputReaderWithNoop(RESET + newLine + "Select a potion" + newLine, inventory_choices);
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
                if (playerChoice == 4) { // fireworks used - end of level
                    break;
                }


            } else if (playerChoice == 4) { // Spell
                InputReaderWithNoop reader = new InputReaderWithNoop(RESET +newLine + "Choose which spell to cast !" + newLine, new String[]{"Wingardium leviosa | x"
                        + wizard.getNumberWingardiumSpells(wizard.getWingardiumLeviosa()) + " remaining","Accio | x"
                        + wizard.getNumberAccioSpells(wizard.getAccio()) + " remaining", "Expecto Patronum | x"
                        + wizard.getNumberExpectoSpells(wizard.getExpectoPatronum()) + " remaining"});

                playerChoice = reader.readInputByNumber();
                if (reader.noopChosen())
                    continue;

                if (reader.noopChosen())
                    continue;
                if (playerChoice == 1) { // "Wingardium leviosa"
                    boolean success = wizard.useWingardiumLeviosa(dolores);
                    if (!success){
                        System.out.println("can't cast wingardium leviosa no more");
                        continue;
                    }
                } else if (playerChoice == 2) {
                    boolean success = wizard.useAccio(dolores);
                    if (!success){
                        System.out.println("can't cast accio  no more");
                        continue;
                    }

                } else if (playerChoice == 3) {
                    boolean success = wizard.useExpecto(dolores);
                    if (!success){
                        System.out.println("can't cast expecto patronum no more");
                        continue;
                    }
                    System.out.println( BLUE_BOLD_BRIGHT + "Your patronus has repelled a dementor !");
                }
            }


            if (dolores.isDead()) {
                System.out.println(GREEN_BOLD_BRIGHT + newLine + "Foes defeated !");
                break;
            }

            dolores.attack(wizard);


            System.out.println(RED_BOLD_BRIGHT+"You took " + (wizard.getCurrentHP() - wizard.getBaseHP()) + " damage !");

            if (wizard.isDead()) {
                System.out.println(RED_BOLD_BRIGHT + newLine + "Game Over");
                return;
            }


            System.out.println(WHITE_BOLD_BRIGHT + "--------------------------------------------------");

            turn_number++;
        }

        Rewards rewards  = new Rewards();
        rewards.getRewards(wizard);

        Shop shop = new Shop();
        shop.enterShop(wizard);

    }
}

