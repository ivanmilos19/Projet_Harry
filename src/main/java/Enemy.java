import lombok.Data;

@Data
public class Enemy extends AbstractEnemy {

    @Override
    public int damageInflicted() {
        return 5;
    }
}
