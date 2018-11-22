/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.bradford.spacegame;

/**
 * An abstract class to declare basic position attributes for the various moving
 * objects in the game. Methods are declared to get and set these attributes.
 * @author prtrundl
 */
public abstract class Entity {
    
    /**
     * xPos is the current x position in the game for this entity
     */
    private int xPos;
    
    /**
     * yPos is the current y position in the game for this entity
     */
    private int yPos;
    
    /**
     * This method returns the current X position for this entity in the game
     * @return The X co-ordinate of this Entity in the game
     */
    public int getX() {
        return xPos;
    }
    
    /**
     * This method returns the current Y position for this entity in the game
     * @return The Y co-ordinate of this Entity in the game
     */
    public int getY() {
        return yPos;
    }
    
    /**
     * Sets the position of the Entity in the game
     * @param x The new X position for this Entity
     * @param y The new Y position for this Entity
     */
    public final void setPosition (int x, int y) {
        xPos = x;
        yPos = y;
    }
    
}
