import java.util.ArrayList;

public class Squad {
    ArrayList<PlayableCharacter> squad;
    int numberOfFaintedStudents;

    Squad (PlayableCharacter[] squadMembers) {
        this.numberOfFaintedStudents = 0;
        for (int i=0; i<squadMembers.length; i++) {
            squad.add(squadMembers[i]);
            if (squadMembers[i].getCurrentHealth() == 0) {
                numberOfFaintedStudents++;
            }
        }
    }

    public boolean addMember(PlayableCharacter newMember) {
        if (squad.size() < 7) {
            squad.add(newMember);
            return true;
        }
        return false;
    }

    public void removeMember (int index) {
        squad.remove(index);
    }

    public int getNumberOfFaintedStudents() {
        return numberOfFaintedStudents;
    }

    public void displaySquad () {
        //Code to display a squad
    }

}
