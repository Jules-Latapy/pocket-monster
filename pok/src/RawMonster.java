import java.util.*;

public class RawMonster
{
    private String name;
    private Type type;
    private int minHP;
    private int maxHP;
    private int minSpeed;
    private int maxSpeed;
    private int minAttack;
    private int maxAttack;
    private int minDefense;
    private int maxDefense;

    private Map<String, Double> specialAttribut = new HashMap<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setMinHP(int minHP) {
        this.minHP = minHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setMinSpeed(int minSpeed) {
        this.minSpeed = minSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setMinAttack(int minAttack) {
        this.minAttack = minAttack;
    }

    public void setMaxAttack(int maxAttack) {
        this.maxAttack = maxAttack;
    }

    public void setMinDefense(int minDefense) {
        this.minDefense = minDefense;
    }

    public void setMaxDefense(int maxDefense) {
        this.maxDefense = maxDefense;
    }

    public Map<String, Double> getSpecialAttribut() {
        return specialAttribut;
    }

    public Monster toMonster() {

        assertTypeCoherence();

        return new Monster(
            name,
            minHP + (int)(Math.random() * ((minHP - maxHP) + 1)),
            minDefense + (int)(Math.random() * ((minDefense - maxDefense) + 1)),
            minAttack + (int)(Math.random() * ((minAttack - maxAttack) + 1)),
            minSpeed + (int)(Math.random() * ((minSpeed - maxSpeed) + 1)),
            type,
            specialAttribut
            );
    }

    private void assertTypeCoherence() {
        List<String> attributes = new ArrayList<>(specialAttribut.keySet());
        attributes.sort(String::compareTo);

        List<String> references = new ArrayList<>(List.of(type.getAttribute())); //List.of = unmodifiable
        references.sort(String::compareTo);

        if (references.size()!=attributes.size())
            throw new IllegalArgumentException("Le fichier de configutation à des parametres de capacité spécial en trop/ en moins");

        for (int i = 0; i < references.size(); i++) {
            if (!references.get(i).equals(attributes.get(i))) {
                throw new IllegalArgumentException("le parametre '"+attributes.get(i)+"' n'est pas reconnu");
            }
        }
    }
}
