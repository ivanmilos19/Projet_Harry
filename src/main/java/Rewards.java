public class Rewards {
    String newLine = System.getProperty("line.separator");
    public void getRewards(Wizard wizard) {


        wizard.setBaseHP(wizard.getBaseHP() + 100);
        wizard.setGold(wizard.getGold() + 20);
        wizard.setManaPool(wizard.getManaPool() + 25);
        wizard.setLevel(wizard.getLevel() + 1);
        wizard.setAttack_strength((wizard.getAttack_strength()) + 15);

        System.out.println("You won 25 Gold! " + newLine);
        System.out.println("Your wizard has leveled up ! The followings stats have been augmented: ");
        System.out.println("+ 100 health" + "  |  " + "+25 mana" + "  |  " + "+20 attack" );



    }
}