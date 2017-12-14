abstract class Character {
	private int initialHealth;
	private int currentHealth;
	private int attack;
	private int intelligence;
	private int defence;
	private int speed;
	
	private String type;
	
	Character(int health, int attack, int intelligence, int defence, int speed, String type) {
			this.initialHealth = health;
			this.currentHealth = health;
			this.attack = attack;
			this.intelligence = intelligence;
			this.defence = defence;
			this.speed = speed;
			this.type = type;
	}
	
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
	
	public void dickFace() {
		System.out.println("My dick small");
	}
}