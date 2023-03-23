
public abstract class Character {

    int currentHP = 250;
    int baseHP = 250;

    public void maxHealth(Character target) {
        int maxHP = 100;
        if (maxHP < target.currentHP) {
            target.currentHP = maxHP;
        }

    }

    /** the character attacks the given target */
    public void attack(Character target)
    {
        int damage = damageInflicted();
        int new_HP = target.currentHP - damage;
        if (new_HP < 0)
            new_HP = 0;
        target.currentHP = new_HP;
    }

    public void defend(Character target) {
        int new_HP = target.currentHP + 5;
        target.currentHP = new_HP;
    }
    /** how much damage can the character inflict when attacking a target */
    public abstract int damageInflicted();

    public boolean isAlive() { return currentHP > 0; }
    public boolean isDead() { return !isAlive(); }
}
