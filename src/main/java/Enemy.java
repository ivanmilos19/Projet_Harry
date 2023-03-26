import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder

public class Enemy extends AbstractEnemy{
    @Override
    public int damageInflicted() {
        return getAttack_strength();
    }

}
