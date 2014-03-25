// -------------------------------------------------------------------------
/**
 * The coordinates of an object
 *
 * @author Danh
 * @version Mar 5, 2014
 */
public class Point2D
{
    private int x;
    private int y;


    // ----------------------------------------------------------
    /**
     * Create a new Point2D object.
     *
     * @param x
     *            x coordinate of the city
     * @param y
     *            y coordinate of the city
     */
    public Point2D(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    // ----------------------------------------------------------
    /**
     * Return the x coordinate of the city
     *
     * @return x coordinate of the city
     */
    public int getX()
    {
        return this.x;
    }


    // ----------------------------------------------------------
    /**
     * Return the y coordinate of the city
     *
     * @return y coordinate of the city
     */
    public int getY()
    {
        return this.y;
    }
}
