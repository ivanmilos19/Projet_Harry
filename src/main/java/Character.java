import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
public abstract class Character {


    protected int currentHP;
    private int baseHP;
    private int attack_strength;
    private String name;



    /** the character attacks the given target */
    public void attack(Character target)
    {
        int damage = damageInflicted();
        if (target.isDefending) {
            damage /= defenseFactor();
        }
        int new_HP = target.currentHP - damage;
        if (new_HP < 0)
            new_HP = 0;
        target.currentHP = new_HP;
    }

    private boolean isDefending;
    public void defend() {
        isDefending = true;
    }
    public void stopDefending() {
        isDefending = false;
    }
    /** how much damage can the character inflict when attacking a target */
    public abstract int damageInflicted();

    /* value by which we divide the inflicted damage by */
    public int defenseFactor() { return 2; }

    public boolean isAlive() { return currentHP > 0; }
    public boolean isDead() { return !isAlive(); }
}
