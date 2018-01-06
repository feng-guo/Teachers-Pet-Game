package battling;

import java.util.ArrayList;

public class ListOfMoves {
    private ArrayList<Move> moveList;

    ListOfMoves () {
        moveList = new ArrayList<Move>();
        //neutral
        moveList.add(new AttackMove("Meter Stick", 65, 0.9, "Neutral", "Attack", 12, 1, null));
        moveList.add(new AttackMove("Throw Basketball", 30, 0.95,"Neutral", "Attack", 20, 2, null));
        moveList.add(new AttackMove("Throw Chair", 80, 0.75, "Neutral", "Attack", 10, 0, null));
        moveList.add(new StatChangeMove("Call Uber", 1.0, "Neutral", 2, "Speed", 10, 0, "Self"));
        moveList.add(new StatChangeMove("Cram", 1.0, "Neutral", 2, "Intelligence", 5, 0, "Self"));
        moveList.add(new HealthMove("Cry", "Neutral", 0, "Health", -2, 5, 0));
        moveList.add(new HealthMove("Make Memes", "Neutral", 0, "Health", -2, 8, 0));
        moveList.add(new HealthMove("Snort Candy", "Neutral", 0, "Health", 40, 20, 0));
        moveList.add(new ProtectMove("Ghost", 1.0, "Neutral", 10, 5));
        moveList.add(new ProtectMove("Dodge Homework", 1.0, "Neutral", 15, 5));
        //math
        moveList.add(new AttackMove("Spam Calculator", 50, 1.0, "Math", "Attack", 15, 0, null));
        moveList.add(new HealthMove("Calculate Mark", "Math", 0, "Health", -2, 8, 0));
        moveList.add(new StatChangeMove("Mark Test", 1.0, "Math", 2, "Speed", 20, 0, "Self"));
        moveList.add(new StatChangeMove("Math Angels", 1.0, "Math", 2, "Intelligence", 5, 0, "Self"));
        moveList.add(new AttackMove("Complete the Square", 40, 1.0, "Math", "Intelligence", 15, 0, null));
        moveList.add(new AttackMove("Divisibility Test", 85, 1.0, "Math", "Intelligence", 8, 0, null));
        moveList.add(new AttackMove("Olympiads Question", 60, 0.95, "Math", "Intelligence", 15, 0, null));
        moveList.add(new AttackMove("Practice Set", 70, 1.0, "Math", "Intelligence", 10, 0, null));
        moveList.add(new AttackMove("Quadratic Formula", 40, 0.99, "Math", "Intelligence", 20, 0, null));
        moveList.add(new AttackMove("Quick Maths", 20, 0.95, "Math", "Intelligence", 15, 3, null));
        moveList.add(new AttackMove("Trigonometry Test", 95, 0.95, "Math", "Intelligence", 10, 0, null));
        //english
        moveList.add(new AttackMove("Rip Essay", 60, 1.0, "English", "Attack", 15, 0, null));
        moveList.add(new AttackMove("Argue", 70, 0.95, "English", "Intelligence", 10, 0, null));
        moveList.add(new AttackMove("Complain", 80, 1.0, "English", "Intelligence", 15, 0, null));
        moveList.add(new AttackMove("Debate", 85, 1.0, "English", "Intelligence", 10, 0, null));
        moveList.add(new StatusMove("Life Lesson", 0.85, "English", "Sleep", 15, 0, "Opponent")); //someone review the last two STRINGS
        //science
        moveList.add(new StatChangeMove("TLAP", 1.0, "Science", 2, "Intelligence", 10, 0, "Self"));
        moveList.add(new AttackMove("Chemistry Pun", 40, 1.0, "Science", "Intelligence", 15, 0, null));
        //technology
        moveList.add(new StatChangeMove("Implement APIs", 1.0, "Technology", 2, "Speed", 8, 0, "Self"));
        moveList.add(new AttackMove("Deoptimize Code", 50, 1.0, "Technology", "Intelligence", 10, 0, null));
        moveList.add(new AttackMove("Stack Overflow", 30, 1.0, "Technology", "Intelligence", 20, 0, null));
        moveList.add(new AttackMove("Steal Code", 40, 0.95, "Technology", "Intelligence", 15, 0, null));
    }

    public Move retrieveMove(String name) {
        for (int i=0; i<moveList.size(); i++) {
            if (name.equals(moveList.get(i).getName())) {
                return moveList.get(i);
            }
        }
        return null;
    }
}
