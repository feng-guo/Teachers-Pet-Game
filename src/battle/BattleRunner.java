package battle;

import java.util.ArrayList;

import characters.ListOfCharacters;
import characters.NonPlayableCharacter;
import characters.PlayableCharacter;
import characters.Squad;
import items.Inventory;
import items.Item;
import items.ListOfInventoryItems;

/* This is now an important file that interacts with a battle class
 */

//import java.util.Scanner;

public class BattleRunner {

    private ListOfCharacters characterList = new ListOfCharacters();
    private ListOfInventoryItems inventoryItems = new ListOfInventoryItems();
    private Inventory inventory = new Inventory();
    private PlayableCharacter[] newSquad = new PlayableCharacter[6];
    private NonPlayableCharacter MrChoi, MrShim, MrTimmerman, randomNiner, alston, michael, msKostanenko;
    private Squad squad;
    public boolean battleStart;
    private Battle battle;
    private boolean battleStarted;
	
	public BattleRunner() {
		battleStart = false;
	}

    public void initializeBattleAssets() {
        newSquad[0] = (PlayableCharacter) characterList.returnCharacter("Feng");
        newSquad[1] = (PlayableCharacter) characterList.returnCharacter("Joyce");
        newSquad[2] = (PlayableCharacter) characterList.returnCharacter("Sihan");
        newSquad[3] = (PlayableCharacter) characterList.returnCharacter("Yash");
        newSquad[4] = (PlayableCharacter) characterList.returnCharacter("Johann");
        newSquad[5] = (PlayableCharacter) characterList.returnCharacter("Misha");
        //newSquad[5] = (PlayableCharacter)characterList.returnCharacter("Angela");
        MrChoi = (NonPlayableCharacter) characterList.returnCharacter("Mr.Choi");
        MrShim = (NonPlayableCharacter) characterList.returnCharacter("Mr.Shim");
        MrTimmerman = (NonPlayableCharacter) characterList.returnCharacter("Mr.Timmerman");
        randomNiner = (NonPlayableCharacter) characterList.returnCharacter("Random Niner");
        alston = (NonPlayableCharacter) characterList.returnCharacter("Alston");
        michael = (NonPlayableCharacter) characterList.returnCharacter("Michael");
        msKostanenko = (NonPlayableCharacter) characterList.returnCharacter("Ms.Kostanenko");
        squad = new Squad(newSquad);

        inventory.addItem(inventoryItems.retrieveItem("Caf Food"));
        inventory.addItem(inventoryItems.retrieveItem("Caf Food"));
        inventory.addItem(inventoryItems.retrieveItem("Caf Food"));
        inventory.addItem(inventoryItems.retrieveItem("Caf Food"));
        inventory.addItem(inventoryItems.retrieveItem("Caf Food"));
        inventory.addItem(inventoryItems.retrieveItem("Caf Food"));
    }


    public void startRandomBattle() {
        double random2 = Math.random();
        NonPlayableCharacter opponent;
        if (random2 < 0.33) {
            opponent = MrChoi;
        } else if (random2 < 0.66) {
            opponent = MrShim;
        } else {
            opponent = MrTimmerman;
        }
        battle = new Battle(squad.getCharacter(0), opponent, squad, inventory);
        battleStart = true;
    }

    public void startBattle (NonPlayableCharacter opponent) {
	    battle = new Battle(squad.getCharacter(0), opponent, squad, inventory);
	    battleStart = true;
    }

    public void runPhase(int choice) {
        if (choice == 10) {
            battle.goBackInMenu();
        } else if (choice == 20) {
            return;
        } else if (battle.isPlayerPickCharacterPhase()) {
            battle.playerPickCharacter(choice);
        } else if (battle.isPlayerSwitchPhase()) {
            battle.playerSwitchCharacter();
        } else if (battle.isPlayerAttackChoicePhase()) {
            battle.playerUseAttack(choice);
        } else if (battle.isPlayerPickAttackPhase()) {
            battle.playerPickAttack();
        } else if (battle.isPlayerInventoryChoicePhase()) {
            battle.useInventoryItem(choice);
        } else if (battle.isPlayerInventoryPhase()) {
            battle.playerPickInventory();
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
