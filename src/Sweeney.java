import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Sweeney implements KeyListener {
    public int xpos, ypos, dx, dy;
    public int width = 60;
    public int height = 80;
    public boolean isAlive = true;
    public Image image;

    private long boostStartTime = 0; // Will store the time when the boost started SOURCE:https://www.w3schools.com/java/ref_keyword_long.asp
    private boolean isBoosting = false; // To track if boost is active
    private long boostEndTime;
    private boolean canBoost = true; // Only true if boost cooldown is over



    public Rectangle rec;


    public Sweeney(int x, int y, Image img) {
        xpos = x;
        ypos = y;
        image = img;
        dx = 0;
        dy = 0;
        rec = new Rectangle(xpos, ypos, width, height);
    }

    public void sweeneywrap(int screenWidth, int screenHeight) {
        xpos += dx;
        ypos += dy;
        if (xpos > screenWidth) xpos = 0;
        if (xpos < 0) xpos = screenWidth;
        if (ypos > screenHeight) ypos = 0;
        if (ypos < 0) ypos = screenHeight;
        rec.setLocation(xpos,ypos);
    }

    public void update() {
        long currentTime = System.currentTimeMillis();

        if (isBoosting) {
            long boostDuration = currentTime - boostStartTime;
            if (boostDuration >= 2000) { // Boost lasts 2 seconds
                if (dx == 15 || dx == -15) {
                    dx = (dx < 0) ? -7 : 7;
                }
                if (dy == 15 || dy == -15) {
                    dy = (dy < 0) ? -7 : 7;
                }
                isBoosting = false;
                boostEndTime = currentTime;
                canBoost = false;
                System.out.println("Boost ended after " + boostDuration + "ms");
            }
        }

        // Check if 5 seconds have passed since the boost ended
        if (!canBoost && currentTime - boostEndTime >= 5000) {
            canBoost = true;
            System.out.println("Boost is ready again");

        }
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
        //speed boost activation
        if (key == 10) { // Enter key
            if (!isBoosting) {
                boostStartTime = System.currentTimeMillis(); // Record when boost starts
                isBoosting = true;
            }

            if (dx == -7) {
                dx = -15;
            }

            if (dx == 7) {
                dx = 15;
            }

            if (dy == -7) {
                dy = -15;
            }

            if (dy == 7) {
                dy = 15;
            }
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
