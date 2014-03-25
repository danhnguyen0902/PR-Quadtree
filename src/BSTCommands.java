import java.util.List;

// -------------------------------------------------------------------------
/**
 * Processing the commands using the Binary Search Trees
 *
 * @author Danh
 * @version Mar 5, 2014
 */
public class BSTCommands
{
    // Variables used for processing commands
    private String              name;
    private String              minName;
    private String              maxName;
    private String              field;
    private int                 population;
    private int                 minPopulation;
    private int                 maxPopulation;
    private City                city;
    private City                minCity;
    private City                maxCity;
    private City                tmp;
    private List<City>          list;
    private List<StringBuilder> space;

    // The two binary search trees
    private DatabaseBST<City>   nameBST;
    private DatabaseBST<City>   populationBST;


    // ----------------------------------------------------------
    /**
     * Create a new BSTCommands object.
     */
    public BSTCommands()
    {
        nameBST = new DatabaseBST<City>(new NameComp());
        populationBST = new DatabaseBST<City>(new PopulationComp());
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
        printCommand(token);

        name = token[1];
        population = Integer.valueOf(token[2]);
        String payload = token[3];
        city = new City(name, population, payload);
        nameBST.insert(city);
        populationBST.insert(city);
    }


    // ----------------------------------------------------------
    /**
     * Find records in the database
     *
     * @param token
     *            separate parts of a particular command
     */
    public void find(String[] token)
    {
        printCommand(token);

        field = token[1];

        // Do a find
        if (field.equals("name"))
        {
            name = token[2];
            city = new City(name, -1, "-1");

            list = nameBST.find(city);
        }

        if (field.equals("population"))
        {
            population = Integer.valueOf(token[2]);
            city = new City("-1", population, "-1");

            list = populationBST.find(city);
        }

        // Now print them out
        if (list == null)
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


    // ----------------------------------------------------------
    /**
     * Find the k-th record in the database
     *
     * @param token
     *            separate parts of a particular command
     */
    public void findKth(String[] token)
    {
        printCommand(token);

        field = token[1];
        int k = Integer.valueOf(token[2]);

        // Do a find
        if (field.equals("name"))
        {
            tmp = nameBST.findKth(k);
        }

        if (field.equals("population"))
        {
            tmp = populationBST.findKth(k);
        }

        // Now print them out
        if (tmp == null)
        {
            System.out.println("Not found");
        }
        else
        {
            System.out.println(tmp.toString());
        }
    }


    // ----------------------------------------------------------
    /**
     * Find the records whose values are in a given range
     *
     * @param token
     *            separate parts of a particular command
     */
    public void findRange(String[] token)
    {
        printCommand(token);

        field = token[1];

        // Do a find
        if (field.equals("name"))
        {
            minName = token[2];
            maxName = token[3];
            minCity = new City(minName, -1, "-1");
            maxCity = new City(maxName, -1, "-1");

            list = nameBST.findRange(minCity, maxCity);
        }

        if (field.equals("population"))
        {
            minPopulation = Integer.valueOf(token[2]);
            maxPopulation = Integer.valueOf(token[3]);
            minCity = new City("-1", minPopulation, "-1");
            maxCity = new City("-1", maxPopulation, "-1");

            list = populationBST.findRange(minCity, maxCity);
        }

        // Now print them out
        if (list == null)
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


    // ----------------------------------------------------------
    /**
     * Remove specified records out of both trees
     *
     * @param token
     *            separate parts of a particular command
     */
    public void delete(String[] token)
    {
        printCommand(token);

        field = token[1];

        // Find all the duplicates with the given value
        if (field.equals("name"))
        {
            name = token[2];
            city = new City(name, -1, "-1");

            list = nameBST.find(city);
        }

        if (field.equals("population"))
        {
            population = Integer.valueOf(token[2]);
            city = new City("-1", population, "-1");

            list = populationBST.find(city);
        }

        // Now delete them on both trees
        if (list == null)
        {
            System.out.println("Not found");
        }
        else
        {
            for (int i = 0; i < list.size(); ++i)
            {
                nameBST.delete(list.get(i));
                populationBST.delete(list.get(i));
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Prints out the records in ascending order according to a particular field
     *
     * @param token
     *            separate parts of a particular command
     */
    public void sort(String[] token)
    {
        printCommand(token);

        field = token[1];

        // Do a traverse
        if (field.equals("name"))
        {
            list = nameBST.sort();
        }

        if (field.equals("population"))
        {
            list = populationBST.sort();
        }

        // Now print them out
        if (list == null)
        {
            System.out.println("Database empty");
        }
        else
        {
            for (int i = 0; i < list.size(); ++i)
            {
                System.out.println(list.get(i).toString());
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Prints out the records in ascending order according to a particular field
     * plus some additional spaces
     *
     * @param token
     *            separate parts of a particular command
     */
    public void tree(String[] token)
    {
        printCommand(token);

        field = token[1];

        // Do a traverse
        if (field.equals("name"))
        {
            list = nameBST.sort();
            space = nameBST.tree();
        }

        if (field.equals("population"))
        {
            list = populationBST.sort();
            space = populationBST.tree();
        }

        // Now print them out
        if (list == null)
        {
            System.out.println("Database empty");
        }
        else
        {
            for (int i = 0; i < list.size(); ++i)
            {
                System.out.print(space.get(i));
                System.out.println(list.get(i).toString());
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Remove all of the records out of the database
     *
     * @param token
     *            separate parts of a particular command
     */
    public void makeNull(String[] token)
    {
        printCommand(token);

        nameBST.makeNull();
        populationBST.makeNull();
    }


// -----------------------PR Quadtrees----------------------------------------
    // ----------------------------------------------------------
    /**
     * Insert a record to the database
     *
     * @param token
     *            separate parts of a particular command
     */
    public void anotherInsert(String[] token)
    {
        printCommand(token);

        name = token[1];
        population = Integer.valueOf(token[2]);
        int x = Integer.parseInt(token[3]);
        int y = Integer.parseInt(token[4]);
        city = new City(name, population, x, y);
        nameBST.insert(city);
        populationBST.insert(city);
    }


    // ----------------------------------------------------------
    /**
     * Remove specified records out of both trees
     *
     * @param deletedCity
     *            the city that needs to be deleted
     */
    public void anotherDelete(City deletedCity)
    {
        // Now delete them on both trees
        if (deletedCity != null)
        {
            nameBST.delete(deletedCity);
            populationBST.delete(deletedCity);
        }
    }
}
