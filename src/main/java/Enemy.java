import lombok.Data;

@Data
public class Enemy extends AbstractEnemy {
    int currentHP = 300;
    int baseHP = 300;;
    @Override
    public int damageInflicted() {
        return 15;
    }
}
