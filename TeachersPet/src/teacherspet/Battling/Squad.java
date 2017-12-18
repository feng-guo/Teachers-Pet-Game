package Battling;

import java.util.ArrayList;

public class Squad {
    ArrayList<PlayableCharacter> squad;

    Party(PlayableCharacter[] squadMembers) {
        for (int i=0; i<squadMembers.length; i++) {
            squad.add(squadMembers[i]);
        }
    }

    public boolean addMember(PlayableCharacter newMember) {
        if (squad.size() < 7) {
            squad.add(newMember);
        }
    }
}
