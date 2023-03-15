public  class Main {
    public static void main(String[] args) {

        //SortingHat sortingHat = new SortingHat();
        //Wand wand = new Wand();

        GameLogic game = new GameLogic();

        Wizard wizard = new Wizard();
        AbstractEnemy enemy = new Boss();

        game.battle(wizard, enemy);
    }
}