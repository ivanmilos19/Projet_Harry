import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Random;

@Data
@SuperBuilder



public class Enemy extends AbstractEnemy {
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
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
            System.out.println(RED_BOLD_BRIGHT +"The troll is about to smash you with his mace and is about to do big damage !");
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
