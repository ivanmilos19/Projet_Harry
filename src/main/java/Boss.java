import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class  Boss extends AbstractEnemy{
    @Override
    public int damageInflicted() {
        return getAttack_strength();
    }


}
