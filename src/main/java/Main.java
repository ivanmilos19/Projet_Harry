public  class Main {
    public static void main(String[] args) {

        //SortingHat sortingHat = new SortingHat();
        //Wand wand = new Wand();
        //Wizard wizard = new Wizard();
        GameLogic game = new GameLogic();
        Enemy enemy = new Enemy();
        game.battle(enemy);
    }
}