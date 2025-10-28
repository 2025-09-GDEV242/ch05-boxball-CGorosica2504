import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class.
 * 
 * This class provides a visual simulation of balls moving and bouncing inside of a drawn box in the canvas. Each ball
 * moves independently from each other and will interact with the box boundaries appropriately.
 *
 * @author Michael Kölling and David J. Barnes
 * @author Christian Gorosica
 * @version 2025.10.27 
*/

public class BallDemo   
{
    private Canvas myCanvas;
    private Box box;
    private Random rand = new Random();

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     * 
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
        box=new Box (100,100,500,400, myCanvas);
        box.draw();
        
    }

    /**
     * This method will simulate a collection of balls bouncing within a box.
     * Each ball is placed within a random position in the box, will have a random color that
     * is not close to white, and will move with random nonzero horizontal and vertical
     * speeds.
     * 
     * The simulation will run indefinitely.
     * 
     * @param numOfBalls number of balls to simulate bouncing, clamped between 5-50. 
     */
    public void boxBounce(int numOfBalls)
    {
        //Clamp to have at least 5 balls
        if (numOfBalls < 5) {
            numOfBalls = 5;
        }
        
        //Clamp to have no more than 50 balls
        if (numOfBalls > 50) {
            numOfBalls = 50;
        }
        
        List<BoxBall> balls = new ArrayList<>();
        
        int left = box.getLeftWall();
        int right = box.getRightWall();
        int top = box.getTopWall();
        int bottom = box.getBottomWall();
        
        //Using a for loop to initialize all of the balls
        for (int i = 0; i < numOfBalls; i++) {
            int diameter = 25;
            int x = left + rand.nextInt((right - left) - diameter);
            int y = top + rand.nextInt((bottom - top) - diameter);
            
            //Setting random colors but avoiding near white colors
            int r = rand.nextInt(200);
            int g = rand.nextInt(200);
            int b = rand.nextInt(200);
            Color color = new Color(r, g, b);
            
            BoxBall ball = new BoxBall(x, y, diameter, color, box, myCanvas);
            ball.draw();
            balls.add(ball);
            
        }
        
        boolean running = true;
        while (running) {
            myCanvas.wait(40);
            
            for (BoxBall b : balls) {
                b.move();
            }
            box.draw();
        }
    }
    
    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
