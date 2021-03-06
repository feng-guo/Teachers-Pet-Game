package characters;

import battle.*;
import game.Handler;
import graphics.Assets;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ListOfCharacters{
    private ArrayList<Character> characterList;
    private Handler handler;
    private ListOfMoves startMoves;

    public ListOfCharacters(Handler handler) {
    		this.handler = handler;
        startMoves = new ListOfMoves();
        characterList = new ArrayList<Character>();
        //Movesets for playable characters
        String[] fengMoveNames = {"Steal Code", "Throw Chair", "Stack Overflow", "Snort Candy"};
        String[] joyceMoveNames = {"Cram", "Throw Basketball", "Quick Maths", "Call Uber"};
        String[] sihanMoveNames = {"Argue", "Cram", "Cry", "Ghost"};
        String[] yashMoveNames = {"Quadratic Formula", "Chemistry Pun", "Splash Acid", "TLAP"};
        //String[] mishaMoveNames = {"Stack Overflow", "Argue", "Cry", "Write Garbage Code"};
        String[] mishaMoveNames = {"Stack Overflow", "Sleep Talk", "Cry", "Write Garbage Code"};
        String[] johannMoveNames = {"Spam Calculator", "Quick Maths", "Work Out", "Not Study"};
        String[] angelaMoveNames = {"Dissect Frog", "Drink Bubble Tea", "Splash Acid", "Chemistry Pun"};
        Move[] fengMoves = returnMoveArray(fengMoveNames);
        Move[] joyceMoves = returnMoveArray(joyceMoveNames);
        Move[] sihanMoves = returnMoveArray(sihanMoveNames);
        Move[] yashMoves = returnMoveArray(yashMoveNames);
        Move[] mishaMoves = returnMoveArray(mishaMoveNames);
        Move[] johannMoves = returnMoveArray(johannMoveNames);
        Move[] angelaMoves = returnMoveArray(angelaMoveNames);

        //Movesets for nonplayable characters
        String[] mrChoiMoveNames = {"Trigonometry Test", "Olympiads Question", "Mark Test", "Quadratic Formula"};
        String[] mrShimMoveNames = {"Trigonometry Test", "Practice Set", "Life Lesson", "Meter Stick"};
        String[] mrTimmermanMoveNames = {"Divisibility Test", "Quadratic Formula", "Complete the Square", "Math Angels"};
        String[] randomNinerMoveNames = {"Not Study", "Flex", "Swear", "Complain"};
        String[] alstonMoveNames = {"Trigonometry Test", "Practice Set", "Chemistry Pun", "Splash Acid"};
        String[] michaelMoveNames = {"Dodge Homework", "Math Angels", "Stack Overflow", "Dissect Frog"};
        String[] msKostanenkoMoveNames = {"Splash Acid", "Teach Stoichiometry", "Titration", "Mark Test"};
        String[] rosemaryMoveNames = {"Cry", "Dissect Frog", "Drink Bubble Tea", "Splash Acid"};
        String[] mrGissingMoveNames = {"Greet", "Clap", "TLAP", "Meter Stick"};
        String[] nikhilMoveNames = {"Cry", "Debate", "Electrocute Paperclip", "Play Clarinet"};
        String[] aaronMoveNames = {"Play Saxophone", "Write Garbage Code", "Burn Piano Book", "Greet"};
        String[] mrHarrisMoveNames = {"Balls Away", "Flex", "Work Out", "Throw Basketball"};

        Move[] mrChoiMoves = returnMoveArray(mrChoiMoveNames);
        Move[] mrShimMoves = returnMoveArray(mrShimMoveNames);
        Move[] mrTimmermanMoves = returnMoveArray(mrTimmermanMoveNames);
        Move[] randomNinerMoves = returnMoveArray(randomNinerMoveNames);
        Move[] alstonMoves = returnMoveArray(alstonMoveNames);
        Move[] michaelMoves = returnMoveArray(michaelMoveNames);
        Move[] msKostanenkoMoves = returnMoveArray(msKostanenkoMoveNames);
        Move[] rosemaryMoves = returnMoveArray(rosemaryMoveNames);
        Move[] mrGissingMoves = returnMoveArray(mrGissingMoveNames);
        Move[] nikhilMoves = returnMoveArray(nikhilMoveNames);
        Move[] aaronMoves = returnMoveArray(aaronMoveNames);
        Move[] mrHarrisMoves = returnMoveArray(mrHarrisMoveNames);


        //List of playable characters
        characterList.add(new PlayableCharacter(50, 60, 35, 50, 60, "Technology", "Feng", "[description]", fengMoves, "Clown", null, null, null, null, null, Assets.feng_down));
        characterList.add(new PlayableCharacter(25, 25, 70, 20, 60, "Math", "Joyce", "[description]", joyceMoves, "Persistent", null, null, null, null, null, Assets.joyce_down));
        characterList.add(new PlayableCharacter(20, 20, 65, 60, 35, "English", "Sihan", "[description]", sihanMoves, "Distressed", null, null, null, null, null, Assets.sihan_down));
        
        characterList.add(new PlayableCharacter(30, 40, 45, 70, 30, "Science", "Yash", "[description]", yashMoves, "Relieve", null, null, null, null, null, Assets.yash_down));
        characterList.add(new PlayableCharacter(80, 60, 80, 10, 70, "Math", "Johann", "[description]", johannMoves, "Extreme Luck", null, null, null, null, null, Assets.johann_down));
        characterList.add(new PlayableCharacter(30, 40, 50, 40, 40, "Technology", "Misha", "[description]", mishaMoves, "Avoidant", null, null, null, null, null, Assets.misha_down));
        characterList.add(new PlayableCharacter(35, 40, 65, 75, 35, "Science", "Angela", "[description]", angelaMoves, "Protective", null, null, null, null, null, Assets.angela_down));
        //List of nonplayable characters
        
        BufferedImage[] opponentSprites = Assets.bill_down;
        try {
        		opponentSprites = handler.getGame().getCurrentOpponentSprites().get(1);
        } catch (NullPointerException e) {
        	
        }

        
        characterList.add(new NonPlayableCharacter(60, 30, 110, 70, 230, "Math", "Mr.Choi", "Perabler is the name of the game..", mrChoiMoves, 100, "Speed Boost", null, opponentSprites));
        characterList.add(new NonPlayableCharacter(300, 50, 100, 30, 20, "Math", "Mr.Shim", "Let homework be your guide", mrShimMoves, 100, "Demoralize", null, opponentSprites));
        characterList.add(new NonPlayableCharacter(200, 40, 100, 60, 100, "Math", "Mr.Timmerman", "Focus isn't just the name of a car..", mrTimmermanMoves, 100, "Unaware", null, opponentSprites));
        characterList.add(new NonPlayableCharacter(20, 30, 30, 30, 30, "Neutral", "Random Niner", "Ahlieeeee faaammm...", randomNinerMoves, 200, "Annoying", null, opponentSprites));
        characterList.add(new NonPlayableCharacter(40, 20, 60, 20, 30, "Science", "Alston", "Always protect senpai", alstonMoves, 200, "Osmosis", null, opponentSprites));
        characterList.add(new NonPlayableCharacter(60, 70, 80, 10, 50, "Technology", "Michael", "My Rattata is better than your Rattata", michaelMoves, 200, "Persistent", null, opponentSprites));
        characterList.add(new NonPlayableCharacter(200, 40, 90, 40, 60, "Science", "Ms.Kostanenko", "Wake up people", msKostanenkoMoves, 300, "Demoralize", null, opponentSprites));
        characterList.add(new NonPlayableCharacter(50, 40, 90, 40, 60, "Neutral", "Rosemary", "What you do means more than what you say.", rosemaryMoves, 300, "Distressed", null, opponentSprites));
        characterList.add(new NonPlayableCharacter(150, 70, 40, 50, 60, "Science", "Mr.Gissing", "Hi everyone", mrGissingMoves, 300, "Friendly", null, opponentSprites));
        characterList.add(new NonPlayableCharacter(40, 60, 40, 50, 80, "English", "Nikhil", "I'm vegan", nikhilMoves, 100, "Clown", null, opponentSprites));
        characterList.add(new NonPlayableCharacter(30, 20, 20, 80, 70, "Technology", "Aaron", "Do u kno da wae", aaronMoves, 100, "Friendly", null, opponentSprites));
        characterList.add(new NonPlayableCharacter(500, 10, 10, 100, 10, "Neutral", "Mr.Harris", "Balls away", mrHarrisMoves, 200, "Power Boost", null, opponentSprites));
    }

    private Move[] returnMoveArray(String[] moveNames) {
        Move[] moveArray = new Move[4];
        for (int i=0; i<4; i++) {
            moveArray[i] = startMoves.retrieveMove(moveNames[i]);
        }
        return moveArray;
    }

    public Character returnCharacter(String name) {
        for(int i=0; i<characterList.size(); i++) {
            if (name.equals(characterList.get(i).getName())) {
                return characterList.get(i);
            }
        }
        return null;
    }
}
