import java.awt.Color;

import javalib.worldimages.*;

/**
 * Class representing a wall object
 * @author Jacob Brandt
 * @version 1.0
 *
 */
public class Wall extends GameObj{
    Boolean done;
    private Boolean ori; //orientation for short
    private Posn size; //x cord = width, y cord = height
    Posn loc;
    private static Boolean HORZ = true;
    Wall(Posn loc, Boolean ori) {
        done = false;
        this.ori = ori;
        this.loc = loc;
        if (ori == HORZ) { size = new Posn(10, 0);}
        else {size = new Posn(0, 10);}
    }

    /**
     * Grows the wall
     * @param n size increment of which the wall grows on in grow call
     */
    private void grow(int n) {
        if(ori == HORZ) {
            this.size.y = this.size.y + n;
        }
        else {
            this.size.x = this.size.x + n;
        }
    }

    @Override
    WorldImage draw() {
       return new RectangleImage(this.loc, this.size.x, 
               this.size.y, 
               Color.green);
    }

    @Override
    boolean collide(GameObj o) {
        Integer distFrom = Math.abs(this.loc.x - o.getLoc().x);
        boolean result = false;
        result = distFrom < (this.size.x) + o.getSize().x;
        distFrom =  Math.abs(this.loc.y - o.getLoc().y);
        return result && distFrom < (this.size.y) + o.getSize().y;           
    }

    @Override
    void onCollide() {
        //takes no action
    }

    @Override
    public void move() {
        if (!this.done()) {
            if (this.shouldGrow()) {this.grow(10);}
            center();
        }
        else {
            //System.out.println("done");
            this.done = true;
           }
    }

    /**
     * checks if this wall should grow
     */
    private boolean shouldGrow(){
        if (this.ori == HORZ) {
            return this.size.y <= Globals.HI;
        }
        else {
            return this.size.x <= Globals.WD;
        }
    }
    /**
     * centers this wall
     */
    private void center() {
        if (this.ori == HORZ) {
            if (this.loc.y > Globals.HI/2) {
                this.loc.y = this.loc.y - 5;
            }
            else if (this.loc.y < Globals.HI/2) {
                this.loc.y = this.loc.y + 5;
            }
        }
        else {
            if (this.loc.x > Globals.WD/2) {
                this.loc.x = this.loc.x - 5;
            }
            else if (this.loc.x < Globals.WD/2) {
                this.loc.x = this.loc.x + 5;
            }
        }
    }

    /**
     * checks if positioning is correct
     * @return
     */
    private boolean done() {
        if (this.ori == HORZ) {
            return (Math.abs(this.loc.y - Globals.HI/2) <= 3) &&
                    (Math.abs(this.size.y - Globals.HI) <= .1);
        }
        else {
            return (Math.abs(this.loc.x - Globals.WD/2) <= 3) &&
                    (Math.abs(this.size.x - Globals.WD) <= .1);   
        }
    }

    @Override
    public Posn getLoc() {
        return this.loc;
    }

    @Override
    public Posn getSize() {
        return this.size;
    }

    /**
     * makes this wall a border for the canvas
     * @param p which border this wall is
     */
    static Wall border(String p){
        Wall w = new Wall(new Posn(0, 0), false);
        w.done = true;
        if (p == "top") {
            w.loc = new Posn(Globals.WD/2, 0);
            w.size = new Posn(Globals.WD, 10);
            w.ori = !HORZ;
        }
        if (p == "bot") {
            w.loc = new Posn(Globals.WD/2, Globals.HI);
            w.size = new Posn(Globals.WD, 10);
            w.ori = !HORZ;
        }
        if (p == "left") {
            w.loc = new Posn(0, Globals.HI/2);
            w.size = new Posn(10, Globals.HI);
            w.ori = HORZ;
        }
        if (p == "right") {
            w.loc = new Posn(Globals.WD, Globals.HI/2);
            w.size = new Posn(10, Globals.HI);
            w.ori = HORZ;
        }
        return w;
        
    }



}
