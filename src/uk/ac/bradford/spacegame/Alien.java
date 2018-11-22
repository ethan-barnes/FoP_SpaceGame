/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.bradford.spacegame;

/**
 * The Alien class extends the Ship class, and is used to track and use the
 * position and hull strength of the aliens in the game. Does not define its
 * own attributes. This class exists to enforce a logical difference between
 * players and aliens, helping ensure that they are not used interchangeably.
 * @author prtrundl
 */
public class Alien extends Ship {
   
    /**
     * Creates an Alien object with specified maximum hull strength and
     * position on the game board.
     * @param m Maximum hull strength for the alien's ship, also used as
     * the starting hull strength.
     * @param x Starting X position for the alien.
     * @param y Starting Y position for the alien.
     */
    public Alien(int m, int x, int y) {
        maxHull = m;
        hullStrength = m;
        setPosition(x, y);
    }
    
}
