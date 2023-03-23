public  class Main {
    public static void main(String[] args) {
        Wizard wizard = new Wizard();

        // give it one potion to start with
        wizard.addPotion(new Potion());

        GameLogic game = new GameLogic();

        AbstractEnemy enemy = new Enemy()   ;
        game.battle(wizard, enemy);


        /*if (wizard.isAlive())
        {
            wizard.currentHP += 10000;
            AbstractEnemy boss = new Boss();
            game.battle(wizard, boss);
        }*/
    }
}

