import lombok.Data;

import java.util.Arrays;

import java.util.Scanner;

import java.util.ArrayList;
@Data
public class Wizard extends Character {
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW

    Pet pet;
    // collection of potions

    ArrayList<Potion> healthPotions = new ArrayList<>();
    ArrayList<Potion> damagePotions = new ArrayList<>();
    ArrayList<Potion> dodgePotions = new ArrayList<>();

    int attack_strength = 20;

    Potion currentDamagePotion = null;
    int damagePotionTurnsLeft = 0;

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


    @Override
    public int damageInflicted() {
        int effective_attack_strength = attack_strength;
        if (damagePotionTurnsLeft > 0) {
            effective_attack_strength += currentDamagePotion.attackImprovement();
            damagePotionTurnsLeft--;
        }
        else
            currentDamagePotion = null; // potion has been used up

        return effective_attack_strength;
    }

    public void addPotion(Potion potion)
    {
        healthPotions.add(potion);
        damagePotions.add(potion);

    }

    public void useHealthPotion() {
        // use the first available potion in my collection of potions
        if (healthPotions.size() > 0) {
            Potion potion = healthPotions.get(0);
            currentHP +=  potion.healthImprovement();
            // now remove the used potion
            healthPotions.remove(0);
        } else {
            System.out.println(YELLOW_BOLD_BRIGHT+"You have no potions left");
        }
    }

    public Potion getAttackPotion() {
        Potion potion = null;
        if (damagePotions.size() > 0) {
            potion = damagePotions.get(0);
            // now remove the used potion
            damagePotions.remove(0);
        } else {
            System.out.println(YELLOW_BOLD_BRIGHT+"You have no potions left");
        }
        return potion;
    }

    // return true if potion equipped successfully, false otherwise
    public void equipDamagePotion() {
        if (currentDamagePotion != null) {
            System.out.println(YELLOW_BOLD_BRIGHT+"A potion is already equipped, not adding another one");
            return;
        }
        currentDamagePotion = getAttackPotion();
        if (currentDamagePotion != null) {
            damagePotionTurnsLeft = 2;
            System.out.println(YELLOW_BOLD_BRIGHT+"Damage potion equipped");

        }
    }

    // override the basic attack to take potions into account

}