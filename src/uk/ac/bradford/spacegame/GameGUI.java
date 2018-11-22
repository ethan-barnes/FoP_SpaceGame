package uk.ac.bradford.spacegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;
import uk.ac.bradford.spacegame.GameEngine.TileType;

/**
 * The GameGUI class is responsible for rendering graphics to the screen to display
 * the game grid, players, asteroids and aliens. The GameGUI class passes keyboard
 * events to a registered InputHandler to be handled.
 * @author prtrundl
 */
public class GameGUI extends JFrame {
    
    /**
     * The three final int attributes below set the size of some graphical elements,
     * specifically the display height and width of tiles in the level and the height
     * of health bars for Ship objects in the game. Tile sizes should match the size
     * of the image files used in the game.
     */
    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;
    public static final int HEALTH_BAR_HEIGHT = 3;
    
    /**
     * The canvas is the area that graphics are drawn to. It is an internal class
     * of the GameGUI class.
     */
    Canvas canvas;
    
    /**
     * Constructor for the GameGUI class. It calls the initGUI method to generate the
     * required objects for display.
     */
    public GameGUI() {
        initGUI();
    }
    
    /**
     * Registers an object to be passed keyboard events captured by the GUI.
     * @param i the InputHandler object that will process keyboard events to
     * make the game respond to input
     */
    public void registerKeyHandler(InputHandler i) {
        addKeyListener(i);
    }
    
    /**
     * Method to create and initialise components for displaying elements of the
     * game on the screen.
     */
    private void initGUI() {
        add(canvas = new Canvas());     //adds canvas to this frame
        setTitle("spAce");
        setSize(816, 615);
        setLocationRelativeTo(null);        //sets position of frame on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Method to update the graphical elements on the screen, usually after entities
     * have moved when a keyboard event was handled. The method
     * requires four arguments and displays corresponding information on the screen.
     * @param tiles A 2-dimensional array of TileTypes. This is the tiles of the
     * current level that should be drawn to the screen.
     * @param player A Player object. This object is used to draw the player in
     * the right tile and display its health. null can be passed for this argument,
     * in which case no player will be drawn.
     * @param aliens An array of Alien objects that is processed to draw
     * aliens in tiles with a health bar. null can be passed for this argument in which
     * case no aliens will be drawn. Elements in the aliens array can also be null,
     * in which case nothing will be drawn for that element of the array.
     * @param asteroids An array of Asteroid objects that is processed to draw the
     * asteroids on the map. null elements in the array, or a null array are both
     * permitted, and any null arrays or null elements in the array will be skipped.
     */
    public void updateDisplay(TileType[][] tiles, Player player, Alien[] aliens, Asteroid[] asteroids) {
        canvas.update(tiles, player, aliens, asteroids);
    }
    
}

/**
 * Internal class used to draw elements within a JPanel. The Canvas class loads
 * images from an asset folder inside the main project folder.
 * @author prtrundl
 */
class Canvas extends JPanel {

    private BufferedImage space1;
    private BufferedImage space2;
    private BufferedImage space3;
    private BufferedImage space4;
    private BufferedImage blackHole;
    private BufferedImage player;
    private BufferedImage asteroid;
    private BufferedImage apulsar;
    private BufferedImage ipulsar;
    private BufferedImage alien;
    
    TileType[][] currentTiles;  //the current 2D array of tiles to display
    Player currentPlayer;       //the current player object to be drawn
    Alien[] currentAliens;   //the current array of monsters to draw
    Asteroid[] currentAsteroids;   //the current array of asteroids
    
    /**
     * Constructor that loads tile images for use in this class
     */
    public Canvas() {
        loadTileImages();
    }
    
    /**
     * Loads tiles images from a fixed folder location within the project directory
     */
    private void loadTileImages() {
        try {
            space1 = ImageIO.read(new File("assets/space1.png"));
            assert space1.getHeight() == GameGUI.TILE_HEIGHT &&
                    space1.getWidth() == GameGUI.TILE_WIDTH;
            space2 = ImageIO.read(new File("assets/space2.png"));
            assert space2.getHeight() == GameGUI.TILE_HEIGHT &&
                    space2.getWidth() == GameGUI.TILE_WIDTH;
            space3 = ImageIO.read(new File("assets/space3.png"));
            assert space3.getHeight() == GameGUI.TILE_HEIGHT &&
                    space3.getWidth() == GameGUI.TILE_WIDTH;
            space4 = ImageIO.read(new File("assets/space4.png"));
            assert space4.getHeight() == GameGUI.TILE_HEIGHT &&
                    space4.getWidth() == GameGUI.TILE_WIDTH;
            blackHole = ImageIO.read(new File("assets/blackhole.png"));
            assert blackHole.getHeight() == GameGUI.TILE_HEIGHT &&
                    blackHole.getWidth() == GameGUI.TILE_WIDTH;
            player = ImageIO.read(new File("assets/player.png"));
            assert player.getHeight() == GameGUI.TILE_HEIGHT &&
                    player.getWidth() == GameGUI.TILE_WIDTH;
            asteroid = ImageIO.read(new File("assets/asteroid.png"));
            assert asteroid.getHeight() == GameGUI.TILE_HEIGHT &&
                    asteroid.getWidth() == GameGUI.TILE_WIDTH;
            apulsar = ImageIO.read(new File("assets/apulsar.png"));
            assert apulsar.getHeight() == GameGUI.TILE_HEIGHT &&
                    apulsar.getWidth() == GameGUI.TILE_WIDTH;
            ipulsar = ImageIO.read(new File("assets/ipulsar.png"));
            assert ipulsar.getHeight() == GameGUI.TILE_HEIGHT &&
                    ipulsar.getWidth() == GameGUI.TILE_WIDTH;
            alien = ImageIO.read(new File("assets/alien.png"));
            assert alien.getHeight() == GameGUI.TILE_HEIGHT &&
                    alien.getWidth() == GameGUI.TILE_WIDTH;
        } catch (IOException e) {
            System.out.println("Exception loading images: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }
    
    /**
     * Updates the current graphics on the screen to display the tiles, player and monsters
     * @param t The 2D array of TileTypes representing the current level of the dungeon
     * @param player The current player object, used to draw the player and its health
     * @param mon The array of monsters to display them and their health
     */
    public void update(TileType[][] t, Player player, Alien[] al, Asteroid[] as) {
        currentTiles = t;
        currentPlayer = player;
        currentAliens = al;
        currentAsteroids = as;
        repaint();
    }
    
    /**
     * Override of method in super class, it draws the custom elements for this
     * game such as the tiles, player, aliens and asteroids.
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSpace(g);
    }

    /**
     * Draws graphical elements to the screen to display the current level
     * tiles, the player, asteroids and the aliens. If the tiles, player or
     * alien objects are null they will not be drawn.
     * @param g Graphics object to use for drawing
     */
    private void drawSpace(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Random r = new Random(555);
        if (currentTiles != null) {
            for (int i = 0; i < currentTiles.length; i++) {
                for (int j = 0; j < currentTiles[i].length; j++) {
                    switch (currentTiles[i][j]) {
                        case SPACE:
                            double ran = r.nextDouble();
                            if (ran < 0.25)
                                g2.drawImage(space1, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            else if (ran < 0.5)
                                g2.drawImage(space2, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            else if (ran < 0.75)
                                g2.drawImage(space3, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            else
                                g2.drawImage(space4, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            break;
                        case BLACK_HOLE:
                            g2.drawImage(blackHole, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            break;
                        case PULSAR_ACTIVE:
                            g2.drawImage(apulsar, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                            break;
                        case PULSAR_INACTIVE:
                            g2.drawImage(ipulsar, i * GameGUI.TILE_WIDTH, j * GameGUI.TILE_HEIGHT, null);
                    }
                }
            }
        }
        if (currentAsteroids != null)
            for(Asteroid a : currentAsteroids)
                if (a != null) {
                    g2.drawImage(asteroid, a.getX() * GameGUI.TILE_WIDTH, a.getY() * GameGUI.TILE_HEIGHT, null);
                }
        if (currentAliens != null)
            for(Alien a : currentAliens)
                if (a != null) {
                    g2.drawImage(alien, a.getX() * GameGUI.TILE_WIDTH, a.getY() * GameGUI.TILE_HEIGHT, null);
                    drawHealthBar(g2, a);
                }
        if (currentPlayer != null) {
            g2.drawImage(player, currentPlayer.getX() * GameGUI.TILE_WIDTH, currentPlayer.getY() * GameGUI.TILE_HEIGHT, null);
            drawHealthBar(g2, currentPlayer);
        }
    }
    
    /**
     * Draws a health bar for the given entity at the bottom of the tile that
     * the entity is located in.
     * @param g2 The graphics object to use for drawing
     * @param e The entity that the health bar will be drawn for
     */
    private void drawHealthBar(Graphics2D g2, Ship e) {
        double remainingHealth = (double)e.getHullStrength() / (double)e.getMaxHull();
        g2.setColor(Color.RED);
        g2.fill(new Rectangle2D.Double(e.getX() * GameGUI.TILE_WIDTH, e.getY() * GameGUI.TILE_HEIGHT + 29, GameGUI.TILE_WIDTH, GameGUI.HEALTH_BAR_HEIGHT));
        g2.setColor(Color.GREEN);
        g2.fill(new Rectangle2D.Double(e.getX() * GameGUI.TILE_WIDTH, e.getY() * GameGUI.TILE_HEIGHT + 29, GameGUI.TILE_WIDTH * remainingHealth, GameGUI.HEALTH_BAR_HEIGHT));
    }
}
