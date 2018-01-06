package battle;

class PlayableCharacter extends Character {
    private String description; //description of the character
    private String status; //Status would be the status condition
    private boolean fainted;
    private int experience; //EXP points
    private int level;
    private StatItem hatItem;
    private StatItem shirtItem;
    private StatItem pantsItem;
    private StatItem shoesItem;

    //Possibly dumb stats to display??
    PlayableCharacter(int health, int attack, int intelligence, int defence, int speed, String type, String name, String description, Move[] moveset, String ability, StatItem heldItem, StatItem hatItem, StatItem shirtItem, StatItem pantsItem, StatItem shoesItem) {
      super(health, attack, intelligence, defence, speed, type, name, moveset, ability, heldItem);
      this.description = description;
      this.status = "";
      this.fainted = false;
      this.level = 1;
      this.hatItem = hatItem;
      this.shirtItem = shirtItem;
      this.pantsItem = pantsItem;
      this.shoesItem = shoesItem;
    }

    public void levelUp(int healthIncrease, int attackIncrease, int intelligenceIncrease, int defenceIncrease, int speedIncrease) {
        changeInitialHealth(healthIncrease);
        changeAttack(attackIncrease);
        changeIntelligence(intelligenceIncrease);
        changeDefence(defenceIncrease);
        changeSpeed(speedIncrease);
    }

    //Status methods
    public String getStatus() {
      return status;
    }
    public void setStatus(String status) {
      this.status = status;
    }
    public void resetStatus() {
      status = "";
    }
    public void faintCharacter() {
        fainted = true;
        setCurrentHealth(0);
    }

    public boolean isFainted() {
        return fainted;
    }

    //Experience methods
    public int getExperience() {
      return experience;
    }
    public void changeExperience(int change) {
      experience += change;
      while (experience > 20*level) {
          experience -= 20*level;
          level++;
      }
    }

    //Clothing item
    //When returning items from removing or adding them, they are returned as items to be put back into the arraylist
    public Item setHatItem(StatItem hatItem) {
        if (hatItem.getName().equals("Shoes")) {
            if (this.hatItem == null) {
                this.hatItem = hatItem;
                return null;
            } else {
                Item returnItem = this.hatItem;
                this.hatItem = hatItem;
                return returnItem;
            }
        } else {
            return null;
        }
    }

    public Item setShirtItem(StatItem shirtItem) {
        if (shirtItem.getName().equals("Shirt")) {
            if (this.shirtItem == null) {
                this.shirtItem = shirtItem;
                return null;
            } else {
                Item returnItem = this.shirtItem;
                this.shirtItem = shirtItem;
                return returnItem;
            }
        } else {
            return null;
        }
    }

    public Item setPantsItem(StatItem pantsItem) {
        if (this.pantsItem == null) {
            this.pantsItem = pantsItem;
            return null;
        } else {
            Item returnItem = this.pantsItem;
            this.pantsItem = pantsItem;
            return returnItem;
        }
    }

    public Item setShoesItem(StatItem shoesItem) {
        if (this.shoesItem == null) {
            this.shoesItem = shoesItem;
            return null;
        } else {
            Item returnItem = this.shoesItem;
            this.shoesItem = shoesItem;
            return returnItem;
        }
    }

    public Item removeHatItem() {
        Item returnItem = hatItem;
        hatItem = null;
        return returnItem;
    }

    public Item removeShirtItem() {
        Item returnItem = shirtItem;
        shirtItem = null;
        return returnItem;
    }

    public Item removePantsItem() {
        Item returnItem = pantsItem;
        pantsItem = null;
        return returnItem;
    }

    public Item removeShoesItem() {
        Item returnItem = shoesItem;
        shoesItem = null;
        return returnItem;
    }

    public StatItem getHatItem() {
        return hatItem;
    }

    public StatItem getShirtItem() {
        return shirtItem;
    }

    public StatItem getPantsItem() {
        return pantsItem;
    }

    public StatItem getShoesItem() {
        return shoesItem;
    }
}
