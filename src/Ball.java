import java.awt.Color;
import java.util.Random;
import javalib.worldimages.*;



/**
 * Class representing a single ball object
 * @author Jacob Brandt
 * @version 1.0
 */
public class Ball extends GameObj{
    private Posn loc;
    private Posn size;
    private Posn traj; //trajectory
    private Random rand = new Random();
    Ball(){
        this.loc = CENTER;
        this.size = new Posn(10, 10);
        this.traj = new Posn(0, 0);
        this.traj.x = 3*rand.nextInt(5);
        this.traj.y = 3*rand.nextInt(5);
    }
    
    @Override
    WorldImage draw() {
        return new CircleImage(this.loc, this.size.x, Color.red);
    }
    
    /**
     * moves this ball based on the given x and y values
     * @param x value
     * @param y value
     */
    public void move() {
        this.loc = new Posn(loc.x + traj.x, loc.y + traj.y);
    }
    
    @Override
    void onCollide() {
        this.traj = this.randTraj();
        
    }
    
    /**
     * randomizes trajecory
     */
    private Posn randTraj(){
        int mx, nx, my, ny;
        if (Math.random() > .5) {mx = 1;}
        else { mx = -1; }
        if (Math.random() > .5) {my = 1;}
        else { my = -1; }
        if (Math.random() > .5) {nx = traj.x;}
        else { nx = traj.y; }
        if (Math.random() > .5) {ny = traj.y;}
        else { ny = traj.x; }
        
        return new Posn(nx *mx, ny*my);
    }
    

    @Override
    boolean collide(GameObj o) {
        return o.collide(this);
    }

    @Override
    public Posn getLoc() {
        return this.loc;
    }

    @Override
    public Posn getSize() {
        return this.size;
    }
    
    
    
}
