import lombok.Data;

import java.util.Scanner;
@Data
public class Wizard extends Character {
    @Override
    public int damageInflicted() { return 10; }

}