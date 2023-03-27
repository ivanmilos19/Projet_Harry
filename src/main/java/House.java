public abstract class House {

    public abstract String getName();

    public double defenseMultiplier() { return 1; };

    public double attackMultiplier() { return 1; }

    public double precision() { return 0.9; }

    public boolean improvedPotions() { return false; }

    public boolean canUseSword() { return false; };

    public boolean canJoinEnemy() { return false; }
}
