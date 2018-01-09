package states;


import game.Handler;

public class UpdateBattleText extends BattleState {
    private String battleText;

    public UpdateBattleText(Handler handler) {
        super(handler, "s");
        battleText = "foobar";
    }

    public void updateString(String s) {
        this.battleText = s;
        //tick();
        //render(g);
    }
    public void updateString(String s, int one, int two) {
        this.battleText = s;
        tick();
    }

    public String getBattleText() {
        return battleText;
    }
}