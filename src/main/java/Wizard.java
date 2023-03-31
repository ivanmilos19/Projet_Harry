import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Random;
import java.util.Scanner;

import java.util.ArrayList;

@Data
@SuperBuilder
public class Wizard extends Character {

    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String RESET = "\033[0m";  // Text Reset

    ////////////// Arrays lists ////////////////////
    protected ArrayList<Potion> healthPotions = new ArrayList<>();
    protected ArrayList<Potion> damagePotions = new ArrayList<>();
    protected ArrayList<Potion> manaPotions = new ArrayList<>();

    protected ArrayList<Spell> wingardiumLeviosa = new ArrayList<>();
    protected ArrayList<Spell> accio = new ArrayList<>();
    protected ArrayList<Spell> expectoPatronum = new ArrayList<>();
    protected ArrayList<Spell> sectumsempra = new ArrayList<>();
    protected ArrayList<Spell> expelliarmus = new ArrayList<>();


    ////////////// Wizard attributes ////////////////////
    private int level;
    private int manaPool;
    private int currentmanaPool;
    private int new_HP;
    private double accuracy;

    private Wand wand;

    protected House house;

    private boolean joinedEnemy;


    ////////////// Spells attributes ////////////////////
    private int wingardiumDmg;
    private int wingardiumCrit;
    private int wingardiumManaUsage;


    private int expectoDmg;
    private int expectoCrit;
    private int expectoManaUsage;


    private int accioDmg;
    private int accioManaUsage;


    private int sectumsempraDmg;
    private int sectumsempraManaUsg;

    private int expelliarmusDmg;
    private int expellarmusManaUsg;

    Spell spell = new Spell();

    //////////// Potions //////////////
    private Potion currentDamagePotion = null;
    private int damagePotionTurnsLeft = 0;
    private int Gold;


    Pet pet;

    @Override
    public String getName() {
        return "Wizard";
    }


    @Override
    public int damageInflicted() {
        int effective_attack_strength = getAttack_strength();
        if (house.canUseSword())
            effective_attack_strength *= 1.5;
        if (damagePotionTurnsLeft > 0) {
            effective_attack_strength += currentDamagePotion.attackImprovement() * house.potionImprovement();
            damagePotionTurnsLeft--;
        } else {
            currentDamagePotion = null;
        }
        Random rand = new Random();
        double probability = accuracy; // hitting accuracy


        if (rand.nextDouble() < probability) {
            return effective_attack_strength;
        } else {
            System.out.println(YELLOW_BOLD_BRIGHT + "Your attack missed");
            return 0; // attack misses
        }
    }

    @Override
    public int defenseFactor() {
        return (int) (super.defenseFactor() * house.defenseMultiplier());
    }


    public void maxHealth() {
        if (getBaseHP() < getCurrentHP()) {
            setCurrentHP(getBaseHP());
        }
    }

    public void maxMana() {
        if (manaPool < currentmanaPool) {
            currentmanaPool = manaPool;
        }
    }

    public void minMana() {
        if (currentmanaPool < 0) {
            currentmanaPool = 0;
        }
    }

    public void addHealthPotion(Potion potion) {
        healthPotions.add(potion);
    }

    public void addDamagePotion(Potion potion) {
        damagePotions.add(potion);
    }

    public void addManaPotion(Potion potion) {
        manaPotions.add(potion);
    }

    public void addSpell(Spell spell) {
        wingardiumLeviosa.add(spell);
        expectoPatronum.add(spell);
        accio.add(spell);
        sectumsempra.add(spell);
        expelliarmus.add(spell);
    }


    public void useHealthPotion() {
        // use the first available potion in my collection of potions
        if (!healthPotions.isEmpty()) {
            Potion potion = healthPotions.get(0);
            int newHP = getCurrentHP() + (int) (potion.healthImprovement() * house.potionImprovement());
            setCurrentHP(newHP);
            // now remove the used potion
            healthPotions.remove(0);
            maxHealth();
        } else {
            System.out.println(YELLOW_BOLD_BRIGHT + "You have no health potions left");
        }
    }

    public void useManaPotion() {
        // use the first available potion in my collection of potions
        if (!manaPotions.isEmpty()) {
            Potion potion = manaPotions.get(0);
            currentmanaPool += potion.manaImprovement() * house.potionImprovement();
            // now remove the used potion
            manaPotions.remove(0);
            maxMana();
        } else {
            System.out.println(YELLOW_BOLD_BRIGHT + " You have no mana potions left ");
        }
    }


    public Potion getAttackPotion() {
        Potion potion;
        if (damagePotions.size() > 0) {
            potion = damagePotions.remove(0);
        } else {
            System.out.println(YELLOW_BOLD_BRIGHT + "You have no attack boost potions left");
            potion = null;
        }
        // return (! damagePotions.isEmpty())?  damagePotions.remove(0) : null;
        return potion;
    }

    // return true if potion equipped successfully, false otherwise
    public void equipDamagePotion() {
        if (currentDamagePotion != null) {
            System.out.println(YELLOW_BOLD_BRIGHT + "A damage potion is already equipped");
            return;
        }
        currentDamagePotion = getAttackPotion();
        if (currentDamagePotion != null) {
            damagePotionTurnsLeft = 2;
            System.out.println(YELLOW_BOLD_BRIGHT + "Damage potion equipped");
        }
    }

    // override the basic attack to take potions into account


    public boolean useWingardiumLeviosa(Character target) {
        boolean success = false;
        if (wingardiumLeviosa.size() > 0 && currentmanaPool > 0) {
            Spell wingardium = wingardiumLeviosa.get(0);
            if (target.getName() == "Troll") {
                new_HP = target.getCurrentHP() - wingardiumCrit;
            } else {
                new_HP = target.getCurrentHP() - wingardiumDmg;
            }
            if (new_HP < 0)
                new_HP = 0;
            target.setCurrentHP(new_HP);
            currentmanaPool -= wingardiumManaUsage;
            wingardiumLeviosa.remove(0);
            success = true;
        }
        minMana();
        return success;
    }

    public boolean useExpecto(Character target) {
        boolean successExpecto = false;
        if (expectoPatronum.size() > 0 && currentmanaPool > 0) {
            Spell expecto = expectoPatronum.get(0);

            if (target.getName() == "Dementor") {
                new_HP = target.getCurrentHP() - expectoCrit;
            } else {
                new_HP = target.getCurrentHP() - expectoDmg;
            }

            if (new_HP < 0)
                new_HP = 0;
            target.setCurrentHP(new_HP);
            currentmanaPool -= expectoManaUsage;
            expectoPatronum.remove(0);
            successExpecto = true;
        }
        minMana();
        return successExpecto;
    }

    public boolean useAccio(Character target) {
        boolean success = false;
        if (accio.size() > 0 && currentmanaPool > 0) {
            Spell spellAccio = accio.get(0);

            if (target.getName() == "Basilic") {
                new_HP = target.getCurrentHP() - accioDmg;
            } else {
                new_HP = target.getCurrentHP() - 0;
            }

            new_HP = target.getCurrentHP() - accioDmg;
            if (new_HP < 0)
                new_HP = 0;
            target.setCurrentHP(new_HP);
            currentmanaPool -= accioManaUsage;
            accio.remove(0);
            success = true;

        }
        minMana();
        return success;
    }


    public boolean useSectumsempra(Character target) {
        boolean success = false;
        if (sectumsempra.size() > 0 && currentmanaPool > 0) {
            Spell sectum = sectumsempra.get(0);

            if ("Basilic".equals(target.getName())) {
                new_HP = target.getCurrentHP() - sectumsempraDmg;
            } else {
                new_HP = target.getCurrentHP() - 0;
            }

            new_HP = target.getCurrentHP() - sectumsempraDmg;
            if (new_HP < 0)
                new_HP = 0;
            target.setCurrentHP(new_HP);
            currentmanaPool -= sectumsempraManaUsg;
            sectumsempra.remove(0);
            success = true;

        }
        minMana();
        return success;
    }

    public boolean useExpelliarmus(Character target) {
        boolean success = false;
        if (expelliarmus.size() > 0 && currentmanaPool > 0) {
            Spell expelli = expelliarmus.get(0);

            if (target.getName() == "Basilic") {
                new_HP = target.getCurrentHP() - expelliarmusDmg;
            } else {
                new_HP = target.getCurrentHP() - 0;
            }

            new_HP = target.getCurrentHP() - expelliarmusDmg;
            if (new_HP < 0)
                new_HP = 0;
            target.setCurrentHP(new_HP);
            currentmanaPool -= expellarmusManaUsg;
            expelliarmus.remove(0);
            success = true;

        }
        minMana();
        return success;
    }

    private static final String choice[] = {"Snowy owl", "Cat", "Toad", "Rat"};
    private static final Pet pets[] = {Pet.SNOWY_OWL, Pet.CAT, Pet.TOAD, Pet.RAT};

    public void Pet() {
        Scanner scanner = new Scanner(System.in);
        String newLine = System.getProperty("line.separator");
        String instructions = newLine + CYAN_BOLD_BRIGHT + "Choose your pet" + RESET;
        InputReader qualityReader = new InputReader(instructions, choice);
        int playerChoice = qualityReader.readInputByNumber();
        int index = playerChoice - 1;
        this.pet = pets[index];
        System.out.println("A " + pet.name().toLowerCase().replace("_", " ") + " will accompany you.");
    }
}