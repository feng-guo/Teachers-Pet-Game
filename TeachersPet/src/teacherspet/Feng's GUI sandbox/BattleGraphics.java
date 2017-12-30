import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
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
        battleScreen.setLayout(new BorderLayout());
        battleScreen.setVisible(true);
        battleScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        battleScreen.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        battleScreen.setVisible(true);
    }

    private class BattleGraphicsPane extends JPanel {
        public void paintComponent(Graphics g) {

        }
    }
}

