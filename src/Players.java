import java.awt.*;

public class Players {
    // Variable definition section
    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isAlive;

    public Rectangle rec;
    public Image image;

    // Constructor
    public Players(int x, int y, Image img) {
        xpos = x;
        ypos = y;
        dx = 0;
        dy = 0;
        width = 60;
        height = 60;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);
        image = img;  // Set image here
    }

    // Move method
    public void move() {
        xpos += dx;
        ypos += dy;
        rec.setLocation(xpos, ypos);  // Update rectangle position
    }

    // Wrap method
    public void wrap(int screenWidth, int screenHeight) {
        if (xpos > screenWidth) {
            xpos = 0;
        }
        if (xpos < 0) {
            xpos = screenWidth;
        }
        if (ypos > screenHeight) {
            ypos = 0;
        }
        if (ypos < 0) {
            ypos = screenHeight;
        }
        rec.setLocation(xpos, ypos);  // Update rectangle position after wrap
    }

    // Bounce method
    public void bounce(int screenWidth, int screenHeight) {
        if (xpos <= 0 || xpos + width >= screenWidth) {
            dx = -dx;
        }
        if (ypos <= 0 || ypos + height >= screenHeight) {
            dy = -dy;
        }
    }

    // Render method
    public void render(Graphics2D g) {
        g.drawImage(image, xpos, ypos, width, height, null);
    }
}
