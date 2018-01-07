package battle;//import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Nothing here works
class KeyBoardListener implements KeyListener{

    public void keyTyped(KeyEvent e) {
    }


    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));

        if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {  //If 'D' is pressed
            System.out.println("YIKES D KEY!");
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {  //If ESC is pressed
            System.out.println("YIKES ESCAPE KEY!"); //close frame & quit
        }
    }


    public void keyReleased(KeyEvent e) {
    }

}
