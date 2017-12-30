import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.GridBagLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.Graphics;

public class BattleGraphics {
    private JFrame battleScreen;
    private int screenX;
    private int screenY;

    BattleGraphics() {
        battleScreen = new JFrame();
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
        public void paintComponent(Graphics g) {

        }
    }
}

