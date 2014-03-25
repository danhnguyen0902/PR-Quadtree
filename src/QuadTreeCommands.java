import java.util.List;

// -------------------------------------------------------------------------
/**
 * Processing the commands using the QuadTree
 *
 * @author Danh
 * @version Mar 5, 2014
 */
public class QuadTreeCommands
{
    // Variables used for processing commands
    private int            x;
    private int            y;

    // PR Quadtree
    private QuadTree<City> quadTree;


    // ----------------------------------------------------------
    /**
     * Create a new QuadTreeCommands object.
     */
    public QuadTreeCommands()
    {
        quadTree = new QuadTree<City>(new CoordinatesExtractor2D());
    }


    // ----------------------------------------------------------
    /**
     * Echo a command to output with its parameters
     *
     * @param token
     *            separate parts of a particular command
     */
    public void printCommand(String[] token)
    {
        System.out.print(token[0]);

        for (int i = 1; i < token.length; ++i)
        {
            System.out.print(" " + token[i]);
        }

        System.out.println();
    }


    // ----------------------------------------------------------
    /**
     * Insert a record to the database
     *
     * @param token
     *            separate parts of a particular command
     */
    public void insert(String[] token)
    {
        String name = token[1];
        int population = Integer.valueOf(token[2]);
        x = Integer.parseInt(token[3]);
        y = Integer.parseInt(token[4]);
        City city = new City(name, population, x, y);

        int result = quadTree.insert(city);
        if (result == 1)
        {
            System.out.println("Out of bounds");
        }
        else if (result == 2)
        {
            System.out.println("Duplicate");
        }
    }


    // ----------------------------------------------------------
    /**
     * Remove all of the records out of the database
     */
    public void makeNull()
    {
        quadTree.makeNull();
    }


    // ----------------------------------------------------------
    /**
     * Traverse the tree in pre-order
     *
     * @param token
     *            separate parts of a particular command
     */
    public void treeLocation(String[] token)
    {
        printCommand(token);

        List<StringBuilder> list = quadTree.treeLocation();
        for (int i = 0; i < list.size(); ++i)
        {
            System.out.println(list.get(i).toString());
        }
    }


    // ----------------------------------------------------------
    /**
     * Find the record with the given location in the database
     *
     * @param token
     *            separate parts of a particular command
     */
    public void findLocation(String[] token)
    {
        printCommand(token);

        x = Integer.parseInt(token[2]);
        y = Integer.parseInt(token[3]);

        City result = quadTree.findLocation(x, y);

        if (result == null)
        {
            System.out.println("Not found");
        }
        else
        {
            System.out.println(result);
        }
    }


    // ----------------------------------------------------------
    /**
     * Remove the record with the given location in the database
     *
     * @param token
     *            separate parts of a particular command
     * @return deleted element, null if there's no element to delete
     */
    public City deleteLocation(String[] token)
    {
        printCommand(token);

        x = Integer.parseInt(token[2]);
        y = Integer.parseInt(token[3]);

        City result = quadTree.deleteLocation(x, y);

        if (result == null)
        {
            System.out.println("Not found");
        }
        return result;
    }


    // ----------------------------------------------------------
    /**
     * Find and print all cities which are in a given bound
     *
     * @param token
     *            separate parts of a particular command
     */
    public void rFind(String[] token)
    {
        printCommand(token);

        x = Integer.parseInt(token[1]);
        y = Integer.parseInt(token[2]);
        int w = Integer.parseInt(token[3]);
        int h = Integer.parseInt(token[4]);

        List<StringBuilder> list = quadTree.rFind(x, y, x + w, y + h);

        if (list.size() == 0)
        {
            System.out.println("Not found");
        }
        else
        {
            for (int i = 0; i < list.size(); ++i)
            {
                System.out.println(list.get(i).toString());
            }
        }
    }
}
