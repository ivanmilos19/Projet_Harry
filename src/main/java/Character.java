import java.util.Scanner;

public abstract class Character {

    int baseHP = 100;
    int currentHP = 100;

    /** the character attacks the given target */
    public void attack(Character target)
    {
        int damage = damageInflicted();
        int new_HP = target.currentHP - damage;
        if (new_HP < 0)
            new_HP = 0;
        target.currentHP = new_HP;
    }

    /** how much damage can the character inflict when attacking a target */
    public abstract int damageInflicted();

    public boolean isAlive() { return currentHP > 0; }
    public boolean isDead() { return !isAlive(); }
}
