import lombok.Data;

@Data
public class Enemy extends AbstractEnemy {
    int baseHP = 100;
    int currentennemyHP = 100;


    @Override
    public void attack() {
        while (currentennemyHP > 0) {
            System.out.println(currentHP + "/" + baseHP);
            int damage = 10;
            currentennemyHP -= damage;

            if (currentennemyHP <= 0) {
                System.out.println("Ennemy has been defeated!");
                break;
            }
        }
    }
}
