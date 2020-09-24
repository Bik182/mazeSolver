package PROJECT.PROJECT1.MazeProj_Mod1.src;

/**
 * enum Type
 * WALL, HALL, START, FINISH
 * Jasen LaBolle
 * Illinois State University
 * IT 340 - Sept. 15 2020
 *
 * @authors Jasen LaBolle
 * @version 1.0
 * @since 2020-09-15
 */
enum Type{
    WALL, HALL, START, FINISH;
}
/**
 * Space.java
 * A class to store the position and type of a location in a Maze.
 * Jasen LaBolle
 * Illinois State University
 * IT 340 - Sept. 15 2020
 *
 * @authors Jasen LaBolle
 * @version 1.0
 * @since 2020-09-15
 */
public class Space {
    private int i, j;
    private Type t;

    /**
     * Constructor
     * @param i The y coordinate
     * @param j The x coordinate
     * @param t The Type of the space.
     */
    Space(int i, int j, Type t){
        this.i = i;
        this.j = j;
        this.t = t;
    }

    /**
     * Gets the Type of the Space
     * @return Type the Type of the Space
     */
    public Type getT(){
        return t;
    }

    /**
     * Sets the Type of the Space.
     * @param t The Type to set the Space to.
     */
    public void setT(Type t){
        this.t = t;
    }

    /**
     * Gets the Y position of the Space
     * @return int The Y position of the Space
     */
    public int getI() {
        return i;
    }

    /**
     * Gets the X position of the Space
     * @return int The X position of the Space.
     */
    public int getJ() {
        return j;
    }
}
