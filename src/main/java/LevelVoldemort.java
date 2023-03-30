import lombok.Data;

import java.util.ArrayList;
import java.util.Scanner;

@Data
public class LevelVoldemort {
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



    public void battle(Wizard wizard, ArrayList<Boss> bosses) {
        int playerChoice;

        while (true) {
            boolean successExpelliarmus = false; // Expeliarmus cast successfully


            String[] enemy_names = new String[bosses.size()];
            int i = 0;
            for (Boss boss: bosses) {
                enemy_names[i++] = boss.getName();
            }


            System.out.print(GREEN_BOLD_BRIGHT + newLine + "Wizard HP: " + wizard.getCurrentHP() + "/" + wizard.getBaseHP() + " ❤"
                    + WHITE_BOLD_BRIGHT  + "  |   " +  BLUE_BOLD_BRIGHT + "Mana: " + wizard.getCurrentmanaPool() + "/" + wizard.getManaPool() + " \uD83D\uDCA7"
                    + WHITE_BOLD_BRIGHT + "  |  " + YELLOW_BOLD_BRIGHT + "Wizard attack: " + wizard.getAttack_strength() + " \uD83D\uDCA5"
                    + WHITE_BOLD_BRIGHT  + "  |   " + PURPLE_BOLD_BRIGHT +  "Accuracy: " + wizard.getAccuracy() + " \uD83C\uDFAF"
                    + WHITE_BOLD_BRIGHT  + "  |   "  +  "Level: " + wizard.getLevel() + " ⭐" + newLine + newLine);


            for (Boss boss : bosses) {
                System.out.print(RED_BOLD_BRIGHT + boss.getName() + ": " + boss.getCurrentHP() + "/" + boss.getBaseHP() + " ❤" + newLine );
            }

            playerChoice = (new InputReader(RESET + newLine + "Choose an action:" + newLine, new String[]{"Basic spell", "Defend", "Potion", "Spell"})).readInputByNumber();
            int target_enemy = 0;

            if (playerChoice == 1) { // Attack

                InputReaderWithNoop reader = new InputReaderWithNoop(RESET + newLine + "Choose an enemy to attack" + newLine, enemy_names);
                target_enemy = reader.readInputByNumber();
                if (reader.noopChosen())
                    continue;


                wizard.attack(bosses.get(target_enemy - 1));

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

                InputReaderWithNoop reader = new InputReaderWithNoop(RESET +newLine + "Choose which spell to cast !" + newLine, new String[]{"Wingardium leviosa | x"
                        + wizard.getNumberWingardiumSpells(wizard.getWingardiumLeviosa()) + " remaining","Accio | x"
                        + wizard.getNumberAccioSpells(wizard.getAccio()) + " remaining", "Expecto Patronum | x"
                        + wizard.getNumberExpectoSpells(wizard.getExpectoPatronum()) + " remaining", "Sectumsempra | x"
                        + wizard.getNumberSectumsempraSpells(wizard.getSectumsempra()) + " remaining", "Expelliarmus | x"
                        + wizard.getNumberExpelliarmusSpells(wizard.getExpelliarmus()) + " remaining"});
                playerChoice = reader.readInputByNumber();

                if (reader.noopChosen())
                    continue;

                reader = new InputReaderWithNoop(RESET + newLine + "Choose an enemy to cast spell on" + newLine, enemy_names);
                target_enemy = reader.readInputByNumber();

                if (reader.noopChosen())
                    continue;

                if (playerChoice == 1) { // "Wingardium leviosa"
                    boolean success = wizard.useWingardiumLeviosa(bosses.get(target_enemy - 1));
                    if (!success){
                        System.out.println("can't cast wingardium leviosa no more");
                        continue;
                    }


                } else if (playerChoice == 2) {
                    boolean success = wizard.useAccio(bosses.get(target_enemy - 1));
                    if (!success){
                        System.out.println("can't cast accio  no more");
                        continue;
                    }

                } else if (playerChoice == 3) {
                    boolean success = wizard.useExpecto(bosses.get(target_enemy - 1));
                    if (!success){
                        System.out.println("can't cast expecto patronum no more");
                        continue;
                    }

                } else if (playerChoice == 4) {
                    boolean success = wizard.useSectumsempra(bosses.get(target_enemy - 1));
                    if (!success){
                        System.out.println("can't cast sectumsempra no more");
                        continue;
                    }
                } else if (playerChoice == 5) {
                    boolean success = wizard.useExpelliarmus(bosses.get(target_enemy - 1));
                    if (!success){
                        System.out.println("can't cast expelliarmus no more");
                        continue;
                    }
                    successExpelliarmus = true;

                }
            }

            boolean allFoesDead = true;
            for (Boss boss: bosses) {
                allFoesDead = allFoesDead && boss.isDead();
            }

            if (allFoesDead) {
                System.out.println(GREEN_BOLD_BRIGHT + newLine + "Foes defeated !");
                break;
            }




            for (Boss boss: bosses) {
                    if (boss.isAlive() && !successExpelliarmus) {
                        if (boss.canUseAvada()) {
                            boss.attack(wizard);
                            System.out.println(RED_BOLD_BRIGHT+"Voldemort has cursed you, you died...");
                        } else {
                            boss.attack(wizard);
                        }
                    }

                    if (successExpelliarmus) {
                        if (wizard.getWand().getCore() == Core.PHOENIX_FEATHER) {
                            System.out.println(CYAN_BOLD_BRIGHT + "You both have phoenix feather cores. You both have taken damage. ");
                            wizard.attack(boss);
                            boss.attack(wizard);
                        }
                    }
                boss.resetUseAvada();
            }


            successExpelliarmus = false;
            wizard.stopDefending(); // the wizard's defense is back to normal

            System.out.println(RED_BOLD_BRIGHT + "You took " + wizard.getLastDamageTaken() + " damage !");

            if (wizard.isDead()) {
                System.out.println(RED_BOLD_BRIGHT + newLine + "Game Over");
                return;
            }


            System.out.println(WHITE_BOLD_BRIGHT + "--------------------------------------------------");


            for (Boss boss: bosses) {
                if (boss.getName() == "Voldemort" && boss.isAlive()) {
                    boss.RandomUseAvada();
                }
            }


        }
    }
}

