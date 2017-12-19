package src;

import java.util.ArrayList;

public class ListOfMoves {
    private ArrayList<Move> moveList;

    ListOfMoves () {
        moveList.add(new AttackMove("Throw Basketball", 30, 0.95,"Neutral", "Attack", 20, 2));
        moveList.add(new AttackMove("Throw Chair", 80, 0.75, "Neutral", "Attack", 10, 0));
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
