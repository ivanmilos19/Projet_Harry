import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Random;

@Data
@SuperBuilder

public class Enemy extends AbstractEnemy {
    @Override
    public int damageInflicted() {
        return getAttack_strength();
    }
    private int new_HP;

    // Troll big attack //
    private boolean castMace;
    private int maceDmg;



    public boolean RandomUseMace() {
        castMace = false;
        Random rand = new Random();
        double probability = 0.2; // hitting accuracy

        if (rand.nextDouble() < probability) {
            System.out.println("The troll is about to smash you with his mace! You should defend !");
            castMace = true;
        }
        return castMace;
    }

    public boolean canUseMace() {
        return castMace;
    }

    public boolean resetMace() {
        castMace = false;
        return castMace;
    }

    public void useMace(Character target) {
        new_HP = target.getCurrentHP() - getMaceDmg();
        if (new_HP < 0)
            new_HP = 0;
        target.setCurrentHP(new_HP);
    }

}
