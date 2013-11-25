import java.awt.Color;
import java.util.ArrayList;
import javalib.impworld.*;
import javalib.worldimages.*;


public class Canvas extends World{
    ArrayList<Ball> balls;
    ArrayList<Wall> walls;
    Boolean prev = false;
    private final Wall top = Wall.border("top");
    private final Wall bot = Wall.border("bot");
    private final Wall left = Wall.border("left");
    private final Wall right = Wall.border("right");
    public Canvas() {
        this.balls = new ArrayList<Ball>();
        balls.add(new Ball());
        this.walls = new ArrayList<Wall>();
        walls.add(top);
        walls.add(bot);
        walls.add(left);
        walls.add(right);
    }
    
    private final WorldImage wallBot = new RectangleImage(
            new Posn(Globals.WD/2, Globals.HI), Globals.WD, 10, Color.darkGray);
    private final WorldImage wallTop = new RectangleImage(
            new Posn(Globals.WD/2, 0), Globals.WD, 10, Color.darkGray);
    private final WorldImage wallLeft = new RectangleImage(
            new Posn(0, Globals.HI/2), 10, Globals.WD, Color.darkGray);
    private final WorldImage wallRight = new RectangleImage(
            new Posn(Globals.WD, Globals.HI/2), 10, Globals.WD, Color.darkGray);
    
    private WorldImage background = 
             new RectangleImage(Globals.CENTER, Globals.HI, 
                    Globals.WD, Color.gray).
                    overlayImages(wallBot).
                    overlayImages(wallLeft).
                    overlayImages(wallRight).
                    overlayImages(wallTop);

    @Override
    public WorldImage makeImage() {
       return new OverlayImages(background, LAlg.draw(LAlg.conertB(balls)).
               overlayImages(LAlg.draw(LAlg.conertW(walls))));
    }
    
    @Override
    public void onTick(){
        LAlg.moveAll(LAlg.conertB(balls));
        LAlg.moveAll(LAlg.conertW(walls));
        LAlg.collision(balls, walls);
    }
    
    @Override
    public void onMouseClicked(Posn p){
        if(LAlg.allDone(walls)) {
            walls.add(new Wall(p, prev));
            this.prev = !prev;
        }
    }

}
