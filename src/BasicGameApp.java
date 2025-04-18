import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BasicGameApp implements Runnable { // Implements Runnable and KeyListener { //will add KeyListener

    // Variable definition section
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    // Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;
    public Image UllmarkPic, CoylePic, CarloPic, FreddyPic, MarchandPic;

    // Declare the objects for players
    private Players Ullmark, Coyle, Carlo, Freddy, Marchand;

    // Main method definition
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   // creates a new instance of the game
        new Thread(ex).start();                 // starts the game in a new thread
    }

    // Constructor Method
    public BasicGameApp() {
        setUpGraphics();// Sets up the graphics

        // Load the player images
        UllmarkPic = Toolkit.getDefaultToolkit().getImage("POOR Ullmark.png");
        MarchandPic = Toolkit.getDefaultToolkit().getImage("POOR POOR MARCHAND.png");
        CoylePic = Toolkit.getDefaultToolkit().getImage("poor coyle.png");
        CarloPic = Toolkit.getDefaultToolkit().getImage("poor carlo.png");
        FreddyPic = Toolkit.getDefaultToolkit().getImage("poor freddy.png");


        // Create players
        Ullmark = new Players(100, 100, UllmarkPic);
        Coyle = new Players(200, 100, CoylePic);
        Carlo = new Players(300, 100, CarloPic);
        Freddy = new Players(400, 100, FreddyPic);
        Marchand = new Players(500, 100, MarchandPic);
    }

    public void moveThings() {

    }



    // Main thread
    public void run() {
        while (true) {
            render();  // Paint the graphics
            moveThings();
            pause(20);  // Pause for 20 ms
        }
    }

    // Pause method
    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    // Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Player Display");  // Create the program window
        panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // Add the canvas to the panel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
    }

    // Method to render the game objects on the screen
    private void render() {

        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // Render each player
        if (Ullmark.isAlive)
            g.drawImage(Ullmark.image, Ullmark.xpos, Ullmark.ypos, Ullmark.width, Ullmark.height, null);


        if (Coyle.isAlive)
            g.drawImage(Coyle.image, Coyle.xpos, Coyle.ypos, Coyle.width, Coyle.height, null);

        if (Carlo.isAlive)
            g.drawImage(Carlo.image, Carlo.xpos, Carlo.ypos, Carlo.width, Carlo.height, null);

        if (Freddy.isAlive)
            g.drawImage(Freddy.image, Freddy.xpos, Freddy.ypos, Freddy.width, Freddy.height, null);

        if (Marchand.isAlive)
            g.drawImage(Marchand.image, Marchand.xpos, Marchand.ypos, Marchand.width, Marchand.height, null);

        g.dispose();
        bufferStrategy.show();
    }
}
