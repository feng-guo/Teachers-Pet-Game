package battle;

import java.util.ArrayList;

import characters.ListOfCharacters;
import characters.NonPlayableCharacter;
import characters.PlayableCharacter;
import characters.Squad;
import game.Handler;
import items.Inventory;
import items.Item;
import items.ListOfInventoryItems;
import items.StatItem;

/* This is now an important file that interacts with a battle class
 */

public class BattleRunner {

    private ListOfCharacters characterList;
    private ListOfInventoryItems inventoryItems = new ListOfInventoryItems();
    private Handler handler;
    private Inventory inventory;
    private PlayableCharacter[] newSquad = new PlayableCharacter[6];


    private NonPlayableCharacter MrChoi, MrShim, MrTimmerman, randomNiner, alston, michael, msKostanenko, mrGissing, nikhil, aaron, rosemary, mrHarris;
    private Squad squad;
    public boolean battleStart;
    private Battle battle;
    private boolean battleStarted;
	
	public BattleRunner(Handler handler) {
		battleStart = false;
		this.handler = handler;
		characterList = new ListOfCharacters(handler);
		this.inventory = handler.getInventory();
	}

    public void initializeBattleAssets() {
        newSquad[0] = (PlayableCharacter) characterList.returnCharacter("Feng");
        newSquad[1] = (PlayableCharacter) characterList.returnCharacter("Joyce");
        newSquad[2] = (PlayableCharacter) characterList.returnCharacter("Sihan");
        newSquad[3] = (PlayableCharacter) characterList.returnCharacter("Yash");
        newSquad[4] = (PlayableCharacter) characterList.returnCharacter("Johann");
        newSquad[5] = (PlayableCharacter)characterList.returnCharacter("Angela");
        //newSquad[5] = (PlayableCharacter) characterList.returnCharacter("Misha");
        //newSquad[5] = (PlayableCharacter)characterList.returnCharacter("Angela");
        newSquad[5].setHeldItem((StatItem)inventoryItems.retrieveItem("Starbucks Card"));
        MrChoi = (NonPlayableCharacter) characterList.returnCharacter("Mr.Choi");
        MrShim = (NonPlayableCharacter) characterList.returnCharacter("Mr.Shim");
        MrTimmerman = (NonPlayableCharacter) characterList.returnCharacter("Mr.Timmerman");
        randomNiner = (NonPlayableCharacter) characterList.returnCharacter("Random Niner");
        alston = (NonPlayableCharacter) characterList.returnCharacter("Alston");
        michael = (NonPlayableCharacter) characterList.returnCharacter("Michael");
        msKostanenko = (NonPlayableCharacter) characterList.returnCharacter("Ms.Kostanenko");
        rosemary = (NonPlayableCharacter) characterList.returnCharacter("Rosemary");
        mrGissing = (NonPlayableCharacter) characterList.returnCharacter("Mr.Gissing");
        nikhil = (NonPlayableCharacter) characterList.returnCharacter("Nikhil");
        aaron = (NonPlayableCharacter) characterList.returnCharacter("Aaron");
        mrHarris = (NonPlayableCharacter) characterList.returnCharacter("Mr.Harris");
        squad = new Squad(newSquad);

        inventory.addItem(inventoryItems.retrieveItem("Caf Food"));
        inventory.addItem(inventoryItems.retrieveItem("Caf Food"));
        inventory.addItem(inventoryItems.retrieveItem("Caf Food"));
        inventory.addItem(inventoryItems.retrieveItem("Caf Food"));
        inventory.addItem(inventoryItems.retrieveItem("Caf Food"));
        inventory.addItem(inventoryItems.retrieveItem("Caf Food"));
        inventory.addItem(inventoryItems.retrieveItem("McDonald's Burger"));
        inventory.addItem(inventoryItems.retrieveItem("Red Bull"));
    }

    public void startRandomBattle() {
        double random2 = Math.random();
        //System.out.println(random2);
        NonPlayableCharacter opponent;
        if (random2 < 0.33) {
            opponent = mrHarris;
        } else if (random2 < 0.66) {
            opponent = rosemary;
        } else {
            opponent = mrGissing;
        }
        battle = new Battle(handler, squad.getCharacter(0), opponent, handler.getSquad(), inventory);
        //battle.setOpponentName(handler.getGame().getCurrentOpponentName());
        battleStart = true;
    }

    public void startBattle (NonPlayableCharacter opponent) {
	    battle = new Battle(handler, squad.getCharacter(0), opponent, squad, inventory);
        //battle.setOpponentName(handler.getGame().getCurrentOpponentName());
	    battleStart = true;
    }

    public void runPhase(int choice) {
        if (choice == -10) {
            battle.goBackInMenu();
        } else if (choice == -1) {
            battle.runBattleTurn(-1);
        } else if (battle.isPlayerInventoryChoicePhase()) {
            battle.useInventoryItem(choice);
        } else if (battle.isPlayerInventoryPhase()) {
            battle.playerPickInventory();
        } else if (choice < 10) {
            if (battle.isPlayerPickCharacterPhase()) {
                battle.playerPickCharacter(choice);
            } else if (battle.isPlayerSwitchPhase()) {
                battle.playerSwitchCharacter();
            } else if (battle.isPlayerAttackChoicePhase()) {
                battle.playerUseAttack(choice);
            } else if (battle.isPlayerPickAttackPhase()) {
                battle.playerPickAttack();
            } else {
                battle.runBattleTurn(choice);
            }
        } else {
            battle.runBattleTurn(choice);
        }
    }

    //Getters
    public Battle getBattle() {
	    return battle;
    }

    public boolean isBattleStart() {
	    return battleStart;
    }

    public ArrayList<String> getTextArrayList() {
	    return battle.getTextArrayList();
    }

    public PlayableCharacter getPlayer() {
	    return battle.getPlayer();
    }

    public NonPlayableCharacter getOpponent() {
	    return battle.getOpponent();
    }

    public String getSelectionStrings(int i) {
	    return battle.getSelectionStrings(i);
    }

    public boolean isBattleEnd() {
	    return battle.isBattleEnd();
    }

    public boolean isPlayerAttacked() {
	    return battle.isPlayerAttacked();
    }

    public boolean isPlayerMovedFirst() {
	    return battle.isPlayerMovedFirst();
    }

    public Squad getSquad() {
	    return squad;
    }

    public boolean isPlayerSwitchPhase() {
	    return battle.isPlayerSwitchPhase();
    }

    public String[] getCharacterStrings() {
	    return battle.getCharacterStrings();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isInventoryChoicePhase() {
	    return battle.isPlayerInventoryChoicePhase();
    }

    public boolean isPlayerPickCharacterPhase() {
	    return battle.isPlayerPickCharacterPhase();
    }

    public String getOpponentStatus() {
	    return battle.getOpponentStatus();
    }
}
