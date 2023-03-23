public class DamagePotion {

    private int damageBoost;
    private int duration;
    private boolean active;

    Wizard wizard = new Wizard();

    public void DamagePotion(int damageBoost, int duration) {
        this.damageBoost = wizard.damageInflicted() + 20;
        this.duration = 3;
        this.active = false;
    }

    public void activate() {
        this.active = true;

    }

    public boolean isActive() {
        return this.active;
    }

    public void endTurn() {
        this.duration--;
        if (this.duration == 0) {
            this.active = false;
        }
    }
}

