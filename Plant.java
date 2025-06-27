/**
 * This class serves as the abstract base for all plant types in the game.
 * It defines fundamental properties and behaviors common to all plants,
 * such as health, position, and basic interaction logic.
 * @author Lim, Israel Sy
 * @author Enriquez, Aaron Mikael Cruz
 * @version 1.0
 * @since 2025-06-27
 */
public abstract class Plant {
    /** The name of the plant. */
    protected final String NAME;

    /** The speed attribute of the plant (e.g., attack speed, production rate). */
    protected final double SPEED; 

    /** The tile number (column) the plant occupies. */
    protected final int TILE_NO;

    /** The lane number (row) the plant occupies. */
    protected final int LANE_NO;

    /** The current health of the plant. */
    protected int health;
    
    /** Tracks the time elapsed since the plant's last action/attack. */
    protected double timeSinceLastAttack = 0;
    
    /**
     * Constructs a new Plant object.
     * @param n The name of the plant.
     * @param s The speed of the plant.
     * @param h The initial health of the plant.
     * @param lane The lane number the plant will occupy.
     * @param tile The tile number the plant will occupy.
     */
    public Plant(String n, double s, int h, int lane, int tile){
        NAME = n;
        SPEED = s;
        health = h;
        LANE_NO = lane;
        TILE_NO = tile;
    }

    /**
     * Defines the primary action of the plant.
     * Subclasses must implement this to specify what the plant does (e.g., shoot, produce sun).
     * @param t The {@link Tile} object where the plant is located.
     */
    public abstract void action(Tile t);

    /**
     * Updates the plant's internal timer for its last action/attack.
     * @param elapsedTime The time elapsed since the last game update.
     */
    public void updateTime(double elapsedTime){
        timeSinceLastAttack += elapsedTime;
    }
    
    /**
     * Attempts to make the plant perform its action based on game logic and timers.
     * Subclasses must implement this to define when and how the plant tries to act.
     * @param t The {@link Tile} object where the plant is currently in.
     * @param tiles All tiles in the plant's lane.
     * @param elapsedTime The time elapsed since the last update.
     */
    public abstract void tryToAction(Tile t, double elapsedTime, Tile[][] tiles);

    /**
     * Allows the plant to take damage, reducing its health.
     * @param damage The amount of damage the plant will take.
     */
    public void takeDamage(int damage){
        health -= damage;
    }

    /**
     * Checks if the lane the plant is in has no zombies.
     * @param tile An array of {@link Tile} objects representing the lane.
     * @return True if there are no zombies in the lane, false otherwise.
     */
    public boolean isLaneClear(Tile[] tile){
        // Iterates through all tiles in the lane to check for zombies.
        for(int t = 0; t < PvZDriver.getMaxTiles(); t++ ){
            if(!(tile[t].noZombies() == 0)){ // If a tile is not empty (contains zombies)
                return false;
            }    
        }
        return true;
    }

    /**
     * Returns the name of the plant.
     * @return The plant's name.
     */
    public String getName(){
        return NAME;
    }

    /**
     * Returns the current health of the plant.
     * @return The plant's health.
     */
    public int getHealth(){
        return health;
    }

    /**
     * Returns the lane number the plant occupies.
     * @return The plant's lane number.
     */
    public int getLaneNo(){
        return LANE_NO;
    }

    /**
     * Returns the tile number the plant occupies.
     * @return The plant's tile number.
     */
    public int getTileNo(){
        return TILE_NO;
    }
}