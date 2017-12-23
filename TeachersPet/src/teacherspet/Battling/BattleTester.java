/* Please ignore this file
 * Testing file for battles
 * So very cool wow
 * And it works too ;)
 */

import java.util.Scanner;

public class BattleTester {
    public static void main(String[] args)  {
        ListOfCharacters characterList = new ListOfCharacters();
        //ListOfInventoryItems inventoryItems = new ListOfInventoryItems();
        //Inventory inventory = new Inventory(null);
        PlayableCharacter Feng = (PlayableCharacter)characterList.returnCharacter("Feng");
        PlayableCharacter Joyce = (PlayableCharacter)characterList.returnCharacter("Joyce");
        PlayableCharacter Sihan = (PlayableCharacter)characterList.returnCharacter("Sihan");
        PlayableCharacter Yash = (PlayableCharacter)characterList.returnCharacter("Yash");
        NonPlayableCharacter MrChoi = (NonPlayableCharacter)characterList.returnCharacter("Mr. Choi");
        NonPlayableCharacter MrShim = (NonPlayableCharacter)characterList.returnCharacter("Mr. Shim");
        NonPlayableCharacter MrTimmerman = (NonPlayableCharacter)characterList.returnCharacter("Mr. Timmerman");


        double random = Math.random();
        double random2 = Math.random();
        PlayableCharacter player;
        NonPlayableCharacter opponent;
        if (random < 0.25) {
            player = Feng;
        } else if (random < 0.5) {
            player = Joyce;
        } else if (random < 0.75) {
            player = Sihan;
        } else {
            player = Yash;
        }
        if (random2 < 0.33) {
            opponent = MrChoi;
        } else if (random2 < 0.66) {
            opponent = MrShim;
        } else {
            opponent = MrTimmerman;
        }
        System.out.println("A battle began between " + player.getName() + " and " + opponent.getName());
        System.out.println(opponent.getName() + " says " + opponent.getSpeech());
        Battle battle = new Battle(player, opponent, 1, 0);
        //do {
            System.out.println("What move would you like to use");
            for (int i=0; i<4; i++) {
                System.out.println(player.getMove(i).getName() + " " + player.getPowerPoints(i) + "/" + player.getMove(i).getMaxPowerPoints());
            }
        //} while(!battle.isBattleEnd());



        /*
        Move[] movesetFeng = new Move[4];
        movesetFeng[0] = new AttackMove("Steal Code", 40, 1.0, "Technology", "Intelligence", 15, 0, null);
        movesetFeng[1] = new HealthMove("Snort Candy", "English", 1, "Health", 40, 20, 0);
        movesetFeng[2] = new AttackMove("Throw Chair", 80, 0.75, "Neutral", "Attack", 10, 0, null);
        movesetFeng[3] = new AttackMove("Stack Overflow", 30, 1.0, "Technology", "Intelligence", 20, 0, null);
        Character Feng = new PlayableCharacter(50, 60, 20, 50, 20, "Technology", "Feng", "FatAss", movesetFeng, "Clown");

        Character MrShim = new NonPlayableCharacter(150, 160, 120, 10, 25, "Math", "Mr. Shim", "Let homework be your guide", movesetFeng, 0, "Demoralize");
        Battle mrShimBattle = new Battle((PlayableCharacter)Feng, (NonPlayableCharacter)MrShim, 1, 0);
        int moveFeng, moveShim;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Enter the move you want to use");
            boolean fengChooseMove = false;
            do {
                do {
                    moveFeng = input.nextInt();
                } while(moveFeng > 3 || moveFeng < 0);
                if (Feng.getPowerPoints(moveFeng) > 0) {
                    Feng.setPowerPoints(moveFeng, -1);
                    fengChooseMove = true;
                }
            } while (!fengChooseMove);
            moveShim = (int)Math.random()*3;
            System.out.println("Feng used " + movesetFeng[moveFeng].getName());
            System.out.println("Mr. Shim used " + movesetFeng[moveShim].getName());
            mrShimBattle.determineOrder(movesetFeng[moveFeng], movesetFeng[moveShim]);
            System.out.println("Feng has " + Feng.getCurrentHealth() + "/" + Feng.getInitialHealth() + " health left");
            System.out.println("Mr. Shim has " + MrShim.getCurrentHealth() + "/" + MrShim.getInitialHealth() + " health left");
            System.out.println("");
        } while (!mrShimBattle.isBattleEnd());
        */
    }
}
