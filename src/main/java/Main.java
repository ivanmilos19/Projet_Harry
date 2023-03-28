import java.util.ArrayList;

public  class Main {
    public static void main(String[] args) {

        final String RESET = "\033[0m";  // Text Reset
        final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
        final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
        final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
        final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
        final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
        final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
        final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
        final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE
        String newLine = System.getProperty("line.separator");



        SortingHat sortingHat = new SortingHat();
        House house = sortingHat.getHouse();

        Wizard wizard = Wizard.builder()
                .currentHP(3000)
                .baseHP(3000)
                .level(1)
                .accuracy(0.85 + house.precision())
                .defense(2 + house.defenseMultiplier())

                .house(sortingHat.getHouse())

                .healthPotions(new ArrayList<>())
                .manaPotions(new ArrayList<>())
                .damagePotions(new ArrayList<>())

                .wingardiumLeviosa(new ArrayList<>())
                .expectoPatronum(new ArrayList<>())
                .accio(new ArrayList<>())

                .attack_strength((int)(12311 * house.attackMultiplier()))
                .manaPool(100)
                .currentmanaPool(100)

                .wingardiumManaUsage(40)
                .wingardiumCrit(250)
                .wingardiumDmg(50)

                .expectoCrit(99999)
                .expectoDmg(60)
                .expectoManaUsage(30)

                .accioDmg(0)
                .accioManaUsage(10)


                .Gold(50)
                .build();

        // give it one potion to start with
        wizard.addHealthPotion(new Potion());
        wizard.addDamagePotion(new Potion());
        wizard.addManaPotion(new Potion());

        // add spells
        wizard.addSpell(new Spell());
        wizard.addSpell(new Spell());
        wizard.addSpell(new Spell());

       /* wizard.Pet();
        Wand wand = new Wand();
        wand.Wand(); */

        ////////// LEVEL 1 //////////////
        LevelTroll levelTroll = new LevelTroll();


        System.out.println(PURPLE_BOLD_BRIGHT + "--------------------------------------------------" + newLine);
        System.out.println(CYAN_BOLD_BRIGHT + "A troll is roaming in the bathroom ! Will you be able to defeat him ?");

        ArrayList<Boss> Troll = new ArrayList<>();

        Troll.add(Boss.builder()
                .currentHP(500)
                .baseHP(500)
                .attack_strength(30)
                .name("Troll")
                .build());


        levelTroll.battle(wizard, Troll);


        ////////// LEVEL 3 //////////////
        if (wizard.isAlive()) {
            System.out.println(PURPLE_BOLD_BRIGHT + "--------------------------------------------------" + newLine);
            System.out.println(CYAN_BOLD_BRIGHT + "The dementors are roaming, you remember a peciliuar spell that can repel them...");

            ArrayList<Enemy> dementors = new ArrayList<>();

            dementors.add(Enemy.builder()
                    .currentHP(200)
                    .baseHP(200)
                    .attack_strength(20)
                    .name("Dementor")
                    .build());

            dementors.add(Enemy.builder()
                    .baseHP(200)
                    .currentHP(200)
                    .attack_strength(20)
                    .name("Dementor")
                    .build());

            dementors.add(Enemy.builder()
                    .baseHP(200)
                    .currentHP(200)
                    .attack_strength(20)
                    .name("Dementor")
                    .build());

            LevelDementor levelDementor = new LevelDementor();
            levelDementor.battle(wizard, dementors);
        }

        ////////// LEVEL 4 //////////////
        if (wizard.isAlive()) {
            System.out.println(PURPLE_BOLD_BRIGHT + "--------------------------------------------------" + newLine);
            System.out.println(CYAN_BOLD_BRIGHT + "Voldemort and Pettigrow are here! you should flee away before it's too late, luckily the Portolion is here, you remember a spell that allows you to pull objects to you...\"");

            ArrayList<Enemy> hangleton = new ArrayList<>();

            hangleton.add(Enemy.builder()
                    .currentHP(600)
                    .baseHP(600)
                    .attack_strength(50)
                    .name("Pettigrow")
                    .build());

            hangleton.add(Enemy.builder()
                    .name("Portolion")
                    .build());


            hangleton.add(Enemy.builder()
                    .baseHP(5000)
                    .currentHP(5000)
                    .attack_strength(20)
                    .name("Voldemort")
                    .build());


            LevelHangleton levelHangleton = new LevelHangleton();
            levelHangleton.battle(wizard, hangleton);
        }
    }
}

