import lombok.Data;

import java.util.Scanner;

@Data
public class GameLogic {

    public  void battle(Wizard wizard, AbstractEnemy enemy) {
        while (true)
        {
            System.out.println("Start of turn");
            System.out.println("Wizard HP " + wizard.currentHP);
            System.out.println("Enemy HP " + enemy.currentHP);
            if (wizard.isDead())
            {
                System.out.println("Game Over");
                break;
            }

            wizard.attack(enemy); // the protagonist is attacking its enemy

            if (enemy.isDead())
            {
                System.out.println("Congrats you won");
                break;
            }

            enemy.attack(wizard); // now the protagonist is attacked
        }
        System.out.println("Final state");
        System.out.println("Wizard HP " + wizard.currentHP);
        System.out.println("Enemy HP " + enemy.currentHP);
    }
}
