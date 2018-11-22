package uk.ac.bradford.spacegame;

/**
 * The Ship class defines basic state information for both the Player and Alien
 * ship types in the game. 
 * @author prtrundl
 */
public abstract class Ship extends Entity {
    /**
     * An integer storing the current hull strength of the ship.
     */
    protected int hullStrength;
    
    /**
     * An integer storing the maximum hull strength of the ship. The
     * ship's hull strength cannot exceed this value.
     */
    protected int maxHull;
    
    /**
     * Method to get the ship's current hullStrength value.
     * @return the current hull strength of this Ship.
     */
    public int getHullStrength() {
        return hullStrength;
    }
    
    /**
     * Changes the Ship's health by the specified amount. Positive values add
     * hull strength (repairing) and negative values reduce hull strength
     * (damage).
     * @param change The value that will be added to the current Ship hull
     * strength value.
     */
    public void changeHullStrength(int change) {
        hullStrength += change;
        if (hullStrength > maxHull)
            hullStrength = maxHull;
    }
    
    /**
     * Method to get the maximum permitted hull strength of the Ship.
     * @return the maximum value permitted for the Ship's hull strength.
     */
    public int getMaxHull() {
        return maxHull;
    }
}
