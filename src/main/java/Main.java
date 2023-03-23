public  class Main {
    public static void main(String[] args) {

        Wizard wizard = new Wizard();

        //SortingHat sortingHat = new SortingHat();
        //wizard.Pet();
        //Wand wand = new Wand();
        //wand.Wand();

        // give it one potion to start with
        wizard.addPotion(new Potion());
        wizard.addSpell(new Spell());

        GameLogic game = new GameLogic();

        AbstractEnemy enemy = new Enemy()   ;
        game.battle(wizard, enemy);


        if (wizard.isAlive())
        {
            wizard.currentHP = wizard.baseHP;
            Boss boss = new Boss();
            game.battle(wizard, boss);
        }


    }
}

