import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Sweeney implements KeyListener {

    public int xpos, ypos, dx, dy;
    public int width = 60;
    public int height = 80;
    public boolean isAlive = true;
    public Image image;

    public Sweeney(int x, int y, Image img) {
        xpos = x;
        ypos = y;
        image = img;
        dx = 0;
        dy = 0;
    }

    public void sweeneymoves() {
        xpos += dx;
        ypos += dy;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == 87 || key == 38) { // W or Up arrow
            dy = -5;
        }
        if (key == 83 || key == 40) { // S or Down arrow
            dy = 5;
        }
        if (key == 65 || key == 37) { // A or Left arrow
            dx = -5;
        }
        if (key == 68 || key == 39) { // D or Right arrow
            dx = 5;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == 87 || key == 83 || key == 38 || key == 40) { // W, S, Up, Down
            dy = 0;
        }
        if (key == 65 || key == 68 || key == 37 || key == 39) { // A, D, Left, Right
            dx = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not needed
    }
}
