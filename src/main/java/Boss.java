public class Boss extends AbstractEnemy{

    int trollCurrentHP = 300;
    int trollBaseHP = 300;;
    @Override
    public int damageInflicted() {
        return 50;
    }
}
