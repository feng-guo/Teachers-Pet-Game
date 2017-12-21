/**
 *
 * @author Feng
 */

abstract class Character {
    //Battling.Character states
    private int initialHealth;
    private int currentHealth;
    private int attack;
    private int intelligence;
    private int defence;
    private int speed;

    //Battling.Character attributes
    private String type;
    private String name;
    private String ability;

    //Battling.Character moveset
    private Move[] moveset;
    private int[] powerPoints;

    Character(int health, int attack, int intelligence, int defence, int speed, String type, String name, Move[] moveset, String ability) {
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
        this.ability = ability;
        this.moveset = moveset;
        int[] powerPointCounter = new int[4];
        for (int i=0; i<4; i++) {
            powerPointCounter[i] = moveset[i].getMaxPowerPoints();
        }
        this.powerPoints = powerPointCounter;
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
    public void setCurrentHealth(int health) {
        currentHealth = health;
    }
    public void resetCurrentHealth() {
        currentHealth = initialHealth;
    }
    public void faintCharacter() {
        currentHealth = 0;
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
    public int getSpeed() {
        return speed;
    }
    public void changeSpeed(int change) {
        speed += change;
    }

    //Name
    public String getName() {
        return name;
    }

    //Type
    public String getType() {
        return type;
    }

    //Ability
    public String getAbility() {
        return ability;
    }

    //Moveset
    public Move[] getMoveset() {
        return moveset;
    }
    public void changeMoveset(int arrayNumber, Move move) {
        moveset[arrayNumber] = move;
    }
}
