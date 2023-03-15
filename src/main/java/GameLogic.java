import java.util.Scanner;

public class GameLogic {
    int baseHP = 100;
    int currentHP = 100;

    int currentennemyHP =100;

    public  void battle(Enemy enemy){
        while (currentHP > 0) {
            String newLine = System.getProperty("line.separator");
            Scanner scanner = new Scanner(System.in);
            System.out.println(newLine + "What do you want to do?");
            System.out.println(currentHP + "/" + baseHP);
            System.out.println("1. Attack");
            System.out.println("2. Defend");
            int choice = scanner.nextInt();

            if (choice == 1) {
                int damage = 10;
                System.out.println(newLine + "You attack the enemy for " + damage + " damage!");
                currentennemyHP -= damage;
                System.out.println(currentennemyHP + "/" + baseHP);

                if (currentHP <= 0) {
                    System.out.println("You have been defeated!");
                    break;
                }
            }
        }
    }
}
