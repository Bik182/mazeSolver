package maze;

/**
 * enum maze.Type
 * WALL, HALL, START, FINISH
 * Jasen LaBolle
 * Illinois State University
 * IT 340 - Sept. 23 2020
 *
 * @authors Jasen LaBolle, Nicholas Souligne, Bikal Thapa
 * @version 1.0
 * @since 2020-09-23
 */
enum Type{
    WALL, HALL, START, FINISH;
}
/**
 * maze.Space.java
 * A class to store the position and type of a location in a Maze.
 * Jasen LaBolle
 * Illinois State University
 * IT 340 - Sept. 23 2020
 *
 * @authors Jasen LaBolle Nicholas Souligne Bikal Thapa
 * @version 1.0
 * @since 2020-09-23
 */
public class Space {
    private int i, j;
    private Type t;

    /**
     * Constructor
     * @param i The y coordinate
     * @param j The x coordinate
     * @param t The maze.Type of the space.
     */
    Space(int i, int j, Type t){
        this.i = i;
        this.j = j;
        this.t = t;
    }

    /**
     * Gets the maze.Type of the maze.Space
     * @return maze.Type the maze.Type of the maze.Space
     */
    public Type getT(){
        return t;
    }

    /**
     * Sets the maze.Type of the maze.Space.
     * @param t The maze.Type to set the maze.Space to.
     */
    public void setT(Type t){
        this.t = t;
    }

    /**
     * Gets the Y position of the maze.Space
     * @return int The Y position of the maze.Space
     */
    public int getI() {
        return i;
    }

    /**
     * Gets the X position of the maze.Space
     * @return int The X position of the maze.Space.
     */
    public int getJ() {
        return j;
    }
}
