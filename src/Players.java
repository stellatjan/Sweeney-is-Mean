import java.awt.*;

public class Players {
    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isAlive;


    public Rectangle rec;
    public Image image;


    public Players(int x, int y, Image img) {
        xpos = x;
        ypos = y;
        dx = 3;
        dy = 3;
        width = 60;
        height = 60;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);
        image = img;
    }

    public void move() {
        xpos += dx;
        ypos += dy;
        rec.setLocation(xpos, ypos);
    }

    public void wrap(int screenWidth, int screenHeight) {
        move();
        if (xpos > screenWidth) xpos = 0;
        if (xpos < 0) xpos = screenWidth;
        if (ypos > screenHeight) ypos = 0;
        if (ypos < 0) ypos = screenHeight;
        rec.setLocation(xpos, ypos);
    }

    public void bounce(int screenWidth, int screenHeight) {
        move();

        // Check if the object hits the left or right edge
        if (xpos <= 0 || xpos + width >= screenWidth) {
            dx = -dx; // Reverse direction
            // Make sure xpos stays within bounds
            xpos = Math.max(0, Math.min(xpos, screenWidth - width));
        }

        // Check if the object hits the top or bottom edge
        if (ypos <= 0 || ypos + height >= screenHeight) {
            dy = -dy; // Reverse direction
            // Make sure ypos stays within bounds
            ypos = Math.max(0, Math.min(ypos, screenHeight - height));
        }

        // Update the rectangle's position
        rec.setLocation(xpos, ypos);
    }


}
