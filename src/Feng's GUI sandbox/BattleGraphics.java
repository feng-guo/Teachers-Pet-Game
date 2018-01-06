mport javax.swing.*; 
//import javax.swing.GridBagLayout;
import java.awt.*;

public class BattleGraphics {
    private JFrame battleScreen;
    private int screenX;
    private int screenY;

    BattleGraphics() {
        battleScreen = new JFrame("Battle");
        screenX = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenY = Toolkit.getDefaultToolkit().getScreenSize().height;
        battleScreen.setVisible(true);
        battleScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        battleScreen.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        battleScreen.setVisible(true);
        BattlePanel battleScreen = new BattlePanel();
        BattleInteractionsPanel battleInteractions = new BattleInteractionsPanel();
    }

    private class BattlePanel extends JPanel {
        public void paintComponent(Graphics g) {

        }
    }

    private class BattleInteractionsPanel extends JPanel {
        BattleInteractionsPanel() {
            setLayout(new GridLayout(2,2));
            add(new FightPanel());
        }

        private class FightPanel extends BattleInteractionsPanel {
            String[] moveList;
            JLabel[] moveListLabel;

            FightPanel() {
                moveList = new String[4];
                for (int i = 0; i < 4; i++) {
                    moveList[i] = "dick" + i;
                    moveListLabel[i] = new JLabel(moveList[i]);
                    add(moveListLabel[i]);
                }
                super.setLayout(new GridLayout(2, 2));
            }
        }
    }
}

