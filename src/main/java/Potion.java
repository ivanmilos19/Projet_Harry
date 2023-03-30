import lombok.Data;

@Data
public class Potion {

    public int attackImprovement() {
        return 60;
    }
    public int healthImprovement() { return 10; }
    public int manaImprovement() {return 40;}


}
