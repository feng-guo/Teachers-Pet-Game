package teacherspet;

/**
 *
 * @author Feng
 */
abstract class Character {

    private int initialHealth;
    private int currentHealth;
    private int attack;
    private int intelligence;
    private int defence;
    private int speed;

    private String type;
    private String name;

    Character(int health, int attack, int intelligence, int defence, int speed, String type, String name) {
        //Integer variables
        this.initialHealth = health;
        this.currentHealth = health;
        this.attack = attack;
        this.intelligence = intelligence;
        this.defence = defence;
        this.speed = speed;
        //String variables
        this.type = type;
        this.name = name;
    }

    //Changing health
    public int getInitialHealth() {
        return initialHealth;
    }

    public void changeInitialHealth(int change) {
        initialHealth += change;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void changeCurrentHealth(int change) {
        currentHealth += change;
    }

    public void resetCurrentHealth() {
        currentHealth = initialHealth;
    }

    //Attack
    public int getAttack() {
        return attack;
    }

    public void changeAttack(int change) {
        attack += change;
    }

    //Intelligence
    public int getIntelligence() {
        return intelligence;
    }

    public void changeIntelligence(int change) {
        intelligence += change;
    }

    //Defence
    public int getDefence() {
        return defence;
    }

    public void changeDefence(int change) {
        defence += change;
    }

    //Speed
    public int getSpeed(){
        return speed;
    }

    public void changeSpeed(int change) {
        speed += change;
    }
}
