public class Gryffindor extends House {
    public String getName() { return "Gryffindor"; }

    @Override
    public int defenseMultiplier() {
        return 1;
    }

    public boolean canUseSword() { return true; };
}
