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

    public void sweeneywrap(int screenWidth, int screenHeight) {
        xpos += dx;
        ypos += dy;
        if (xpos > screenWidth) xpos = 0;
        if (xpos < 0) xpos = screenWidth;
        if (ypos > screenHeight) ypos = 0;
        if (ypos < 0) ypos = screenHeight;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == 87 || key == 38) { // W or Up arrow
            dy = -7;
        }
        if (key == 83 || key == 40) { // S or Down arrow
            dy = 7;
        }
        if (key == 65 || key == 37) { // A or Left arrow
            dx = -7;
        }
        if (key == 68 || key == 39) { // D or Right arrow
            dx = 7;
        }

        //enter key = speed boost
        if(key==10 && dx==-7){
            dx = -15;

        }

        if(key==10 && dx==7) {
            dx = 15;


        }

        if(key==10 && dy==-7){
           dy=-15;
        }

        if(key==10 && dy==7){
            dy=15;
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
        if (key==10){
            dx=0;
        }
        if (key==10){
            dy=0;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not needed
    }
}
