import lombok.Data;

import java.util.Arrays;

import java.util.Scanner;

import java.util.ArrayList;
@Data
public class Wizard extends Character {
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW

    ArrayList<Potion> healthPotions = new ArrayList<>();
    ArrayList<Potion> damagePotions = new ArrayList<>();
    ArrayList<Potion> manaPotions = new ArrayList<>();

    ArrayList<Spell> wingardiumLeviosa = new ArrayList<>();
    ArrayList<Spell> accio = new ArrayList<>();
    ArrayList<Spell> expectoPatronum = new ArrayList<>();
    ArrayList<Spell> sectumsempra = new ArrayList<>();
    ArrayList<Spell> expelliarmus = new ArrayList<>();


    int manaPool = 100;
    int currentmanaPool = 100;
    int attack_strength = 30;
    int addingDmg = 0;
    Potion currentHealthPotion = null;
    int damagePotionTurnsLeft = 0;
    int Gold = 50;


    Spell spell = new Spell();
    int[] wingardiumNumbers = getSpell().wingardiumLeviosa();

    @Override
    public int damageInflicted() {
        int effective_attack_strength = attack_strength;
        if (damagePotionTurnsLeft > 0) {
            effective_attack_strength += currentHealthPotion.attackImprovement();
            damagePotionTurnsLeft--;
        }
        else
            currentHealthPotion = null;
        // potion has been used up
        return effective_attack_strength;
    }

    public void maxHealth( ) {

        if (baseHP < currentHP) {
            currentHP = baseHP;
        }

    }

    public void manaSpent() {
            int spending = wingardiumNumbers[0];
            int newMana = currentmanaPool - spending;
            if (newMana < 0)
                newMana = 0;
            currentmanaPool = newMana;
    }

    public void addPotion(Potion potion)
    {
        healthPotions.add(potion);
        damagePotions.add(potion);
        manaPotions.add(potion);


    }

    public void addSpell(Spell spell)
    {
        wingardiumLeviosa.add(spell);
    }


    public void useHealthPotion() {
        // use the first available potion in my collection of potions
        if (healthPotions.size() > 0) {
            Potion potion = healthPotions.get(0);
            currentHP +=  potion.healthImprovement();
            // now remove the used potion
            healthPotions.remove(0);
            maxHealth();
        } else {
            System.out.println(YELLOW_BOLD_BRIGHT+"You have no health potions left");
        }
    }

    public void useManaPotion() {
        // use the first available potion in my collection of potions
        if (manaPotions.size() > 0) {
            Potion potion = manaPotions.get(0);
            currentmanaPool += potion.manaImprovement();
            // now remove the used potion
            manaPotions.remove(0);
        } else {
            System.out.println(YELLOW_BOLD_BRIGHT+" You have no mana potions left ");
        }
    }


    public Potion getAttackPotion() {
        Potion potion = null;
        if (damagePotions.size() > 0) {
            potion = damagePotions.get(0);
            // now remove the used potion
            damagePotions.remove(0);
        } else {
            System.out.println(YELLOW_BOLD_BRIGHT+"You have no attack boost potions left");
        }
        return potion;
    }

    // return true if potion equipped successfully, false otherwise
    public void equipDamagePotion() {
        if (currentHealthPotion != null) {
            System.out.println(YELLOW_BOLD_BRIGHT+"A damage potion is already equipped");
            return;
        }
        currentHealthPotion = getAttackPotion();
        if (currentHealthPotion != null) {
            damagePotionTurnsLeft = 2;
            System.out.println(YELLOW_BOLD_BRIGHT + "Damage potion equipped");
        }
    }

    // override the basic attack to take potions into account


    public void useWingardiumLeviosa(Character target) {
        int wingardiumDamage = addingDmg;
        if (wingardiumLeviosa.size() > 0) {
            Spell wingardium = wingardiumLeviosa.get(0);
            wingardiumDamage +=  wingardiumNumbers[0];
            int new_HP = target.currentHP - wingardiumDamage;
            if (new_HP < 0)
                new_HP = 0;
            target.currentHP = new_HP;
            wingardiumLeviosa.remove(0);
            manaSpent();
        } else {
            System.out.println(YELLOW_BOLD_BRIGHT + "You can't cast wingardium anymore");
        }

    }




    Pet pet;

    private static String choice[] = {"Snowy owl", "Cat", "Toad", "Rat" };
    private static  Pet pets [] = {Pet.SNOWY_OWL, Pet.CAT, Pet.TOAD, Pet.RAT};
    public void Pet(){
        Scanner scanner = new Scanner(System.in);
        String newLine = System.getProperty("line.separator");
        String instructions = newLine + "Choose your pet";
        InputReader qualityReader = new InputReader(instructions, choice);
        String playerChoice = qualityReader.readInput();
        int index = Arrays.asList(choice).indexOf(playerChoice);
        Pet pet = pets[index];
        System.out.println("A " + pet.name().toLowerCase().replace("_", " ") + " will accompany you.");
    }

}