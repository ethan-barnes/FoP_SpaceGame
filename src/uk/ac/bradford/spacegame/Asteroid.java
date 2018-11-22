/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.bradford.spacegame;

import java.util.Random;

/**
 * The Asteroid class extends the Entity class and adds a Direction enumeration
 * type and a single attribute to store a Direction value. Objects of this class
 * are used to store the position and movement direction of asteroids in the game.
 * @author prtrundl
 */
public class Asteroid extends Entity {
    
    /**
     * An enumeration type to represent the movement direction of an asteroid.
     * Four directions, UP, DOWN, LEFT, RIGHT and NONE are permitted.
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT, NONE
    }
    
    /**
     * Stores the movement direction for a single asteroid object.
     */
    private Direction moveDirection;
    
    /**
     * Gets the direction value for this asteroid.
     * @return UP, DOWN, LEFT, RIGHT or NONE depending on the movement direction
     * for this asteroid.
     */
    public Direction getMovementDirection() {
        return moveDirection;
    }
    
    /**
     * Creates an asteroid object with a random Direction value chosen uniformly
     * from all permitted values and a position specified by the two integer
     * values passed to this constructor.
     * @param x The x co-ordinate for this asteroid
     * @param y The y co-ordinate for this asteroid
     */
    public Asteroid(int x, int y) {
        this(x, y, Direction.values()[new Random().nextInt(Direction.values().length)]);
    }
    
    /**
     * Creates an asteroid object with a position specified by the two integer
     * arguments passed to the constructor, and a given movement direction.
     * @param x The x co-ordinate for this asteroid
     * @param y The y co-ordinate for this asteroid
     * @param d The movement direction for this asteroid, which must be one of
     * the vales permitted in the Direction enumeration type
     */
    public Asteroid(int x, int y, Direction d) {
        setPosition(x, y);
        moveDirection = d;
    }
}
