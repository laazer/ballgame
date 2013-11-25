
/**
 * game runner class
 * @author Jacob Brandt
 *
 */
public class GameRunner {

    Canvas world = new Canvas();
    
    /**
     * Method to setup bigBang
     */
    void run() {
       world.bigBang(Globals.WD, Globals.HI, .1);
    }

    /**
     * standard main method
     * @param argv
     */
    public static void main(String[] argv) {
        GameRunner game = new GameRunner();
        game.run();
    }
}

