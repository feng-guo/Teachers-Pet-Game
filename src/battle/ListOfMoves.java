package battle;

import java.util.ArrayList;

public class ListOfMoves {
    private ArrayList<Move> moveList;

    public ListOfMoves () {
        moveList = new ArrayList<Move>();
        //Basic moves
        Move selfSleep = new StatusMove("SelfSleep", 4.0, "Neutral", "Sleep", 100, 0, "Self");
        Move opponentSleep = new StatusMove("OpponentSleep", 4.0, "Neutral", "Sleep", 100, 0, "Opponent");
        Move randomBurn50 = new StatusMove("randomBurn50", 0.5, "Neutral", "Burn", 100, 0, "Random"); //Code in the random!!
        Move opponentBurn75 = new StatusMove("opponentBurn75", 0.75, "Neutral", "Burn", 100, 0, "Opponent");
        Move doubleUserDefence = new StatChangeMove("doubleUserDefence", 4.0, "Neutral", 2, "Defence", 100, 0, "Self");
        Move doubleUserIntelligence = new StatChangeMove("doubleUserIntelligence", 4.0, "Neutral", 2, "Intelligence", 100, 0, "Self");
        Move doubleUserSpeed = new StatChangeMove("doubleUserSpeed", 4.0, "Neutral", 2, "Speed", 100, 0, "Self");
        Move doubleUserIntelligence10 = new StatChangeMove("doubleUserIntelligence10", 0.1, "Neutral", 2, "Intelligence", 100, 0, "Self");
        Move halveOpponentDefence10 = new StatChangeMove("halveOpponentDefence10", 0.1, "Neutral", 2, "Defence", 100, 0, "Opponent");
        Move halveOpponentIntelligence10 = new StatChangeMove("halveOpponentIntelligence10", 0.1, "Neutral", 2, "Intelligence", 100, 0, "Opponent");
        Move opponentPoison10 = new StatusMove("opponentPoison10", 0.1, "Science", "Poison", 100, 0, "Opponent");
        Move opponentPoison30 = new StatusMove("opponentPoison10", 0.3, "Science", "Poison", 100, 0, "Opponent");
        Move opponentStun70 = new StatusMove("opponentStun70", 0.7, "Neutral", "Stun", 100, 0, "Opponent");
        //neutral
        moveList.add(new AttackMove("Burn Piano Book", 60, 1.0, "Neutral", "Attack", 12, 0, randomBurn50));
        moveList.add(new AttackMove("Explode Phone", 120, 0.8, "Neutral", "Attack", 10, 0, opponentBurn75));
        moveList.add(new AttackMove("Meter Stick", 65, 0.9, "Neutral", "Attack", 12, 1, null));
        moveList.add(new AttackMove("Throw Basketball", 30, 0.95,"Neutral", "Attack", 20, 2, null));
        moveList.add(new AttackMove("Throw Chair", 80, 0.75, "Neutral", "Attack", 10, 0, null));
        moveList.add(new StatChangeMove("Call Uber", 1.0, "Neutral", 2, "Speed", 10, 0, "Self"));
        moveList.add(new StatChangeMove("Cram", 1.0, "Neutral", 2, "Intelligence", 5, 0, "Self"));
        moveList.add(new StatChangeMove("Crash", 1.0, "Neutral", 2, "Attack", 10, 0, "Self", selfSleep));
        moveList.add(new StatChangeMove("Flex", 1.0, "Neutral", 2, "Attack", 10, 0, "Self", doubleUserDefence));
        moveList.add(new StatChangeMove("Not Study", 1.0, "Neutral", 2, "Intelligence", 10, 0, "Opponent", doubleUserIntelligence));
        moveList.add(new StatChangeMove("Work Out", 1.0, "Neutral", 2, "Attack", 20, 0, "Self", doubleUserSpeed));
        moveList.add(new HealthMove("Cry", "Neutral", 0, "Health", -1, 5, 0, selfSleep));
        moveList.add(new HealthMove("Make Memes", "Neutral", 0, "Health", -2, 8, 0, null));
        moveList.add(new HealthMove("Snort Candy", "Neutral", 0, "Health", 40, 20, 0, null));
        moveList.add(new ProtectMove("Ghost", 1.0, "Neutral", 10, 5));
        moveList.add(new ProtectMove("Dodge Homework", 1.0, "Neutral", 15, 5));
        moveList.add(new HealthMove("Drink Bubble Tea", "Neutral", 0, "Health", -2, 10, 0, null));
        moveList.add(new AttackMove("Clap", 40, 1.0, "Neutral", "Attack",  15, 3, doubleUserIntelligence10));
        moveList.add(new StatChangeMove("Greet", 1.0, "Neutral", 2, "Defence", 15, 0, "Opponent", null));
        moveList.add(new AttackMove("Play Clarinet", 20, 1.0, "Neutral", "Intelligence", 20, 0, doubleUserIntelligence));
        moveList.add(new AttackMove("Play Saxophone", 20, 1.0, "Neutral", "Intelligence", 20, 0, doubleUserIntelligence));
        //math
        moveList.add(new AttackMove("Spam Calculator", 50, 1.0, "Math", "Attack", 15, 0, null));
        moveList.add(new HealthMove("Calculate Mark", "Math", 0, "Health", -2, 8, 0, null));
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
        moveList.add(new AttackMove("Scream", 80, .95, "English", "Intelligence", 8, 0, halveOpponentDefence10));
        moveList.add(new AttackMove("Swear", 30, 0.95, "English", "Intelligence", 20, 0, null));
        moveList.add(new AttackMove("Write Reflection", 90, 1.0, "English", "Intelligence", 10, 0, halveOpponentIntelligence10));
        moveList.add(new StatusMove("Life Lesson", 0.85, "English", "Sleep", 15, 0, "Opponent")); //someone review the last two STRINGS
        moveList.add(new SleepTalkMove("Sleep Talk", "English", 15, 0));
        moveList.add(new StatChangeMove("Self Loathe", 1, "English", 2, "Attack", 10, 0, "Self", doubleUserDefence));
        //science
        moveList.add(new StatChangeMove("TLAP", 1.0, "Science", 2, "Intelligence", 10, 0, "Self"));
        moveList.add(new StatChangeMove("Teach Stoichiometry", 1.0, "Science", 2, "Intelligence", 12, 0, "Opponent", opponentSleep));
        moveList.add(new AttackMove("Chemistry Pun", 40, 1.0, "Science", "Intelligence", 15, 0, null));
        moveList.add(new AttackMove("Splash Acid", 80, 1.0, "Science", "Attack", 10, 0, opponentPoison10));
        moveList.add(new StatusMove("Dissect Frog", 0.9, "Science","Poison", 15, 0, "Opponent"));
        moveList.add(new AttackMove("Titration", 70,1.0, "Science", "Intelligence", 15, 0, opponentPoison30));
        moveList.add(new AttackMove("Electrocute Paperclip", 120, 0.7, "Science", "Attack", 10, 0, opponentStun70));
        //technology
        moveList.add(new StatChangeMove("Implement APIs", 1.0, "Technology", 2, "Speed", 8, 0, "Self"));
        moveList.add(new AttackMove("Deoptimize Code", 50, 1.0, "Technology", "Intelligence", 10, 0, null));
        moveList.add(new AttackMove("Stack Overflow", 30, 1.0, "Technology", "Intelligence", 20, 0, null));
        moveList.add(new AttackMove("Steal Code", 40, 0.95, "Technology", "Intelligence", 15, 0, null));
        moveList.add(new AttackMove("Write Garbage Code", 70, 1.0, "Technology", "Intelligence", 12, 0, halveOpponentIntelligence10));
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
