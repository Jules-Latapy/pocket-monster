import java.util.Random;

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
    private double paralysisChance;
    private double burnChance;
    private double poisonChance;
    private double fallChance;
    private double floodChance;
    private double healChance;
    private double hideChance;

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

    public void setParalysisChance(double paralysisChance) {
        this.paralysisChance = paralysisChance;
    }

    public void setFloodChance(double i) {
        this.floodChance=i;
    }

    public void setFallChance(double i) {
        this.fallChance=i;
    }

    public void setHideChance(double i) {
        this.hideChance=i;
    }

    public void setHealChance(double i) {
        this.healChance=i;
    }

    public void setPoisonChance(double i) {
        this.poisonChance=i;
    }

    public void setBurnChance(double i) {
        this.burnChance=i;
    }

    public Monster toMonster() {
        return new Monster(
            new Attaque[4],
            minDefense + (int)(Math.random() * ((minDefense - maxDefense) + 1)),
            minSpeed + (int)(Math.random() * ((minSpeed - maxSpeed) + 1)),
            0,
            type,
            minAttack + (int)(Math.random() * ((minAttack - maxAttack) + 1)),
            minHP + (int)(Math.random() * ((minHP - maxHP) + 1))
        );
    }
}
