import java.util.ArrayList;

class ListOfCharacters{
    ArrayList<Character> characterList;

    ListOfCharacters() {
        Move[] Feng = {"Steal Code", "Throw Chair", "Stack Overflow", "Snort Candy"};
        Move[] Joyce = {"Cram", "Throw Basketball", "Quick Maths", "Call Uber"};
        Move[] Sihan = {"Argue", "Cram", "Cry", "Ghost"};
        Move[] Yash = {"Quadratic Formula", "Chemistry Pun", "Dodge Homework", "TLAP"};
        //characterList.add(new PlayableCharacter());
        characterList.add(new PlayableCharacter(50, 60, 20, 50, 60, "Technology", "Feng", "[description]", Feng, "Clown"));
        characterList.add(new PlayableCharacter(25, 25, 70, 20, 60, "Math", "Joyce", "[description]", Joyce, "Persistent"));
        characterList.add(new PlayableCharacter(20, 20, 65, 60, 35), "English", "Sihan", "[description]", Sihan, "Distressed");
        characterList.add(new PlayableCharacter(30, 40, 30, 70, 30), "Science", "Yash", "[description]", Yash, "Relieve");
    }
}
