import lombok.Data;

import java.util.Scanner;
@Data
public class Wizard extends Character {
    int baseHP = 100;
    int currentHP = 100;
    Wand wand = new Wand();


    @Override
    public void attack() {
        while (currentHP > 0) {
            String newLine = System.getProperty("line.separator");
            Scanner scanner = new Scanner(System.in);
            System.out.println(newLine + "What do you want to do?");
            System.out.println( currentHP + "/" + baseHP);
            System.out.println("1. Attack");
            System.out.println("2. Defend");
            int choice = scanner.nextInt();

            if (choice == 1) {
                int damage = 10;
                System.out.println(newLine + "You attack the enemy for " + damage + " damage!");
                currentHP -= damage;

                if (currentHP <= 0) {
                    System.out.println("You have been defeated!");
                    break;
                }
            }

        }
    }
}
