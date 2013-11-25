import java.awt.Color;
import java.util.ArrayList;

import javalib.worldimages.*;
/**
 * Algorithms for <code>ArrayList</code>s
 * @author Jacob Brandt
 *
 */
public class LAlg {
    
    /**
     * draws the <code>GameObj</code> as a <code>WorldImage</code>
     * @return a <code>WorldImage</code> representation of this object
     */
    public static WorldImage draw(ArrayList<GameObj> alist){
        WorldImage result = new CircleImage(new Posn(0, 0), 0, Color.white);
        for(GameObj o : alist) {
            result = result.overlayImages(o.draw());
        }
        return result;
    }
    
    /**
     * checks colllsion between balls and walls
     * @param blist
     * @param wlist
     */
    public static boolean collision(ArrayList<Ball> blist, 
            ArrayList<Wall> wlist){
        boolean result = false;
        for(Ball b : blist) {
            for(int i = 0; i < wlist.size(); i++) {
                Wall w = wlist.get(i);
                if(b.collide(w)){
                    b.onCollide();
                    if(!w.done) {wlist.remove(i);}
                    result = true;
                }
            }
        }
        return result;
    }
    
    /**
     * moves evrything in arraylist of game opjects
     * @param alist
     */
    public static void moveAll(ArrayList<GameObj> alist) {
        for(GameObj o : alist) {
            o.move();
        }
    }
    
    /**
     * converts a list of balls to a list of GameObj
     * @param alist a list of balls
     * @return
     */
    public static ArrayList<GameObj> conertB(ArrayList<Ball> alist) {
        ArrayList<GameObj> rlist = new ArrayList<GameObj>();
        for(Ball b : alist){
            rlist.add(b.convertGO());
        }
        return rlist;
    }
    
    /**
     * converts a list of walls to a list of GameObj
     * @param alist a list of walls
     * @return
     */
    public static ArrayList<GameObj> conertW(ArrayList<Wall> alist) {
        ArrayList<GameObj> rlist = new ArrayList<GameObj>();
        for(Wall w : alist){
            rlist.add(w.convertGO());
        }
        return rlist;
    }
    
    public static boolean allDone(ArrayList<Wall> alist) {
        boolean result = true;
        for (Wall w : alist) {
            result = result && w.done;
        }
        return result;
    }
    
    
}
