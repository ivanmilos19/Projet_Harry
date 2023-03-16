public  class Main {
    public static void main(String[] args) {
        Wizard wizard = new Wizard();
        //SortingHat sortingHat = new SortingHat();
        //wizard.Pet();
        //Wand wand = new Wand();
        //wand.Wand();

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
