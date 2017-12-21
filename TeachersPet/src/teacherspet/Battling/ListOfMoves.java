import java.util.ArrayList;

public class ListOfMoves {
    private ArrayList<Move> moveList;

    ListOfMoves () {
        moveList.add(new AttackMove("Throw Basketball", 30, 0.95,"Neutral", "Attack", 20, 2, null));
        moveList.add(new AttackMove("Throw Chair", 80, 0.75, "Neutral", "Attack", 10, 0, null));
        moveList.add(new StatChangeMove("Call Uber", 1.0, "Neutral", 2, "Speed", 10, 0, "Self"));
        moveList.add(new StatChangeMove("Cram", 1.0, "Neutral", 2, "Intelligence", 5, 0, "Self"));
        moveList.add(new HealthMove("Cry", "Neutral", 0, "Health", -2, 5, 0));
        moveList.add(new HealthMove("Make Memes", "Neutral", 0, "Health", -2, 8, 0));
        moveList.add(new HealthMove("Snort Candy", "Neutral", 0, "Health", 40, 20, 0));
        moveList.add(new AttackMove("Spam Calculator", 50, 1.0, "Math", "Attack", 15, 0, null));
        moveList.add(new HealthMove("Calculate Mark", "Math", 0, "Health", -2, 8, 0));
        moveList.add(new StatChangeMove("Mark Test", 1.0, "Math", 2, "Speed", 20, 0, "Self"));
        //moveList.add(new )
    }

    public Move retrieveMove(String name) {
        for (int i=0; i<moveList.size(); i++) {
            if (moveList.get(i).getName().equals(name)) {
                return moveList.get(i);
            }
        }
        return null;
    }
}
