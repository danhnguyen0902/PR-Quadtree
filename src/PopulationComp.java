
import java.util.Comparator;

// -------------------------------------------------------------------------
/**
 * The comparator for the Population Binary Search Tree
 *
 * @author Danh Nguyen (danh0902)
 * @version Feb 10, 2014
 */
public class PopulationComp
    implements Comparator<City>
{
    @Override
    public int compare(City a, City b)
    {
        return (a.getPopulation() - b.getPopulation());
    }
}
