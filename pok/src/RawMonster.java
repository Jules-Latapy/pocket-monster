public class rawMonster
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getMinHP() {
        return minHP;
    }

    public void setMinHP(int minHP) {
        this.minHP = minHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(int minSpeed) {
        this.minSpeed = minSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMinAttack() {
        return minAttack;
    }

    public void setMinAttack(int minAttack) {
        this.minAttack = minAttack;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public void setMaxAttack(int maxAttack) {
        this.maxAttack = maxAttack;
    }

    public int getMinDefense() {
        return minDefense;
    }

    public void setMinDefense(int minDefense) {
        this.minDefense = minDefense;
    }

    public int getMaxDefense() {
        return maxDefense;
    }

    public void setMaxDefense(int maxDefense) {
        this.maxDefense = maxDefense;
    }

    public double getParalysisChance() {
        return paralysisChance;
    }

    public void setParalysisChance(double paralysisChance) {
        this.paralysisChance = paralysisChance;
    }

    public void setFloodChance(int i) {
    }

    public void setFallChance(int i) {
    }

    public void setHideChance(int i) {
    }

    public void setHealChance(int i) {
    }

    public void setPoisonChance(int i) {
    }

    public void setBurnChance(int i) {
        this.burnChance=i;
    }

    public Monster toMonster(Monster monster) {
        return new Monster(

        )
    }
}
