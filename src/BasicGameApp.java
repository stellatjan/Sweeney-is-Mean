import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
public class BasicGameApp implements Runnable {

    final int WIDTH = 1000;
    final int HEIGHT = 700;
    int frameCount = 0;

    public int score;


    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;
    public Image UllmarkPic, CoylePic, CarloPic, FreddyPic, MarchandPic;
    //make background array: Carlo:Sweeney.png , Coyle:Sweeney.png , Freddy:Sweeney.png , Marchand:Sweeney.png , Ullmark.Sweeney.png (when a background score=alie and all charachters =not alive).
    //want to add a score bar every second you are alive +1 point (public int score) -- tried to put this in render did not work:   //g.drawString(score,100,100, WIDTH, HEIGHT);
    //want to make enter speed boost 1 second max (so like if dx or dy =15/-15 for more than 1 second then dx/dy=7/-7


    private Players Ullmark, Coyle, Carlo, Freddy, Marchand;
    public Sweeney sweeney;
    public Image SweeneyPic;

    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();
        new Thread(ex).start();
    }

    public BasicGameApp() {
        setUpGraphics();

        SweeneyPic = Toolkit.getDefaultToolkit().getImage("The \"GM\".png");
        sweeney = new Sweeney(400, 300, SweeneyPic);
        canvas.addKeyListener(sweeney);

        // Load images
        UllmarkPic = Toolkit.getDefaultToolkit().getImage("POOR Ullmark.png");
        MarchandPic = Toolkit.getDefaultToolkit().getImage("POOR POOR MARCHAND.png");
        CoylePic = Toolkit.getDefaultToolkit().getImage("poor coyle.png");
        CarloPic = Toolkit.getDefaultToolkit().getImage("poor carlo.png");
        FreddyPic = Toolkit.getDefaultToolkit().getImage("poor freddy.png");

// Create players with random positions using Math.random()
        // Use placeholder width and height values
        int defaultWidth = 60;
        int defaultHeight = 80;

        // Create players with random positions and print starting positions to ensure they are random
        Ullmark = new Players((int)(Math.random() * (WIDTH - defaultWidth)), (int)(Math.random() * (HEIGHT - defaultHeight)), UllmarkPic);
        System.out.println("Ullmark x: " + Ullmark.xpos + ", y: " + Ullmark.ypos);

        Coyle = new Players((int)(Math.random() * (WIDTH - defaultWidth)), (int)(Math.random() * (HEIGHT - defaultHeight)), CoylePic);
        System.out.println("Coyle x: " + Coyle.xpos + ", y: " + Coyle.ypos);

        Carlo = new Players((int)(Math.random() * (WIDTH - defaultWidth)), (int)(Math.random() * (HEIGHT - defaultHeight)), CarloPic);
        System.out.println("Carlo x: " + Carlo.xpos + ", y: " + Carlo.ypos);

        Freddy = new Players((int)(Math.random() * (WIDTH - defaultWidth)), (int)(Math.random() * (HEIGHT - defaultHeight)), FreddyPic);
        System.out.println("Freddy x: " + Freddy.xpos + ", y: " + Freddy.ypos);

        Marchand = new Players((int)(Math.random() * (WIDTH - defaultWidth)), (int)(Math.random() * (HEIGHT - defaultHeight)), MarchandPic);
        System.out.println("Marchand x: " + Marchand.xpos + ", y: " + Marchand.ypos);
    }

    public void moveThings() {

        Ullmark.wrap(700, 1000);

        Coyle.bounce(700, 1000);

        Carlo.wrap(700, 1000);

        Freddy.bounce(700, 1000);

        Marchand.wrap(700, 1000);

        sweeney.sweeneywrap(700,1000);
    }

    public void run() {
        while (true) {
            render();        // Render the graphics
            moveThings();    // Move the players

            frameCount++;    // Increment the frame count

            if (frameCount == 1000) { // Every ~20 seconds
                System.out.println("Speed increased");

                // Increase speed but ensure max speed is 10
                Ullmark.dx = Math.min(Ullmark.dx + 1, 10);
                Ullmark.dy = Math.min(Ullmark.dy + 1, 10);

                Coyle.dx = Math.min(Coyle.dx + 1, 10);
                Coyle.dy = Math.min(Coyle.dy + 1, 10);

                Carlo.dx = Math.min(Carlo.dx + 1, 10);
                Carlo.dy = Math.min(Carlo.dy + 1, 10);

                Freddy.dx = Math.min(Freddy.dx + 1, 10);
                Freddy.dy = Math.min(Freddy.dy + 1, 10);

                Marchand.dx = Math.min(Marchand.dx + 1, 10);
                Marchand.dy = Math.min(Marchand.dy + 1, 10);

                frameCount = 0; // Reset counter
            }

            pause(20); // Pause for 20 ms before the next frame
        }
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    private void setUpGraphics() {
        frame = new JFrame("Player Display");
        panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
    }

    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        //g.drawString(score,100,100, WIDTH, HEIGHT);


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


        if (sweeney.isAlive)
            g.drawImage(sweeney.image, sweeney.xpos, sweeney.ypos, sweeney.width, sweeney.height, null);

        g.dispose();
        bufferStrategy.show();
    }
}
