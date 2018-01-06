package battleFiles;

import java.util.ArrayList;

public class Squad {
    ArrayList<PlayableCharacter> squad;
    int numberOfFaintedStudents;

    Squad (PlayableCharacter[] squadMembers) {
        this.numberOfFaintedStudents = 0;
        squad = new ArrayList<>();
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

    public boolean removeMember (int index) {
        if (index < 6 && index>-1) {
            squad.remove(index);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeMember (String name) {
        for (int i=0; i<6; i++) {
            if (name.equals(squad.get(i).getName())) {
                squad.remove(i);
                return true;
            }
        }
        return false;
    }

    public PlayableCharacter getCharacter(int index) {
        return squad.get(index);
    }

    public int getSize() {
        return squad.size();
    }

    public int getNumberOfFaintedStudents() {
        int numberOfFaintedStudents = 0;
        for (int i=0; i<squad.size(); i++) {
            if (squad.get(i).getCurrentHealth() == 0) {
                numberOfFaintedStudents++;
            }
        }
        return numberOfFaintedStudents;
    }

    public void displaySquad () {
        for (int i=0; i<squad.size(); i++) {
            System.out.println(squad.get(i).getName() + " " + squad.get(i).getCurrentHealth() + "/" + squad.get(i).getInitialHealth());
        }
    }

}
