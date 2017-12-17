/* Please ignore this file  
 * Testing file for battles
 * So very cool wow
 * And it works too ;)
 */
import java.util.Scanner;

public class BattleTester {
    public static void main(String[] args) {
        Move[] movesetFeng = new Move[4];
        movesetFeng[0] = new AttackMove("Steal Code", 40, 1.0, "Technology", "Intelligence");
        movesetFeng[1] = new HealthMove("Snort Candy", "English", 1, "Health", 40);
        movesetFeng[2] = new AttackMove("Throw Chair", 80, 0.75, "Neutral", "Attack");
        movesetFeng[3] = new AttackMove("Stack Overflow", 50, 1.0, "Technology", "Intelligence");
        Character Feng = new PlayableCharacter(50, 60, 20, 50, 20, "Technology", "Feng", "FatAss", movesetFeng);

        movesetFeng[0] = new AttackMove("Steal Code", 40, 1.0, "Technology", "Intelligence");
        movesetFeng[1] = new HealthMove("Snort Candy", "English", 1, "Health", 40);
        movesetFeng[2] = new AttackMove("Throw Chair", 80, 0.75, "Neutral", "Attack");
        movesetFeng[3] = new AttackMove("Stack Overflow", 50, 1.0, "Technology", "Intelligence");
        Character MrShim = new NonPlayableCharacter(150, 160, 120, 10, 25, "Math", "Mr. Shim", "Let homework be your guide", movesetFeng, 0);
        Battle mrShimBattle = new Battle((PlayableCharacter)Feng, (NonPlayableCharacter)MrShim, 1, 0);
        int moveFeng, moveShim;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Enter the move you want to use");
            moveFeng = input.nextInt();
            moveShim = (int)Math.random()*4;
            System.out.println("Feng used " + movesetFeng[moveFeng].getName());
            System.out.println("Mr. Shim used " + movesetFeng[moveShim].getName());
            mrShimBattle.determineOrder(movesetFeng[moveFeng], movesetFeng[moveShim]);
            System.out.println("Feng has " + Feng.getCurrentHealth() + "/" + Feng.getInitialHealth() + " health left");
            System.out.println("Mr. Shim has " + MrShim.getCurrentHealth() + "/" + MrShim.getInitialHealth() + " health left");
            System.out.println("");
        } while (!mrShimBattle.isBattleEnd());
    }
}
