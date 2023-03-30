import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Random;

@Data
@SuperBuilder

public class Enemy extends AbstractEnemy {
    @Override
    public int damageInflicted() {
        int attack_strength = getAttack_strength();
        if (canUseMace()) {
            attack_strength *= attackStrengthMultiplier;
        }
        return attack_strength;
    }
    private int new_HP;

    // Troll big attack //
    private boolean castMace;
    private int attackStrengthMultiplier;

    public boolean randomUseMace() {
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

    public void resetMace() {
        castMace = false;
    }

}
