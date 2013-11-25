import javalib.*;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

/**
 * An abstract class for game objects
 * @author Jacob Brandt
 *
 */
public abstract class GameObj implements Globals{
    
    /**
     * draws the <code>GameObj</code> as a <code>WorldImage</code>
     * @return a <code>WorldImage</code> representation of this object
     */
    abstract WorldImage draw();
    
    /**
     * returns if 2 <code>GameObj</code>s collided
     * @return true if 2 gameobjects collided
     */
    abstract boolean collide(GameObj o);
    
    /**
     * action to take when 2 objects collide
     */
    abstract void onCollide();
    
    /**
     * moves the object
     */
    abstract public void move();
    
    /**
     * returns the location of this object
     * @return the location of this <code>GameObj</code>
     * as a <code>Posn</code>
     */
    abstract public Posn getLoc();
    
    /**
     * returns the size of this object
     * @return the size of this <code>GameObj</code>
     * as a <code>Posn</code>
     */
    abstract public Posn getSize();
    
    /**
     * converts this object to a game object
     * @return creates a gameobject form of this object
     */
    public GameObj convertGO(){
        return this;
    }
    
}
