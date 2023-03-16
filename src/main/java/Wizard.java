import lombok.Data;

import java.util.Arrays;

import java.util.Scanner;
@Data
public class Wizard extends Character {

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
    @Override
    public int damageInflicted() {
        return 34;
    }

}