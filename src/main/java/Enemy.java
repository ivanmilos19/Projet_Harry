import lombok.Data;

import java.util.Scanner;

@Data
public class Enemy extends AbstractEnnemy {
    int baseHP = 100;
    int currentHP = 100;

    @Override
    public void attack() {
        while (currentHP > 0) {
            String newLine = System.getProperty("line.separator");
            Scanner scanner = new Scanner(System.in);

            System.out.println( currentHP + "/" + baseHP);

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
