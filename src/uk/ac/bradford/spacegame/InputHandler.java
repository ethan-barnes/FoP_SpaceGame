package uk.ac.bradford.spacegame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class handles keyboard events (key presses) captured by a GameGUI object
 * that are passed to an instance of this class. The class is responsible for
 * calling methods in the GameEngine class that will update tiles, players and
 * aliens for the various key presses that are handled.
 * @author prtrundl
 */
public class InputHandler implements KeyListener {

    GameEngine engine;      //GameEngine that this class calls methods from
    
    /**
     * Constructor that forms a connection between a InputHandler object and
     * a GameEngine object. The GameEngine object registered here is the one that will
     * have methods called to change player and alien positions etc.
     * @param eng The GameEngine object that this InputHandler is linked to
     */
    public InputHandler(GameEngine eng) {
        engine = eng;
    }
    
    /**
     * Unused method
     * @param e 
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Method to handle key presses captured by the GameGUI. The method currently
     * calls the game engine to do a game turn for any key press, but if the up,
     * down, left or right arrow keys are pressed it also calls methods in the
     * engine to update the game by moving the player (and monsters if implemented)
     * @param e A KeyEvent object generated when a keyboard key is pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: engine.movePlayerLeft(); break;  //handle left arrow
            case KeyEvent.VK_RIGHT: engine.movePlayerRight(); break;//handle right arrow
            case KeyEvent.VK_UP: engine.movePlayerUp(); break;      //handle up arrow
            case KeyEvent.VK_DOWN: engine.movePlayerDown(); break;  //handle down arrow
        }
        engine.doTurn();    //any key press will result in this method being called
    }

    /**
     * Unused method
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {}
    
}
