import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Random;

@Data
@SuperBuilder
public class  Boss extends AbstractEnemy {
    @Override
    public int damageInflicted() {
        return getAttack_strength();
    }

    private int AvadaKedavraDmg;
    private int new_HP;
    private boolean castAvada;


    public boolean RandomUseAvada() {
        castAvada = false;
        Random rand = new Random();
        double probability = 0.3; // hitting accuracy

        if (rand.nextDouble() < probability) {
            System.out.println("Voldemort is preparing to use Avada Kedravra!");
            castAvada = true;
        }
        return castAvada;
    }

    public boolean canUseAvada() {
        return castAvada;
    }



    public boolean resetUseAvada() {
        castAvada = false;
        return castAvada;
    }

    public void useAvadaKedavra(Character target) {
            int damage = damageInflicted();
            new_HP = target.getCurrentHP() - getAvadaKedavraDmg();
            if (new_HP < 0)
                new_HP = 0;
            target.setCurrentHP(new_HP);
    }






}
