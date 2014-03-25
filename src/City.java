// -------------------------------------------------------------------------
/**
 * Records that are referenced by the binary search trees
 *
 * @author Danh Nguyen (danh0902)
 * @version Feb 10, 2014
 */
public class City
{
    private String name;
    private int    population;
    private String payload;

    // PR Quadtrees
    private int    x;
    private int    y;


    // ----------------------------------------------------------
    /**
     * Create a new City object.
     *
     * @param name
     *            name of the city
     * @param population
     *            number of people living in the city
     * @param payload
     *            other information about the city
     */
    public City(String name, int population, String payload)
    {
        this.name = name;
        this.population = population;
        this.payload = payload;
    }


    // ----------------------------------------------------------
    /**
     * Get name of a city
     *
     * @return name of the city
     */
    public String getName()
    {
        return name;
    }


    // ----------------------------------------------------------
    /**
     * Replace name of a city with the parameter of the function
     *
     * @param name
     *            the given name
     */
    public void setName(String name)
    {
        this.name = name;
    }


    // ----------------------------------------------------------
    /**
     * Get population of a city
     *
     * @return the population of the city
     */
    public int getPopulation()
    {
        return population;
    }


    // ----------------------------------------------------------
    /**
     * Replace population of a city with the parameter of the function
     *
     * @param population
     *            the given population
     */
    public void setPopulation(int population)
    {
        this.population = population;
    }


    // ----------------------------------------------------------
    /**
     * Get other information about a city
     *
     * @return other information of the city
     */
    public String getPayload()
    {
        return payload;
    }


    // ----------------------------------------------------------
    /**
     * Replace other information of a city with the parameter of the function
     *
     * @param payload
     *            given other information
     */
    public void setPayload(String payload)
    {
        this.payload = payload;
    }


    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append(name);
        builder.append(" ");
        builder.append(population);
        builder.append(" ");
        if (payload != null)
        {
            builder.append(payload);
        }
        else
        {
            builder.append("(");
            builder.append(x);
            builder.append(", ");
            builder.append(y);
            builder.append(")");

        }

        return builder.toString();
    }


// -----------------------------PR Quadtrees--------------------------------
    // ----------------------------------------------------------
    /**
     * Create a new City object.
     *
     * @param name
     *            name of the city
     * @param population
     *            number of people living in the city
     * @param x
     *            x coordinate of the city
     * @param y
     *            y coordinate of the city
     */
    public City(String name, int population, int x, int y)
    {
        this.name = name;
        this.population = population;
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
