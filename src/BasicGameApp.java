import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
public class BasicGameApp implements Runnable {

    final int WIDTH = 1000;
    final int HEIGHT = 700;
    int frameCount = 0;

    public int score;
    int scoreTimer = 0; // counts frames to track time

    // background[i] == true means Sweeney collided with that character
    boolean[] background = new boolean[5];


// Index mapping:
// 0 - Carlo
// 1 - Coyle
// 2 - Freddy
// 3 - Marchand
// 4 - Ullmark


    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;
    public Image UllmarkPic, CoylePic, CarloPic, FreddyPic, MarchandPic;
    Image CarloBackgroundImage, CoyleBackgroundImage, FreddyBackgroundImage, MarchandBackgroundImage, UllmarkBackgroundImage;




    private Players Ullmark, Coyle, Carlo, Freddy, Marchand;
    public Sweeney sweeney;
    public Image SweeneyPic;
    boolean gameOver = false;

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

        //Load background images
        CarloBackgroundImage = Toolkit.getDefaultToolkit().getImage("CarloBackgroundImage.png");
        CoyleBackgroundImage = Toolkit.getDefaultToolkit().getImage("CoyleBackgroundImage.png");
        FreddyBackgroundImage = Toolkit.getDefaultToolkit().getImage("FreddyBackgroundImage.png");
        MarchandBackgroundImage = Toolkit.getDefaultToolkit().getImage("MarchandBackgroundImage.png");
        UllmarkBackgroundImage = Toolkit.getDefaultToolkit().getImage("UllmarkBackgroundImage.png");

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

        sweeney.sweeneywrap(700,900); //smaller for sweeney so wrap appears smoother and he cant hide

        collisions(); //call collisions

    }

    public void collisions() {
        if (sweeney.rec.intersects(Carlo.rec) && Carlo.isAlive) {
            switchBackground(0);
            Carlo.isAlive = false;
            Coyle.isAlive= false;
            Freddy.isAlive=false;
            Marchand.isAlive=false;
            Ullmark.isAlive=false;
            sweeney.isAlive=false;
        }

        if (sweeney.rec.intersects(Coyle.rec) && Coyle.isAlive) {
            switchBackground(1);
            Carlo.isAlive = false;
            Coyle.isAlive= false;
            Freddy.isAlive=false;
            Marchand.isAlive=false;
            Ullmark.isAlive=false;
            sweeney.isAlive=false;
        }

        if (sweeney.rec.intersects(Freddy.rec) && Freddy.isAlive) {
            switchBackground(2);
            Carlo.isAlive = false;
            Coyle.isAlive= false;
            Freddy.isAlive=false;
            Marchand.isAlive=false;
            Ullmark.isAlive=false;
            sweeney.isAlive=false;
        }

        if (sweeney.rec.intersects(Marchand.rec) && Marchand.isAlive) {
            switchBackground(3);
            Carlo.isAlive = false;
            Coyle.isAlive= false;
            Freddy.isAlive=false;
            Marchand.isAlive=false;
            Ullmark.isAlive=false;
            sweeney.isAlive=false;
        }

        if (sweeney.rec.intersects(Ullmark.rec) && Ullmark.isAlive) {
            switchBackground(4);
            Carlo.isAlive = false;
            Coyle.isAlive= false;
            Freddy.isAlive=false;
            Marchand.isAlive=false;
            Ullmark.isAlive=false;
            sweeney.isAlive=false;
        }

}

    public void run() {
        while (true) {
            render();
            moveThings();
            sweeney.update();

            frameCount++;

            if (!gameOver) {
                scoreTimer++;
                if (scoreTimer >= 30) {
                    score++;
                    scoreTimer = 0;
                }
            }

            if (frameCount == 1000) {
                System.out.println("Speed increased");

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

                frameCount = 0;
            }

            pause(20); // 20 ms
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


        // Render Carlo
        if (background[0]) {
            g.drawImage(CarloBackgroundImage, 0, 0, WIDTH, HEIGHT, null);  // Background if collided
        } else if (Carlo.isAlive) {
            g.drawImage(Carlo.image, Carlo.xpos, Carlo.ypos, Carlo.width, Carlo.height, null);  // Player if not collided
        }

        // Render Coyle
        if (background[1]) {
            g.drawImage(CoyleBackgroundImage, 0, 0, WIDTH, HEIGHT, null);  // Background if collided
        } else if (Coyle.isAlive) {
            g.drawImage(Coyle.image, Coyle.xpos, Coyle.ypos, Coyle.width, Coyle.height, null);  // Player if not collided

        }

        // Render Freddy
        if (background[2]) {
            g.drawImage(FreddyBackgroundImage, 0, 0, WIDTH, HEIGHT, null);  // Background if collided
        } else if (Freddy.isAlive) {
            g.drawImage(Freddy.image, Freddy.xpos, Freddy.ypos, Freddy.width, Freddy.height, null);  // Player if not collided
        }

        // Render Marchand
        if (background[3]) {
            g.drawImage(MarchandBackgroundImage, 0, 0, WIDTH, HEIGHT, null);  // Background if collided
        } else if (Marchand.isAlive) {
            g.drawImage(Marchand.image, Marchand.xpos, Marchand.ypos, Marchand.width, Marchand.height, null);  // Player if not collided
        }

        // Render Ullmark
        if (background[4]) {
            g.drawImage(UllmarkBackgroundImage, 0, 0, WIDTH, HEIGHT, null);  // Background if collided
        } else if (Ullmark.isAlive) {
            g.drawImage(Ullmark.image, Ullmark.xpos, Ullmark.ypos, Ullmark.width, Ullmark.height, null);  // Player if not collided
        }

        // Always render Sweeney last to appear on top of others
        if (sweeney.isAlive) {
            g.drawImage(sweeney.image, sweeney.xpos, sweeney.ypos, sweeney.width, sweeney.height, null);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g.drawString("Score: " + score, 5, 20);

        g.dispose();
        bufferStrategy.show();
    }

    void switchBackground(int i){                       //Eli Berk helped here
        for(int x =0;x<background.length;x++){
            background[x]=false;
        }
        if(i<background.length&&i>0) {
            this.background[i] = true;
            gameOver = true;
        }
    }

}
