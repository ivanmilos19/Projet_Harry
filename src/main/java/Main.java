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
                .currentHP(500)
                .previousHP(500)
                .baseHP(500)
                .level(1)
                .accuracy(0.85 + house.precision())
                .house(sortingHat.getHouse())
                .joinedEnemy(false)


                .healthPotions(new ArrayList<>())
                .manaPotions(new ArrayList<>())
                .damagePotions(new ArrayList<>())

                .wingardiumLeviosa(new ArrayList<>())
                .expectoPatronum(new ArrayList<>())
                .accio(new ArrayList<>())
                .sectumsempra(new ArrayList<>())
                .expelliarmus(new ArrayList<>())

                .attack_strength((int)(40 * house.attackMultiplier()))
                .manaPool(150)
                .currentmanaPool(150)

                .wingardiumManaUsage(15)
                .wingardiumCrit(140)
                .wingardiumDmg(70)

                .expectoCrit(99999)
                .expectoDmg(40)
                .expectoManaUsage(80)

                .expelliarmusDmg(250)
                .expellarmusManaUsg(30)

                .accioDmg(150)
                .accioManaUsage(15)

                .sectumsempraDmg(450)
                .sectumsempraManaUsg(70)



                .Gold(70)
                .build();

        // give it 4 potion to start with
        wizard.addHealthPotion(new Potion());
        wizard.addDamagePotion(new Potion());
        wizard.addManaPotion(new Potion());

        wizard.addHealthPotion(new Potion());
        wizard.addDamagePotion(new Potion());
        wizard.addManaPotion(new Potion());



        // add spells
        wizard.addSpell(new Spell());
        wizard.addSpell(new Spell());



        wizard.Pet();
        Wand wand = new Wand();
        wizard.setWand(wand);


        ////////// LEVEL 1 //////////////
        LevelTroll levelTroll = new LevelTroll();


        System.out.println(PURPLE_BOLD_BRIGHT + "--------------------------------------------------" + newLine);
        System.out.println(CYAN_BOLD_BRIGHT + "A troll is roaming in the bathroom ! Will you be able to defeat him ?");



       Enemy troll = Enemy.builder()
                .currentHP(800)
                .baseHP(800)
                .attack_strength(30)
                .attackStrengthMultiplier(3)
                .name("Troll")
                .build();

       //levelTroll.battle(wizard, troll);



        ////////// LEVEL 2 //////////////

        LevelBasilic levelBasilic = new LevelBasilic();


        System.out.println(PURPLE_BOLD_BRIGHT + "--------------------------------------------------" + newLine);
        System.out.println(CYAN_BOLD_BRIGHT + "The basilic has escaped. Will you use the sword of Godric to slay him ? Or use one of his teeth to destroy Tom Riddle's journal using accio?");

        if (wizard.getHouse().canUseSword()) {
            System.out.println(CYAN_BOLD_BRIGHT + "You have acquired Gryffondor's legendary sword ! Your attacks will deal more damage.");
        }


        Boss basilic = Boss.builder()
                .currentHP(900)
                .baseHP(900)
                .attack_strength(35)
                .attackStrengthMultiplier(5)
                .name("Basilic")
                .build();


        //levelBasilic.battle(wizard, basilic);



        ////////// LEVEL 3 //////////////
        if (wizard.isAlive()) {
            System.out.println(PURPLE_BOLD_BRIGHT + "--------------------------------------------------" + newLine);
            System.out.println(CYAN_BOLD_BRIGHT + "The dementors are roaming, you remember a peciliuar spell that can repel them... Expecto Patronum !");

            ArrayList<Enemy> dementors = new ArrayList<>();

            dementors.add(Enemy.builder()
                    .currentHP(350)
                    .baseHP(350)
                    .attack_strength(35)
                    .name("Dementor")
                    .build());

            dementors.add(Enemy.builder()
                    .baseHP(350)
                    .currentHP(350)
                    .attack_strength(35)
                    .name("Dementor")
                    .build());

            dementors.add(Enemy.builder()
                    .baseHP(350)
                    .currentHP(350)
                    .attack_strength(35)
                    .name("Dementor")
                    .build());

            LevelDementor levelDementor = new LevelDementor();
            //levelDementor.battle(wizard, dementors);
        }

        ////////// LEVEL 4 //////////////

        if(wizard.getNumberWingardiumSpells(wizard.getWingardiumLeviosa()) < 5) {
            // Add a new spell if the wizard has less than 5 spells
            wizard.addSpell(new Spell());
        }

        if (wizard.isAlive()) {
            System.out.println(PURPLE_BOLD_BRIGHT + "--------------------------------------------------" + newLine);
            System.out.println(CYAN_BOLD_BRIGHT + "You won the Sorcerer's Tourney, but Voldemort and Pettigrow are here! You are no match for them, you should flee while you can, luckily the Portoloin is here, you remember a spell that allows you to pull objects to you... Accio!\"");

            ArrayList<Boss> Hangleton = new ArrayList<>();

            Hangleton.add(Boss.builder()
                    .baseHP(2000)
                    .currentHP(2000)
                    .attack_strength(110)
                    .name("Voldemort")
                    .build());


            Hangleton.add(Boss.builder()
                    .baseHP(600)
                    .currentHP(600)
                    .attack_strength(50)
                    .name("Pettigrow")
                    .build());

            Hangleton.add(Boss.builder()
                    .name("Portoloin")
                    .build());


            LevelHangleton levelHangleton = new LevelHangleton();
            //levelHangleton.battle(wizard, Hangleton);
        }

        ////////// LEVEL 5 //////////////
        if (wizard.isAlive()) {

            System.out.println(PURPLE_BOLD_BRIGHT + "--------------------------------------------------" + newLine);
            System.out.println(CYAN_BOLD_BRIGHT + "OWL time! Distract dolores for 5 turns, so you can get some fireworks in your inventory. Let the party begin !");


            Enemy dolores = Enemy.builder()
                    .currentHP(2000)
                    .baseHP(2000)
                    .attack_strength(60)
                    .attackStrengthMultiplier(2)
                    .name("Dolores Ombrage")
                    .build();

            LevelDolores levelDolores = new LevelDolores();
            //levelDolores.battle(wizard, dolores);
        }

        ////////// LEVEL 6 //////////////





        if (wizard.isAlive()) {
            System.out.println(PURPLE_BOLD_BRIGHT + "--------------------------------------------------" + newLine);
            System.out.println(CYAN_BOLD_BRIGHT + "The Death Eaters have invaded Hogwarts. You learned from the Half Blood prince a powerful spell : Sectumsempra !! Use it wisely...");

            ArrayList<Enemy> deathEaters = new ArrayList<>();

            deathEaters.add(Enemy.builder()
                    .currentHP(1000)
                    .baseHP(1000)
                    .attack_strength(50)
                    .name("Death Eater ")
                    .build());


            deathEaters.add(Enemy.builder()
                    .baseHP(1000)
                    .currentHP(1000)
                    .attack_strength(50)
                    .name("Death Eater ")
                    .build());

            deathEaters.add(Enemy.builder()
                    .baseHP(1000)
                    .currentHP(1000)
                    .attack_strength(50)
                    .name("Death Eater ")
                    .build());


            LevelDeathEaters levelDeathEaters = new LevelDeathEaters();
            levelDeathEaters.battle(wizard, deathEaters);
        }




        ////////// LEVEL 7 //////////////


        if (wizard.isAlive()) {
            System.out.println(PURPLE_BOLD_BRIGHT + "--------------------------------------------------" + newLine);
            System.out.println(CYAN_BOLD_BRIGHT + "It's time to face Voldemort and end this. But be careful, not only is Bellatrix with him, you will also need to avoid voldemort's avada kedavra by using expelliarmus.");


            ArrayList<Boss> bosses = new ArrayList<>();

            bosses.add(Boss.builder()
                    .currentHP(1100)
                    .baseHP(1100)
                    .attack_strength(60)
                    .name("Bellatrix Lestrange ")
                    .build());

            bosses.add(Boss.builder()
                    .currentHP(2000)
                    .baseHP(2000)
                    .attack_strength(110)
                    .attackStrengthMultiplier(999)
                    .name("Voldemort")
                    .build());


            LevelVoldemort levelVoldemort= new LevelVoldemort();
            levelVoldemort.battle(wizard, bosses);
        }
    }
}


