package battle;

import java.awt.Graphics;

import game.Handler;

/* This is now an important file that interacts with a battle class
 */

//import java.util.Scanner;

public class BattleRunner {
	
	private String finalText;
	private Handler handler;
    private ListOfCharacters characterList = new ListOfCharacters();
    private ListOfInventoryItems inventoryItems = new ListOfInventoryItems();
    private Inventory inventory = new Inventory();
    private PlayableCharacter[] newSquad = new PlayableCharacter[6];
    private NonPlayableCharacter MrChoi, MrShim, MrTimmerman;
    private Squad squad;
    private boolean battleStart;
    private Battle battle;
    private Graphics g;
	
	public BattleRunner(Handler handler, Graphics g) {
		this.handler = handler;
		this.g = g;
		battleStart = false;
	}

    public void initializeBattleAssets() {
        newSquad[0] = (PlayableCharacter)characterList.returnCharacter("Feng");
        newSquad[1] = (PlayableCharacter)characterList.returnCharacter("Joyce");
        newSquad[2] = (PlayableCharacter)characterList.returnCharacter("Sihan");
        newSquad[3] = (PlayableCharacter)characterList.returnCharacter("Yash");
        newSquad[4] = (PlayableCharacter)characterList.returnCharacter("Johann");
        newSquad[5] = (PlayableCharacter)characterList.returnCharacter("Misha");
        MrChoi = (NonPlayableCharacter)characterList.returnCharacter("Mr. Choi");
        MrShim = (NonPlayableCharacter)characterList.returnCharacter("Mr. Shim");
        MrTimmerman = (NonPlayableCharacter)characterList.returnCharacter("Mr. Timmerman");
        squad = new Squad(newSquad);
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
        battle = new Battle(squad.getCharacter(0), opponent, squad, inventory, handler, g);
    }

    public void startBattle (NonPlayableCharacter opponent) {
	    battle = new Battle(squad.getCharacter(0), opponent, squad, inventory, handler, g);
    }

	public void simulateBattle(int choice){
	    battle.runBattleTurn(choice);
    }

    //Getters
    public Battle getBattle() {
	    return battle;
    }
}
